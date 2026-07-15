package model.dto;

public class BorrowerDisplay {

	  private int borrowerId;
	  private String borrowerType;
	  private String borrowerNo;
	  private String fullName;
	  private String department;
	  private String grade;
	  private String section;
	  
	   
	  public BorrowerDisplay(int borroweId, String borrowerType,String borrowerNo, String fullName, String department, String grade,  String section) {
		this.borrowerId = borroweId;
		this.borrowerType = borrowerType;
		this.borrowerNo = borrowerNo;
		this.fullName = fullName;
		this.department = department;
		this.grade = grade;
		this.section = section;
	  }


	  
	  //GETTER
	  public int getBorroweId() {
		  return borrowerId;
	  }


	  public String getBorrowerType() {
		  return borrowerType;
	  }


	  public String getFullName() {
		  return fullName;
	  }


	  public String getDepartment() {
		  return department;
	  }


	  public String getGrade() {
		  return grade;
	  }
	  
	  
	  public String getBorrowerUniqueNo() {
		return borrowerNo;
	}

	  public String getSection() {
		return section;
	}






	  //SETTER
	  public void setBorroweId(int borroweId) {
		  this.borrowerId = borroweId;
	  }


	  public void setBorrowerType(String borrowerType) {
		  this.borrowerType = borrowerType;
	  }


	  public void setFullName(String fullName) {
		  this.fullName = fullName;
	  }


	  public void setDepartment(String department) {
		  this.department = department;
	  }


	  public void setGrade(String grade) {
		  this.grade = grade;
	  }
	


	
}
