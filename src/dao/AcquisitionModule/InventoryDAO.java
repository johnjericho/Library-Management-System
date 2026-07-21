package dao.AcquisitionModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AcqusitionModule.InventoryDisplay;
import utility.DatabaseHelper;

public class InventoryDAO {
	
	

    public String generateNextAccessionNo(Connection conn) throws SQLException {
        String sql = "SELECT accessionNo FROM tbl_inventory ORDER BY inventoryId DESC LIMIT 1 FOR UPDATE";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int nextNumber = 1;
            if (rs.next()) {
                String lastAccessionNo = rs.getString("accessionNo"); // e.g. ACC-000045
                nextNumber = Integer.parseInt(lastAccessionNo.substring(lastAccessionNo.indexOf("-") + 1)) + 1;
            }
            return String.format("ACC-%06d", nextNumber);
        }
    }
    
    

    public void saveInventoryItem(Connection conn, int detailId, String accessionNo) throws SQLException {
        String sql = "INSERT INTO tbl_inventory (detailId, accessionNo, status) VALUES (?, ?, 'Available')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detailId);
            stmt.setString(2, accessionNo);
            stmt.executeUpdate();
        }
    }
    
    
    
    public ArrayList<InventoryDisplay> loadInventoryDisplay() throws SQLException{
    	
    	ArrayList<InventoryDisplay> inventoryList = new ArrayList<>();
    	
    	String sql = "SELECT i.inventoryId, i.accessionNo, b.bookTitle, "
    				+ "i.bookCondition, i.status "
    				+ "FROM tbl_inventory i "
    				+ "INNER JOIN tbl_acquisition_detail d ON i.detailId = d.detailId "
    				+ "INNER JOIN tbl_book b ON d.bookId = b.bookId "
    				+ "ORDER BY b.bookTitle ASC";
    	try(
    	Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(); )
    	{
    		while(rs.next()) {
    			InventoryDisplay inv = new InventoryDisplay(
    									rs.getInt("inventoryId"),
    									rs.getString("accessionNo"),
    									rs.getString("bookTitle"),
    									rs.getString("bookCondition"),
    									rs.getString("status")
    									);
    			inventoryList.add(inv);
    		}
    		return inventoryList;
    	}
    	
    }
    
    
    public ArrayList<InventoryDisplay> searchInventory(String keyword) throws SQLException {

	    ArrayList<InventoryDisplay> result = new ArrayList<>();

	    String sql = """
	    		   SELECT i.inventoryId, i.accessionNo, b.bookTitle, i.bookCondition, i.status 
	    		   FROM tbl_inventory i 
	    		   
	    		   INNER JOIN tbl_acquisition_detail d ON i.detailId = d.detailId 
	    		   INNER JOIN tbl_book b ON d.bookId = b.bookId 
	    		   
	    		   WHERE LOWER(i.accessionNo) LIKE LOWER(?) 
	    		   OR LOWER(b.bookTitle) LIKE LOWER(?) 
	    		   OR LOWER(i.bookCondition) LIKE LOWER(?) 
	    		   OR LOWER(i.status) LIKE LOWER(?) 
	    		   
	    		   ORDER BY b.bookTitle
	    		   """;	    

	    
	   
	    
	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {

	        String likeKeyword = "%" + keyword + "%";

	        stmt.setString(1, likeKeyword);
	        stmt.setString(2, likeKeyword);
	        stmt.setString(3, likeKeyword);
	        stmt.setString(4, likeKeyword);
	    

	        try (ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {

	            	InventoryDisplay display = new InventoryDisplay(
	            			rs.getInt("inventoryId"),
	            			rs.getString("accessionNo"),
	            			rs.getString("bookTitle"),
	            			rs.getString("bookCondition"),
	            			rs.getString("status")
	            			);
	            	result.add(display);
	            }
	        }
	    }

	    return result;
	}
    
    	
}