package services.CirculationModule;

import dao.CirculationModule.CirculationDAO;

public class CirculationServices {

	private CirculationDAO circulationDao;
	
	public CirculationServices(CirculationDAO circulationDao) {
		this.circulationDao = circulationDao;
	}
}
