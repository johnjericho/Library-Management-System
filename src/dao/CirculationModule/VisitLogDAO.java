package dao.CirculationModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.dto.VisitLogDisplay;
import utility.DatabaseHelper;

public class VisitLogDAO {

	public void timeIn(Integer studentId, Integer facultyId, LocalDateTime timeIn) throws SQLException {
		String sql = "INSERT INTO tbl_visitlog (studentId, facultyId, timeIn) VALUES(?, ?, ?)";
		try (
				Connection conn = DatabaseHelper.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setObject(1, studentId);
			stmt.setObject(2, facultyId);
			stmt.setObject(3, timeIn);
			stmt.executeUpdate();
		}
	}

	public void timeOut(int visitId, LocalDateTime timeOut) throws SQLException {
		String sql = "UPDATE tbl_visitlog SET timeOut = ? WHERE visitId = ?";
		try (
				Connection conn = DatabaseHelper.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setObject(1, timeOut);
			stmt.setInt(2, visitId);
			stmt.executeUpdate();
		}
	}

	public ArrayList<VisitLogDisplay> loadVisitor() throws SQLException {
		ArrayList<VisitLogDisplay> loadVisitor = new ArrayList<>();
		String sql =
				"SELECT v.visitId, " +
				"CASE WHEN v.studentId IS NOT NULL THEN s.lrn ELSE f.employeeNo END AS borrowerNo, " +
				"CASE WHEN v.studentId IS NOT NULL THEN CONCAT(s.lastName, ', ', s.firstName, ' ', s.middleName) " +
				"     ELSE CONCAT(f.lastName, ', ', f.firstName, ' ', f.middleName) END AS borrowerName, " +
				"CASE WHEN v.studentId IS NOT NULL THEN 'Student' ELSE 'Faculty' END AS borrowerType, " +
				"v.timeIn, v.timeOut, " +
				"d.departmentName AS department, " +
				"g.gradeLvl AS grade, " +
				"sec.sectionName AS section " +
				"FROM tbl_visitlog v " +
				"LEFT JOIN tbl_student s ON v.studentId = s.studentId " +
				"LEFT JOIN tbl_faculty f ON v.facultyId = f.facultyId " +
				"LEFT JOIN tbl_section sec ON s.sectionId = sec.sectionId " +
				"LEFT JOIN tbl_grade g ON COALESCE(sec.gradeId, f.gradeId) = g.gradeId " +
				"LEFT JOIN tbl_department d ON g.departmentId = d.departmentId " +
				"ORDER BY borrowerName ASC";

		try (
				Connection conn = DatabaseHelper.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				VisitLogDisplay visitor = new VisitLogDisplay(
						rs.getInt("visitId"),
						rs.getString("borrowerNo"),
						rs.getString("borrowerName"),
						rs.getString("borrowerType"),
						rs.getObject("timeIn", LocalDateTime.class),
						rs.getObject("timeOut", LocalDateTime.class),
						rs.getString("department"),
						rs.getString("grade"),
						rs.getString("section")
				);
				loadVisitor.add(visitor);
			}
		}
		return loadVisitor;
	}
}