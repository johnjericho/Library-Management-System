package services.UserModule.FacultyTab;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.UserModule.FacultyTab.FacultyMaintenanceDAO;
import model.dto.FacultyDisplay;

public class FacultyMaintenanceServices {

    private FacultyMaintenanceDAO facultyMaintenanceDao;

    public FacultyMaintenanceServices(FacultyMaintenanceDAO facultyMaintenanceDao) {
        this.facultyMaintenanceDao = facultyMaintenanceDao;
    }

    public void addFaculty(String employeeNo,
                            String firstName,
                            String lastName,
                            String middleName,
                            String gender,
                            int gradeId,
                            String contactNo,
                            String email,
                            String address) {
        employeeNo = employeeNo.trim().replaceAll("\\s+", " ");
        if(employeeNo.isEmpty()) throw new IllegalArgumentException("Employee No. cannot be empty!");
        if(gender == null || gender.isEmpty()) throw new IllegalArgumentException("Gender must be selected!");
        if(gradeId == -1) throw new IllegalArgumentException("Grade must be selected!");

        try {
            facultyMaintenanceDao.addFaculty(employeeNo, firstName, lastName, middleName,
                                              gender, gradeId, contactNo, email, address);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error");
        }
    }

    public ArrayList<FacultyDisplay> loadFacultyDisplay() {
        try {
            return facultyMaintenanceDao.loadFacultyDisplay();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error");
        }
    }

    public FacultyDisplay getFacultyById(int facultyId) {
        try {
            return facultyMaintenanceDao.getFacultyById(facultyId);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error");
        }
    }

    public void updateFaculty(int facultyId,
                               String employeeNo,
                               String firstName,
                               String lastName,
                               String middleName,
                               String gender,
                               int gradeId,
                               String contactNo,
                               String email,
                               String address) {
        employeeNo = employeeNo.trim().replaceAll("\\s+", " ");
        if(employeeNo.isEmpty()) throw new IllegalArgumentException("Employee No. cannot be empty!");
        if(gender == null || gender.isEmpty()) throw new IllegalArgumentException("Gender must be selected!");
        if(gradeId == -1) throw new IllegalArgumentException("Grade must be selected!");

        try {
            facultyMaintenanceDao.updateFaculty(facultyId, employeeNo, firstName, lastName,
                                                 middleName, gender, gradeId,
                                                 contactNo, email, address);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error");
        }
    }

    public void deleteFaculty(int facultyId) {
        try {
            facultyMaintenanceDao.deleteFaculty(facultyId);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error");
        }
    }
}