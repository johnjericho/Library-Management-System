package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AcquisitionModule.AcquisitionTransactionDAO;

public class AcquisitionTransactionServices {
	
	private AcquisitionTransactionDAO acquisitionTransactionDao;
	
	public AcquisitionTransactionServices(AcquisitionTransactionDAO acquisitionTransactionDao) {
		this.acquisitionTransactionDao = acquisitionTransactionDao;
	}
	
	

}
