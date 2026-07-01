package services.BookModuleServices;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookModuleDAO.AuthorDAO;
import model.BookModule.Author;

public class AuthorServices {
	
	 private AuthorDAO authorDao;
	 
	public AuthorServices(AuthorDAO authorDao) {
		this.authorDao = authorDao;
	}

	
    public void addAuthor(String authorName) {
    	authorName = authorName.trim().replaceAll("\\s+", " ");
    	authorName = capitalizeWords(authorName);   
    	
		if(authorName.isEmpty()) {
	        throw new IllegalArgumentException("Please input all fields");  }// ← throw, hindi JOptionPane		
		if(isExisting(authorName)){
			throw new IllegalArgumentException("Author Already Exist");  }  
		if(authorName.length() > 255) { 
			throw new IllegalArgumentException("Author is too long! Max 255 characters."); }
		try {		
		    	    authorDao.addAuthor(authorName);
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error");
		}
    }
    
    
    public boolean isExisting(String authorName) {
    try {	
    	boolean existing = authorDao.isExisting(authorName); 
        return existing;
    }catch(SQLException e) {
    	throw new RuntimeException("Database Connection Error");
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
    
     
    
    public ArrayList<Author> loadAuthor() {
    try {	
    	return authorDao.loadAuthor();
    }catch(SQLException e) {
    	throw new RuntimeException("Failed to load author");
       }
    }
            
    
    public void updateAuthor(int id, String authorName) {
    	authorName = authorName.trim().replaceAll("\\s+", " ");
    	authorName = capitalizeWords(authorName);
   	
     if(id < 0) throw new IllegalArgumentException("Select author first!"); 			   
	 if(authorName.isEmpty()) throw new IllegalArgumentException("Please input all fieldss"); // ← throw, hindi JOptionPane
     if(isExisting(authorName)) throw new IllegalArgumentException("Author Already Exist"); 				
     if(authorName.length() > 255) throw new IllegalArgumentException("Author is too long! Max 255 characters."); 
     try {	
    	authorDao.editAuthor(id, authorName);
   }catch(SQLException e) {
	    throw new RuntimeException("Database Error");
   }
   
}
    
    
    public void deleteAuthor(int id) {
    if(id == -1) {
    throw new IllegalArgumentException("Select Author First"); }
  try {
    	authorDao.deleteAuthor(id);
  }catch(SQLException e) {
	      throw new RuntimeException("Database Error");
     }      
   }
    
    
    public ArrayList<Author> searchAuthor(String keyword) {
        try {  
            return authorDao.searchAuthor(keyword); // DAO na ang mag-filter
        } catch (SQLException e) {
            throw new RuntimeException("Database Error");
        }
    }
    
    
	
    
    
    
}
