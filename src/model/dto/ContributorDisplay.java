package model.dto;

public class ContributorDisplay {

    private int contributorId;
    private String contributorType; // "supplier" or "donor"
    private String name;
    private String contactPerson;
    private String contactNumber;
    private String address;

    public ContributorDisplay(int contributorId, String contributorType, String name,
                               String contactPerson, String contactNumber, String address) {
        this.contributorId = contributorId;
        this.contributorType = contributorType;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getContributorId() { return contributorId; }
    public String getContributorType() { return contributorType; }
    public String getName() { return name; }
    public String getContactPerson() { return contactPerson; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
}