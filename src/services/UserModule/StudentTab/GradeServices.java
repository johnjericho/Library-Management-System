package services.UserModule.StudentTab;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserModule.StudentTab.GradeDAO;
import model.UserModule.StudentTab.Grade;

public class GradeServices {

    private GradeDAO gradeDao;

    public GradeServices(GradeDAO gradeDao) {
        this.gradeDao = gradeDao;
    }

    public ArrayList<Grade> getGradesByDepartment(int departmentId) {
        try {
            return gradeDao.getGradesByDepartment(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error!");
        }
    }
}