package model.AcqusitionModule;

import java.util.Date;

public class Acquisition {

    private int acquisitionId;
    private String transactionNo;
    private String accessionNo;
    private int bookId;
    private Integer supplierId;  // Integer, pwedeng null
    private Integer donorId;     // Integer, pwedeng null
    private int bookPrice;
    private Date dateAcquired;

    public Acquisition() {
    }

    public Acquisition(String transactionNo, String accessionNo, int bookId,
                        Integer supplierId, Integer donorId, int bookPrice, Date dateAcquired) {
        this.transactionNo = transactionNo;
        this.accessionNo = accessionNo;
        this.bookId = bookId;
        this.supplierId = supplierId;
        this.donorId = donorId;
        this.bookPrice = bookPrice;
        this.dateAcquired = dateAcquired;
    }

    public Acquisition(int acquisitionId, String transactionNo, String accessionNo, int bookId,
                        Integer supplierId, Integer donorId, int bookPrice, Date dateAcquired) {
        this.acquisitionId = acquisitionId;
        this.transactionNo = transactionNo;
        this.accessionNo = accessionNo;
        this.bookId = bookId;
        this.supplierId = supplierId;
        this.donorId = donorId;
        this.bookPrice = bookPrice;
        this.dateAcquired = dateAcquired;
    }

    public int getAcquisitionId() { return acquisitionId; }
    public void setAcquisitionId(int acquisitionId) { this.acquisitionId = acquisitionId; }

    public String getTransactionNo() { return transactionNo; }
    public void setTransactionNo(String transactionNo) { this.transactionNo = transactionNo; }

    public String getAccessionNo() { return accessionNo; }
    public void setAccessionNo(String accessionNo) { this.accessionNo = accessionNo; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    public Integer getDonorId() { return donorId; }
    public void setDonorId(Integer donorId) { this.donorId = donorId; }

    public int getBookPrice() { return bookPrice; }
    public void setBookPrice(int bookPrice) { this.bookPrice = bookPrice; }

    public Date getDateAcquired() { return dateAcquired; }
    public void setDateAcquired(Date dateAcquired) { this.dateAcquired = dateAcquired; }
}