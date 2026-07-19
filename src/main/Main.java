package main;

import java.awt.EventQueue;

import controller.LoginController;
import controller.AcquisitionModule.AcquisitionController;
import controller.AcquisitionModule.DeadLayer;
import controller.AcquisitionModule.AcqusitionDetailController;
import controller.AcquisitionModule.DonorController;
import controller.AcquisitionModule.SupplierController;
import controller.BookModule.AuthorController;
import controller.BookModule.BookMaintenanceController;
import controller.BookModule.CategoryController;
import controller.BookModule.PublisherController;
import controller.CirculationModule.CirculationController;
import controller.CirculationModule.VisitLogController;
import controller.UserModule.FacultyTab.FacultyMaintenanceController;
import controller.UserModule.LibrarianTab.LibrarianMaintenanceController;
import controller.UserModule.StudentTab.DepartmentController;
import controller.UserModule.StudentTab.GradeController;
import controller.UserModule.StudentTab.SectionController;
import controller.UserModule.StudentTab.StudentMaintenanceController;
import dao.UserDAO;
import dao.AcquisitionModule.AcquisitionDAO;
import dao.AcquisitionModule.AcquisitionTransactionDAO;
import dao.AcquisitionModule.AcqusitionDetailDAO;
import dao.AcquisitionModule.DonorDAO;
import dao.AcquisitionModule.SupplierDAO;
import dao.BookModule.AuthorDAO;
import dao.BookModule.BookMaintenanceDAO;
import dao.BookModule.CategoryDAO;
import dao.BookModule.PublisherDAO;
import dao.CirculationModule.CirculationDAO;
import dao.CirculationModule.VisitLogDAO;
import dao.UserModule.FacultyTab.FacultyMaintenanceDAO;
import dao.UserModule.LibrarianTab.LibrarianMaintenanceDAO;
import dao.UserModule.StudentTab.DepartmentDAO;
import dao.UserModule.StudentTab.GradeDAO;
import dao.UserModule.StudentTab.SectionDAO;
import dao.UserModule.StudentTab.StudentMaintenanceDAO;
import services.LoginServices;
import services.AcquisitionModule.AcquisitionServices;
import services.AcquisitionModule.AcquisitionTransactionServices;
import services.AcquisitionModule.AcqusitionDetailServices;
import services.AcquisitionModule.DonorServices;
import services.AcquisitionModule.SupplierServices;
import services.BookModule.AuthorServices;
import services.BookModule.BookMaintenanceServices;
import services.BookModule.CategoryServices;
import services.BookModule.PublisherServices;
import services.CirculationModule.CirculationServices;
import services.CirculationModule.VisitLogServices;
import services.UserModule.FacultyTab.FacultyMaintenanceServices;
import services.UserModule.LibrarianTab.LibrarianMaintenanceServices;
import services.UserModule.StudentTab.DepartmentServices;
import services.UserModule.StudentTab.GradeServices;
import services.UserModule.StudentTab.SectionServices;
import services.UserModule.StudentTab.StudentMaintenanceServices;
import view.LogIn;
import utility.AppContext;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
			    AppContext ctx = AppContext.getInstance();
			    ctx.setLoginController(new LoginController(new LoginServices(new UserDAO())));
			    ctx.setAuthorController(new AuthorController(new AuthorServices(new AuthorDAO())));
			    ctx.setPublisherController(new PublisherController(new PublisherServices(new PublisherDAO()))); 
			    ctx.setCategoryController(new CategoryController(new CategoryServices(new CategoryDAO())));	
			    ctx.setSupplierController(new SupplierController(new SupplierServices(new SupplierDAO())));
			    ctx.setDonorController(new DonorController(new DonorServices(new DonorDAO())));
			    ctx.setBookMaintenanceController(new BookMaintenanceController(new BookMaintenanceServices(new BookMaintenanceDAO())));
				ctx.setAcquisitionController(new AcquisitionController(new AcquisitionServices(new AcquisitionDAO())));
				ctx.setDepartmentController(new DepartmentController(new DepartmentServices(new DepartmentDAO())));
				ctx.setGradeController(new GradeController(new GradeServices(new GradeDAO())));
				ctx.setSectionController(new SectionController(new SectionServices(new SectionDAO())));
				ctx.setStudentMaintenanceController(new StudentMaintenanceController(new StudentMaintenanceServices(new StudentMaintenanceDAO())));
				ctx.setFacultyMaintenanceController(new FacultyMaintenanceController(new FacultyMaintenanceServices(new FacultyMaintenanceDAO())));
				ctx.setLibrarianMaintenanceController(new LibrarianMaintenanceController(new LibrarianMaintenanceServices(new LibrarianMaintenanceDAO())));
				ctx.setCirculationController(new CirculationController(new CirculationServices(new CirculationDAO())));
				ctx.setVisitLogController(new VisitLogController(new VisitLogServices(new VisitLogDAO())));
				ctx.setAcquisitionTransactionController(new DeadLayer(new AcquisitionTransactionServices(new AcquisitionTransactionDAO())));
				ctx.setAcqusitionDetailController(new AcqusitionDetailController(new AcqusitionDetailServices(new AcqusitionDetailDAO())));						 

				LogIn login = new LogIn();
				login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

