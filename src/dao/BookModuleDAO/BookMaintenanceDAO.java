package dao.BookModuleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import utility.DatabaseHelper;

public class BookMaintenanceDAO {

	public void addBook(String isbn,
			String title,
			int selectedAuthorId,
			String publisher,
			String category,
			String contributor,
			Date date,
			int reserve) throws SQLException {
		
		String sql = "INSERT into tbl_book "
				+ "(isbn, bookTitle, authorId, bookPublisher, bookCategory, bookContributor, bookDatePublished, bookReserve) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		stmt.setString(1, isbn);
		stmt.setString(2, title);
		stmt.setInt(3, selectedAuthorId);
		stmt.setString(4, publisher);
		stmt.setString(5, category);
		stmt.setString(6, contributor);
		stmt.setDate(7, new java.sql.Date(date.getTime()));
		stmt.setInt(8, reserve);
		stmt.executeUpdate();
		}
	}
	
}

