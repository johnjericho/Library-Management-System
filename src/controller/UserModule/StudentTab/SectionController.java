package controller.UserModule.StudentTab;

import java.util.ArrayList;

import model.UserModule.StudentTab.Section;
import services.UserModule.StudentTab.SectionServices;

public class SectionController {

    private SectionServices sectionServices;

    public SectionController(SectionServices sectionServices) {
        this.sectionServices = sectionServices;
    }

    public void addSection(String sectionName, int gradeId) { 
        sectionServices.addSection(sectionName, gradeId);     
    }
    
    
    public ArrayList<Section> getSectionsByGrade(int gradeId) {
        return sectionServices.getSectionsByGrade(gradeId);
    }
    
 // BAGO: passthrough papuntang Services, sinusunod pattern ng addSection()
    public void updateSection(int sectionId, String sectionName, int gradeId) {
        sectionServices.updateSection(sectionId, sectionName, gradeId);
    }

    public void deleteSection(int sectionId) {
        sectionServices.deleteSection(sectionId);
    }
    
}