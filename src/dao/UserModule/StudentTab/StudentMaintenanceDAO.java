package dao.UserModule.StudentTab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dto.StudentDisplay;
import utility.DatabaseHelper;

public class StudentMaintenanceDAO {

	public void addStudent(String lrn,
							 String firstName,
							 String lastName,
							 String middleName,
							 String gender,
							 int sectionId,
							 String address,
							 String contactNo,
							 String email) throws SQLException {
	    String sql = "INSERT INTO tbl_student (lrn, firstName, lastName, middleName, gender, "
				   + "sectionId, address, contactNo, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	        stmt.setString(1, lrn);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.setString(4, middleName);
	        stmt.setString(5, gender);
	        stmt.setInt(6, sectionId);
	        stmt.setString(7, address);
	        stmt.setString(8, contactNo);
	        stmt.setString(9, email);
	        stmt.executeUpdate();
	    }
	}

	public ArrayList<StudentDisplay> loadStudentDisplay() throws SQLException {
	    ArrayList<StudentDisplay> studentList = new ArrayList<>();
	    String sql = "SELECT s.studentId, s.lrn, s.firstName, s.lastName, s.middleName, "
	                +"s.gender, dept.departmentName, gr.gradeLvl, sec.sectionName, s.address, s.contactNo, s.email "
	                +"FROM tbl_student s "
	                +"JOIN tbl_section sec ON s.sectionId = sec.sectionId "
	                +"JOIN tbl_grade gr ON sec.gradeId = gr.gradeId "
	                +"JOIN tbl_department dept ON gr.departmentId = dept.departmentId "
	                +"ORDER BY s.firstName ASC";
	    try(
	    Connection conn = DatabaseHelper.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery())
	    {
	        while(rs.next()) {
	            StudentDisplay student = new StudentDisplay();
	            student.setStudentId(rs.getInt("studentId"));
	            student.setLrn(rs.getString("lrn"));
	            student.setFirstName(rs.getString("firstName"));
	            student.setLastName(rs.getString("lastName"));
	            student.setMiddleName(rs.getString("middleName"));
	            student.setGender(rs.getString("gender"));
	            student.setDepartmentName(rs.getString("departmentName"));
	            student.setGradeName(rs.getString("gradeLvl"));
	            student.setSectionName(rs.getString("sectionName"));
	            student.setAddress(rs.getString("address"));
	            student.setContactNo(rs.getString("contactNo"));
	            student.setEmail(rs.getString("email"));
	            studentList.add(student);
	        }
	    }
	    return studentList;
	}

	public StudentDisplay getStudentById(int studentId) throws SQLException {
	    String sql = "SELECT s.studentId, s.lrn, s.firstName, s.lastName, s.middleName, "
	                +"s.gender, dept.departmentName, gr.gradeLvl, sec.sectionId, sec.sectionName, "
	                +"s.address, s.contactNo, s.email "
	                +"FROM tbl_student s "
	                +"JOIN tbl_section sec ON s.sectionId = sec.sectionId "
	                +"JOIN tbl_grade gr ON sec.gradeId = gr.gradeId "
	                +"JOIN tbl_department dept ON gr.departmentId = dept.departmentId "
	                +"WHERE s.studentId = ?";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	        stmt.setInt(1, studentId);
	        try(ResultSet rs = stmt.executeQuery()) {
	            if(rs.next()) {
	                StudentDisplay student = new StudentDisplay();
	                student.setStudentId(rs.getInt("studentId"));
	                student.setLrn(rs.getString("lrn"));
	                student.setFirstName(rs.getString("firstName"));
	                student.setLastName(rs.getString("lastName"));
	                student.setMiddleName(rs.getString("middleName"));
	                student.setGender(rs.getString("gender"));
	                student.setDepartmentName(rs.getString("departmentName"));
	                student.setGradeName(rs.getString("gradeLvl"));
	                student.setSectionId(rs.getInt("sectionId"));
	                student.setSectionName(rs.getString("sectionName"));
	                student.setAddress(rs.getString("address"));
	                student.setContactNo(rs.getString("contactNo"));
	                student.setEmail(rs.getString("email"));
	                return student;
	            }
	        }
	    }
	    return null;
	}

	public void updateStudent(int studentId,
	                           String lrn,
	                           String firstName,
	                           String lastName,
	                           String middleName,
	                           String gender,
	                           int sectionId,
	                           String address,
	                           String contactNo,
	                           String email) throws SQLException {
	    String sql = "UPDATE tbl_student SET lrn=?, firstName=?, lastName=?, middleName=?, "
	                +"gender=?, sectionId=?, address=?, contactNo=?, email=? WHERE studentId=?";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	        stmt.setString(1, lrn);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.setString(4, middleName);
	        stmt.setString(5, gender);
	        stmt.setInt(6, sectionId);
	        stmt.setString(7, address);
	        stmt.setString(8, contactNo);
	        stmt.setString(9, email);
	        stmt.setInt(10, studentId);
	        stmt.executeUpdate();
	    }
	}

	public void deleteStudent(int studentId) throws SQLException {
	    String sql = "DELETE FROM tbl_student WHERE studentId = ?";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	        stmt.setInt(1, studentId);
	        stmt.executeUpdate();
	    }
	}
}