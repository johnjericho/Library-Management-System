package model.BookModule;

public class Supplier {

    private int supplierId;
    private String supplierName;
    private String supplierNumber;
    private String supplierContactPerson;
    private String supplierAddress;



  

	public Supplier() { }

    public Supplier(int supplierId, String supplierName, String supplierNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    // ============================================

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    // ============================================

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }
    
    
    public String getSupplierContactPerson() {
  		return supplierContactPerson;
  	}

  	public String getSupplierAddress() {
  		return supplierAddress;
  	}

  	public void setSupplierContactPerson(String supplierContactPerson) {
  		this.supplierContactPerson = supplierContactPerson;
  	}

  	public void setSupplierAddress(String supplierAddress) {
  		this.supplierAddress = supplierAddress;
  	}
}