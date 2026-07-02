package services.AcquisitionModuleServices;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.AcquisitionModuleDAO.SupplierDAO;
import model.BookModule.Supplier;

public class SupplierServices {
    private SupplierDAO supplierDao;

    public SupplierServices(SupplierDAO supplierDao) {
        this.supplierDao = supplierDao;
    }

    public void addSupplier(String donorType, String supplierName, String contactPerson ,String contactNo, String supplierAddress) {
        supplierName = supplierName.trim().replaceAll("\\s+", " ");
        supplierName = capitalizeWords(supplierName);
        contactNo = contactNo.trim().replaceAll("\\s+", " ");
        donorType = donorType.trim().replaceAll("\\s+", " ");
        
        
        if (donorType == null || donorType.isEmpty()) throw new IllegalArgumentException("Must select contirbutor type!");
        if(!donorType.equals("supplier"))  throw new IllegalArgumentException("Select proper type!");

        if (supplierName.isEmpty()) throw new IllegalArgumentException("Supplier Cannot be empty!");
        if (contactNo.isEmpty()) throw new IllegalArgumentException("Number Cannot be empty!");
		if(supplierName.length() > 255) throw new IllegalArgumentException("Supplier is too long! Max 255 characters.");
		
        if(!contactNo.matches("09\\d{9}")) throw new IllegalArgumentException("Contact number must be a valid mobile number (09XXXXXXXXX)!");
        if(contactNo.length() > 11 || contactNo.length() < 11) throw new IllegalArgumentException("Number is 11 digit only!"); 
      
        if (isExisting(supplierName, contactNo)) throw new IllegalArgumentException("Supplier already exist!");



        try {
            supplierDao.addSupplier(supplierName, contactPerson, contactNo, supplierAddress);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error!");
        }
    }

    public boolean isExisting(String supplierName, String supplierNumber) {
        supplierName = supplierName.trim().replaceAll("\\s+", " ");
        supplierNumber = supplierNumber.trim().replaceAll("\\s+", " ");
        try {
            return supplierDao.isExisting(supplierName, supplierNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error!");
        }
    }

    public ArrayList<Supplier> loadSupplier() {
        try {
            return supplierDao.loadSupplier();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error!");
        }
    }

    public void updateSupplier(int supplierId, String supplierName, String contactPerson, String contactNo, String supplierAddress) {
        supplierName = supplierName.trim().replaceAll("\\s+", " ");
        supplierName = capitalizeWords(supplierName);
        contactNo = contactNo.trim().replaceAll("\\s+", " ");
        contactNo = capitalizeWords(contactNo);

        if (supplierId < 0) throw new IllegalArgumentException("Select supplier first!");
        if (supplierName.isEmpty()) throw new IllegalArgumentException("Supplier Cannot be empty!");
        if (contactNo.isEmpty()) throw new IllegalArgumentException("Number Cannot be empty!");
    	if(supplierName.length() > 255) throw new IllegalArgumentException("Supplier is too long! Max 255 characters.");

        if(!contactNo.matches("09\\d{9}")) throw new IllegalArgumentException("Contact number must be a valid mobile number (09XXXXXXXXX)!");
        if(contactNo.length() > 11 || contactNo.length() < 11) throw new IllegalArgumentException("Number is 11 digit only!"); 
      
        if (isExisting(supplierName, contactNo)) throw new IllegalArgumentException("Supplier already exist");
        

        try {
            supplierDao.updateSupplier(supplierId, supplierName,contactPerson, contactNo, supplierAddress);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection error!");
        }
    }

    public void deleteSupplier(int supplierId) {
        if (supplierId < 0) throw new IllegalArgumentException("Select supplier first!");
        try {
            supplierDao.deleteSupplier(supplierId);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error!");
        }
    }

    public ArrayList<Supplier> searchSupplier(String keyword) {
        keyword = keyword.trim();
        try {
            return supplierDao.searchSupplier(keyword);
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