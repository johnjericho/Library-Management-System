package model.AcqusitionModule;

import java.util.Date;

public class AcquisitionDetailDisplay {

	private int detailId;
	private String transactionNo;
	private String contributorName;
	private String contributorType;
	private Date dateAquired;
	
	
	public AcquisitionDetailDisplay(int detailId, String transactionNo, String contributorName, String contributorType,
			Date dateAquired) {
		super();
		this.detailId = detailId;
		this.transactionNo = transactionNo;
		this.contributorName = contributorName;
		this.contributorType = contributorType;
		this.dateAquired = dateAquired;
	}


	public int getDetailId() {
		return detailId;
	}


	public String getTransactionNo() {
		return transactionNo;
	}


	public String getContributorName() {
		return contributorName;
	}


	public String getContributorType() {
		return contributorType;
	}


	public Date getDateAquired() {
		return dateAquired;
	}


	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}


	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}


	public void setContributorName(String contributorName) {
		this.contributorName = contributorName;
	}


	public void setContributorType(String contributorType) {
		this.contributorType = contributorType;
	}


	public void setDateAquired(Date dateAquired) {
		this.dateAquired = dateAquired;
	}
	
	
	
	
	
}
