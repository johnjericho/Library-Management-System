package utility;

import controller.AcquisitionModule.AcquisitionController;
import controller.AcquisitionModule.DonorController;
import controller.AcquisitionModule.SupplierController;
import controller.BookModule.AuthorController;
import controller.BookModule.BookMaintenanceController;
import controller.BookModule.CategoryController;
import controller.BookModule.PublisherController;
import controller.CirculationModule.CirculationController;
import controller.CirculationModule.VisitLogController;
import controller.UserModule.FacultyTab.FacultyMaintenanceController;
import controller.UserModule.LibrarianTab.LibrarianMaintenanceController;
import controller.UserModule.StudentTab.DepartmentController;
import controller.UserModule.StudentTab.GradeController;
import controller.UserModule.StudentTab.SectionController;
import controller.UserModule.StudentTab.StudentMaintenanceController;
import view.MainFrame;

public class AppContext {

    private static AppContext instance;

    private MainFrame mainFrame;
    private AuthorController authorController;
    private PublisherController publisherController;
    private CategoryController categoryController;
    private SupplierController supplierController;
    private DonorController donorController;
    private BookMaintenanceController bookModuleController;
    private AcquisitionController acquisitionController;
    private DepartmentController departmentController;
    private GradeController gradeController;
    private SectionController sectionController;
    private StudentMaintenanceController studentMaintenanceController;
    private FacultyMaintenanceController facultyMaintenanceController;
    private LibrarianMaintenanceController librarianMaintenanceController;
    private CirculationController circulationController;
    private VisitLogController visitLogController;
    
    

	private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    // Getters
    public MainFrame getMainFrame() {return mainFrame; }
    public AuthorController getAuthorController() { return authorController; }
    public PublisherController getPublisherController() { return publisherController; }
    public CategoryController getCategoryController() { return categoryController; }
    public SupplierController getSupplierController() { return supplierController; }
    public DonorController getDonorController() { return donorController; }
    public BookMaintenanceController getBookMaintenanceController() {return bookModuleController; }
    public AcquisitionController getAcquisitionController() {return acquisitionController; }
    public DepartmentController getDepartmentController() {return departmentController; }
    public GradeController getdGradeController() {return gradeController; }
    public SectionController getSectionController() {return sectionController; }
    public StudentMaintenanceController getStudentMaintenanceController() {return studentMaintenanceController; }
    public FacultyMaintenanceController getFacultyMaintenanceController() {return facultyMaintenanceController; }
    public LibrarianMaintenanceController getLibrarianMaintenanceController() {return librarianMaintenanceController; }
    public  CirculationController getCirculationController() { return  circulationController;  }
    public VisitLogController getVisitLogController() {	return visitLogController; }

    



    


    // Setters — sa Main.java lang gagamitin
    public void setMainFrame(MainFrame mainFrame) { this.mainFrame = mainFrame; }
    public void setAuthorController(AuthorController authorController) { this.authorController = authorController; }
    public void setPublisherController(PublisherController publishgerController) { this.publisherController = publishgerController; }
    public void setCategoryController(CategoryController categoryController) { this.categoryController = categoryController; }
    public void setSupplierController(SupplierController supplierController) { this.supplierController = supplierController; }
    public void setDonorController(DonorController donorController) { this.donorController = donorController; }
    public void setBookMaintenanceController(BookMaintenanceController bookModuleController) { this.bookModuleController = bookModuleController; }
    public void setAcquisitionController(AcquisitionController aquisitionController) { this.acquisitionController = aquisitionController; }
    public void setDepartmentController(DepartmentController departmentController) { this.departmentController = departmentController; }
    public void setGradeController(GradeController gradeController) { this.gradeController = gradeController; }
    public void setSectionController(SectionController sectionController) { this.sectionController = sectionController; }
    public void setStudentMaintenanceController(StudentMaintenanceController studentMaintenanceController) { this.studentMaintenanceController = studentMaintenanceController; }
    public void setFacultyMaintenanceController(FacultyMaintenanceController facultyMaintenanceController) { this.facultyMaintenanceController = facultyMaintenanceController; }
	public void setLibrarianMaintenanceController(LibrarianMaintenanceController librarianMaintenanceController) { this.librarianMaintenanceController = librarianMaintenanceController; }
    public void setCirculationController( CirculationController circulationController) { this.circulationController = circulationController; }
	public void setVisitLogController(VisitLogController visitLogController) {	this.visitLogController = visitLogController; }


    
}