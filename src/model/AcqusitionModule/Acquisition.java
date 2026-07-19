

/*
 * TODO gumawa ng DTO,
 * aralin ang return type changable
 * gumawa ng documentation
 */
















package model.AcqusitionModule;

import java.sql.Date;

public class Acquisition {

    private int acquisitionId;
    private String transactionNo;
    private Integer supplierId;  // Integer, pwedeng null
    private Integer donorId;     // Integer, pwedeng null
    private Date dateAcquired;

   

    public Acquisition(int acquisitionId, String transactionNo,
                        Integer supplierId, Integer donorId, Date dateAcquired) {
    	this.acquisitionId = acquisitionId;
        this.transactionNo = transactionNo;
        this.supplierId = supplierId;
        this.donorId = donorId;
        this.dateAcquired = dateAcquired;
    }



    public int getAcquisitionId() { return acquisitionId; }
    public void setAcquisitionId(int acquisitionId) { this.acquisitionId = acquisitionId; }

    public String getTransactionNo() { return transactionNo; }
    public void setTransactionNo(String transactionNo) { this.transactionNo = transactionNo; }


    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    public Integer getDonorId() { return donorId; }
    public void setDonorId(Integer donorId) { this.donorId = donorId; }



    public Date getDateAcquired() { return dateAcquired; }
    public void setDateAcquired(Date dateAcquired) { this.dateAcquired = dateAcquired; }
}