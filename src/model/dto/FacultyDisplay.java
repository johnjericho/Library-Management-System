package model.dto;

public class FacultyDisplay {

    private int facultyId;
    private String employeeNo;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String departmentName;
    private String gradeLvl;
    private int gradeId;        // FK, kailangan para sa edit mode/update (tulad ng sectionId sa StudentDisplay)
    private String contactNo;
    private String email;
    private String address;

    public FacultyDisplay() {}

    // Getters
    public int getFacultyId() { return facultyId; }
    public String getEmployeeNo() { return employeeNo; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }
    public String getGender() { return gender; }
    public String getDepartmentName() { return departmentName; }
    public String getGradeLvl() { return gradeLvl; }
    public int getGradeId() { return gradeId; }
    public String getContactNo() { return contactNo; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Setters
    public void setFacultyId(int facultyId) { this.facultyId = facultyId; }
    public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public void setGradeLvl(String gradeLvl) { this.gradeLvl = gradeLvl; }
    public void setGradeId(int gradeId) { this.gradeId = gradeId; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
}