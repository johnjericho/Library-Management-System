package controller.BookModule;
import java.util.ArrayList;

import model.BookModule.Publisher;
import services.BookModuleServices.PublisherServices;
public class PublisherController {
    
	private PublisherServices publisherServices; //composition Dependency injection
	
	public PublisherController(PublisherServices publisherServices) {
		this.publisherServices = publisherServices;
	}
	
	public void addPublisher(String publisherName) {

		publisherServices.addPublisher(publisherName);	
	}
	
	
	public boolean isExisting(String publisherName) {
		
		boolean isExisting = publisherServices.isExisting(publisherName);
		return isExisting;		
	}
	
	
	
	public ArrayList<Publisher> loadPublisher(){     
	
     	ArrayList<Publisher> loadPublisher = publisherServices.loadPublisher();
		return loadPublisher;
		
	}
	
	
	public void updatePublisher(int publisherId, String publisherName) {
		
		publisherServices.updatePublisher(publisherId, publisherName);    
	}
	
	public void deletePublisher(int publisherId) {	
		
		publisherServices.deletePublisher(publisherId);		
	}
	
    
	public ArrayList<Publisher> searchPublisher(String publisherName){	
		
		ArrayList<Publisher> filteredPublisher = publisherServices.searchPublisher(publisherName);
		return filteredPublisher;
		
	}
	
	
     
	
	
}
