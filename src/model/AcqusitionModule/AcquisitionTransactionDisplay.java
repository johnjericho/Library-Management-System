package model.AcqusitionModule;

import java.util.Date;

public class AcquisitionTransactionDisplay {
	
     private int acqisitionId;
     private String transactionNo;
     private String ContributorName;
     private String ContributorType;
     private Date DateReceived;
     
     public AcquisitionTransactionDisplay() {}
     
	 public AcquisitionTransactionDisplay(int acqisitionId, 
   					    	String transactionNo, 
							 String contributorName,
							 String contributorType,
							 Date dateReceived) {
		 
		this.acqisitionId = acqisitionId;
		this.transactionNo = transactionNo;
		this.ContributorName = contributorName;
		this.ContributorType = contributorType;
		this.DateReceived = dateReceived;
	 }

	 //GETTER
	 public int getAcqisitionId() {
		 return acqisitionId;
	 }


	 public String getTransactionNo() {
		 return transactionNo;
	 }


	 public String getContributorName() {
		 return ContributorName;
	 }


	 public String getContributorType() {
		 return ContributorType;
	 }


	 public Date getDateReceived() {
		 return DateReceived;
	 }

	 //SETTER
	 public void setAcqisitionId(int acqisitionId) {
		 this.acqisitionId = acqisitionId;
	 }


	 public void setTransactionNo(String transactionNo) {
		 this.transactionNo = transactionNo;
	 }


	 public void setContributorName(String contributorName) {
		 ContributorName = contributorName;
	 }


	 public void setContributorType(String contributorType) {
		 ContributorType = contributorType;
	 }


	 public void setDateReceived(Date dateReceived) {
		 DateReceived = dateReceived;
	 }
     
     
	 
	 
	 
	 
	 
	 
	 
	 

}
