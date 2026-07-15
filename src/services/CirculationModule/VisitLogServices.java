package services.CirculationModule;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import dao.CirculationModule.VisitLogDAO;
import model.dto.VisitLogDisplay;

public class VisitLogServices {
    private VisitLogDAO visitLogDao;

    public VisitLogServices(VisitLogDAO visitLogDao) {
        this.visitLogDao = visitLogDao;
    }

    public void timeIn(int borrowerId, String borrowerType) {
        try {
            if (borrowerType.equals("Student")) { visitLogDao.timeIn(borrowerId, null, LocalDateTime.now()); }
            if (borrowerType.equals("Faculty")) { visitLogDao.timeIn(null, borrowerId, LocalDateTime.now()); }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database connection Error!");
        }
    }

    public void timeOut(int visitId) {
        try {
            visitLogDao.timeOut(visitId, LocalDateTime.now());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database connection Error!");
        }
    }

    public ArrayList<VisitLogDisplay> loadVisitor() {
        try {
            return visitLogDao.loadVisitor();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database connection Error!");
        }
    }
}