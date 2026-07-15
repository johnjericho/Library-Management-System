package services.UserModule.StudentTab;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserModule.StudentTab.DepartmentDAO;
import model.UserModule.StudentTab.Department;

public class DepartmentServices {

    private DepartmentDAO departmentDao;

    public DepartmentServices(DepartmentDAO departmentDao) {
        this.departmentDao = departmentDao;
    }

    public ArrayList<Department> getAllDepartments() {
        try {
            return departmentDao.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error!");
        }
    }
}