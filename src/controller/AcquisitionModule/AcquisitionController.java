package controller.AcquisitionModule;

import java.util.ArrayList;
import java.util.Date;

import model.AcqusitionModule.Acquisition;
import model.AcqusitionModule.AcquisitionDetail;
import model.AcqusitionModule.AcquisitionDisplay;
import services.AcquisitionModule.AcquisitionServices;

public class AcquisitionController {

	private AcquisitionServices acquServices;

	public AcquisitionController(AcquisitionServices acquServices) {
		this.acquServices = acquServices;
	}

	public String generateNextTransactionNo() {
		return acquServices.generateNextTransactionNo();
	}
	
	public void addAcqTransaction(String transactionNo, int contributorId, String contributorType, Date dateReceived) {		
		acquServices.addAcqTransaction(transactionNo, contributorId, contributorType, dateReceived);			
	}
	
	public ArrayList<AcquisitionDisplay> loadAcquisition() {
		return acquServices.loadAcquisition();
	}
	
	public AcquisitionDisplay getTransactionDetailsById(int selectedAcquisitionId) {
		return acquServices.getAcquisitionDisplayById(selectedAcquisitionId);
	}

	public ArrayList<AcquisitionDisplay> searchAcquisition(String keyword) {
		return acquServices.searchAcquisition(keyword);
	}
	
	
	//ACQUISITION DETAIL
	
	public void saveAcquisitionWithInventory(ArrayList<AcquisitionDetail> bookDetails) {
	    acquServices.saveAcquisitionWithInventory(bookDetails);
	}

	
	
}