package dao.AcquisitionModule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AcquisitionDisplay;
import utility.DatabaseHelper;

public class AcquisitionTransactionDAO {


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
	
	
	
	
	public ArrayList<AcquisitionDisplay> loadAcqTransaction() throws SQLException {

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
	
}
