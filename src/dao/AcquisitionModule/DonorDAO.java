package dao.AcquisitionModule;

import utility.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;

import model.BookModule.Donor;

public class DonorDAO {

    // CREATE / INSERT
    public void addDonor(String donorName, String contactPer, String donorNo, String donorAddress) throws SQLException {

        String sql = "INSERT INTO tbl_donor "
                   + "(donorName, donorContactPer, donorNo, donorAddress ) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donorName);
            stmt.setString(2, contactPer);
            stmt.setString(3, donorNo);
            stmt.setString(4, donorAddress);



            stmt.executeUpdate();
        }
    }

	public boolean isExisting(String DonorName, String DonorNo) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_Donor "
				+ "WHERE LOWER(DonorName) = LOWER(?) "
				+ "AND donorNo = ?";
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql))
	{
	    stmt.setString(1, DonorName);	
	    stmt.setString(2, DonorNo);	
	    
       try( ResultSet rs = stmt.executeQuery())
       {
		
		if(rs.next()) {
			return rs.getInt(1) > 0;
		}
       }
	 }	
	return false;						
	} 
    
    // READ / LOAD ALL
    public ArrayList<Donor> loadDonor() throws SQLException {

        ArrayList<Donor> donors = new ArrayList<>();

        String sql = "SELECT * FROM tbl_donor";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Donor donor = new Donor();

                donor.setDonorId(rs.getInt("donorId"));
                donor.setDonorName(rs.getString("donorName"));
                donor.setDonorContactPerson(rs.getString("donorContactper"));
                donor.setDonorNumber(rs.getString("donorNo"));
                donor.setDonorAddress(rs.getString("donorAddress"));


                donors.add(donor);
            }
        }

        return donors;
    }

    // UPDATE
    public void updateDonor(int donorId, String donorName, String donorNumber) throws SQLException {

        String sql = "UPDATE tbl_donor "
                   + "SET donorName = ?, donorNo = ? "
                   + "WHERE donorId = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donorName);
            stmt.setString(2, donorNumber);
            stmt.setInt(3, donorId);

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteDonor(int donorId) throws SQLException {

        String sql = "DELETE FROM tbl_donor WHERE donorId = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, donorId);

            stmt.executeUpdate();
        }
    }

    // SEARCH
    public ArrayList<Donor> searchDonor(String keyword) throws SQLException {

        ArrayList<Donor> donors = new ArrayList<>();

        String sql = "SELECT * FROM tbl_donor "
                   + "WHERE LOWER(donorName) LIKE LOWER(?) "
                   + "OR donorNo LIKE ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Donor donor = new Donor(
                            rs.getInt("donorId"),
                            rs.getString("donorName"),
                            rs.getString("donorNo")
                    );

                    donors.add(donor);
                }
            }
        }

        return donors;
    }
}