package controller.AcquisitionModule;

import java.util.ArrayList;
import java.util.Date;

import model.dto.AcquisitionDisplay;
import services.AcquisitionModule.AcquisitionTransactionServices;

public class AcquisitionTransactionController {
	
	private AcquisitionTransactionServices acquisitionTransactionServices;
	
		public AcquisitionTransactionController(AcquisitionTransactionServices acquisitionTransactionServices) {
			this.acquisitionTransactionServices = acquisitionTransactionServices;
		}

		public void addAcqTransaction(String transactionNo, int contributorId, String contributorType, Date dateReceived) {		
			acquisitionTransactionServices.addAcqTransaction(transactionNo, contributorId, contributorType, dateReceived);			
		}
		
		public  ArrayList<AcquisitionDisplay> loadAcqTransaction(){
			return acquisitionTransactionServices.loadAcqTransaction();
		}
}

