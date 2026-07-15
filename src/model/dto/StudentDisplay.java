package model.dto;

public class StudentDisplay {


		private int studentId;
		private String lrn;
		private String firstName;
		private String lastName;
		private String middleName;
		private String gender;
		private String departmentName;
		private String gradeName;
		private int sectionId;
		private String sectionName;
		private String address;
		private String contactNo;
		private String email;
		
		public StudentDisplay() {}
		
		public StudentDisplay(String lrn, String firstName, String lastName, String middleName, String gender,
				String departmentName, String gradeName, String sectionName, String address, String contactNo,
				String email) {
		
			this.lrn = lrn;
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
			this.gender = gender;
			this.departmentName = departmentName;
			this.gradeName = gradeName;
			this.sectionName = sectionName;
			this.address = address;
			this.contactNo = contactNo;
			this.email = email;
		}
		
		
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
		public String getDepartmentName() {
			return departmentName;
		}
		public String getGradeLvl() {
			return gradeName;
		}
		
		public int getSectionId() {
			return sectionId;
		}
		
		public String getSectionName() {
			return sectionName;
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
		public void setDepartmentName(String departmentId) {
			this.departmentName = departmentId;
		}
		public void setGradeName(String gradeId) {
			this.gradeName = gradeId;
		}
		
		public void setSectionId(int sectionId) {
			this.sectionId = sectionId;
		}
		
		public void setSectionName(String sectionName) {
			this.sectionName = sectionName;
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
