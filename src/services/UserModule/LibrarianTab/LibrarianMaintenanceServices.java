package services.UserModule.LibrarianTab;

import java.sql.SQLException;
import java.util.List;

import dao.UserModule.LibrarianTab.LibrarianMaintenanceDAO;
import model.UserModule.LibrarianTab.Librarian;
import model.dto.LibrarianDisplay;

public class LibrarianMaintenanceServices {

	private LibrarianMaintenanceDAO librarianMaintenanceDao;

	public LibrarianMaintenanceServices(LibrarianMaintenanceDAO librarianMaintenanceDao) {
		this.librarianMaintenanceDao = librarianMaintenanceDao;
	}

	public void addLibrarian(String positionType, String firstName, String lastName, String middleName,
			String address, String contactNo, String email, String password) {

		firstName = firstName.trim().replaceAll("\\s+", " ");
		lastName = lastName.trim().replaceAll("\\s+", " ");
		middleName = middleName == null ? "" : middleName.trim().replaceAll("\\s+", " ");
		contactNo = contactNo.trim();
		email = email.trim();
		address = address.trim();

		if (positionType == null || positionType.isEmpty()) { throw new IllegalArgumentException("Position type must not be empty!"); }
		if (firstName.isEmpty())  { throw new IllegalArgumentException("First name must not be empty!"); }
		if (lastName.isEmpty())   { throw new IllegalArgumentException("Last name must not be empty!"); }
		if (email.isEmpty())      { throw new IllegalArgumentException("Email must not be empty!"); }
		if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) { throw new IllegalArgumentException("Invalid email format!"); }
		if (password == null || password.isEmpty()) { throw new IllegalArgumentException("Password must not be empty!"); }
		if (firstName.length() > 255) { throw new IllegalArgumentException("First name is too long!"); }
		if (lastName.length() > 100)  { throw new IllegalArgumentException("Last name is too long!"); }

		try {
			librarianMaintenanceDao.addLibrarian(positionType, firstName, lastName, middleName, address, contactNo, email, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection problem");
		}
	}

	public void updateLibrarian(int librarianId, String positionType, String firstName, String lastName,
			String middleName, String contactNo, String email, String address, String password) {

		firstName = firstName.trim().replaceAll("\\s+", " ");
		lastName = lastName.trim().replaceAll("\\s+", " ");
		middleName = middleName == null ? "" : middleName.trim().replaceAll("\\s+", " ");
		contactNo = contactNo.trim();
		email = email.trim();
		address = address.trim();

		if (positionType == null || positionType.isEmpty()) { throw new IllegalArgumentException("Position type must not be empty!"); }
		if (firstName.isEmpty())  { throw new IllegalArgumentException("First name must not be empty!"); }
		if (lastName.isEmpty())   { throw new IllegalArgumentException("Last name must not be empty!"); }
		if (email.isEmpty())      { throw new IllegalArgumentException("Email must not be empty!"); }
		if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) { throw new IllegalArgumentException("Invalid email format!"); }
		if (firstName.length() > 255) { throw new IllegalArgumentException("First name is too long!"); }
		if (lastName.length() > 100)  { throw new IllegalArgumentException("Last name is too long!"); }

		try {
			librarianMaintenanceDao.updateLibrarian(librarianId, positionType, firstName, lastName, middleName, contactNo, email, address, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection problem");
		}
	}

	public void deleteLibrarian(int librarianId) {
		if (librarianId < 0) throw new IllegalArgumentException("Select row first!");

		try {
			librarianMaintenanceDao.deleteLibrarian(librarianId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database connection Error");
		}
	}

	public List<LibrarianDisplay> getAllLibrarians() {
		try {
			return librarianMaintenanceDao.getAllLibrarians();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error");
		}
	}

	public LibrarianDisplay getLibrarianById(int librarianId) {
		try {
			return librarianMaintenanceDao.getLibrarianById(librarianId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error");
		}
	}

	public List<LibrarianDisplay> searchLibrarians(String keyword) {
		try {
			return librarianMaintenanceDao.searchLibrarians(keyword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error!");
		}
	}

	public Librarian getLibrarianByEmail(String email) {
		try {
			return librarianMaintenanceDao.getLibrarianByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection Error");
		}
	}
}