package services.BookModuleServices;

import java.util.Date;

public class BookMaintenanceServices {
	
	public void addBook(String isbn,
			String title,
			int selectedAuthorId,
			String publisher,
			String category,
			String contributor,
			Date date,
			int reserve)
	{
		// 1. Null check muna BAGO mag-trim — kasi kung null ang param,sumabog na agad sa NullPointerException 'yung .trim(), since null not empty
		if(contributor == null || contributor.equalsIgnoreCase("")) {throw new IllegalArgumentException("Must choose contributor!"); }
		if (date == null) { throw new IllegalArgumentException("Date must not be null!"); }

		isbn = isbn.trim().replaceAll("\\s+", " ");
		title = title.trim().replaceAll("\\s+", " ");
		publisher = publisher.trim().replaceAll("\\s+", " ");
		category = category.trim().replaceAll("\\s+", " ");
		contributor = contributor.trim().replaceAll("\\s+", " ");

		if (isbn.isEmpty())        { throw new IllegalArgumentException("ISBN must not be empty!"); }
		if (title.isEmpty())       { throw new IllegalArgumentException("Title must not be empty!"); }
		if (selectedAuthorId  < 0)      { throw new IllegalArgumentException("Author must not be empty!"); }
		if (publisher.isEmpty())      { throw new IllegalArgumentException("publisher must not be empty!"); }
		if (category.isEmpty())    { throw new IllegalArgumentException("Category must not be empty!"); }
		if (contributor.isEmpty()) { throw new IllegalArgumentException("Contributor must not be empty!"); }

		if (!isbn.matches("\\d+"))     { throw new IllegalArgumentException("ISBN must contain numbers only!"); }
		if (!isbn.matches("\\d{13}"))  { throw new IllegalArgumentException("ISBN must contain exactly 13 digits!"); }

		if (title.length() > 255)  { throw new IllegalArgumentException("Title is too long! Max 255 characters."); }

		// 6. Date validation — assumption ko dito: "date" = date added/acquired, kaya hindi dapat future date
		if (date.after(new Date())) {
			throw new IllegalArgumentException("Date must be from past -> present!");
		}

		// 7. Reserve count validation — hindi dapat negative ang stock/reserve
		if (reserve < 0) {
			throw new IllegalArgumentException("Reserve count must not be negative!");
		}


	}
	
	
	
	
	
	
	

}
