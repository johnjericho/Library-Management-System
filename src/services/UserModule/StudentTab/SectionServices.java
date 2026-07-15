package services.UserModule.StudentTab;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserModule.StudentTab.SectionDAO;
import model.UserModule.StudentTab.Section;

public class SectionServices {
	
	private SectionDAO sectionDao;
	
	public SectionServices(SectionDAO sectionDao) {
		this.sectionDao = sectionDao;
	}
	
	public void addSection(String sectionName, int gradeId) {

	    sectionName = sectionName.trim().replaceAll("\\s+", " ");
	    sectionName = capitalizeWords(sectionName);

	    if(sectionName.isEmpty()) throw new IllegalArgumentException("Section Cannot be empty!");
	    if(isExisting(sectionName)) throw new IllegalArgumentException("Category already exist!");
	    if(sectionName.length() > 255) throw new IllegalArgumentException("Category is too long! Max 255 characters.");
	    if(gradeId <= 0) throw new IllegalArgumentException("Please select a valid grade."); // idagdag ito

	    try {
	        sectionDao.addSection(sectionName, gradeId); // dagdagan ng gradeId
	    } catch(SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database connection error!");
	    }
	}
		
	public boolean isExisting(String sectionName) {
		try {
		boolean existing = sectionDao.isExisting(sectionName);
		return existing;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Connection error!");
		}
	}
	
	private String capitalizeWords(String input) {
	    if (input == null || input.trim().isEmpty()) {
	        return "";
	    }

	    StringBuilder result = new StringBuilder();

	    String[] words = input.trim().split("\\s+");
	    
	    for (String word : words) {
	        if (word.isEmpty()) continue;
	        String eachWord = Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
	        result.append(eachWord);
	    }
	    return result.toString().trim();
	}
	
	public ArrayList<Section> getSectionsByGrade(int gradeId) {
	    try {
	        return sectionDao.getSectionsByGrade(gradeId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database connection error!");
	    }
	}
	
	// BAGO: Update — parehong validation ng addSection(), pero excluded yung sariling row sa duplicate check
	public void updateSection(int sectionId, String sectionName, int gradeId) {
	    sectionName = sectionName.trim().replaceAll("\\s+", " ");
	    sectionName = capitalizeWords(sectionName);

	    if (sectionName.isEmpty()) throw new IllegalArgumentException("Section cannot be empty!");
	    if (sectionName.length() > 255) throw new IllegalArgumentException("Section is too long! Max 255 characters.");
	    if (gradeId <= 0) throw new IllegalArgumentException("Please select a valid grade.");

	    // gamitin yung overload na may excludeSectionId, hindi yung dating isExisting()
	    if (isExisting(sectionName, sectionId)) {
	        throw new IllegalArgumentException("Section already exists!");
	    }

	    try {
	        sectionDao.updateSection(sectionId, sectionName, gradeId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database connection error!");
	    }
	}

	// BAGO: overload — tinatawag ng updateSection() sa itaas
	public boolean isExisting(String sectionName, int excludeSectionId) {
	    try {
	        return sectionDao.isExisting(sectionName, excludeSectionId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database connection error!");
	    }
	}

	// BAGO: Delete — walang masyadong validation dapat, sectionId na lang existence check
	public void deleteSection(int sectionId) {
	    if (sectionId <= 0) throw new IllegalArgumentException("Invalid section.");

	    try {
	        sectionDao.deleteSection(sectionId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database connection error!");
	    }
	}

}
