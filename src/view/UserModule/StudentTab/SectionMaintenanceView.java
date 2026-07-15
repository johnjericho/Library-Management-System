package view.UserModule.StudentTab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.UserModule.StudentTab.DepartmentController;
import controller.UserModule.StudentTab.GradeController;
import controller.UserModule.StudentTab.SectionController;
import dao.UserModule.StudentTab.DepartmentDAO;
import dao.UserModule.StudentTab.GradeDAO;
import dao.UserModule.StudentTab.SectionDAO;
import model.UserModule.StudentTab.Department;
import model.UserModule.StudentTab.Grade;
import services.UserModule.StudentTab.DepartmentServices;
import services.UserModule.StudentTab.GradeServices;
import services.UserModule.StudentTab.SectionServices;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;

public class SectionMaintenanceView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtSection;
	private JTextField txtSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JComboBox<Department> cmbDepartment;
	private JComboBox<Grade> cmbGrade;

	private DefaultTableModel tblModel;
	private JTable tblSection;
	private JScrollPane scrollPane;

	public SectionMaintenanceView() {
		 initialize();
	}
	
	public void initialize() {
		setPanel();
		initComponent();
		loadDepartments();
		initAction();
	}
	
	public void setPanel() {
		this.setBounds(100, 100, 900, 720);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 884, 681);
		this.getContentPane().add(contentPanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	
	public void initComponent() {
		
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(41, 72, 803, 120);
		contentPanel.add(componentsBorder);
		
		JLabel lblSection = new JLabel("Section: ");
		lblSection.setForeground(new Color(51, 102, 51));
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSection.setBounds(25, 18, 139, 32);
		componentsBorder.add(lblSection);
		
		txtSection = new JTextField();
		txtSection.setText("");
		txtSection.setColumns(10);
		txtSection.setBounds(142, 23, 209, 23);
		componentsBorder.add(txtSection);
		
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 68, 80, 20);
		componentsBorder.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(133, 68, 95, 20);
		componentsBorder.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(256, 68, 95, 20);
		componentsBorder.add(btnDelete);

		// ===== BAGONG DEPARTMENT / GRADE COMBOBOX (kanang bahagi, walang ma-o-overlap) =====
		JLabel lblDepartment = new JLabel("Department: ");
		lblDepartment.setForeground(new Color(51, 102, 51));
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartment.setBounds(400, 18, 100, 20);
		componentsBorder.add(lblDepartment);

		cmbDepartment = new JComboBox<>();
		cmbDepartment.setBounds(500, 18, 200, 22);
		componentsBorder.add(cmbDepartment);

		JLabel lblGrade = new JLabel("Grade: ");
		lblGrade.setForeground(new Color(51, 102, 51));
		lblGrade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrade.setBounds(400, 50, 100, 20);
		componentsBorder.add(lblGrade);

		cmbGrade = new JComboBox<>();
		cmbGrade.setBounds(500, 50, 200, 22);
		componentsBorder.add(cmbGrade);
		// ===== END BAGONG COMBOBOX =====
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(40, 660, 803, 10);
		contentPanel.add(separator);
			
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(637, 203, 206, 23);
		contentPanel.add(txtSearch);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(576, 207, 64, 14);
		contentPanel.add(lblSearch);	
		
	       String[] column = {"Section ID", "Section"};
	       tblModel = new DefaultTableModel(column, 0) {
	    	   public boolean  isCellEditable(int row, int column){ return false; }
	       };  
	       tblSection = new JTable(tblModel);

	       scrollPane = new JScrollPane(tblSection);
	       scrollPane.setBounds(41, 237, 803, 412);	       
	       contentPanel.add(scrollPane);
		
	}

	// ===== BAGONG METHOD: pinopopulate ang cmbDepartment gamit DB data =====
	public void loadDepartments() {
		DepartmentController deptController =
			new DepartmentController(new DepartmentServices(new DepartmentDAO()));

		ArrayList<Department> departments = deptController.getAllDepartments();

		cmbDepartment.removeAllItems();
		for (Department d : departments) {
			cmbDepartment.addItem(d);
		}
	}
	
	public void initAction() {

		// ===== BAGONG LISTENER: kapag pinili ang department, mag-lo-load ng grades =====
		cmbDepartment.addItemListener(e -> {
			Department selectedDept = (Department) cmbDepartment.getSelectedItem();
			if (selectedDept == null) return;

			GradeController gradeController =
				new GradeController(new GradeServices(new GradeDAO()));

			ArrayList<Grade> grades = gradeController.getGradesByDepartment(selectedDept.getDepartmentId());

			cmbGrade.removeAllItems();
			for (Grade g : grades) {
				cmbGrade.addItem(g);
			}
		});

		btnAdd.addActionListener(e ->{
			String name = txtSection.getText();
			Grade selectedGrade = (Grade) cmbGrade.getSelectedItem();

			if (selectedGrade == null) {
				JOptionPane.showMessageDialog(this, "Please select a grade.");
				return;
			}

			int gradeId = selectedGrade.getGradeId();

			SectionController section = new SectionController(new SectionServices(new SectionDAO()));
			section.addSection(name, gradeId);
		});
	}
	
	
	public static void main(String[] args) {
		try {
			SectionMaintenanceView dialog = new SectionMaintenanceView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}