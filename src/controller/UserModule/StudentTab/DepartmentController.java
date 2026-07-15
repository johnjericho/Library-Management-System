package controller.UserModule.StudentTab;

import java.util.ArrayList;

import model.UserModule.StudentTab.Department;
import services.UserModule.StudentTab.DepartmentServices;

public class DepartmentController {

    private DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    public ArrayList<Department> getAllDepartments() {
        return departmentServices.getAllDepartments();
    }
}