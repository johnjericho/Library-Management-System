package dao.UserModule.FacultyTab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dto.FacultyDisplay;
import utility.DatabaseHelper;

public class FacultyMaintenanceDAO {

    public void addFaculty(String employeeNo,
                            String firstName,
                            String lastName,
                            String middleName,
                            String gender,
                            int gradeId,
                            String contactNo,
                            String email,
                            String address) throws SQLException {
        String sql = "INSERT INTO tbl_faculty (employeeNo, firstName, lastName, middleName, gender, "
                   + "gradeId, contactNo, email, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, employeeNo);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, middleName);
            stmt.setString(5, gender);
            stmt.setInt(6, gradeId);
            stmt.setString(7, contactNo);
            stmt.setString(8, email);
            stmt.setString(9, address);
            stmt.executeUpdate();
        }
    }

    public ArrayList<FacultyDisplay> loadFacultyDisplay() throws SQLException {
        ArrayList<FacultyDisplay> facultyList = new ArrayList<>();
        String sql = "SELECT f.facultyId, f.employeeNo, f.firstName, f.lastName, f.middleName, "
                    +"f.gender, dept.departmentName, gr.gradeLvl, f.contactNo, f.email, f.address "
                    +"FROM tbl_faculty f "
                    +"JOIN tbl_grade gr ON f.gradeId = gr.gradeId "
                    +"JOIN tbl_department dept ON gr.departmentId = dept.departmentId "
                    +"ORDER BY f.firstName ASC";
        try(
        Connection conn = DatabaseHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()) {
                FacultyDisplay faculty = new FacultyDisplay();
                faculty.setFacultyId(rs.getInt("facultyId"));
                faculty.setEmployeeNo(rs.getString("employeeNo"));
                faculty.setFirstName(rs.getString("firstName"));
                faculty.setLastName(rs.getString("lastName"));
                faculty.setMiddleName(rs.getString("middleName"));
                faculty.setGender(rs.getString("gender"));
                faculty.setDepartmentName(rs.getString("departmentName"));
                faculty.setGradeLvl(rs.getString("gradeLvl"));
                faculty.setContactNo(rs.getString("contactNo"));
                faculty.setEmail(rs.getString("email"));
                faculty.setAddress(rs.getString("address"));
                facultyList.add(faculty);
            }
        }
        return facultyList;
    }

    public FacultyDisplay getFacultyById(int facultyId) throws SQLException {
        String sql = "SELECT f.facultyId, f.employeeNo, f.firstName, f.lastName, f.middleName, "
                    +"f.gender, dept.departmentName, gr.gradeId, gr.gradeLvl, f.contactNo, f.email, f.address "
                    +"FROM tbl_faculty f "
                    +"JOIN tbl_grade gr ON f.gradeId = gr.gradeId "
                    +"JOIN tbl_department dept ON gr.departmentId = dept.departmentId "
                    +"WHERE f.facultyId = ?";
        try(
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setInt(1, facultyId);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    FacultyDisplay faculty = new FacultyDisplay();
                    faculty.setFacultyId(rs.getInt("facultyId"));
                    faculty.setEmployeeNo(rs.getString("employeeNo"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setGender(rs.getString("gender"));
                    faculty.setDepartmentName(rs.getString("departmentName"));
                    faculty.setGradeId(rs.getInt("gradeId"));
                    faculty.setGradeLvl(rs.getString("gradeLvl"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setAddress(rs.getString("address"));
                    return faculty;
                }
            }
        }
        return null;
    }

    public void updateFaculty(int facultyId,
                               String employeeNo,
                               String firstName,
                               String lastName,
                               String middleName,
                               String gender,
                               int gradeId,
                               String contactNo,
                               String email,
                               String address) throws SQLException {
        String sql = "UPDATE tbl_faculty SET employeeNo=?, firstName=?, lastName=?, middleName=?, "
                    +"gender=?, gradeId=?, contactNo=?, email=?, address=? WHERE facultyId=?";
        try(
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, employeeNo);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, middleName);
            stmt.setString(5, gender);
            stmt.setInt(6, gradeId);
            stmt.setString(7, contactNo);
            stmt.setString(8, email);
            stmt.setString(9, address);
            stmt.setInt(10, facultyId);
            stmt.executeUpdate();
        }
    }

    public void deleteFaculty(int facultyId) throws SQLException {
        String sql = "DELETE FROM tbl_faculty WHERE facultyId = ?";
        try(
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setInt(1, facultyId);
            stmt.executeUpdate();
        }
    }
}