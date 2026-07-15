package view.CirculationModule;

import javax.swing.JPanel;

import controller.CirculationModule.CirculationController;
import controller.CirculationModule.VisitLogController;
import controller.UserModule.StudentTab.StudentMaintenanceController;
import model.dto.VisitLogDisplay;
import utility.AppContext;
import view.MainFrame;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class VisitorLogView extends JPanel {
	private MainFrame mainFrame;

	private static final long serialVersionUID = 1L;

	private VisitLogController visitLogController = AppContext.getInstance().getVisitLogController();
	private CirculationController circulationController = AppContext.getInstance().getCirculationController();
	private StudentMaintenanceController studentMaintenanceController = AppContext.getInstance().getStudentMaintenanceController();

	private JPanel tblBorder;
	private JButton btnVisitLog;
	private JButton btnCirculation;
	private JPanel tabPanel;
	private DefaultTableModel tblModel;
	private JTable tblVisitor;
	private JScrollPane scrollPane;

	private JLabel lblSearch;
	private JTextField txtSearch;
	private JPanel borrowerPanel;
	private JTextField txtBorrowerIdNo;
	private JButton btnFindBorrower;
	private JLabel lblFullName;
	private JTextField txtFullName;
	private JLabel lblDepartment;
	private JTextField txtDepartment;
	private JLabel lblGrade;
	private JTextField txtGrade;
	private JLabel lblSection;
	private JTextField txtSection;
	private JLabel lblBorrower;
	private JPanel actionPanel;
	private JLabel lblTime;
	private JLabel lblDate;
	private JLabel lblTimeValue;
	private JLabel lblDateValue;
	private JLabel lblBorrowerType;
	private Timer clockTimer;

	private int selectedBorrowerId = -1;
	private String selectedBorrowerType = "";

	private int selectedVisitorId = -1;

	// BAGO: parallel list na kasabay ng tblModel, para dito kukunin ang department/grade/section
	private ArrayList<VisitLogDisplay> currentVisitList = new ArrayList<>();

	private JButton btnTimeIn;
	private JButton btnTimeOut;

	public VisitorLogView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		initialize();
	}

	public void initialize() {
		setPanel();
		initComponent();
		initAction();
	}

	public void setPanel() {
		this.setLayout(null);
		this.setSize(1126, 743);
	}

	public void initComponent() {

		lblBorrower = new JLabel("Borrower");
		lblBorrower.setOpaque(true);
		lblBorrower.setForeground(new Color(51, 102, 51));
		lblBorrower.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBorrower.setBackground(UIManager.getColor("Button.background"));
		lblBorrower.setBounds(134, 53, 80, 14);
		add(lblBorrower);

		lblSearch = new JLabel("Search");
		lblSearch.setBounds(780, 304, 58, 23);
		add(lblSearch);
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setOpaque(true);

		txtSearch = new JTextField();
		txtSearch.setBounds(848, 304, 174, 23);
		add(txtSearch);
		txtSearch.setColumns(10);

		tblBorder = new JPanel();
		tblBorder.setLayout(null);
		tblBorder.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		tblBorder.setBounds(115, 316, 916, 397);
		add(tblBorder);

		String[] column = {"Visit ID", "BORROWER ID", "FULL NAME", "TIME IN", "TIME OUT"};
		tblModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblVisitor = new JTable(tblModel);

		scrollPane = new JScrollPane(tblVisitor);
		scrollPane.setBounds(10, 32, 895, 354);
		tblBorder.add(scrollPane);

		tblVisitor.getColumnModel().getColumn(0).setMinWidth(0);
		tblVisitor.getColumnModel().getColumn(0).setMaxWidth(0);
		tblVisitor.getColumnModel().getColumn(0).setWidth(0);
		
		tabPanel = new JPanel();
		tabPanel.setLayout(null);
		tabPanel.setBorder(new LineBorder(new Color(192, 192, 192), 18, true));
		tabPanel.setBackground(Color.LIGHT_GRAY);
		tabPanel.setBounds(115, 0, 295, 42);
		add(tabPanel);

		btnCirculation = new JButton("CIRCULATION");
		btnCirculation.setForeground(new Color(51, 102, 51));
		btnCirculation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCirculation.setEnabled(true);
		btnCirculation.setBounds(10, 11, 132, 20);
		tabPanel.add(btnCirculation);

		btnVisitLog = new JButton("VISITOR LOG");
		btnVisitLog.setForeground(new Color(51, 102, 51));
		btnVisitLog.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVisitLog.setEnabled(true);
		btnVisitLog.setBounds(152, 11, 132, 20);
		tabPanel.add(btnVisitLog);

		borrowerPanel = new JPanel();
		borrowerPanel.setLayout(null);
		borrowerPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
		borrowerPanel.setBounds(115, 61, 916, 210);
		add(borrowerPanel);

		JLabel lblAction = new JLabel(" Action");
		lblAction.setBounds(605, 22, 63, 14);
		lblAction.setOpaque(true);
		lblAction.setForeground(new Color(51, 102, 51));
		lblAction.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAction.setBackground(UIManager.getColor("Button.background"));
		borrowerPanel.add(lblAction);

		txtBorrowerIdNo = new JTextField();
		txtBorrowerIdNo.setEditable(false);
		txtBorrowerIdNo.setColumns(10);
		txtBorrowerIdNo.setBounds(187, 20, 103, 20);
		borrowerPanel.add(txtBorrowerIdNo);

		btnFindBorrower = new JButton("FIND");
		btnFindBorrower.setForeground(new Color(51, 102, 51));
		btnFindBorrower.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFindBorrower.setEnabled(true);
		btnFindBorrower.setBounds(293, 20, 68, 19);
		borrowerPanel.add(btnFindBorrower);

		lblFullName = new JLabel("Full Name :");
		lblFullName.setForeground(new Color(0, 100, 0));
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFullName.setBounds(112, 49, 68, 18);
		borrowerPanel.add(lblFullName);

		txtFullName = new JTextField();
		txtFullName.setEditable(false);
		txtFullName.setColumns(10);
		txtFullName.setBounds(187, 49, 174, 20);
		borrowerPanel.add(txtFullName);

		lblDepartment = new JLabel("Department :");
		lblDepartment.setForeground(new Color(0, 100, 0));
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDepartment.setBounds(96, 73, 83, 20);
		borrowerPanel.add(lblDepartment);

		txtDepartment = new JTextField();
		txtDepartment.setEditable(false);
		txtDepartment.setColumns(10);
		txtDepartment.setBounds(187, 75, 174, 20);
		borrowerPanel.add(txtDepartment);

		lblGrade = new JLabel("Grade :");
		lblGrade.setForeground(new Color(0, 100, 0));
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrade.setBounds(134, 98, 45, 20);
		borrowerPanel.add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setColumns(10);
		txtGrade.setBounds(187, 100, 174, 20);
		borrowerPanel.add(txtGrade);

		lblSection = new JLabel("Section :");
		lblSection.setForeground(new Color(0, 100, 0));
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSection.setBounds(125, 123, 54, 20);
		borrowerPanel.add(lblSection);

		txtSection = new JTextField();
		txtSection.setEditable(false);
		txtSection.setColumns(10);
		txtSection.setBounds(187, 125, 174, 20);
		borrowerPanel.add(txtSection);

		actionPanel = new JPanel();
		actionPanel.setBounds(582, 31, 295, 114);
		borrowerPanel.add(actionPanel);
		actionPanel.setLayout(null);
		actionPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));

		btnTimeIn = new JButton("Time in");
		btnTimeIn.setForeground(new Color(51, 102, 51));
		btnTimeIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimeIn.setEnabled(true);
		btnTimeIn.setBounds(32, 25, 103, 20);
		actionPanel.add(btnTimeIn);

		btnTimeOut = new JButton("Time out");
		btnTimeOut.setForeground(new Color(51, 102, 51));
		btnTimeOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimeOut.setEnabled(true);
		btnTimeOut.setBounds(145, 25, 103, 20);
		actionPanel.add(btnTimeOut);

		lblTime = new JLabel("Time :");
		lblTime.setForeground(new Color(0, 100, 0));
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTime.setBounds(32, 85, 124, 18);
		actionPanel.add(lblTime);

		lblDate = new JLabel("Date :");
		lblDate.setForeground(new Color(0, 100, 0));
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(32, 64, 124, 18);
		actionPanel.add(lblDate);
		
		lblTimeValue = new JLabel();
		lblTimeValue.setForeground(new Color(0, 100, 0));
		lblTimeValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeValue.setBounds(80, 85, 150, 18); // ayusin mo na lang position depende sa layout mo
		actionPanel.add(lblTimeValue);

		lblDateValue = new JLabel();
		lblDateValue.setForeground(new Color(0, 100, 0));
		lblDateValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateValue.setBounds(80, 64, 150, 18);
		actionPanel.add(lblDateValue);

		lblBorrowerType = new JLabel("LRN / EmpNo :");
		lblBorrowerType.setForeground(new Color(0, 100, 0));
		lblBorrowerType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBorrowerType.setBounds(87, 19, 92, 18);
		borrowerPanel.add(lblBorrowerType);
	}

	private void initAction() {
		btnCirculation.addActionListener(e -> {
			mainFrame.showPanel(new CirculationView(mainFrame));
		});

		btnFindBorrower.addActionListener(e -> {
			loadBorrowerDialog();
		});

		btnTimeIn.addActionListener(e -> {
			timeIn();
		});

		btnTimeOut.addActionListener(e -> {
			timeOut();
		});

		loadVisitLog();

		startClock();
		
		tblVisitor.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) return;

			int selectedRow = tblVisitor.getSelectedRow();

			if (selectedRow != -1) {
				VisitLogDisplay selected = currentVisitList.get(selectedRow);

				selectedVisitorId = selected.getVisitId();

				txtBorrowerIdNo.setText(selected.getBorrowerNo());
				txtFullName.setText(selected.getBorrowerName());
				txtDepartment.setText(selected.getDepartment() != null ? selected.getDepartment() : "-");
				txtGrade.setText(selected.getGrade() != null ? selected.getGrade() : "-");
				txtSection.setText(selected.getSection() != null ? selected.getSection() : "-");
			}
		});
	}

	public void loadBorrowerDialog() {
		BorrowerDialogPicker borrowerDialogPicker = new BorrowerDialogPicker();
		borrowerDialogPicker.setModal(true);
		borrowerDialogPicker.setVisible(true);

		selectedBorrowerId = borrowerDialogPicker.getSelectedBorrowerId();
		selectedBorrowerType = borrowerDialogPicker.getSelectedBorrowerType();

		String selectedBorroweNo = borrowerDialogPicker.getSelectedBorroweNo();
		String selectedBorrowerName = borrowerDialogPicker.getSelectedBorrowerName();
		String selectedBorrowerDept = borrowerDialogPicker.getSelectedBorrowerDept();
		String selectedBorrowerGrade = borrowerDialogPicker.getSelectedBorrowrGrade();
		String selectedBorrowerSec = borrowerDialogPicker.getSelectedBorrowerSec();

		txtBorrowerIdNo.setText(selectedBorroweNo);
		txtFullName.setText(selectedBorrowerName);
		txtDepartment.setText(selectedBorrowerDept);
		txtGrade.setText(selectedBorrowerGrade);
		txtSection.setText(selectedBorrowerSec);
	}

	public void loadVisitLog() {
		tblModel.setRowCount(0);
		currentVisitList.clear();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a");

		ArrayList<VisitLogDisplay> visitorList = visitLogController.loadVisitor();
		currentVisitList.addAll(visitorList);

		for (VisitLogDisplay vLog : visitorList) {
			tblModel.addRow(new Object[] {
					vLog.getVisitId(),
					vLog.getBorrowerNo(),
					vLog.getBorrowerName(),
					vLog.getTimeIn().format(formatter),
					vLog.getTimeOut() == null ? "-" : vLog.getTimeOut().format(formatter)
			});
		}
	}

	public void timeIn() {
		int id = selectedBorrowerId;
		String borrowerType = selectedBorrowerType;
		try {
			visitLogController.timeIn(id, borrowerType);
			JOptionPane.showMessageDialog(this, "Time in");
			loadVisitLog();
			clearFields();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Success", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void timeOut() {
		int visitorId = selectedVisitorId;
		try {
			visitLogController.timeOut(visitorId);
			JOptionPane.showMessageDialog(this, "Time out");
			loadVisitLog();
			clearFields() ;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	

	public void clearFields() {
		selectedBorrowerId = -1;
		selectedVisitorId = -1;
		
		txtBorrowerIdNo.setText("");
		txtFullName.setText("");
		txtDepartment.setText("");
		txtGrade.setText("");
		txtSection.setText("");
	}
	
	
	private void startClock() {
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

	    clockTimer = new Timer(1000, e -> {
	        LocalDateTime now = LocalDateTime.now();
	        lblTimeValue.setText(now.format(timeFormatter));
	        lblDateValue.setText(now.format(dateFormatter));
	    });
	    clockTimer.setInitialDelay(0);
	    clockTimer.start();
	}
	
	
}