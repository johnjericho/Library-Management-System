package model.BookModule;

public class Donor {

    private int donorId;
    private String donorName;
    private String donorNumber;

    public Donor() { }

    public Donor(int donorId, String donorName, String donorNumber) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorNumber = donorNumber;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    // ============================================

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    // ============================================

    public String getDonorNumber() {
        return donorNumber;
    }

    public void setDonorNumber(String donorNumber) {
        this.donorNumber = donorNumber;
    }
}