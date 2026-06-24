package controller.BookModule;

import java.util.ArrayList;
import services.BookModuleServices.SupplierServices;
import model.Supplier;

public class SupplierController {
    private SupplierServices supplierServices;

    public SupplierController(SupplierServices supplierServices) {
        this.supplierServices = supplierServices;
    }

    public void addSupplier(String supplierName, String supplierNumber) {
        supplierServices.addSupplier(supplierName, supplierNumber);
    }

    public boolean isExisting(String supplierName, String supplierNumber) {
        return supplierServices.isExisting(supplierName, supplierNumber);
    }

    public ArrayList<Supplier> loadSupplier() {
        return supplierServices.loadSupplier();
    }

    public void updateSupplier(int supplierId, String supplierName, String supplierNumber) {
        supplierServices.updateSupplier(supplierId, supplierName, supplierNumber);
    }

    public void deleteSupplier(int supplierId) {
        supplierServices.deleteSupplier(supplierId);
    }

    public ArrayList<Supplier> searchSupplier(String keyword) {
        return supplierServices.searchSupplier(keyword);
    }
}