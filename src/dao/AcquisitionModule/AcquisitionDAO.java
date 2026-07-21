package dao.AcquisitionModule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.AcqusitionModule.AcquisitionDetail;
import model.AcqusitionModule.AcquisitionDisplay;
import utility.DatabaseHelper;

public class AcquisitionDAO {

	
	
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



	public void addAcqTransaction(String transactionNo, Integer supplierId, Integer donorId, Date dateReceived) throws SQLException{
		
		String sql = "INSERT INTO tbl_acquisition "
				+ "(transactionNo, supplierId, DonorId, dateAcquired) "
				+ "VALUES(?, ?, ?, ?)";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		stmt.setString(1, transactionNo);
		stmt.setObject(2, supplierId);
		stmt.setObject(3, donorId);
		stmt.setDate(4, dateReceived);
		
		stmt.executeUpdate();
		}

	}



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
	
	
	public AcquisitionDisplay getAcquisitionDetailsById(int acquisitionId) throws SQLException {
	    String sql = """
	        SELECT 
	            a.acquisitionId,
	            a.transactionNo,
	            a.dateAcquired,
	            COALESCE(s.supplierName, d.donorName) AS contributorName,
	            CASE 
	                WHEN a.supplierId IS NOT NULL THEN 'Supplier'
	                WHEN a.donorId IS NOT NULL THEN 'Donor'
	            END AS type
	        FROM tbl_acquisition a
	        LEFT JOIN tbl_supplier s ON a.supplierId = s.supplierId
	        LEFT JOIN tbl_donor d ON a.donorId = d.donorId
	        WHERE a.acquisitionId = ?
	        """;

	    try (Connection conn = DatabaseHelper.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setInt(1, acquisitionId);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new AcquisitionDisplay(
	                    rs.getInt("acquisitionId"),
	                    rs.getString("transactionNo"),
	                    rs.getString("contributorName"),
	                    rs.getString("type"),
	                    rs.getDate("dateAcquired")
	                );
	            }
	        }
	    }
	    return null;
	}
	
	
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
	
	
	
	
	
	//ACQUISITION DETAIL
	
	// ACQUISITION DETAIL — batch save (uses shared connection for transaction)

	public int saveAcquisitionDetail(Connection conn, AcquisitionDetail detail) throws SQLException {
	    String sql = "INSERT INTO tbl_acquisition_detail (acquisitionId, bookId, quantity, price) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setInt(1, detail.getAcquisitionId());
	        stmt.setInt(2, detail.getBookId());
	        stmt.setInt(3, detail.getQuantity());
	        stmt.setDouble(4, detail.getPrice());
	        stmt.executeUpdate();

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) return rs.getInt(1);
	        }
	    }
	    throw new SQLException("Failed to retrieve generated detailId.");
	}
	
}