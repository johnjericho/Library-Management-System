package dao.BookModuleDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BookModule.Book;
import model.dto.BookDisplay;
import utility.DatabaseHelper;

public class BookMaintenanceDAO {

	public void addBook(String isbn,
			String title,
			int selectedAuthorId,
			int selectedPublisherId,
			int selectedCategoryId,
			Date date,
			int reserve) throws SQLException {
		
		String sql = "INSERT into tbl_book "
				+ "(isbn, bookTitle, authorId, publisherId, categoryId, bookDatePublished, bookReserve) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		stmt.setString(1, isbn);
		stmt.setString(2, title);
		stmt.setInt(3, selectedAuthorId);
		stmt.setInt(4, selectedPublisherId);
		stmt.setInt(5, selectedCategoryId);
		stmt.setDate(6, new java.sql.Date(date.getTime()));
		stmt.setInt(7, reserve);
		stmt.executeUpdate();
		}
	}
	
	public boolean isExisting(String bookIsbn, int excludeBookId) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM tbl_book WHERE LOWER(isbn) = LOWER(?) AND bookId != ?";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql))
	    {
	        stmt.setString(1, bookIsbn);
	        stmt.setInt(2, excludeBookId);
	        try( ResultSet rs = stmt.executeQuery())
	        {
	            if(rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	
	
	public ArrayList<BookDisplay> getAllBooks() throws SQLException {

	    ArrayList<BookDisplay> loadBookDisplay = new ArrayList<>();

	    String sql = "SELECT b.bookId, b.isbn, b.bookTitle, "
	               + "a.authorName, p.publisherName, c.categoryName, "
	               + "b.bookDatePublished, b.bookReserve "
	               + "FROM tbl_book b "
	               + "JOIN tbl_author a ON b.authorId = a.authorId "
	               + "JOIN tbl_publisher p ON b.publisherId = p.publisherId "
	               + "JOIN tbl_category c ON b.categoryId = c.categoryId "
	               + "ORDER BY b.bookTitle ASC";

	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	    ) {
	        while (rs.next()) {
	            BookDisplay bookDisplay = new BookDisplay(
	                    rs.getInt("bookId"),
	                    rs.getString("isbn"),
	                    rs.getString("bookTitle"),
	                    rs.getString("authorName"),
	                    rs.getString("publisherName"),
	                    rs.getString("categoryName"),
	                    rs.getDate("bookDatePublished"),
	                    rs.getInt("bookReserve")
	                    );

	            loadBookDisplay.add(bookDisplay);
	        }
	    }
	    return loadBookDisplay;
	}
	
	public Book getBookById(int bookId) throws SQLException {
	    String sql = "SELECT * FROM tbl_book WHERE bookId = ?";

	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {
	        stmt.setInt(1, bookId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Book book = new Book();
	                book.setBookId(rs.getInt("bookId"));
	                book.setBookIsbn(rs.getString("isbn"));
	                book.setBookTitle(rs.getString("bookTitle"));
	                book.setAuthorId(rs.getInt("authorId"));
	                book.setPublisherId(rs.getInt("publisherId"));
	                book.setCategoryId(rs.getInt("categoryId"));
	                book.setBookDatePublished(rs.getDate("bookDatePublished"));
	                book.setBookReserveCopy(rs.getInt("bookReserve"));
	                return book;
	            }
	        }
	    }
	    return null;  // walang nahanap na row na tugma sa ID
	}
	
			
	public void updateBook(int bookId,
            String isbn,
            String title,
            int selectedAuthorId,
            int selectedPublisherId,
            int selectedCategoryId,
            Date datePublished,
            int reserve) throws SQLException {

String sql = "UPDATE tbl_book SET isbn = ?, bookTitle = ?, authorId = ?, "
			+ "publisherId = ?, categoryId = ?, bookDatePublished = ?, bookReserve = ? "
			+ "WHERE bookId = ?";

	try (Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, isbn);
			stmt.setString(2, title);
			stmt.setInt(3, selectedAuthorId);
			stmt.setInt(4, selectedPublisherId);
			stmt.setInt(5, selectedCategoryId);
			stmt.setDate(6, datePublished);
			stmt.setInt(7, reserve);
			stmt.setInt(8, bookId);   // WHERE clause value, laging huli
			
			stmt.executeUpdate();
			}
		}
				
	
	public void deleteBook(int bookId) throws SQLException {
		
		String sql = "DELETE FROM tbl_book WHERE bookId = ?";
     
		try(
		Connection conn = DatabaseHelper.getConnection();
    	PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		  stmt.setInt(1, bookId);
		  stmt.executeUpdate();
		}
		
	}
	
	public ArrayList<BookDisplay> searchBook(String filteredText) throws SQLException {
	    ArrayList<BookDisplay> filteredResult = new ArrayList<>();

	    String sql = "SELECT " +
	                "b.bookId, " +
	                "b.isbn, " +
	                "b.bookTitle, " +
	                "a.authorName, " +
	                "p.publisherName, " +
	                "c.categoryName, " +
	                "b.bookDatePublished, " +
	                "b.bookReserve " +
	                "FROM tbl_book b " +
	                "JOIN tbl_author a ON b.authorId = a.authorId " +
	                "JOIN tbl_publisher p ON b.publisherId = p.publisherId " +
	                "JOIN tbl_category c ON b.categoryId = c.categoryId " +
	                "WHERE b.isbn LIKE ? " +
	                "OR b.bookTitle LIKE ? " +
	                "OR a.authorName LIKE ? " +
	                "OR p.publisherName LIKE ? " +
	                "OR c.categoryName LIKE ? " +
	                "OR DATE_FORMAT(b.bookDatePublished, '%Y-%m-%d') LIKE ? " +
	                "OR CAST(b.bookReserve AS CHAR) LIKE ? " +
	                "ORDER BY b.bookTitle ASC";

	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {
	        String keyword = "%" + filteredText + "%";

	        stmt.setString(1, keyword);
	        stmt.setString(2, keyword);
	        stmt.setString(3, keyword);
	        stmt.setString(4, keyword);
	        stmt.setString(5, keyword);
	        stmt.setString(6, keyword);
	        stmt.setString(7, keyword);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                BookDisplay book = new BookDisplay(
	                        rs.getInt("bookId"),
	                        rs.getString("isbn"),
	                        rs.getString("bookTitle"),
	                        rs.getString("authorName"),
	                        rs.getString("publisherName"),
	                        rs.getString("categoryName"),
	                        rs.getDate("bookDatePublished"),
	                        rs.getInt("bookReserve")
	                );
	                filteredResult.add(book);
	            }
	        }
	    }

	    return filteredResult;
	}
}

