package view;

import javax.swing.JPanel;

import view.BookModule.BookMaintenanceView;
import view.CirculationModule.CirculationView;
import view.InventoryModule.AcquisitionTab.AcquisitionView;
import view.InventoryModule.InventoryTab.InventoryView;
import view.UserModule.StudentTab.StudentMaintenanceView;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Modules extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnDashboard;
    private JButton btnFileMaintenance;
    private JButton btnCirculation;
    private MainFrame mainFrame;
    private JButton btnInventory;
    private JButton btnUserMaintenance;
    private JButton btnAuditTrail;

    private DashboardView dashboardView;
    private CirculationView circulationView;
    private BookMaintenanceView bookMaintenanceView;
    private AcquisitionView acquisitionView;
    private StudentMaintenanceView studentMaintenanceView;
    private InventoryView inventoryView;
    private JButton btnLogout;

    public Modules(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPanel();
        initViews(); 
        initAction();
    }

    public void setPanel() {
        setBackground(new Color(51, 204, 51));
        setLayout(null);
        JLabel logo = new JLabel();
        ImageIcon originalIcon = new ImageIcon(
        Modules.class.getResource("/img/images__1_-removebg-preview.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 162, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaledImage));
        logo.setBounds(20, 32, 230, 159);
        add(logo);

        btnDashboard = new JButton("DASHBOARD");
        btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDashboard.setBounds(21, 232, 200, 30);
        add(btnDashboard);

        btnFileMaintenance = new JButton("BOOK MAINTENANCE");
        btnFileMaintenance.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnFileMaintenance.setBounds(21, 314, 200, 30);
        add(btnFileMaintenance);

        btnCirculation = new JButton("CIRCULATION");
        btnCirculation.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCirculation.setBounds(20, 273, 200, 30);
        add(btnCirculation);

        btnInventory = new JButton("BOOK INVENTORY");
        btnInventory.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnInventory.setBounds(21, 396, 200, 30);
        add(btnInventory);

        btnUserMaintenance = new JButton("USER MAINTENANCE");
        btnUserMaintenance.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnUserMaintenance.setBounds(21, 355, 200, 30);
        add(btnUserMaintenance);

        btnAuditTrail = new JButton("AUDIT TRAIL");
        btnAuditTrail.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAuditTrail.setBounds(20, 437, 200, 30);
        add(btnAuditTrail);
        
        btnLogout = new JButton("Log out");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLogout.setBounds(20, 642, 200, 30);
        add(btnLogout);
    }


    private void initViews() {
        dashboardView = new DashboardView();
        circulationView = new CirculationView(mainFrame);
        bookMaintenanceView = new BookMaintenanceView();
        studentMaintenanceView = new StudentMaintenanceView(mainFrame);
        inventoryView = new InventoryView(mainFrame);
        acquisitionView = new AcquisitionView(mainFrame);
        
        inventoryView.setAcquisitionView(acquisitionView);
        acquisitionView.setInventoryView(inventoryView);

    }

    public void initAction() {
      
        btnDashboard.addActionListener(e ->
            mainFrame.showPanel(dashboardView));

        btnCirculation.addActionListener(e ->
            mainFrame.showPanel(circulationView));

        btnFileMaintenance.addActionListener(e ->
            mainFrame.showPanel(bookMaintenanceView));


        btnUserMaintenance.addActionListener(e ->
            mainFrame.showPanel(studentMaintenanceView));

        btnInventory.addActionListener(e ->
            mainFrame.showPanel(inventoryView));
           
    	}
    
    public AcquisitionView getAcquisitionInstance() {
    	return acquisitionView;
    }
}