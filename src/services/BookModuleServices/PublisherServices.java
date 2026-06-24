package services.BookModuleServices;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookModuleDAO.PublisherDAO;
import model.Publisher;

public class PublisherServices {
	
	private PublisherDAO publisherDao;
	
	public PublisherServices(PublisherDAO publisherDao) {
		this.publisherDao = publisherDao;
	}

	
	public void addPublisher(String publisherName) {
		
    	publisherName = publisherName.trim().replaceAll("\\s+", " ");
    	publisherName = capitalizeWords(publisherName);
    	
	    if(publisherName.isEmpty()) throw new IllegalArgumentException("Input all fields!");
	    if(isExisting(publisherName)) throw new IllegalArgumentException("Publisher already Exist!");
		if(publisherName.length() > 255) throw new IllegalArgumentException("Publsiher is too long! Max 255 characters."); 

	    
	
	try {
	publisherDao.addPublisher(publisherName);
	}catch(SQLException e) {
		throw new RuntimeException("Database Connection Error!");
	}
}


public boolean isExisting(String publisherName) {
	try {
	boolean isExisting = publisherDao.isExisting(publisherName);
	return isExisting;
	}catch(SQLException e) {
		throw new RuntimeException("Database Connection Error!");
	}
}


 private String capitalizeWords(String input) {
	    String[] words = input.split(" ");
	    StringBuilder result = new StringBuilder();
	    for (String word : words) {
	        if (!word.isEmpty()) {
	            result.append(Character.toUpperCase(word.charAt(0))) // capitalize first letter
	                  .append(word.substring(1)) // ✅ preserve na lang yung remaining letters
	                  .append(" ");
	        }
	    }
	    return result.toString().trim();
	}

public ArrayList<Publisher> loadPublisher(){     
	try {
		ArrayList<Publisher> loadPublisher = publisherDao.loadPublisher();
		return loadPublisher;
	}catch(SQLException e) {
		throw new RuntimeException("Database Connection Error");
	}
}


public void updatePublisher(int publisherId, String publisherName) {
	
	publisherName = publisherName.trim().replaceAll("\\s+", " ");
	
	if(publisherId < 0)throw new IllegalArgumentException("Select publisher first!");
	if(publisherName.isEmpty()) throw new IllegalArgumentException("Input all fields!");
    if(isExisting(publisherName)) throw new IllegalArgumentException("Publisher already Exist!");
	if(publisherName.length() > 255) throw new IllegalArgumentException("Publsiher is too long! Max 255 characters."); 

    try {
       publisherDao.updatePublisher(publisherId, publisherName);
    }catch(SQLException e) {
    	throw new RuntimeException("Database Connection Error");
    }
    
}

public void deletePublisher(int publisherId) {
	if(publisherId < 0) {throw new IllegalArgumentException("Select Publisher first!");}
	try {
	publisherDao.deletePublisher(publisherId);
	}catch(SQLException e) {
		throw new RuntimeException("Database Connection Error");
	}
}


public ArrayList<Publisher> searchPublisher(String publisherName){	
	try {
	ArrayList<Publisher> filteredPublisher = publisherDao.searchPublisher(publisherName);
	return filteredPublisher;
	}catch(SQLException e) {
		throw new RuntimeException("Database Connection Error!");
	}
}
	
	
}
