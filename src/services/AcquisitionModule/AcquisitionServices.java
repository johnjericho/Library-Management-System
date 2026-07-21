package services.AcquisitionModule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AcquisitionModule.AcquisitionDAO;
import dao.AcquisitionModule.InventoryDAO;
import model.AcqusitionModule.AcquisitionDetail;
import model.AcqusitionModule.AcquisitionDisplay;
import utility.DatabaseHelper;

public class AcquisitionServices {

	private AcquisitionDAO acquDao;
	private InventoryDAO inventoryDao;

	public AcquisitionServices(AcquisitionDAO acquDao,  InventoryDAO inventoryDao) {
		this.acquDao = acquDao;
		this.inventoryDao = inventoryDao;
	}


	public String generateNextTransactionNo() {
		try {
			return acquDao.generateNextTransactionNo();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	//TRANSACTION
	public void addAcqTransaction(String transactionNo, int selectedContributorId, String contributorType, Date dateReceived) {
		
		if(contributorType.isEmpty()) throw new IllegalArgumentException("Select Cotributor!");
		if(dateReceived == null) throw new IllegalArgumentException("Select Date Received!");
		
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
		
		
		
		
		//ACQUISITION DETAIL > INVENTORY
		
		// ACQUISITION DETAIL — batch save with inventory generation

		public void saveAcquisitionWithInventory(ArrayList<AcquisitionDetail> bookDetails) {

		    if (bookDetails == null || bookDetails.isEmpty())  throw new IllegalArgumentException("No Currently Selected Book.");
		    
		    for (AcquisitionDetail detail : bookDetails) {
		    	
		        if (detail.getQuantity() <= 0)   throw new IllegalArgumentException("Cannot Add Empty Quantity.");
		        
		        if (detail.getPrice() < 0) throw new IllegalArgumentException("Price cannot be negative.");
		        
		    }

		    Connection conn = null;
		    try {
		        conn = DatabaseHelper.getConnection();
		        conn.setAutoCommit(false);

		        for (AcquisitionDetail detail : bookDetails) {
		            int detailId = acquDao.saveAcquisitionDetail(conn, detail);

		            for (int i = 0; i < detail.getQuantity(); i++) {
		                String accessionNo = inventoryDao.generateNextAccessionNo(conn);
		                inventoryDao.saveInventoryItem(conn, detailId, accessionNo);
		            }
		        }

		        conn.commit();

		    } catch (SQLException e) {
		        if (conn != null) {
		            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
		        }
		        e.printStackTrace();
		        throw new RuntimeException("Database Connection Error!");

		    } finally {
		        if (conn != null) {
		            try {
		                conn.setAutoCommit(true);
		                conn.close();
		            } catch (SQLException ex) { ex.printStackTrace(); }
		        }
		    }
		}
	
		
		
		
}