package model.AcqusitionModule;

public class AcquisitionDetailDisplay {

    private int detailId;      // -1 kung hindi pa naiimbak sa DB, actual PK kung existing
    private int bookId;
    private String bookIsbn;
    private String bookTitle;
    private int quantity;
    private double price;

    public AcquisitionDetailDisplay(int bookId, String bookTitle, int quantity, double price) {}
    
    public AcquisitionDetailDisplay(int detailId, int bookId, String bookIsbn,
            String bookTitle, int quantity, double price) {
        this.detailId = detailId;
        this.bookId = bookId;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal() {
        return quantity * price;
    }

	public int getDetailId() {
		return detailId;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    
    
    
    
}