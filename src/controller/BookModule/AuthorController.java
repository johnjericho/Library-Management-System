package controller.BookModule;

import java.util.ArrayList;

import model.BookModule.Author;
import services.BookModuleServices.AuthorServices;
 
public class AuthorController {
      private AuthorServices authorServices;
      
        public AuthorController(AuthorServices authorServices) {
        	this.authorServices = authorServices;
        }
       
        
        public void addAuthor(String authorName) {
        	
    		authorServices.addAuthor(authorName);  		
        }
        
        public boolean isExisting(String authorName) {
        	
        	boolean existing = authorServices.isExisting(authorName); 
            return existing;   
        }
        
        
        
        public ArrayList<Author> loadAuthor() {       
        	return authorServices.loadAuthor();
        }
                
        
        public void editAuthor(int id, String editAuthor) {
       
        	authorServices.updateAuthor(id, editAuthor);
        }
        
        
        public void deleteAuthor(int id) {
        
        	authorServices.deleteAuthor(id);    
       }
        
        
        public ArrayList<Author> searchAuthor(String keyword) {  
            
        	return authorServices.searchAuthor(keyword); // DAO na ang mag-filter
        }
        
        
        
}
