package model.BookModule.dto;

import java.sql.Date;

public class BookDisplay {

	private int bookId;
	private String bookIsbn;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublisher;
	private String bookCategory;
	private Date bookDatePublished;
	private int bookReserveCopy;
	
	
	public BookDisplay() { }
	
	public BookDisplay(int bookId,
		       			String bookIsbn,
						String bookTitle,
						String bookAuthor,
						String bookPublisher,
						String bookCategory,
						Date bookDatePublished,
						int bookReserveCopy) {
			this.bookId = bookId;
			this.bookIsbn = bookIsbn;
			this.bookTitle = bookTitle;
			this.bookAuthor = bookAuthor;
			this.bookPublisher = bookPublisher;
			this.bookCategory = bookCategory;
			this.bookDatePublished = bookDatePublished;
			this.bookReserveCopy = bookReserveCopy;
}
	
	//bookId
	public int getBookId() {
		return bookId;
	}
		
	//bookIsbn
	public String getBookIsbn() {
		return bookIsbn;
	}
	
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	
	//bookTitle
	public String getBookTitle() {
		return bookTitle;
	}
	
	public void setBookTitle(String booktTitle) {
		this.bookTitle = booktTitle;
	}
	
	//authorId
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	//publisherId
	public String getBookPublisher() {
		return bookPublisher;
	}
	
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	
	//categoryId
	public String getBookCategory() {
		return bookCategory;
	}
	
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	
	//bookReleaseDate
	public Date getBookDatePublished() {
		return bookDatePublished;
	}
	
	public void setBookDatePublished(Date bookDatePublished) {
		this.bookDatePublished = bookDatePublished;
	}
	
	//bookReserve
	public int getBookReserveCopy() {
		return bookReserveCopy;
	}
	
	public void setBookbookReserve(int bookReserveCopy) {
		this.bookReserveCopy = bookReserveCopy;
	}
	
}
