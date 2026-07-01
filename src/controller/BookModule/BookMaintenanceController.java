

//add service layer



package controller.BookModule;
import java.util.ArrayList;
import java.util.Date;

import model.BookModule.Book;
import model.BookModule.dto.BookDisplay;
import services.BookModuleServices.BookMaintenanceServices;

public class BookMaintenanceController {
	
	private BookMaintenanceServices bookMaintenanceServices;
	
	public BookMaintenanceController(BookMaintenanceServices  bookMaintenanceServices) {
		this.bookMaintenanceServices = bookMaintenanceServices;
	}
	
	public void addBook(String isbn,
						String title,
						int selectedAuthorId,
						int selectedPublisherId,
						int selectedCategoryId,
						Date date,
						int reserve) 
	{
                   
		bookMaintenanceServices.addBook(isbn, title, selectedAuthorId, selectedPublisherId, selectedCategoryId, date, reserve);

	}
	

	
	public ArrayList<BookDisplay> loadBookForDisplay(){
		return bookMaintenanceServices.loadBookForDisplay();
	}

	public void updateBook(int bookId,
					       String isbn,
					       String title,
					       int selectedAuthorId,
					       int selectedPublisherId,
					       int selectedCategoryId,
					       Date date,
					       int reserve) {
		
		bookMaintenanceServices.updateBook(bookId, isbn, title, selectedAuthorId, selectedPublisherId, selectedCategoryId, date, reserve);
			
	}
	
	
	public Book getBookById(int bookId) {
	    return bookMaintenanceServices.getBookById(bookId);
	}
	
	public void deleteBook(int bookId) {
		bookMaintenanceServices.deleteBook(bookId);
	}
	
	public ArrayList<BookDisplay> searchBook(String keyword){
		return bookMaintenanceServices.searchBook(keyword);
	}
	
}


