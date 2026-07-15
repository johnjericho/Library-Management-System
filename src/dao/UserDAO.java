package dao;

import model.User;
import utility.DatabaseHelper;
import java.sql.*;

public class UserDAO {

    public User validateLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM tbl_user WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("email"), rs.getString("password"));
            }
 
        } 
        return null; // null means login failed
    }
}