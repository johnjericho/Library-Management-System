package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AcquisitionModule.AcquisitionDAO;
import model.dto.AcquisitionDisplay;

public class AcquisitionServices {

	private AcquisitionDAO acquDao;

	public AcquisitionServices(AcquisitionDAO acquDao) {
		this.acquDao = acquDao;
	}

	public ArrayList<AcquisitionDisplay> loadAcquisition() {
		try {
			return acquDao.loadAcquisition();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	public String generateNextTransactionNo() {
		try {
			return acquDao.generateNextTransactionNo();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	// ---------- ADD ----------
	public void addAcquisition(String transactionNo,
								int bookId,
								int supplierId,
								int donorId,
								int bookPrice,
								Date bookDateAcquired,
								int quantity) {

		if (transactionNo == null || transactionNo.trim().isEmpty()) {
			throw new IllegalArgumentException("Transaction number must not be empty!");
		}
		if (bookId < 0) {
			throw new IllegalArgumentException("Book title must not be empty!");
		}
		if (supplierId < 0 && donorId < 0) {
			throw new IllegalArgumentException("Contributor must not be empty!");
		}
		if (bookPrice < 0) {
			throw new IllegalArgumentException("Price must not be negative!");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be at least 1!");
		}
		if (bookDateAcquired == null) {
			throw new IllegalArgumentException("Date received must not be empty!");
		}
		if (bookDateAcquired.after(new Date())) {
			throw new IllegalArgumentException("Date received must be from past -> present!");
		}

		java.sql.Date sqlDate = new java.sql.Date(bookDateAcquired.getTime());

		try {
			// isang transactionNo, maramihang AccessionNo (isa bawat kopya)
			for (int i = 0; i < quantity; i++) {
				String nextAccessionNo = acquDao.generateNextAccessionNo();
				acquDao.addAcquisition(transactionNo, nextAccessionNo, bookId, supplierId, donorId, bookPrice, sqlDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	// ---------- UPDATE ----------
	public void updateAcquisition(int acquisitionId,
								   int bookId,
								   int supplierId,
								   int donorId,
								   int bookPrice,
								   Date bookDateAcquired) {

		if (acquisitionId < 0) {
			throw new IllegalArgumentException("Select row first!");
		}
		if (bookId < 0) {
			throw new IllegalArgumentException("Book title must not be empty!");
		}
		if (supplierId < 0 && donorId < 0) {
			throw new IllegalArgumentException("Contributor must not be empty!");
		}
		if (bookPrice < 0) {
			throw new IllegalArgumentException("Price must not be negative!");
		}
		if (bookDateAcquired == null) {
			throw new IllegalArgumentException("Date received must not be empty!");
		}
		if (bookDateAcquired.after(new Date())) {
			throw new IllegalArgumentException("Date received must be from past -> present!");
		}

		java.sql.Date sqlDate = new java.sql.Date(bookDateAcquired.getTime());

		try {
			acquDao.updateAcquisition(acquisitionId, bookId, supplierId, donorId, bookPrice, sqlDate);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}

	// ---------- DELETE ----------
	public void deleteAcquisition(int acquisitionId) {
		if (acquisitionId < 0) {
			throw new IllegalArgumentException("Select row first!");
		}
		try {
			acquDao.deleteAcquisition(acquisitionId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error");
		}
	}
	
	
	// ---------- SEARCH ----------
		public ArrayList<AcquisitionDisplay> searchAcquisition(String keyword) {
			try {
				return acquDao.searchAcquisition(keyword);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Database Connection error");
			}
		}
}