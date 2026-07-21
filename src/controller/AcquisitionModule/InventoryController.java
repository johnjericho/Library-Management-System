package controller.AcquisitionModule;

import java.util.ArrayList;

import model.AcqusitionModule.InventoryDisplay;
import services.AcquisitionModule.InventoryServices;

public class InventoryController {
	
	private InventoryServices inventoryServices;
	
	public InventoryController(InventoryServices inventoryServices) {
		this.inventoryServices = inventoryServices;
	}

	public ArrayList<InventoryDisplay> loadInventoryDisplay(){	
		return inventoryServices.loadInventoryDisplay();
	}
	
	public ArrayList<InventoryDisplay> searchInventory(String keyword){
		return inventoryServices.searchInventory(keyword);
	}
	
}
