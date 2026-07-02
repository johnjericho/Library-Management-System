package view;

import javax.swing.JPanel;

import view.AcquisitionModule.AcquisitionView;
import view.BookModule.BookMaintenanceView;
import view.InventoryModule.InventoryView;
import view.UserModule.UserMaintenanceView;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modules extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnDashboard;
    private JButton btnFileMaintenance;
    private JButton btnCirculation;

    // BINAGO: AdminDash → MainFrame na ang reference
    private MainFrame mainFrame;
    private JButton btnInventory;
    private JButton btnUserMaintenance;
    private JButton btnAquition;
   

    
    // BINAGO: AdminDash → MainFrame na ang parameter
    public Modules(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPanel();
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
        btnFileMaintenance.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnFileMaintenance.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnFileMaintenance.setBounds(21, 314, 200, 30);
        add(btnFileMaintenance);

        btnCirculation = new JButton("CIRCULATION");
        btnCirculation.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCirculation.setBounds(20, 273, 200, 30);
        add(btnCirculation);
        
        btnInventory = new JButton("BOOK INVENTORY");
        btnInventory.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnInventory.setBounds(21, 437, 200, 30);
        add(btnInventory);
        
        btnUserMaintenance = new JButton("USER MAINTENANCE");
        btnUserMaintenance.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnUserMaintenance.setBounds(20, 396, 200, 30);
        add(btnUserMaintenance);
        
        btnAquition = new JButton("ACQUISITION");
        btnAquition.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAquition.setBounds(20, 355, 200, 30);
        add(btnAquition);
    }

    public void initAction() {
        // BINAGO: mainFrame na ang ginagamit, hindi na adminDash
        btnDashboard.addActionListener(e ->
            mainFrame.showPanel(new DashboardView()));

        btnFileMaintenance.addActionListener(e ->
            mainFrame.showPanel(new BookMaintenanceView()));
        
        btnAquition.addActionListener(e ->{
        	mainFrame.showPanel(new AcquisitionView());
        });
        
        btnUserMaintenance.addActionListener( e -> {
        	mainFrame.showPanel(new UserMaintenanceView());
        });

        btnInventory.addActionListener(e ->{
        	mainFrame.showPanel(new InventoryView());
        });        
    }
}