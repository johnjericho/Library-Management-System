package dao.UserModule.LibrarianTab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.UserModule.LibrarianTab.Librarian;
import model.dto.LibrarianDisplay;
import utility.DatabaseHelper;

public class LibrarianMaintenanceDAO {

	public void addLibrarian(String role, String firstName, String lastName, String middleName,
	        String address, String contactNo, String email, String password) throws SQLException {

	    String sqlLibrarian = "INSERT INTO tbl_librarian (role, firstName, lastName, middle_name, address, contactNo) "
	            + "VALUES (?, ?, ?, ?, ?, ?)";

	    String sqlUser = "INSERT INTO tbl_user (librarianId, email, password) VALUES (?, ?, ?)";

	    try (Connection conn = DatabaseHelper.getConnection()) {

	        conn.setAutoCommit(false); // simulan ang transaction

	        try (PreparedStatement ps1 = conn.prepareStatement(sqlLibrarian, Statement.RETURN_GENERATED_KEYS)) {
	            ps1.setString(1, role);
	            ps1.setString(2, firstName);
	            ps1.setString(3, lastName);
	            ps1.setString(4, middleName);
	            ps1.setString(5, address);
	            ps1.setString(6, contactNo);
	            ps1.executeUpdate();

	            int librarianId;
	            try (ResultSet rs = ps1.getGeneratedKeys()) {
	                rs.next();
	                librarianId = rs.getInt(1);
	            }

	            try (PreparedStatement ps2 = conn.prepareStatement(sqlUser)) {
	                ps2.setInt(1, librarianId);
	                ps2.setString(2, email);
	                ps2.setString(3, password); // TODO: i-hash bago i-store
	                ps2.executeUpdate();
	            }
	        }

	        conn.commit(); // successful ang dalawa - i-save
	    }
	}

	// CHANGED: hinati sa DALAWANG UPDATE statement (tbl_librarian at tbl_user)
	// dahil hiwalay na ang email/password sa tbl_user. Ginawang transaction
	// gamit ang parehong pattern ng addLibrarian - walang catch block dito,
	// awtomatikong magrorollback ang JDBC driver kung hindi umabot sa commit().
	public void updateLibrarian(int librarianId, String role, String firstName, String lastName,
			String middleName, String contactNo, String email, String address, String password) throws SQLException {

		boolean updatePassword = password != null && !password.isEmpty();

		// CHANGED: tinanggal ang email at password dito - wala na silang column sa tbl_librarian
		String sqlLibrarian = "UPDATE tbl_librarian SET role=?, firstName=?, lastName=?, middle_name=?, "
				+ "contactNo=?, address=? WHERE librarianId=?";

		// NEW: hiwalay na UPDATE papunta sa tbl_user - dito na papasok ang email/password
		String sqlUser = "UPDATE tbl_user SET email=?"
				+ (updatePassword ? ", password=?" : "")
				+ " WHERE librarianId=?";

		try (Connection conn = DatabaseHelper.getConnection()) {

			conn.setAutoCommit(false); // simulan ang transaction

			try (PreparedStatement ps1 = conn.prepareStatement(sqlLibrarian)) {
				ps1.setString(1, role);
				ps1.setString(2, firstName);
				ps1.setString(3, lastName);
				ps1.setString(4, middleName);
				ps1.setString(5, contactNo);
				ps1.setString(6, address);
				ps1.setInt(7, librarianId);
				ps1.executeUpdate();
			}

			try (PreparedStatement ps2 = conn.prepareStatement(sqlUser)) {
				int idx = 1;
				ps2.setString(idx++, email);
				if (updatePassword) ps2.setString(idx++, password); // TODO: i-hash bago i-store
				ps2.setInt(idx, librarianId);
				ps2.executeUpdate();
			}

			conn.commit(); // successful ang dalawang update - i-save
		}
	}

	// CHANGED: dahil may kaakibat na row sa tbl_user (FK sa librarianId), kailangan
	// munang burahin ang tbl_user record bago ang tbl_librarian record (o mag-error
	// dahil sa FK constraint kung meron). Ginawang transaction din, walang catch block.
	public void deleteLibrarian(int librarianId) throws SQLException {
		String sqlUser = "DELETE FROM tbl_user WHERE librarianId=?";
		String sqlLibrarian = "DELETE FROM tbl_librarian WHERE librarianId=?";

		try (Connection conn = DatabaseHelper.getConnection()) {

			conn.setAutoCommit(false); // simulan ang transaction

			// NEW: burahin muna ang tbl_user (child record) bago ang tbl_librarian (parent)
			try (PreparedStatement ps1 = conn.prepareStatement(sqlUser)) {
				ps1.setInt(1, librarianId);
				ps1.executeUpdate();
			}

			try (PreparedStatement ps2 = conn.prepareStatement(sqlLibrarian)) {
				ps2.setInt(1, librarianId);
				ps2.executeUpdate();
			}

			conn.commit(); // successful ang dalawang delete - i-save
		}
	}

	// CHANGED: JOIN na sa tbl_user para makuha ang email (wala na ito sa tbl_librarian)
	public List<LibrarianDisplay> getAllLibrarians() throws SQLException {
		List<LibrarianDisplay> list = new ArrayList<>();
		String sql = "SELECT l.librarianId, l.role, l.firstName, l.lastName, l.middle_name, l.contactNo, "
				+ "u.email, l.address "
				+ "FROM tbl_librarian l "
				+ "JOIN tbl_user u ON l.librarianId = u.librarianId "
				+ "ORDER BY l.lastName, l.firstName";

		try (Connection conn = DatabaseHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new LibrarianDisplay(
						rs.getInt("librarianId"),
						rs.getString("role"),
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("middle_name"),
						rs.getString("contactNo"),
						rs.getString("email"),
						rs.getString("address")
				));
			}
		}
		return list;
	}

	// CHANGED: JOIN na sa tbl_user para makuha ang email
	public LibrarianDisplay getLibrarianById(int librarianId) throws SQLException {
		String sql = "SELECT l.librarianId, l.role, l.firstName, l.lastName, l.middle_name, l.contactNo, "
				+ "u.email, l.address "
				+ "FROM tbl_librarian l "
				+ "JOIN tbl_user u ON l.librarianId = u.librarianId "
				+ "WHERE l.librarianId=?";

		try (Connection conn = DatabaseHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, librarianId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new LibrarianDisplay(
							rs.getInt("librarianId"),
							rs.getString("role"),
							rs.getString("firstName"),
							rs.getString("lastName"),
							rs.getString("middle_name"),
							rs.getString("contactNo"),
							rs.getString("email"),
							rs.getString("address")
					);
				}
			}
		}
		return null;
	}

	// CHANGED: JOIN na sa tbl_user - ang paghahanap sa "email LIKE ?" ay
	// tumutukoy na ngayon sa u.email sa halip na l.email
	public List<LibrarianDisplay> searchLibrarians(String keyword) throws SQLException {
		List<LibrarianDisplay> list = new ArrayList<>();
		String sql = "SELECT l.librarianId, l.role, l.firstName, l.lastName, l.middle_name, l.contactNo, "
				+ "u.email, l.address "
				+ "FROM tbl_librarian l "
				+ "JOIN tbl_user u ON l.librarianId = u.librarianId "
				+ "WHERE l.firstName LIKE ? OR l.lastName LIKE ? OR l.middle_name LIKE ? OR u.email LIKE ? "
				+ "ORDER BY l.lastName, l.firstName";

		try (Connection conn = DatabaseHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			String param = "%" + keyword + "%";
			ps.setString(1, param);
			ps.setString(2, param);
			ps.setString(3, param);
			ps.setString(4, param);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new LibrarianDisplay(
							rs.getInt("librarianId"),
							rs.getString("role"),
							rs.getString("firstName"),
							rs.getString("lastName"),
							rs.getString("middle_name"),
							rs.getString("contactNo"),
							rs.getString("email"),
							rs.getString("address")
					));
				}
			}
		}
		return list;
	}

	// CHANGED: ito ang gagamitin sa LOGIN. Dahil ang email/password ay nasa
	// tbl_user na, ang paghahanap ay ngayon nakabase sa u.email (hindi na l.email
	// dahil wala na 'yun). JOIN pa rin para makuha ang role at personal info kasabay.
	public Librarian getLibrarianByEmail(String email) throws SQLException {
		String sql = "SELECT l.librarianId, l.role, l.firstName, l.lastName, l.middle_name, l.contactNo, "
				+ "u.email, l.address, u.password "
				+ "FROM tbl_librarian l "
				+ "JOIN tbl_user u ON l.librarianId = u.librarianId "
				+ "WHERE u.email=?";

		try (Connection conn = DatabaseHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Librarian(
							rs.getInt("librarianId"),
							rs.getString("role"),
							rs.getString("firstName"),
							rs.getString("lastName"),
							rs.getString("middle_name"),
							rs.getString("contactNo"),
							rs.getString("email"),
							rs.getString("address"),
							rs.getString("password")
					);
				}
			}
		}
		return null;
	}
}