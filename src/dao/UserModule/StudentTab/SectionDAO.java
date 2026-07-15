package dao.UserModule.StudentTab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BookModule.Category;
import model.UserModule.StudentTab.Section;
import utility.DatabaseHelper;

public class SectionDAO {

	public void addSection(String sectionName, int gradeId) throws SQLException {
	    String sql = "INSERT INTO tbl_section (sectionName, gradeId) VALUES(?, ?)";
	    try(
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	        stmt.setString(1, sectionName);
	        stmt.setInt(2, gradeId);
	        stmt.executeUpdate();
	    }
	}
	
	public boolean isExisting(String sectionName) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_section WHERE LOWER(sectionName) = LOWER(?)";
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql))
	{
	    stmt.setString(1, sectionName);	    
       try( ResultSet rs = stmt.executeQuery())
       {
		
		if(rs.next()) {
			return rs.getInt(1) > 0; // getInt(1) column 1 na ibinagy ni resultSet isang column lang ang rinereturn at bibilangin ang row
		}
       }
	 }	
	return false;						
	}
	
	// BAGO: para sa Update — kelangan i-exclude yung sarili niyang ID sa duplicate check
	public boolean isExisting(String sectionName, int excludeSectionId) throws SQLException {
	    // di kasama yung current row sa pag-check ng duplicate, kaya pwede mo i-save
	    // yung parehong pangalan without triggering "already exists" error
	    String sql = "SELECT COUNT(*) FROM tbl_section WHERE LOWER(sectionName) = LOWER(?) AND sectionId != ?";
	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql))
	    {
	        stmt.setString(1, sectionName);
	        stmt.setInt(2, excludeSectionId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}

	// BAGO: Update — single row lang, sinusunod pattern ng addSection()
	public void updateSection(int sectionId, String sectionName, int gradeId) throws SQLException {
	    String sql = "UPDATE tbl_section SET sectionName = ?, gradeId = ? WHERE sectionId = ?";
	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql))
	    {
	        stmt.setString(1, sectionName);
	        stmt.setInt(2, gradeId);
	        stmt.setInt(3, sectionId);
	        stmt.executeUpdate();
	    }
	}

	// BAGO: Delete — hard delete (walang status column ang system mo, base sa pattern mo)
	public void deleteSection(int sectionId) throws SQLException {
	    String sql = "DELETE FROM tbl_section WHERE sectionId = ?";
	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql))
	    {
	        stmt.setInt(1, sectionId);
	        stmt.executeUpdate();
	    }
	}

	public ArrayList<Section> loadSection() throws SQLException{
	    ArrayList<Section> loadSection = new ArrayList<>();
	    String sql = "SELECT * FROM tbl_section";
	    
	    try(	
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();)
	    {
	        while(rs.next()) {
	            Section sec = new Section();
	            sec.setSectionId(rs.getInt("sectionId"));
	            sec.setSectionName(rs.getString("sectionName"));
	            sec.setGradeId(rs.getInt("gradeId")); // idagdag ito
	            loadSection.add(sec);
	        }
	        return loadSection;
	    }
	}
	
	public ArrayList<Section> getSectionsByGrade(int gradeId) throws SQLException {
	    ArrayList<Section> list = new ArrayList<>();
	    String sql = "SELECT sectionId, sectionName, gradeId FROM tbl_section WHERE gradeId = ?";

	    try (
	        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {
	        stmt.setInt(1, gradeId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Section sec = new Section();
	                sec.setSectionId(rs.getInt("sectionId"));
	                sec.setSectionName(rs.getString("sectionName"));
	                sec.setGradeId(rs.getInt("gradeId"));
	                list.add(sec);
	            }
	        }
	    }
	    return list;
	}
	
}
