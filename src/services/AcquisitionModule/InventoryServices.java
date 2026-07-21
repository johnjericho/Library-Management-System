package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AcquisitionModule.InventoryDAO;
import model.AcqusitionModule.InventoryDisplay;

public class InventoryServices {
	
	private InventoryDAO inventoryDao;
	
	public InventoryServices(InventoryDAO inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	public ArrayList<InventoryDisplay> loadInventoryDisplay(){
		
		try {
		 ArrayList<InventoryDisplay> inventoryList = inventoryDao.loadInventoryDisplay();
		 return inventoryList;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
		
	}
	
	
	 public ArrayList<InventoryDisplay> searchInventory(String keyword){
		 try {
		 ArrayList<InventoryDisplay> result = inventoryDao.searchInventory(keyword);
		 return result;
		 }catch(SQLException e) {
			 e.printStackTrace();
				throw new RuntimeException("Database Connection error");

		 }
	 }

	 
	 
}
