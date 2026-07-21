package model.AcqusitionModule;

public class InventoryDisplay {
	
	private int inventoryId;
	private String accessionNo;
	private String bookTitle;
	private String condition;
	private String status;
	
	
	public InventoryDisplay(int inventoryId,
							String accessionNo, 
							String bookTitle, 
							String condition,
							String status) {
		
		this.inventoryId = inventoryId;
		this.accessionNo = accessionNo;
		this.bookTitle = bookTitle;
		this.condition = condition;
		this.status = status;
	}
	
	//GETTER
	public int getInventoryId() {
		return inventoryId;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getCondition() {
		return condition;
	}
	public String getStatus() {
		return status;
	}
	
	//SETTER
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
