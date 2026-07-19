package controller.AcquisitionModule;

import services.AcquisitionModule.AcqusitionDetailServices;
import utility.AppContext;

public class AcqusitionDetailController {
	
	private AcqusitionDetailServices acqusitionDetailServices;
	
	private AcquisitionController acquisitionController = AppContext.getInstance().getAcquisitionController();
	
	public AcqusitionDetailController(AcqusitionDetailServices acqusitionDetailServices) {
		this.acqusitionDetailServices = acqusitionDetailServices;
	}
	


}
