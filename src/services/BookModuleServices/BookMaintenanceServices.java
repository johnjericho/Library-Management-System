package services.BookModuleServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.BookModuleDAO.BookMaintenanceDAO;
import model.BookModule.Book;
import model.dto.BookDisplay;

public class BookMaintenanceServices {
	
	private BookMaintenanceDAO bookMaintenanceDao;
	
	public BookMaintenanceServices(BookMaintenanceDAO bookMaintenanceDao) {
		this.bookMaintenanceDao = bookMaintenanceDao;
	}
	
	public void addBook(String isbn,
			String title,
			int selectedAuthorId,
			int selectedPublisherId,
			int selectedCategoryId,
			Date bookDatePublished,
			int reserve)
	{
		
		
		if (bookDatePublished == null) { throw new IllegalArgumentException("Date must not be null!"); }

		isbn = isbn.trim().replaceAll("\\s+", " ");
		title = title.trim().replaceAll("\\s+", " ");

		
		if (isbn.isEmpty()){ 
                    isbn = "-";			
		}else if(!isbn.matches("\\d+") && !isbn.matches("-")){
			 throw new IllegalArgumentException("ISBN must contain numbers only!");
		}else if(!isbn.matches("\\d{13}") && !isbn.matches("-")) {
			throw new IllegalArgumentException("ISBN must contain exactly 13 digits!");
		}
		if(!isbn.equals("-") && isExisting(isbn, -1)) {
		    throw new IllegalArgumentException("ISBN already exist");
		}
		
		
		
		if (title.isEmpty())       { throw new IllegalArgumentException("Title must not be empty!"); }
		if (selectedAuthorId  < 0)      { throw new IllegalArgumentException("Author must not be empty!"); }
		if (selectedPublisherId < 0)      { throw new IllegalArgumentException("publisher must not be empty!"); }
		if (selectedCategoryId < 0)    { throw new IllegalArgumentException("Category must not be empty!"); }

		

		if (title.length() > 255)  { throw new IllegalArgumentException("Title is too long! Max 255 characters."); }

		if (bookDatePublished.after(new Date())) {
			throw new IllegalArgumentException("Date must be from past -> present!");
		}
		
		java.sql.Date sqlDate = new java.sql.Date(bookDatePublished.getTime());

		if (reserve < 0) {
			throw new IllegalArgumentException("Reserve count must not be negative!");
		}
		
		

		try {
		bookMaintenanceDao.addBook(isbn, title, selectedAuthorId, selectedPublisherId, selectedCategoryId, sqlDate, reserve);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection problem");
		}
     

	}
		
	
	
		public ArrayList<BookDisplay> loadBookForDisplay() {
		    try {
				ArrayList<BookDisplay> bookList = bookMaintenanceDao.getAllBooks();
		        return bookList;    
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new RuntimeException("Database Connection Error");
		    }
		}
		
		
		public Book getBookById(int bookId) {
		    try {
		    	Book getbookId = bookMaintenanceDao.getBookById(bookId);
		        return getbookId;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new RuntimeException("Database Connection Error");
		    }
		}
		
		public void updateBook(int bookId,
							   String isbn,
							   String title,
							   int selectedAuthorId,
							   int selectedPublisherId,
							   int selectedCategoryId,
							   Date bookDatePublished,
							   int reserve) {
			
			if (bookDatePublished == null) { throw new IllegalArgumentException("Date must not be null!"); }

			isbn = isbn.trim().replaceAll("\\s+", " ");
			title = title.trim().replaceAll("\\s+", " ");

			
			if (isbn.isEmpty()){ 
	                    isbn = "-";			
			}else if(!isbn.matches("\\d+") && !isbn.matches("-")){
				 throw new IllegalArgumentException("ISBN must contain numbers only!");
			}else if(!isbn.matches("\\d{13}") && !isbn.matches("-")) {
				throw new IllegalArgumentException("ISBN must contain exactly 13 digits!");
			}
			
			if(!isbn.equals("-") && isExisting(isbn, bookId)) {
			    throw new IllegalArgumentException("ISBN already exist");
			}

			
			if (title.isEmpty())       { throw new IllegalArgumentException("Title must not be empty!"); }
			if (selectedAuthorId  < 0)      { throw new IllegalArgumentException("Author must not be empty!"); }
			if (selectedPublisherId < 0)      { throw new IllegalArgumentException("publisher must not be empty!"); }
			if (selectedCategoryId < 0)    { throw new IllegalArgumentException("Category must not be empty!"); }

			

			if (title.length() > 255)  { throw new IllegalArgumentException("Title is too long! Max 255 characters."); }

			if (bookDatePublished.after(new Date())) {
				throw new IllegalArgumentException("Date must be from past -> present!");
			}

			if (reserve < 0) {
				throw new IllegalArgumentException("Reserve count must not be negative!");
			}
			
			java.sql.Date sqlDate = new java.sql.Date(bookDatePublished.getTime());

						
			try {
			bookMaintenanceDao.updateBook(bookId, isbn, title, selectedAuthorId, selectedPublisherId, selectedCategoryId, sqlDate, reserve);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Database Connection problem");
			}
			
		}
		
		public boolean isExisting(String bookIsbn, int excludeBookId) {
		    try {
		        boolean isExisting = bookMaintenanceDao.isExisting(bookIsbn, excludeBookId);
		        return isExisting;
		    }catch(SQLException e) {
		        e.printStackTrace();
		        throw new RuntimeException("Database connection error");
		    }
		}
		
		
		public void deleteBook(int bookId) {	
			if(bookId < 0) throw new IllegalArgumentException("Select row first!");
			
			try {
			bookMaintenanceDao.deleteBook(bookId);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Database connection Error");
			}
		}
	
		public ArrayList<BookDisplay> searchBook(String keyword){
			try{
			ArrayList<BookDisplay> filteredBook = bookMaintenanceDao.searchBook(keyword);
			return filteredBook;
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Database Connection Error!");
			}
		}
	
	
	

}
