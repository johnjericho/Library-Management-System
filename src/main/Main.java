package main;

import java.awt.EventQueue;

import controller.BookModule.AuthorController;
import controller.BookModule.BookMaintenanceController;
import controller.BookModule.CategoryController;
import controller.BookModule.DonorController;
import controller.BookModule.PublisherController;
import controller.BookModule.SupplierController;
import dao.BookModuleDAO.AuthorDAO;
import dao.BookModuleDAO.BookMaintenanceDAO;
import dao.BookModuleDAO.CategoryDAO;
import dao.BookModuleDAO.DonorDAO;
import dao.BookModuleDAO.PublisherDAO;
import dao.BookModuleDAO.SupplierDAO;
import services.BookModuleServices.AuthorServices;
import services.BookModuleServices.BookMaintenanceServices;
import services.BookModuleServices.CategoryServices;
import services.BookModuleServices.DonorServices;
import services.BookModuleServices.PublisherServices;
import services.BookModuleServices.SupplierServices;
import view.LogIn;
import utility.AppContext;

public class Main {

// learn App context, servie locator

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//				AuthorDAO authorDao = new AuthorDAO();	
//				AuthorController authorController = new AuthorController(authorDao);	
//				
// 				PublisherDAO publisherDao = new PublisherDAO();
//				PublisherController publisherController = new PublisherController(publisherDao);
//				
//				CategoryDAO categoryDao = new CategoryDAO();
//				CategoryController categoryController = new CategoryController(categoryDao);
					 
			    AppContext ctx = AppContext.getInstance();
			    ctx.setAuthorController(new AuthorController(new AuthorServices(new AuthorDAO())));
			    ctx.setPublisherController(new PublisherController(new PublisherServices(new PublisherDAO()))); 
			    ctx.setCategoryController(new CategoryController(new CategoryServices(new CategoryDAO())));	
			    ctx.setSupplierController(new SupplierController(new SupplierServices(new SupplierDAO())));
			    ctx.setDonorController(new DonorController(new DonorServices(new DonorDAO())));
			    ctx.setBookMaintenanceController(new BookMaintenanceController(new BookMaintenanceServices(new BookMaintenanceDAO())));
				
				
				LogIn login = new LogIn();
				login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

//no dependecy in AuthorDAO/Controller , for educational purpose only
