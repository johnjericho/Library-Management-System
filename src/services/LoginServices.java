package services;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class LoginServices {

	private UserDAO userDao;
	
	public LoginServices(UserDAO userDao) {
		this.userDao = userDao;
	}
	
    public boolean login(String userName, String password) {
    	try {
        User user = userDao.logIn(userName, password);
        if(user != null) { return true; }
        else { return false; }
        
        
        
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new RuntimeException("Database Connection Problem");
     	}
        
    	
    	
    }
	
}
