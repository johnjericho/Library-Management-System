package model.dto;

import java.util.Date;

public class AcquisitionDisplay {

    private int acquisitionId;
    private String transactionNo;
    private String accessionNo;
    private String bookTitle;        // resolved mula sa bookId
    private String contributorName;  // resolved mula sa supplierId o donorId
    private String contributorType;  // "supplier" o "donor"
    private int bookPrice;
    private Date dateAcquired;

    public AcquisitionDisplay() {
    }

    public AcquisitionDisplay(int acquisitionId, String transactionNo, String accessionNo,
                               String bookTitle, String contributorName, String contributorType,
                               int bookPrice, Date dateAcquired) {
        this.acquisitionId = acquisitionId;
        this.transactionNo = transactionNo;
        this.accessionNo = accessionNo;
        this.bookTitle = bookTitle;
        this.contributorName = contributorName;
        this.contributorType = contributorType;
        this.bookPrice = bookPrice;
        this.dateAcquired = dateAcquired;
    }

    public int getAcquisitionId() { return acquisitionId; }
    public void setAcquisitionId(int acquisitionId) { this.acquisitionId = acquisitionId; }

    public String getTransactionNo() { return transactionNo; }
    public void setTransactionNo(String transactionNo) { this.transactionNo = transactionNo; }

    public String getAccessionNo() { return accessionNo; }
    public void setAccessionNo(String accessionNo) { this.accessionNo = accessionNo; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getContributorName() { return contributorName; }
    public void setContributorName(String contributorName) { this.contributorName = contributorName; }

    public String getContributorType() { return contributorType; }
    public void setContributorType(String contributorType) { this.contributorType = contributorType; }

    public int getBookPrice() { return bookPrice; }
    public void setBookPrice(int bookPrice) { this.bookPrice = bookPrice; }

    public Date getDateAcquired() { return dateAcquired; }
    public void setDateAcquired(Date dateAcquired) { this.dateAcquired = dateAcquired; }
}