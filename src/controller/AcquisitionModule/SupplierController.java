package controller.AcquisitionModule;

import java.util.ArrayList;

import model.BookModule.Supplier;
import services.AcquisitionModule.SupplierServices;

public class SupplierController {
    private SupplierServices supplierServices;

    public SupplierController(SupplierServices supplierServices) {
        this.supplierServices = supplierServices;
    }

    public void addSupplier(String donorType, String supplierName, String contactPerson ,String contactNo, String supplierAddress) {
        supplierServices.addSupplier(donorType, supplierName, contactPerson, contactNo, supplierAddress);
    }

    public boolean isExisting(String supplierName, String supplierNumber) {
        return supplierServices.isExisting(supplierName, supplierNumber);
    }

    public ArrayList<Supplier> loadSupplier() {
        return supplierServices.loadSupplier();
    }

    public void updateSupplier(int supplierId, String supplierName, String contactPerson, String contactNo, String supplierAddress) {
        supplierServices.updateSupplier(supplierId, supplierName,contactPerson, contactNo ,supplierAddress );
    }

    public void deleteSupplier(int supplierId) {
        supplierServices.deleteSupplier(supplierId);
    }

    public ArrayList<Supplier> searchSupplier(String keyword) {
        return supplierServices.searchSupplier(keyword);
    }
}