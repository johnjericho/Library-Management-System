package model.UserModule.StudentTab;

public class Section {
	
	private int sectionId; 
	private int gradeId;
	private String sectionName;
	
	public Section() {  }
	
	//getter
	public Section(int sectionId, String sectionName) {
		this.sectionId = sectionId;
		this.sectionName = sectionName;
	}


	public int getGradeId() {
		return gradeId;
	}


	public int getSectionId() {
		return sectionId;
	}


	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	//setter

	public String getSectionName() {
		return sectionName;
	}


	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	
	
}
