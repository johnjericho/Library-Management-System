package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AcquisitionModule.AcquisitionDAO;
import model.AcqusitionModule.AcquisitionDisplay;

public class AcquisitionServices {

	private AcquisitionDAO acquDao;

	public AcquisitionServices(AcquisitionDAO acquDao) {
		this.acquDao = acquDao;
	}


	public String generateNextTransactionNo() {
		try {
			return acquDao.generateNextTransactionNo();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	public void addAcqTransaction(String transactionNo, int selectedContributorId, String contributorType, Date dateReceived) {
		
		Integer supplierId = null;
		Integer donnorId = null;
		if(contributorType.equals("Supplier")) supplierId = selectedContributorId; 
		if(contributorType.equals("Donor")) donnorId = selectedContributorId;
		
		
		java.sql.Date sqlDate = new java.sql.Date(dateReceived.getTime());

		try {
			acquDao.addAcqTransaction(transactionNo, supplierId, donnorId, sqlDate);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error!");
		}
		
	}	
	
	
	public ArrayList<AcquisitionDisplay> loadAcquisition() {
		try {
			return acquDao.loadAcquisition();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}
	
	
	public AcquisitionDisplay getAcquisitionDisplayById(int selectedAcquisitionId) {
		try {
			AcquisitionDisplay acquiDetail = acquDao.getAcquisitionDetailsById(selectedAcquisitionId);
			return acquiDetail;

		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error!");
		}
	}
	
	
		public ArrayList<AcquisitionDisplay> searchAcquisition(String keyword) {
			try {
				return acquDao.searchAcquisition(keyword);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Database Connection error");
			}
		}
}