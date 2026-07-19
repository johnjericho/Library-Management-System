package model.AcqusitionModule;

import java.util.Date;

public class AcquisitionDisplay {

    private int acquisitionId;
    private String transactionNo;
    private String contributorName;  // resolved mula sa supplierId o donorId
    private String contributorType;  // "supplier" o "donor"
    private Date dateAcquired;

    public AcquisitionDisplay() {
    }

    public AcquisitionDisplay(int acquisitionId,
    						String transactionNo,
                            String contributorName,
                            String contributorType,
                            Date dateAcquired) {
        this.acquisitionId = acquisitionId;
        this.transactionNo = transactionNo;
        this.contributorName = contributorName;
        this.contributorType = contributorType;
        this.dateAcquired = dateAcquired;
    }

    public int getAcquisitionId() { return acquisitionId; }
    public void setAcquisitionId(int acquisitionId) { this.acquisitionId = acquisitionId; }

    public String getTransactionNo() { return transactionNo; }
    public void setTransactionNo(String transactionNo) { this.transactionNo = transactionNo; }



    public String getContributorName() { return contributorName; }
    public void setContributorName(String contributorName) { this.contributorName = contributorName; }

    public String getContributorType() { return contributorType; }
    public void setContributorType(String contributorType) { this.contributorType = contributorType; }


    public Date getDateAcquired() { return dateAcquired; }
    public void setDateAcquired(Date dateAcquired) { this.dateAcquired = dateAcquired; }
}