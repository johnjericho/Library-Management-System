package controller;


import services.LoginServices;

public class LoginController {

    private LoginServices loginServices;

    public LoginController(LoginServices loginServices) {
      this.loginServices = loginServices;
    }
 
    public boolean login(String userName, String passWord) {
    	
    	return loginServices.login(userName, passWord);
    }

}