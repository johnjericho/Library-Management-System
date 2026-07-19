package services.AcquisitionModule;

import dao.AcquisitionModule.AcqusitionDetailDAO;
import model.AcqusitionModule.AcquisitionTransactionDisplay;

public class AcqusitionDetailServices {
	private AcqusitionDetailDAO acqusitionDetailDao;
	
	public AcqusitionDetailServices(AcqusitionDetailDAO acqusitionDetailDao) {
		this.acqusitionDetailDao = acqusitionDetailDao;
	}
	

}
