package dao.UserModule.StudentTab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModule.StudentTab.Grade;
import utility.DatabaseHelper;

public class GradeDAO {

    public ArrayList<Grade> getGradesByDepartment(int departmentId) throws SQLException {
        ArrayList<Grade> list = new ArrayList<>();
        String sql = "SELECT gradeId, departmentId, gradeLvl FROM tbl_grade WHERE departmentId = ?";

        try (
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, departmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Grade g = new Grade();
                    g.setGradeId(rs.getInt("gradeId"));
                    g.setDepartmentId(rs.getInt("departmentId"));
                    g.setGradeLvl(rs.getInt("gradeLvl"));
                    list.add(g);
                }
            }
        }
        return list;
    }
}