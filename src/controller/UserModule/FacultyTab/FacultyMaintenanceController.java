package controller.UserModule.FacultyTab;

import java.util.ArrayList;
import model.dto.FacultyDisplay;
import services.UserModule.FacultyTab.FacultyMaintenanceServices;

public class FacultyMaintenanceController {

    private FacultyMaintenanceServices facultyServices;

    public FacultyMaintenanceController(FacultyMaintenanceServices facultyServices) {
        this.facultyServices = facultyServices;
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
        facultyServices.addFaculty(employeeNo, firstName, lastName, middleName,
                                    gender, gradeId, contactNo, email, address);
    }

    public ArrayList<FacultyDisplay> loadFacultyDisplay() {
        return facultyServices.loadFacultyDisplay();
    }

    public FacultyDisplay getFacultyById(int facultyId) {
        return facultyServices.getFacultyById(facultyId);
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
        facultyServices.updateFaculty(facultyId, employeeNo, firstName, lastName,
                                       middleName, gender, gradeId,
                                       contactNo, email, address);
    }

    public void deleteFaculty(int facultyId) {
        facultyServices.deleteFaculty(facultyId);
    }
}