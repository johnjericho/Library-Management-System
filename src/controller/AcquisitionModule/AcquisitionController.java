package controller.AcquisitionModule;

import java.util.ArrayList;
import java.util.Date;

import model.dto.AcquisitionDisplay;
import services.AcquisitionModule.AcquisitionServices;

public class AcquisitionController {

	private AcquisitionServices acquServices;

	public AcquisitionController(AcquisitionServices acquServices) {
		this.acquServices = acquServices;
	}

	public ArrayList<AcquisitionDisplay> loadAcquisition() {
		return acquServices.loadAcquisition();
	}

	public String generateNextTransactionNo() {
		return acquServices.generateNextTransactionNo();
	}

	public void addAcquisition(String transactionNo, int bookId, int supplierId, int donorId,
								int bookPrice, Date bookDateAcquired, int quantity) {
		acquServices.addAcquisition(transactionNo, bookId, supplierId, donorId, bookPrice, bookDateAcquired, quantity);
	}

	public void updateAcquisition(int acquisitionId, int bookId, int supplierId, int donorId,
								   int bookPrice, Date bookDateAcquired) {
		acquServices.updateAcquisition(acquisitionId, bookId, supplierId, donorId, bookPrice, bookDateAcquired);
	}

	public void deleteAcquisition(int acquisitionId) {
		acquServices.deleteAcquisition(acquisitionId);
	}
	
	public ArrayList<AcquisitionDisplay> searchAcquisition(String keyword) {
		return acquServices.searchAcquisition(keyword);
	}
	
}