package view.CirculationModule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.UserModule.FacultyTab.FacultyMaintenanceController;
import controller.UserModule.StudentTab.StudentMaintenanceController;
import model.dto.BorrowerDisplay;
import model.dto.FacultyDisplay;
import model.dto.StudentDisplay;
import utility.AppContext;

public class BorrowerDialogPicker extends JDialog {

    private static final long serialVersionUID = 1L;
    private StudentMaintenanceController studentMaintenanceController = AppContext.getInstance().getStudentMaintenanceController();
    private FacultyMaintenanceController facultyMaintenanceController = AppContext.getInstance().getFacultyMaintenanceController();

    
    private JPanel contentPanel;
    private JTable tblBorrower;
    private JScrollPane scrollPane;
    private JTextField txtSearch;
    private DefaultTableModel tblModel;

    private ArrayList<BorrowerDisplay> borrowerList = new ArrayList<>();
    
    private int selectedBorrowerId = -1;
    private String selectedBorrowerType = "";
    private String selectedBorroweNo = "";
    private String selectedBorrowerName = "";
    private String selectedBorrowerDept = "";
    private String selectedBorrowerGrade = "";
    private String selectedBorrowerSec = "";


	public BorrowerDialogPicker() {
        initialize();
    }

    public void initialize() {
        setDialog();
        initComponent();
        initAction();
        loadBorrower();
    }

    public void setDialog() {

        setBounds(100, 100, 850, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.setBounds(0, 0, 834, 461);
        contentPanel.setLayout(null);

        getContentPane().add(contentPanel);

    }

    public void initComponent() {

        JPanel tblPanel = new JPanel();
        tblPanel.setBorder(new LineBorder(new Color(0, 128, 0), 3, true));
        tblPanel.setBounds(10, 52, 814, 360);
        tblPanel.setLayout(null);
        contentPanel.add(tblPanel);

        String[] column = {
                "ID",
                "TYPE",
                "BORROWER ID",
                "FULL NAME",
                "DEPARTMENT",
                "GRADE",
                "SECTION"
        };

        tblModel = new DefaultTableModel(column, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblBorrower = new JTable(tblModel);
        tblBorrower.setRowHeight(30);

        scrollPane = new JScrollPane(tblBorrower);
        scrollPane.setBounds(10, 35, 794, 315);
        tblPanel.add(scrollPane);

        JLabel lblSearch = new JLabel("Search");
        lblSearch.setForeground(new Color(51, 102, 51));
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSearch.setBounds(560, 18, 70, 20);
        contentPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(630, 18, 194, 25);
        contentPanel.add(txtSearch);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 430, 814, 10);
        contentPanel.add(separator);

        // hide ID
        tblBorrower.getColumnModel().getColumn(0).setMinWidth(0);
        tblBorrower.getColumnModel().getColumn(0).setMaxWidth(0);
        tblBorrower.getColumnModel().getColumn(0).setWidth(0);

    }

    public void initAction() {

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) {filter(); }
            public void changedUpdate(DocumentEvent e) {filter();   }

        });

        tblBorrower.addMouseListener(new MouseAdapter() {
            
        		public void mouseClicked(MouseEvent e) {
                int row = tblBorrower.getSelectedRow();
                if (row == -1)  return;
                
                selectedBorrowerId = (int) tblModel.getValueAt(row, 0);
                selectedBorrowerType = (String) tblModel.getValueAt(row, 1);
                
                selectedBorroweNo = (String) tblModel.getValueAt(row, 2);
                selectedBorrowerName = (String) tblModel.getValueAt(row, 3);
                selectedBorrowerDept = (String) tblModel.getValueAt(row, 4);
                selectedBorrowerGrade = (String) tblModel.getValueAt(row, 5);
                selectedBorrowerSec = (String) tblModel.getValueAt(row, 6);

                if (e.getClickCount() == 2) {
                    dispose();
                }

            }

        });

    }

    public void loadBorrower() {

        try {

            borrowerList.clear();
            ArrayList<StudentDisplay> studentlist = studentMaintenanceController.loadStudentDisplay();

            for(StudentDisplay s : studentlist) {
            	borrowerList.add(new BorrowerDisplay(
            						s.getStudentId(),
            						"Student",
            						s.getLrn(),
            						s.getLastName() +" "+ s.getFirstName() +" "+ s.getMiddleName(),
            						s.getDepartmentName(),
            						s.getGradeLvl(),
            						s.getSectionName()
            						));   	
            					}
            
            
            ArrayList<FacultyDisplay> facultyList = facultyMaintenanceController.loadFacultyDisplay();

            for(FacultyDisplay f : facultyList) {
            	borrowerList.add(new BorrowerDisplay(
            						f.getFacultyId(),
            						"Faculty",
            						f.getEmployeeNo(),
            						f.getLastName() +" "+ f.getFirstName() +" "+ f.getMiddleName(),
            						f.getDepartmentName(),
            						f.getGradeLvl(),
            						"-"					
            						));   	
            					}
            
            
            populateTable(borrowerList);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning",JOptionPane.WARNING_MESSAGE);
        }

    }

    private void populateTable(ArrayList<BorrowerDisplay> list) {

        tblModel.setRowCount(0);

        for (BorrowerDisplay borrower : list) {

            tblModel.addRow(new Object[] {
                    borrower.getBorroweId(),
                    borrower.getBorrowerType(),  
                    borrower.getBorrowerUniqueNo(),
                    borrower.getFullName(),           
                    borrower.getDepartment(),
                    borrower.getGrade(),
                    borrower.getSection()
            });

        }

    }

    private void filter() {

        String keyword = txtSearch.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {

            populateTable(borrowerList);
            return;

        }

        ArrayList<BorrowerDisplay> filtered = new ArrayList<>();

        for (BorrowerDisplay borrower : borrowerList) {

            if (borrower.getBorrowerType().toLowerCase().contains(keyword)
                || borrower.getFullName().toLowerCase().contains(keyword)
                || borrower.getDepartment().toLowerCase().contains(keyword)
                || borrower.getGrade().toLowerCase().contains(keyword)) {

                filtered.add(borrower);

            }

        }

        populateTable(filtered);

    }

    public int getSelectedBorrowerId() {
        return selectedBorrowerId;
    }
    
    public String getSelectedBorrowerType() {
    	return selectedBorrowerType;
    }
    
    public String getSelectedBorroweNo() {
		return selectedBorroweNo;
	}

    public String getSelectedBorrowerName() {
        return selectedBorrowerName;
    }
    

	public String getSelectedBorrowerDept() {
		return selectedBorrowerDept;
	}

	public String getSelectedBorrowrGrade() {
		return selectedBorrowerGrade;
	}

	public String getSelectedBorrowerSec() {
		return selectedBorrowerSec;
	}

}