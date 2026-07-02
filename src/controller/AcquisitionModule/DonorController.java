package controller.BookModule;

import java.util.ArrayList;

import model.BookModule.Donor;
import services.BookModuleServices.DonorServices;

public class DonorController {
    private DonorServices donorServices;

    public DonorController(DonorServices donorServices) {
        this.donorServices = donorServices;
    }

    public void addDonor(String donorName, String donorNumber) {
        donorServices.addDonor(donorName, donorNumber);
    }

    public boolean isExisting(String donorName, String donorNumber) {
        return donorServices.isExisting(donorName, donorNumber);
    }

    public ArrayList<Donor> loadDonor() {
        return donorServices.loadDonor();
    }

    public void updateDonor(int donorId, String donorName, String donorNumber) {
        donorServices.updateDonor(donorId, donorName, donorNumber);
    }

    public void deleteDonor(int donorId) {
        donorServices.deleteDonor(donorId);
    }

    public ArrayList<Donor> searchDonor(String keyword) {
        return donorServices.searchDonor(keyword);
    }
}