package dao.UserModule.StudentTab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModule.StudentTab.Department;
import utility.DatabaseHelper;

public class DepartmentDAO {

    public ArrayList<Department> getAllDepartments() throws SQLException {
        ArrayList<Department> list = new ArrayList<>();
        String sql = "SELECT departmentId, departmentName FROM tbl_department";

        try (
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("departmentId"));
                dept.setDepartmentName(rs.getString("departmentName"));
                list.add(dept);
            }
        }
        return list;
    }
}