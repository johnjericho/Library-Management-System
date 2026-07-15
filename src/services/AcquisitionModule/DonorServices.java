package services.AcquisitionModule;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AcquisitionModule.DonorDAO;
import model.BookModule.Donor;

public class DonorServices {
    private DonorDAO donorDao;

    public DonorServices(DonorDAO donorDao) {
        this.donorDao = donorDao;
    }

    public void addDonor(String donorName,String contactPer, String donorNo, String donorAddress) {
        donorName = donorName.trim().replaceAll("\\s+", " ");
        donorName = capitalizeWords(donorName);
        donorNo = donorNo.trim().replaceAll("\\s+", " ");
        if(donorName.length() > 255) throw new IllegalArgumentException("Donor is too long! Max 255 characters!");

        if (donorName.isEmpty()) throw new IllegalArgumentException("Donor Cannot be empty!");
        if (donorNo.isEmpty()) throw new IllegalArgumentException("Number Cannot be empty!");
        
        if(!donorNo.matches("09\\d{9}")) throw new IllegalArgumentException("Contact number must be a valid mobile number (09XXXXXXXXX)!");
        if(donorNo.length() > 11 || donorNo.length() < 11) throw new IllegalArgumentException("Number is 11 digit only!"); 

        if (isExisting(donorName, donorNo)) throw new IllegalArgumentException("Donor already exist!");
       
        try {
            donorDao.addDonor(donorName, contactPer,  donorNo, donorAddress);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error!");
        }
    }

    public boolean isExisting(String donorName, String donorNumber) {
        donorName = donorName.trim().replaceAll("\\s+", " ");
        donorNumber = donorNumber.trim().replaceAll("\\s+", " ");
        try {
            return donorDao.isExisting(donorName, donorNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error!");
        }
    }

    public ArrayList<Donor> loadDonor() {
        try {
            return donorDao.loadDonor();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error!");
        }
    }

    public void updateDonor(int donorId, String donorName,String contacPer, String donorNo, String donorAddress) {
        donorName = donorName.trim().replaceAll("\\s+", " ");
        donorName = capitalizeWords(donorName);
        donorNo = donorNo.trim().replaceAll("\\s+", " ");
        donorNo = capitalizeWords(donorNo);

        if (donorId < 0) throw new IllegalArgumentException("Select donor first!");
        if (donorName.isEmpty()) throw new IllegalArgumentException("Donor Cannot be empty!");
        if (donorNo.isEmpty()) throw new IllegalArgumentException("Number Cannot be empty!");
        if(donorName.length() > 255) throw new IllegalArgumentException("Donor is too long! Max 255 characters!");

        if(!donorNo.matches("09\\d{9}")) throw new IllegalArgumentException("Contact number must be a valid mobile number (09XXXXXXXXX)!");
        if(donorNo.length() > 11 || donorNo.length() < 11) throw new IllegalArgumentException("Number is 11 digit only!"); 
        
        if (isExisting(donorName, donorNo)) throw new IllegalArgumentException("Donor already exist");
       

        try {
            donorDao.updateDonor(donorId, donorName, donorNo);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error!");
        }
    }

    public void deleteDonor(int donorId) {
        if (donorId < 0) throw new IllegalArgumentException("Select donor first!");
        try {
            donorDao.deleteDonor(donorId);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error!");
        }
    }

    public ArrayList<Donor> searchDonor(String keyword) {
        keyword = keyword.trim();
        try {
            return donorDao.searchDonor(keyword);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error!");
        }
    }

    private String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        return result.toString().trim();
    }
}