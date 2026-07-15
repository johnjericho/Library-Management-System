package model.dto;

public class LibrarianDisplay {

	private int librarianId;
	private String positionType; // "Librarian" or "Assistant librarian"
	private String firstName;
	private String lastName;
	private String middleName;
	private String contactNo;
	private String email;
	private String address;
	private String password;

	public LibrarianDisplay() {}

	public LibrarianDisplay(int librarianId,
							String positionType,
							String firstName,
							String lastName,
							String middleName,
							String contactNo,
							String email, 
							String address
							) {
		this.librarianId = librarianId;
		this.positionType = positionType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
	}

	public int getLibrarianId() { return librarianId; }
	public void setLibrarianId(int librarianId) { this.librarianId = librarianId; }

	public String getPositionType() { return positionType; }
	public void setPositionType(String positionType) { this.positionType = positionType; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getMiddleName() { return middleName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }

	public String getContactNo() { return contactNo; }
	public void setContactNo(String contactNo) { this.contactNo = contactNo; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}