package model.dto;

import java.time.LocalDateTime;

public class VisitLogDisplay {
    private int visitId;
    private String borrowerNo;
    private String borrowerName;
    private String borrowerType;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String department;
    private String grade;
    private String section;

    public VisitLogDisplay(int visitId, String borrowerNo, String borrowerName,
                            String borrowerType, LocalDateTime timeIn, LocalDateTime timeOut,
                            String department, String grade, String section) {
        this.visitId = visitId;
        this.borrowerNo = borrowerNo;
        this.borrowerName = borrowerName;
        this.borrowerType = borrowerType;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.department = department;
        this.grade = grade;
        this.section = section;
    }

    public int getVisitId() { return visitId; }
    public String getBorrowerNo() { return borrowerNo; }
    public String getBorrowerName() { return borrowerName; }
    public String getBorrowerType() { return borrowerType; }
    public LocalDateTime getTimeIn() { return timeIn; }
    public LocalDateTime getTimeOut() { return timeOut; }
    public String getDepartment() { return department; }
    public String getGrade() { return grade; }
    public String getSection() { return section; }
}