package controller.CirculationModule;

import java.util.ArrayList;
import model.dto.VisitLogDisplay;
import services.CirculationModule.VisitLogServices;

public class VisitLogController {

    private VisitLogServices services;

    public VisitLogController(VisitLogServices services) {
        this.services = services;
    }

    public void timeIn(int borrowerId, String borrowerType) {
        services.timeIn(borrowerId, borrowerType);
    }

    public void timeOut(int visitId) {
        services.timeOut(visitId);
    }

    public ArrayList<VisitLogDisplay> loadVisitor() {
        return services.loadVisitor();
    }
}