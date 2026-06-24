package dao.BookModuleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Supplier;
import utility.DatabaseHelper;

public class SupplierDAO {

	public void addSupplier(String supplierName, String number)throws SQLException {
		String sql = "INSERT INTO tbl_supplier "
				+ "(supplierName, supplierNumber) "
				+ "VALUES(?, ?)";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		stmt.setString(1, supplierName);
		stmt.setString(2, number);
		stmt.executeUpdate();
		}
	}
	
	public boolean isExisting(String supplierName, String supplierNumber) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_supplier "
				+ "WHERE LOWER(supplierName) = LOWER(?) "
				+ "AND supplierNumber = ?";
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql))
	{
	    stmt.setString(1, supplierName);	
	    stmt.setString(2, supplierNumber);	
	    
       try( ResultSet rs = stmt.executeQuery())
       {
		
		if(rs.next()) {
			return rs.getInt(1) > 0;
		}
       }
	 }	
	return false;						
	}
	
	public ArrayList<Supplier> loadSupplier() throws SQLException {
	    ArrayList<Supplier> loadSupplier = new ArrayList<>();

	    String sql = "SELECT * FROM tbl_supplier";

	    try (Connection conn = DatabaseHelper.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Supplier supplier = new Supplier();

	            supplier.setSupplierId(rs.getInt("supplierId"));
	            supplier.setSupplierName(rs.getString("supplierName"));
	            supplier.setSupplierNumber(rs.getString("supplierNumber"));

	            loadSupplier.add(supplier);
	        }
	    }

	    return loadSupplier;
	}
	  
			public void updateSupplier(int supplierId, String supplierName, String supplierNumber) throws SQLException {
			
			String sql = "UPDATE tbl_supplier "
			+ "SET supplierName = ?, supplierNumber = ? "
			+ "WHERE supplierId = ?";
			
			try (Connection conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, supplierName);
			stmt.setString(2, supplierNumber);
			stmt.setInt(3, supplierId);
			
			stmt.executeUpdate();
		}
	}
	
			
			public void deleteSupplier(int supplierId) throws SQLException {

			    String sql = "DELETE FROM tbl_supplier WHERE supplierId = ?";

			    try (Connection conn = DatabaseHelper.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {

			        stmt.setInt(1, supplierId);
			        stmt.executeUpdate();
			    }
			}
			
			
			public ArrayList<Supplier> searchSupplier(String keyword) throws SQLException {

			    ArrayList<Supplier> filteredSupplier = new ArrayList<>();

			    String sql = "SELECT * FROM tbl_supplier "
			               + "WHERE LOWER(supplierName) LIKE LOWER(?) "
			               + "OR supplierNumber LIKE ?";

			    try (Connection conn = DatabaseHelper.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {

			        stmt.setString(1, "%" + keyword + "%");
			        stmt.setString(2, "%" + keyword + "%");

			        try (ResultSet rs = stmt.executeQuery()) {

			            while (rs.next()) {
			                filteredSupplier.add(
			                    new Supplier(
			                        rs.getInt("supplierId"),
			                        rs.getString("supplierName"),
			                        rs.getString("supplierNumber")
			                    )
			                );
			            }
			        }
			    }

			    return filteredSupplier;
			}
	
	
}
