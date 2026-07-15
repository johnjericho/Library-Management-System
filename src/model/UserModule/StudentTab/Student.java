package model.UserModule.StudentTab;

public class Student {
	private int studentId;
	private String lrn;
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private int sectionId;
	private String address;
	private String contactNo;
	private String email;
	
	
	
	
	//Getter
	
	public int getStudentId() {
		return studentId;
	}
	public String getLrn() {
		return lrn;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getGender() {
		return gender;
	}
	
	public int getSectionId() {
		return sectionId;
	}
	public String getAddress() {
		return address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public String getEmail() {
		return email;
	}
	
	
	//Setter
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setLrn(String lrn) {
		this.lrn = lrn;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
	
}
