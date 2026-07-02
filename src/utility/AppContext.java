package utility;

import controller.AcquisitionModule.DonorController;
import controller.AcquisitionModule.SupplierController;
import controller.BookModule.AuthorController;
import controller.BookModule.BookMaintenanceController;
import controller.BookModule.CategoryController;
import controller.BookModule.PublisherController;

public class AppContext {

    private static AppContext instance;

    private AuthorController authorController;
    private PublisherController publisherController;
    private CategoryController categoryController;
    private SupplierController supplierController;
    private DonorController donorController;
    private BookMaintenanceController bookModuleController;
  


    private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    // Getters
    public AuthorController getAuthorController() { return authorController; }
    public PublisherController getPublisherController() { return publisherController; }
    public CategoryController getCategoryController() { return categoryController; }
    public SupplierController getSupplierController() { return supplierController; }
    public DonorController getDonorController() { return donorController; }
    public BookMaintenanceController getBookMaintenanceController() {return bookModuleController; }


    // Setters — sa Main.java lang gagamitin
    public void setAuthorController(AuthorController authorController) { this.authorController = authorController; }
    public void setPublisherController(PublisherController publishgerController) { this.publisherController = publishgerController; }
    public void setCategoryController(CategoryController categoryController) { this.categoryController = categoryController; }
    public void setSupplierController(SupplierController supplierController) { this.supplierController = supplierController; }
    public void setDonorController(DonorController donorController) { this.donorController = donorController; }
    public void setBookMaintenanceController(BookMaintenanceController bookModuleController) { this.bookModuleController = bookModuleController; }


}