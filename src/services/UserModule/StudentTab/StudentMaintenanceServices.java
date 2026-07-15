package services.UserModule.StudentTab;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.UserModule.StudentTab.StudentMaintenanceDAO;
import model.dto.StudentDisplay;

public class StudentMaintenanceServices {
    private StudentMaintenanceDAO studentmaintenanceDao;

	public StudentMaintenanceServices(StudentMaintenanceDAO studentmaintenanceDao) {
		this.studentmaintenanceDao = studentmaintenanceDao;
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
		lrn = lrn.trim().replaceAll("\\s+", " ");
		if(lrn.isEmpty()) throw new IllegalArgumentException("LRN cannot be empty!");

		try {
			studentmaintenanceDao.addStudent(lrn, firstName, lastName, middleName,
											  gender, sectionId, address, contactNo, email);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	public ArrayList<StudentDisplay> loadStudentDisplay() {
		try {
			return studentmaintenanceDao.loadStudentDisplay();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database connection error");
		}
	}

	public StudentDisplay getStudentById(int studentId) {
		try {
			return studentmaintenanceDao.getStudentById(studentId);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database connection error");
		}
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
		lrn = lrn.trim().replaceAll("\\s+", " ");
		if(lrn.isEmpty()) throw new IllegalArgumentException("LRN cannot be empty!");

		try {
			studentmaintenanceDao.updateStudent(studentId, lrn, firstName, lastName,
												 middleName, gender, sectionId,
												 address, contactNo, email);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	public void deleteStudent(int studentId) {
		try {
			studentmaintenanceDao.deleteStudent(studentId);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}
}