package controller.UserModule.StudentTab;

import java.util.ArrayList;

import model.UserModule.StudentTab.Grade;
import services.UserModule.StudentTab.GradeServices;

public class GradeController {

    private GradeServices gradeServices;

    public GradeController(GradeServices gradeServices) {
        this.gradeServices = gradeServices;
    }

    public ArrayList<Grade> getGradesByDepartment(int departmentId) {
        return gradeServices.getGradesByDepartment(departmentId);
    }
}