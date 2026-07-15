package controller.AcquisitionModule;

import java.util.ArrayList;

import model.BookModule.Donor;
import services.AcquisitionModule.DonorServices;

public class DonorController {
    private DonorServices donorServices;

    public DonorController(DonorServices donorServices) {
        this.donorServices = donorServices;
    }

    public void addDonor(String donorName,String contacPer, String donorNo, String donorAddress) {
        donorServices.addDonor(donorName,contacPer, donorNo, donorAddress);
    }

    public boolean isExisting(String donorName, String donorNumber) {
        return donorServices.isExisting(donorName, donorNumber);
    }

    public ArrayList<Donor> loadDonor() {
        return donorServices.loadDonor();
    }

    public void updateDonor(int donorId, String donorName, String donorContactPer, String donorNo, String donorAddress) {
        donorServices.updateDonor(donorId,donorContactPer, donorName, donorNo, donorAddress);
    }

    public void deleteDonor(int donorId) {
        donorServices.deleteDonor(donorId);
    }

    public ArrayList<Donor> searchDonor(String keyword) {
        return donorServices.searchDonor(keyword);
    }
}