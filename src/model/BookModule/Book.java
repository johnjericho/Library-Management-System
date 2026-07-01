package model.BookModule;

import java.sql.Date;

public class Book {

	private int bookId;
	private String bookIsbn;
	private String bookTitle;
	private int authorId;
	private int publisherId;
	private int categoryId;
	private Date bookDatePublished;
	private int bookReserveCopy;
	
	public Book() { }
	
	public Book(int bookId,
			    String bookIsbn,
				String bookTitle,
				int bookAuthor,
				int bookPublisher,
				int bookCategory,
				Date bookDatePublished,
				int reserveCopy) {
		
		this.bookId = bookId;
		this.bookIsbn = bookIsbn;
		this.bookTitle =bookTitle;
		this.authorId = bookAuthor;
		this.publisherId = bookPublisher;
		this.categoryId = bookCategory;
		this.bookDatePublished = bookDatePublished;
		this.bookReserveCopy = reserveCopy;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String booksbn) {
		this.bookIsbn = booksbn;
		
		
		
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int bookAuthor) {
		this.authorId = bookAuthor;
	}
	
	
	
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int bookPublisher) {
		this.publisherId = bookPublisher;
	}
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int bookCategory) {
		this.categoryId = bookCategory;
	}
	
	
	
	public Date getBookDatePublished() {
		return bookDatePublished;
	}
	public void setBookDatePublished(Date bookDatePublished) {
		this.bookDatePublished = bookDatePublished;
	}
	
	
	
	public int getBookReserveCopy() {
		return bookReserveCopy;
	}
	public void setBookReserveCopy(int bookReserveCopy) {
		this.bookReserveCopy = bookReserveCopy;
	}
	
	
	
	
	
}
