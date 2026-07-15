package controller.UserModule.StudentTab;
import java.util.ArrayList;
import model.dto.StudentDisplay;
import services.UserModule.StudentTab.StudentMaintenanceServices;

public class StudentMaintenanceController {

	private StudentMaintenanceServices studentMaintenanceServices;

	public StudentMaintenanceController(StudentMaintenanceServices studentMaintenanceServices) {
		this.studentMaintenanceServices = studentMaintenanceServices;
	}

	public void addStudent(String lrn,
						 String firstName,
						 String lastName,
						 String middleName,
						 String gender,
						 int sectionId,
						 String address,
						 String contactNo,
						 String email) {
		studentMaintenanceServices.addStudent(lrn, firstName, lastName, middleName,
											   gender, sectionId, address, contactNo, email);
	}

	public ArrayList<StudentDisplay> loadStudentDisplay() {
		return studentMaintenanceServices.loadStudentDisplay();
	}

	public StudentDisplay getStudentById(int studentId) {
		return studentMaintenanceServices.getStudentById(studentId);
	}

	public void updateStudent(int studentId,
	                           String lrn,
	                           String firstName,
	                           String lastName,
	                           String middleName,
	                           String gender,
	                           int sectionId,
	                           String address,
	                           String contactNo,
	                           String email) {
		studentMaintenanceServices.updateStudent(studentId, lrn, firstName, lastName,
												  middleName, gender, sectionId,
												  address, contactNo, email);
	}

	public void deleteStudent(int studentId) {
		studentMaintenanceServices.deleteStudent(studentId);
	}
}