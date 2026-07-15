package view.UserModule.FacultyTab;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.UserModule.StudentTab.DepartmentController;
import controller.UserModule.StudentTab.GradeController;
import controller.UserModule.FacultyTab.FacultyMaintenanceController;
import model.UserModule.StudentTab.Department;
import model.UserModule.StudentTab.Grade;
import model.dto.FacultyDisplay;
import utility.AppContext;
import view.MainFrame;
import view.UserModule.LibrarianTab.LibarianMaintenanceView;
import view.UserModule.StudentTab.StudentMaintenanceView;

import javax.swing.JComboBox;
import java.util.ArrayList;

public class FacultyMaintenanceView extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;

	private JTextField txtEmployeNo;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMiddleName;
	private JComboBox cmbGender;
	private JTextField txtSearch;
	private JButton btnFaculty;
	private JButton btnStudent;
	private JButton btnLibrarian;
	private JTextField txtContactNo;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JComboBox<Department> cmbDepartment;
	private JComboBox<Grade> cmbGrade;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable tblFaculty;
	private DefaultTableModel tblModel;
	private JScrollPane scrollPane;

	private DepartmentController departmentController = AppContext.getInstance().getDepartmentController();
	private GradeController gradeController = AppContext.getInstance().getdGradeController();
	private FacultyMaintenanceController facultyMaintenanceController = AppContext.getInstance().getFacultyMaintenanceController();

	private int selectedFacultyId = -1;
	private boolean isEditable = false;

	public FacultyMaintenanceView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		initialize();
	}
	
	  public void initialize() {
	    	setPanel();
	    	initComponent();
	    	initAction();
	        loadDepartments();
	    }
	
	   public void setPanel(){
	       this.setLayout(null);
	       this.setSize(1126,743);
	    }
	   
	   
	   public void initComponent() {
		   
	       JPanel componentsBorder = new JPanel();
	       componentsBorder.setLayout(null);
	       componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
	       componentsBorder.setBounds(18, 65, 1083, 216);
	       add(componentsBorder);
	       
	       JLabel lblEmplyeeNo = new JLabel("Employee no.");
	       lblEmplyeeNo.setForeground(new Color(51, 102, 51));
	       lblEmplyeeNo.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblEmplyeeNo.setBounds(20, 30, 107, 17);
	       componentsBorder.add(lblEmplyeeNo);
	       
	       JLabel lblFirstName = new JLabel("First name");
	       lblFirstName.setForeground(new Color(51, 102, 51));
	       lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblFirstName.setBounds(37, 61, 80, 14);
	       componentsBorder.add(lblFirstName);
	       
	       JLabel lblLastName = new JLabel("Last name");
	       lblLastName.setForeground(new Color(51, 102, 51));
	       lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblLastName.setBounds(37, 90, 80, 14);
	       componentsBorder.add(lblLastName);
	       
	       JLabel lblMiddleName = new JLabel("Middle name");
	       lblMiddleName.setForeground(new Color(51, 102, 51));
	       lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblMiddleName.setBounds(20, 122, 97, 14);
	       componentsBorder.add(lblMiddleName);
	       
	       JLabel lblGender = new JLabel("Gender");
	       lblGender.setForeground(new Color(51, 102, 51));
	       lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblGender.setBounds(62, 145, 54, 27);
	       componentsBorder.add(lblGender);
	       
	       JSeparator separator_1 = new JSeparator();
	       separator_1.setOrientation(SwingConstants.VERTICAL);
	       separator_1.setForeground(new Color(51, 204, 51));
	       separator_1.setBounds(534, 11, 24, 194);
	       componentsBorder.add(separator_1);
	       
	       txtEmployeNo = new JTextField();
	       txtEmployeNo.setColumns(10);
	       txtEmployeNo.setBounds(125, 27, 191, 20);
	       componentsBorder.add(txtEmployeNo);
	       
	       txtFirstName = new JTextField();
	       txtFirstName.setColumns(10);
	       txtFirstName.setBounds(125, 58, 191, 20);
	       componentsBorder.add(txtFirstName);
	       
	       txtLastName = new JTextField();
	       txtLastName.setColumns(10);
	       txtLastName.setBounds(125, 87, 191, 20);
	       componentsBorder.add(txtLastName);
	       
	       txtMiddleName = new JTextField();
	       txtMiddleName.setColumns(10);
	       txtMiddleName.setBounds(125, 118, 191, 20);
	       componentsBorder.add(txtMiddleName);
	       
	       cmbGender = new JComboBox();
	       cmbGender.setBounds(125, 147, 191, 20);
	       cmbGender.addItem(null);
	       cmbGender.addItem("male");
	       cmbGender.addItem("female");
	       componentsBorder.add(cmbGender);
	       
	       JLabel lblDepartment = new JLabel("Department");
	       lblDepartment.setForeground(new Color(51, 102, 51));
	       lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblDepartment.setBounds(587, 30, 94, 20);
	       componentsBorder.add(lblDepartment);
	       
	       cmbDepartment = new JComboBox<>();
	       cmbDepartment.setBounds(682, 32, 191, 20);
	       componentsBorder.add(cmbDepartment);
	       
	       JLabel lblGrade = new JLabel("Grade");
	       lblGrade.setForeground(new Color(51, 102, 51));
	       lblGrade.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblGrade.setBounds(627, 61, 54, 23);
	       componentsBorder.add(lblGrade);
	       
	       cmbGrade = new JComboBox<Grade>();
	       cmbGrade.setBounds(682, 63, 191, 19);
	       componentsBorder.add(cmbGrade);
	       
	       JLabel lblContactNo = new JLabel("Phone");
	       lblContactNo.setForeground(new Color(51, 102, 51));
	       lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblContactNo.setBounds(627, 91, 54, 20);
	       componentsBorder.add(lblContactNo);
	       
	       txtContactNo = new JTextField();
	       txtContactNo.setColumns(10);
	       txtContactNo.setBounds(682, 90, 191, 20);
	       componentsBorder.add(txtContactNo);
	       
	       JLabel lblEmail = new JLabel("Email");
	       lblEmail.setForeground(new Color(51, 102, 51));
	       lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblEmail.setBounds(629, 120, 46, 20);
	       componentsBorder.add(lblEmail);
	       
	       txtEmail = new JTextField();
	       txtEmail.setColumns(10);
	       txtEmail.setBounds(682, 119, 191, 20);
	       componentsBorder.add(txtEmail);
	       
	       txtAddress = new JTextField();
	       txtAddress.setColumns(10);
	       txtAddress.setBounds(682, 147, 191, 58);
	       componentsBorder.add(txtAddress);
	       
	       JLabel lblAddress = new JLabel("Address");
	       lblAddress.setForeground(new Color(51, 102, 51));
	       lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblAddress.setBounds(614, 147, 65, 20);
	       componentsBorder.add(lblAddress);
	       
	       String[] column = {"facultyId", "EMPLOYEE NO", "FIRST NAME", "LAST NAME", "MIDDLE NAME",
	    		   			   "GENDER", "DEPARTMENT", "GRADE", "PHONE", "EMAIL", "ADDRESS"};
	       tblModel = new DefaultTableModel(column, 0) {
	    	   public boolean isCellEditable(int row, int column){ return false; }
	       };
	       tblFaculty = new JTable(tblModel);
	       
	       scrollPane = new JScrollPane(tblFaculty);
	       scrollPane.setBounds(18, 330, 1083, 383);
	       add(scrollPane);
	       
	       btnAdd = new JButton("ADD");
	       btnAdd.setForeground(new Color(51, 102, 51));
	       btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
	       btnAdd.setEnabled(true);
	       btnAdd.setBounds(18, 295, 80, 20);
	       add(btnAdd);
	       
	       btnUpdate = new JButton("UPDATE");
	       btnUpdate.setForeground(new Color(51, 102, 51));
	       btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
	       btnUpdate.setEnabled(false);
	       btnUpdate.setBounds(108, 295, 95, 20);
	       add(btnUpdate);
	       
	       btnDelete = new JButton("DELETE");
	       btnDelete.setForeground(new Color(51, 102, 51));
	       btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
	       btnDelete.setEnabled(false);
	       btnDelete.setBounds(213, 295, 95, 20);
	       add(btnDelete);
	       
	       JSeparator separator = new JSeparator();
	       separator.setBackground(new Color(51, 204, 51));
	       separator.setBounds(18, 724, 1083, 6);
	       add(separator);
	       
	       JLabel lblSearch = new JLabel("Search");
	       lblSearch.setForeground(new Color(51, 102, 51));
	       lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblSearch.setBounds(859, 302, 58, 14);
	       add(lblSearch);
	       
	       txtSearch = new JTextField();
	       txtSearch.setColumns(10);
	       txtSearch.setBounds(927, 299, 174, 20);
	       add(txtSearch);
	       
	       JPanel panel = new JPanel();
	       panel.setBorder(new LineBorder(new Color(192, 192, 192), 18, true));
	       panel.setBackground(Color.LIGHT_GRAY);
	       panel.setBounds(41, 0, 369, 42);
	       add(panel);
	       panel.setLayout(null);
	       
	       btnStudent = new JButton("STUDENT");
	       btnStudent.setBounds(10, 11, 107, 20);
	       panel.add(btnStudent);
	       btnStudent.setForeground(new Color(51, 102, 51));
	       btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnStudent.setEnabled(true);
	       
	       btnLibrarian = new JButton("LIBRARIAN");
	       btnLibrarian.setBounds(244, 11, 114, 20);
	       panel.add(btnLibrarian);
	       btnLibrarian.setForeground(new Color(51, 102, 51));
	       btnLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnLibrarian.setEnabled(true);
	       
	       btnFaculty = new JButton("FACULTY");
	       btnFaculty.setBounds(127, 11, 107, 20);
	       panel.add(btnFaculty);
	       btnFaculty.setForeground(new Color(51, 102, 51));
	       btnFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnFaculty.setEnabled(true);
		   
	   }
	   
	   public void initAction(){
		   btnStudent.addActionListener(e ->{
			   StudentMaintenanceView studentView = new StudentMaintenanceView(mainFrame);
			   mainFrame.showPanel(studentView);
		   });
		   
		   btnLibrarian.addActionListener(e -> {
			   mainFrame.showPanel(new LibarianMaintenanceView(mainFrame));
		   });
		   
		   cmbDepartment.addItemListener(e -> {
			   Department selectedDept = (Department) cmbDepartment.getSelectedItem();
			   if (selectedDept == null) {
				   cmbGrade.removeAllItems();
				   return;
			   }

			   ArrayList<Grade> grades = gradeController.getGradesByDepartment(selectedDept.getDepartmentId());

			   cmbGrade.removeAllItems();
			   for (Grade g : grades) {
				   cmbGrade.addItem(g);
			   }
		   });
		   
		   // ESC key
		   this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
			   .put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "escapeAction");

		   this.getActionMap().put("escapeAction", new javax.swing.AbstractAction() {
			   public void actionPerformed(java.awt.event.ActionEvent e) {
				   tryExitEditMode();
			   }
		   });

		   // Empty area click
		   this.addMouseListener(new java.awt.event.MouseAdapter() {
			   public void mouseClicked(java.awt.event.MouseEvent e) {
				   tryExitEditMode();
			   }
		   });
		   
		   //CRUD
		   btnAdd.addActionListener(e -> {
			   addFaculty();
		   });
		   
		   loadFaculty();
		   
		   tblFaculty.getSelectionModel().addListSelectionListener(e -> {
			   if(e.getValueIsAdjusting()) return;
			   int selectedRow = tblFaculty.getSelectedRow();

			   if(selectedRow != -1) {

				   int id = (int) tblModel.getValueAt(selectedRow, 0);

				   String employeeNo = (String) tblModel.getValueAt(selectedRow, 1);
				   String firstName = (String) tblModel.getValueAt(selectedRow, 2);
				   String lastName = (String) tblModel.getValueAt(selectedRow, 3);
				   String middleName = (String) tblModel.getValueAt(selectedRow, 4);
				   String gender = (String) tblModel.getValueAt(selectedRow, 5);
				   String department = (String) tblModel.getValueAt(selectedRow, 6);
				   String grade = String.valueOf(tblModel.getValueAt(selectedRow, 7));
				   String contactNo = (String) tblModel.getValueAt(selectedRow, 8);
				   String email = (String) tblModel.getValueAt(selectedRow, 9);
				   String address = (String) tblModel.getValueAt(selectedRow, 10);

				   if(id != selectedFacultyId) {
					   selectedFacultyId = id;

					   txtEmployeNo.setText(employeeNo);
					   txtFirstName.setText(firstName);
					   txtLastName.setText(lastName);
					   txtMiddleName.setText(middleName);
					   cmbGender.setSelectedItem(gender);

					   for (int i = 0; i < cmbDepartment.getItemCount(); i++) {
						   Department d = cmbDepartment.getItemAt(i);
						   if (d != null && d.getDepartmentName().equals(department)) {
							   cmbDepartment.setSelectedItem(d);
							   break;
						   }
					   }

					   for (int i = 0; i < cmbGrade.getItemCount(); i++) {
						   Grade g = cmbGrade.getItemAt(i);
						   if (g != null && String.valueOf(g.getGradeLvl()).equals(grade)) {
							   cmbGrade.setSelectedItem(g);
							   break;
						   }
					   }

					   txtContactNo.setText(contactNo);
					   txtEmail.setText(email);
					   txtAddress.setText(address);

					   editMode();
				   }
			   }
		   });
		   
		   btnUpdate.addActionListener(e -> {
			   updateFaculty();
		   });
		   
		   btnDelete.addActionListener(e -> {
			   deleteFaculty();
		   });
	   }
	   
	   private void editMode() {
		   isEditable = true;
		   btnAdd.setEnabled(false);
		   btnUpdate.setEnabled(true);
		   btnDelete.setEnabled(true);
	   }
	   
	   private void exitEditMode() {
		   isEditable = false;
		   btnAdd.setEnabled(true);
		   btnUpdate.setEnabled(false);
		   btnDelete.setEnabled(false);
		   
		   txtEmployeNo.setText("");
		   txtFirstName.setText("");
		   txtLastName.setText("");
		   txtMiddleName.setText("");
		   cmbGender.setSelectedItem(null);
		   cmbDepartment.setSelectedItem(null);
		   cmbGrade.setSelectedItem(null);
		   txtContactNo.setText("");
		   txtEmail.setText("");
		   txtAddress.setText("");
		   
		   selectedFacultyId = -1;
		   tblFaculty.clearSelection();
	   }
	   
	   private void restoreSelectedRow() {
		   if(selectedFacultyId == -1) return;

		   for(int i = 0; i < tblModel.getRowCount(); i++) {
			   int rowId = (int) tblModel.getValueAt(i, 0);
			   if(rowId == selectedFacultyId) {
				   tblFaculty.setRowSelectionInterval(i, i);
				   break;
			   }
		   }
	   }
	   
	   private boolean hasChanges() {
		   String originalEmployeeNo = "";
		   String originalFirstName = "";
		   String originalLastName = "";
		   String originalMiddleName = "";
		   String originalGender = "";
		   String originalDepartment = "";
		   String originalGrade = "";
		   String originalContactNo = "";
		   String originalEmail = "";
		   String originalAddress = "";

		   for (int i = 0; i < tblModel.getRowCount(); i++) {
			   if ((int) tblModel.getValueAt(i, 0) == selectedFacultyId) {
				   originalEmployeeNo = (String) tblModel.getValueAt(i, 1);
				   originalFirstName = (String) tblModel.getValueAt(i, 2);
				   originalLastName = (String) tblModel.getValueAt(i, 3);
				   originalMiddleName = (String) tblModel.getValueAt(i, 4);
				   originalGender = (String) tblModel.getValueAt(i, 5);
				   originalDepartment = (String) tblModel.getValueAt(i, 6);
				   originalGrade = String.valueOf(tblModel.getValueAt(i, 7));
				   originalContactNo = (String) tblModel.getValueAt(i, 8);
				   originalEmail = (String) tblModel.getValueAt(i, 9);
				   originalAddress = (String) tblModel.getValueAt(i, 10);
				   break;
			   }
		   }

		   Department selectedDept = (Department) cmbDepartment.getSelectedItem();
		   Grade selectedGrade = (Grade) cmbGrade.getSelectedItem();
		   String currentDepartment = selectedDept != null ? selectedDept.getDepartmentName() : "";
		   String currentGrade = selectedGrade != null ? String.valueOf(selectedGrade.getGradeLvl()) : "";
		   String currentGender = cmbGender.getSelectedItem() != null ? (String) cmbGender.getSelectedItem() : "";

		   return !txtEmployeNo.getText().trim().equals(originalEmployeeNo) ||
				  !txtFirstName.getText().trim().equals(originalFirstName) ||
				  !txtLastName.getText().trim().equals(originalLastName) ||
				  !txtMiddleName.getText().trim().equals(originalMiddleName) ||
				  !currentGender.equals(originalGender) ||
				  !currentDepartment.equals(originalDepartment) ||
				  !currentGrade.equals(originalGrade) ||
				  !txtContactNo.getText().trim().equals(originalContactNo) ||
				  !txtEmail.getText().trim().equals(originalEmail) ||
				  !txtAddress.getText().trim().equals(originalAddress);
	   }
	   
	   private void tryExitEditMode() {
		   if (!isEditable) return;

		   if (hasChanges()) {
			   int confirm = JOptionPane.showConfirmDialog(
				   this, "Discard changes?", "Unsaved Changes", JOptionPane.YES_NO_OPTION
			   );
			   if (confirm == JOptionPane.YES_OPTION) {
				   exitEditMode();
			   }
		   } else {
			   exitEditMode();
		   }
	   }
	   
	   public void loadDepartments() {
		   ArrayList<Department> departments = departmentController.getAllDepartments();

		   cmbDepartment.removeAllItems();
		   cmbDepartment.addItem(null);
		   cmbGrade.addItem(null);
		   for (Department d : departments) {
			   cmbDepartment.addItem(d);
		   }
	   }
	   
	   public void addFaculty() {
		   String employeeNo = txtEmployeNo.getText();
		   String firstName = txtFirstName.getText();
		   String lastName = txtLastName.getText();
		   String middleName = txtMiddleName.getText();
		   String gender = (String) cmbGender.getSelectedItem();
		   Grade selectedGrade = (Grade) cmbGrade.getSelectedItem();
		   int gradeId = selectedGrade != null ? selectedGrade.getGradeId() : -1;
		   String contactNo = txtContactNo.getText();
		   String email = txtEmail.getText();
		   String address = txtAddress.getText();

		   try {
			   facultyMaintenanceController.addFaculty(employeeNo, firstName, lastName, middleName,
														  gender, gradeId, contactNo, email, address);
			   JOptionPane.showMessageDialog(this, "Successfully Added!");
			   loadFaculty();
			   exitEditMode();
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		   }
	   }
	   
	   public void updateFaculty() {
		   if (!hasChanges()) {
			   JOptionPane.showMessageDialog(this, "No changes detected.");
			   return;
		   }

		   String employeeNo = txtEmployeNo.getText();
		   String firstName = txtFirstName.getText();
		   String lastName = txtLastName.getText();
		   String middleName = txtMiddleName.getText();
		   String gender = (String) cmbGender.getSelectedItem();
		   Grade selectedGrade = (Grade) cmbGrade.getSelectedItem();
		   int gradeId = selectedGrade != null ? selectedGrade.getGradeId() : -1;
		   String contactNo = txtContactNo.getText();
		   String email = txtEmail.getText();
		   String address = txtAddress.getText();

		   try {
			   facultyMaintenanceController.updateFaculty(selectedFacultyId, employeeNo, firstName, lastName,
														     middleName, gender, gradeId,
														     contactNo, email, address);
			   JOptionPane.showMessageDialog(this, "Successfully Updated!");
			   loadFaculty();
			   exitEditMode();
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		   }
	   }
	   
	   public void deleteFaculty() {
		   try {
			   facultyMaintenanceController.deleteFaculty(selectedFacultyId);
			   JOptionPane.showMessageDialog(this, "Successfully Deleted!");
			   loadFaculty();
			   exitEditMode();
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		   }
	   }
	   
	   public void loadFaculty() {
		   try {
			   tblModel.setRowCount(0);
			   ArrayList<FacultyDisplay> facultyList = facultyMaintenanceController.loadFacultyDisplay();

			   for(FacultyDisplay faculty : facultyList) {
				   Object[] eachFaculty = {faculty.getFacultyId(),
											faculty.getEmployeeNo(),
											faculty.getFirstName(),
											faculty.getLastName(),
											faculty.getMiddleName(),
											faculty.getGender(),
											faculty.getDepartmentName(),
											faculty.getGradeLvl(),
											faculty.getContactNo(),
											faculty.getEmail(),
											faculty.getAddress()
											};
				   tblModel.addRow(eachFaculty);
			   }

			   SwingUtilities.invokeLater(() -> {
				   restoreSelectedRow();
			   });
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		   }
	   }
	}