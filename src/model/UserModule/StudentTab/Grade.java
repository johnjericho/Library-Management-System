package model.UserModule.StudentTab;

public class Grade {
    private int gradeId;
    private int departmentId;
    private int gradeLvl;

    public Grade() {
    }

    public Grade(int gradeId, int departmentId, int gradeLvl) {
        this.gradeId = gradeId;
        this.departmentId = departmentId;
        this.gradeLvl = gradeLvl;
    }

    public int getGradeId() { return gradeId; }
    public void setGradeId(int gradeId) { this.gradeId = gradeId; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public int getGradeLvl() { return gradeLvl; }
    public void setGradeLvl(int gradeLvl) { this.gradeLvl = gradeLvl; }

    @Override
    public String toString() {
        return "Grade " + gradeLvl; // ito ang lalabas sa JComboBox
    }
}