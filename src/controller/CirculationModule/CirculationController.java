package controller.CirculationModule;

import services.CirculationModule.CirculationServices;

public class CirculationController {

	private CirculationServices circulationServices;
	
	public CirculationController( CirculationServices circulationServices) {
		this.circulationServices = circulationServices;
	}
}
