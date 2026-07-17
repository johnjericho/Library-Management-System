package dao.AcquisitionModule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AcquisitionDisplay;
import utility.DatabaseHelper;

public class AcquisitionDAO {

	// ---------- GENERATE NEXT TRANSACTION NO ----------
	public String generateNextTransactionNo() throws SQLException {

		String sql = "SELECT MAX(transactionNo) AS maxTxn FROM tbl_acquisition";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		) {
			int nextNumber = 1;

			if (rs.next() && rs.getString("maxTxn") != null) {
				String lastTxn = rs.getString("maxTxn");
				String digitsOnly = lastTxn.replaceAll("[^0-9]", "");
				if (!digitsOnly.isEmpty()) {
					nextNumber = Integer.parseInt(digitsOnly) + 1;
				}
			}
			return String.format("TRX - %04d", nextNumber);
		}
	}


	// ---------- GENERATE NEXT ACCESSION NO ----------
	public String generateNextAccessionNo() throws SQLException {

		String sql = "SELECT MAX(AccessionNo) AS maxAcc FROM tbl_acquisition";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		) {
			int nextNumber = 1;

			if (rs.next() && rs.getString("maxAcc") != null) {
				String lastAcc = rs.getString("maxAcc");
				String digitsOnly = lastAcc.replaceAll("[^0-9]", "");
				if (!digitsOnly.isEmpty()) {
					nextNumber = Integer.parseInt(digitsOnly) + 1;
				}
			}

			return String.format("ACC-%04d", nextNumber);
		}
	}


	// ---------- ADD (isang row lang; ang pag-loop ay trabaho ng Service) ----------
	public void addAcquisition(String transactionNo, String accessionNo, int bookId,
								int supplierId, int donorId, int bookPrice,
								Date bookDateAcquired) throws SQLException {

		String sql = "INSERT INTO tbl_acquisition "
					+ "(transactionNo, AccessionNo, bookId, supplierId, DonorId, bookPrice, dateAcquired) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, transactionNo);
			stmt.setString(2, accessionNo);
			stmt.setInt(3, bookId);

			if (supplierId > 0) stmt.setInt(4, supplierId); else stmt.setNull(4, java.sql.Types.INTEGER);
			if (donorId > 0) stmt.setInt(5, donorId); else stmt.setNull(5, java.sql.Types.INTEGER);

			stmt.setInt(6, bookPrice);
			stmt.setDate(7, bookDateAcquired);

			stmt.executeUpdate();
		}
	}


	// ---------- UPDATE ----------
	public void updateAcquisition(int acquisitionId, int bookId, int supplierId,
								   int donorId, int bookPrice,
								   Date bookDateAcquired) throws SQLException {

		String sql = "UPDATE tbl_acquisition SET "
					+ "bookId = ?, supplierId = ?, DonorId = ?, bookPrice = ?, dateAcquired = ? "
					+ "WHERE acquisitionId = ?";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setInt(1, bookId);
			if (supplierId > 0) stmt.setInt(2, supplierId); else stmt.setNull(2, java.sql.Types.INTEGER);
			if (donorId > 0) stmt.setInt(3, donorId); else stmt.setNull(3, java.sql.Types.INTEGER);
			stmt.setInt(4, bookPrice);
			stmt.setDate(5, bookDateAcquired);
			stmt.setInt(6, acquisitionId);

			stmt.executeUpdate();
		}
	}


	// ---------- DELETE ----------
	public void deleteAcquisition(int acquisitionId) throws SQLException {

		String sql = "DELETE FROM tbl_acquisition WHERE acquisitionId = ?";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setInt(1, acquisitionId);
			stmt.executeUpdate();
		}
	}


	// ---------- LOAD ----------
	public ArrayList<AcquisitionDisplay> loadAcquisition() throws SQLException {

		ArrayList<AcquisitionDisplay> loadAcqu = new ArrayList<>();

		String sql = "SELECT a.acquisitionId, a.transactionNo, a.supplierId, a.donorId , a.dateAcquired, "
				    + "s.supplierName, d.donorName "
					+ "FROM tbl_acquisition a "
					+ "LEFT JOIN tbl_Supplier s ON a.supplierId = s.supplierId "
					+ "LEFT JOIN tbl_donor d ON a.donorId = d.donorId "
					+ "ORDER BY a.transactionNo ASC";

		try (
			Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		) {
			while (rs.next()) {
				AcquisitionDisplay acquiDisplay = new AcquisitionDisplay();
				acquiDisplay.setAcquisitionId(rs.getInt("acquisitionId"));
				acquiDisplay.setTransactionNo(rs.getString("transactionNo"));
				
				rs.getInt("supplierId");
				if (!rs.wasNull()) {
					acquiDisplay.setContributorType("Supplier");
					acquiDisplay.setContributorName(rs.getString("supplierName"));
				} else {
					rs.getInt("DonorId");
					if (!rs.wasNull()) {
						acquiDisplay.setContributorType("Donor");
						acquiDisplay.setContributorName(rs.getString("donorName"));
					}
				}

				acquiDisplay.setDateAcquired(rs.getDate("dateAcquired"));

				loadAcqu.add(acquiDisplay);
			}
			return loadAcqu;
		}
	}
	
	
	// ---------- SEARCH ----------
	public ArrayList<AcquisitionDisplay> searchAcquisition(String keyword) throws SQLException {

	    ArrayList<AcquisitionDisplay> searchAcqu = new ArrayList<>();

	    String sql =
	            "SELECT a.acquisitionId, a.transactionNo, a.dateAcquired, " +
	            "a.supplierId, s.supplierName, " +
	            "a.donorId, d.donorName " +
	            "FROM tbl_acquisition a " +
	            "LEFT JOIN tbl_supplier s ON a.supplierId = s.supplierId " +
	            "LEFT JOIN tbl_donor d ON a.donorId = d.donorId " +
	            "WHERE a.transactionNo LIKE ? " +
	            "OR s.supplierName LIKE ? " +
	            "OR d.donorName LIKE ? " +
	            "ORDER BY a.acquisitionId DESC";

	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {

	        String likeKeyword = "%" + keyword + "%";

	        stmt.setString(1, likeKeyword);
	        stmt.setString(2, likeKeyword);
	        stmt.setString(3, likeKeyword);

	        try (ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {

	                AcquisitionDisplay display = new AcquisitionDisplay();

	                display.setAcquisitionId(rs.getInt("acquisitionId"));
	                display.setTransactionNo(rs.getString("transactionNo"));
	                display.setDateAcquired(rs.getDate("dateAcquired"));

	                if (rs.getObject("supplierId") != null) {
	                    display.setContributorType("Supplier");
	                    display.setContributorName(rs.getString("supplierName"));
	                } else if (rs.getObject("donorId") != null) {
	                    display.setContributorType("Donor");
	                    display.setContributorName(rs.getString("donorName"));
	                }

	                searchAcqu.add(display);
	            }
	        }
	    }

	    return searchAcqu;
	}
	
}