

//add service layer



package controller.BookModule;
import java.util.Date;

import services.BookModuleServices.BookMaintenanceServices;

public class BookMaintenanceController {
	
	private BookMaintenanceServices bookMaintenanceServices;
	
	public BookMaintenanceController(BookMaintenanceServices  bookMaintenanceServices) {
		this.bookMaintenanceServices = bookMaintenanceServices;
	}
	
	public void addBook(String isbn,
						String title,
						int selectedAuthorId,
						String publisher,
						String category,
						String contributor,
						Date date,
						int reserve) 
	{
                   
		bookMaintenanceServices.addBook(isbn, title, selectedAuthorId, publisher, category, contributor, date, reserve);

	}

	
	
	
	
}


