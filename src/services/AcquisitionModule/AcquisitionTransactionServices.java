package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AcquisitionModule.AcquisitionTransactionDAO;
import model.dto.AcquisitionDisplay;

public class AcquisitionTransactionServices {
	
	private AcquisitionTransactionDAO acquisitionTransactionDao;
	
	public AcquisitionTransactionServices(AcquisitionTransactionDAO acquisitionTransactionDao) {
		this.acquisitionTransactionDao = acquisitionTransactionDao;
	}
	
	public void addAcqTransaction(String transactionNo, int selectedContributorId, String contributorType, Date dateReceived) {
		
		Integer supplierId = null;
		Integer donnorId = null;
		if(contributorType.equals("Supplier")) supplierId = selectedContributorId; 
		if(contributorType.equals("Donor")) donnorId = selectedContributorId;
		
		
		java.sql.Date sqlDate = new java.sql.Date(dateReceived.getTime());

		try {
		acquisitionTransactionDao.addAcqTransaction(transactionNo, supplierId, donnorId, sqlDate);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error!");
		}
		
	}
	
	
	
	public ArrayList<AcquisitionDisplay> loadAcqTransaction() {
		
		try {
		ArrayList<AcquisitionDisplay> transaction = acquisitionTransactionDao.loadAcqTransaction();
		return transaction;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error!");
		}
		
	}
	
	
	

}
