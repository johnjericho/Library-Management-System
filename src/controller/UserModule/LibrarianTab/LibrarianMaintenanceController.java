package controller.UserModule.LibrarianTab;

import java.util.List;

import model.UserModule.LibrarianTab.Librarian;
import model.dto.LibrarianDisplay;
import services.UserModule.LibrarianTab.LibrarianMaintenanceServices;

public class LibrarianMaintenanceController {

	private LibrarianMaintenanceServices librarianMaintenanceServices;

	public LibrarianMaintenanceController(LibrarianMaintenanceServices librarianMaintenanceServices) {
		this.librarianMaintenanceServices = librarianMaintenanceServices;
	}

	public void addLibrarian(String positionType, String firstName, String lastName, String middleName,
			String address, String contactNo, String email, String password) {
		
				
		librarianMaintenanceServices.addLibrarian(positionType, firstName, lastName, middleName, address, contactNo, email, password);
	}

	public void updateLibrarian(int librarianId, String positionType, String firstName, String lastName,
			String middleName, String contactNo, String email, String address, String password) {
		librarianMaintenanceServices.updateLibrarian(librarianId, positionType, firstName, lastName, middleName, contactNo, email, address, password);
	}

	public void deleteLibrarian(int librarianId) {
		librarianMaintenanceServices.deleteLibrarian(librarianId);
	}

	public List<LibrarianDisplay> getAllLibrarians() {
		return librarianMaintenanceServices.getAllLibrarians();
	}

	public LibrarianDisplay getLibrarianById(int librarianId) {
		return librarianMaintenanceServices.getLibrarianById(librarianId);
	}

	public List<LibrarianDisplay> searchLibrarians(String keyword) {
		return librarianMaintenanceServices.searchLibrarians(keyword);
	}

	public Librarian getLibrarianByEmail(String email) {
		return librarianMaintenanceServices.getLibrarianByEmail(email);
	}
}