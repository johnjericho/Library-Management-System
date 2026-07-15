package view.UserModule.StudentTab;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;


import controller.UserModule.StudentTab.DepartmentController;
import controller.UserModule.StudentTab.GradeController;
import controller.UserModule.StudentTab.StudentMaintenanceController;

import model.UserModule.StudentTab.Department;
import model.UserModule.StudentTab.Grade;
import model.dto.StudentDisplay;

import utility.AppContext;
import view.MainFrame;
import view.UserModule.FacultyTab.FacultyMaintenanceView;
import view.UserModule.LibrarianTab.LibarianMaintenanceView;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StudentMaintenanceView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtLrn;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMiddleName;
	private JTextField txtSearch;
	private JButton btnFaculty;
	private MainFrame mainFrame;
	private JButton btnLibrarian;
	private JButton btnStudent;
	 
	private DefaultTableModel tblModel;
	private JTable tblStudent;
	private JScrollPane scrollPane;
	private JComboBox cmbGender;
	private JTextField cmbSection;
	private JComboBox<Department> cmbDepartment;
	private JComboBox<Grade> cmbGrade;
	private JButton btnBrowseSection;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnAdd;
	
	
    private DepartmentController departmentController = AppContext.getInstance().getDepartmentController();
    private GradeController gradeController = AppContext.getInstance().getdGradeController();
    private StudentMaintenanceController studentMaintenanceController = AppContext.getInstance().getStudentMaintenanceController();
    private int selectedSectionId = -1;
    private boolean isEditable = false;
    private int selectedStudentId;
    private JLabel lblPhone;
    private JTextField txtContactNo;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JLabel lblAddress;
    

	
	
	public StudentMaintenanceView(MainFrame mainFrame) {
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
	       componentsBorder.setBounds(18, 65, 1083, 226);
	       add(componentsBorder);
	       
	       JLabel lblLrn = new JLabel("LRN");
	       lblLrn.setForeground(new Color(51, 102, 51));
	       lblLrn.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblLrn.setBounds(71, 33, 46, 14);
	       componentsBorder.add(lblLrn);
	       
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
	       
	       JLabel lblGrade = new JLabel("Grade");
	       lblGrade.setForeground(new Color(51, 102, 51));
	       lblGrade.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblGrade.setBounds(562, 59, 54, 23);
	       componentsBorder.add(lblGrade);
	       
	       JLabel lblSection = new JLabel("Section");
	       lblSection.setForeground(new Color(51, 102, 51));
	       lblSection.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblSection.setBounds(552, 88, 65, 20);
	       componentsBorder.add(lblSection);
	       
	       
	       JLabel lblSearch = new JLabel("Search");
	       lblSearch.setForeground(new Color(51, 102, 51));
	       lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblSearch.setBounds(859, 302, 58, 14);
	       add(lblSearch);
	       
	       JLabel lblDepartment = new JLabel("Department");
	       lblDepartment.setForeground(new Color(51, 102, 51));
	       lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblDepartment.setBounds(520, 27, 97, 23);
	       componentsBorder.add(lblDepartment);
	       
	       
	       //LABEL
	       
	       
	       JSeparator separator_1 = new JSeparator();
	       separator_1.setOrientation(SwingConstants.VERTICAL);
	       separator_1.setForeground(new Color(51, 204, 51));
	       separator_1.setBounds(505, 11, 24, 204);
	       componentsBorder.add(separator_1);
	       
	       txtLrn = new JTextField();
	       txtLrn.setColumns(10);
	       txtLrn.setBounds(125, 27, 191, 20);
	       componentsBorder.add(txtLrn);
	       
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
	       cmbGender.setBounds(126, 149, 191, 19);
	       cmbGender.addItem(null);
	       cmbGender.addItem("male");
	       cmbGender.addItem("female");
	       componentsBorder.add(cmbGender);     
	       
	       cmbSection = new JTextField();
	       cmbSection.setEditable(false);
	       cmbSection.setColumns(10);
	       cmbSection.setBounds(617, 90, 191, 20);
	       componentsBorder.add(cmbSection);
	       
	       btnBrowseSection = new JButton("browse");
	       btnBrowseSection.setForeground(new Color(51, 102, 51));
	       btnBrowseSection.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowseSection.setBounds(818, 92, 80, 20);
	       componentsBorder.add(btnBrowseSection);
	           
	       
	       cmbDepartment = new JComboBox<>();
	       cmbDepartment.setBounds(617, 29, 191, 19);
	       componentsBorder.add(cmbDepartment);

	       cmbGrade = new JComboBox<>();
	       cmbGrade.setBounds(617, 61, 191, 19);
	       componentsBorder.add(cmbGrade);
	       
	       lblPhone = new JLabel("Phone");
	       lblPhone.setForeground(new Color(51, 102, 51));
	       lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblPhone.setBounds(562, 119, 54, 20);
	       componentsBorder.add(lblPhone);
	       
	       txtContactNo = new JTextField();
	       txtContactNo.setText((String) null);
	       txtContactNo.setColumns(10);
	       txtContactNo.setBounds(617, 118, 191, 20);
	       componentsBorder.add(txtContactNo);
	       
	       lblEmail = new JLabel("Email");
	       lblEmail.setForeground(new Color(51, 102, 51));
	       lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblEmail.setBounds(564, 148, 46, 20);
	       componentsBorder.add(lblEmail);
	       
	       txtEmail = new JTextField();
	       txtEmail.setText((String) null);
	       txtEmail.setColumns(10);
	       txtEmail.setBounds(617, 147, 191, 20);
	       componentsBorder.add(txtEmail);
	       
	       txtAddress = new JTextField();
	       txtAddress.setText((String) null);
	       txtAddress.setColumns(10);
	       txtAddress.setBounds(620, 173, 191, 42);
	       componentsBorder.add(txtAddress);
	       
	       lblAddress = new JLabel("Address");
	       lblAddress.setForeground(new Color(51, 102, 51));
	       lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
	       lblAddress.setBounds(552, 173, 65, 20);
	       componentsBorder.add(lblAddress);
     
	           
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
	       btnLibrarian.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       	}
	       });
	       btnLibrarian.setForeground(new Color(51, 102, 51));
	       btnLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnLibrarian.setEnabled(true);
	       
	       btnFaculty = new JButton("FACULTY");

	       btnFaculty.setBounds(127, 11, 107, 20);
	       panel.add(btnFaculty);
	       btnFaculty.setForeground(new Color(51, 102, 51));
	       btnFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnFaculty.setEnabled(true);    
		   
	       String[] column = {"studentId", "LRN", "FIRST NAME", "LAST NAME", "MIDDLE NAME",
	    		   			   "GENDER","DEPARTMENT", "GRADE","SECTION","ADDRESS", "PHONE", "EMAIL"};
	       tblModel = new DefaultTableModel(column, 0) {
	   		public boolean  isCellEditable(int row, int column){ return false; }
	   	   };  
	       tblStudent = new JTable(tblModel);
	       
	       scrollPane = new JScrollPane(tblStudent);
	       scrollPane.setBounds(18, 326, 1083, 383);	       
	       this.add(scrollPane);
	   }
	   
	   public void initAction() {
		   btnFaculty.addActionListener(e->{
	    	   FacultyMaintenanceView facultyView = new FacultyMaintenanceView(mainFrame);
	    	   mainFrame.showPanel(facultyView);	             
	       });   
		   
		   btnLibrarian.addActionListener(e -> {
			   mainFrame.showPanel(new LibarianMaintenanceView(mainFrame));
		   });
		   
		   
		   cmbDepartment.addItemListener(e -> {

			    Department selectedDept = (Department) cmbDepartment.getSelectedItem();
			    if (selectedDept == null) {
			        cmbGrade.removeAllItems();   // I-CLEAR MUNA bago mag-return
			        return;
			    }

			    ArrayList<Grade> grades = gradeController.getGradesByDepartment(selectedDept.getDepartmentId());

			    cmbGrade.removeAllItems();
			    for (Grade g : grades) {
			        cmbGrade.addItem(g);
			    }
			});
		   
		   
		   btnBrowseSection.addActionListener(e -> {
			    Grade selectedGrade = (Grade) cmbGrade.getSelectedItem();

			    if (selectedGrade == null) {
			        JOptionPane.showMessageDialog(this, "Select Department and grade first.");
			        return;
			    }

			    SectionPickerDialog picker = new SectionPickerDialog(selectedGrade.getGradeId());
			    picker.setModal(true);
			    picker.setVisible(true); // dito huhupindi ang execution hanggang ma-dispose ang dialog

			    selectedSectionId = picker.getSelectedSectionId();			    
			    if (selectedSectionId != -1) {
			    cmbSection.setText(picker.getSelectedSectionName());			        
			    }
			    System.out.println("id "+ selectedSectionId + " Section "+ picker.getSelectedSectionName());
			});
		   
		   
		// ESC key
			this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "escapeAction");

			this.getActionMap().put("escapeAction", new javax.swing.AbstractAction() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exitEditMode();
				}
			});

			// Empty area click
			this.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					exitEditMode();
				}
			});
		   
		   //CRUD
		   btnAdd.addActionListener(e ->{
			   addStudent();
		   });
		   
		   loadStudent();
		   
		   tblStudent.getSelectionModel().addListSelectionListener(e ->{
			    if(e.getValueIsAdjusting()) return; //mouse is long press
			    int selectedRow = tblStudent.getSelectedRow();

			    if(selectedRow != -1) {

			        int id = (int) tblModel.getValueAt(selectedRow, 0);

			        String lrn = (String) tblModel.getValueAt(selectedRow, 1);
			        String firstName = (String) tblModel.getValueAt(selectedRow, 2);
			        String lastName = (String) tblModel.getValueAt(selectedRow, 3);
			        String middleName = (String) tblModel.getValueAt(selectedRow, 4);
			        String gender = (String) tblModel.getValueAt(selectedRow, 5);
			        String department = (String) tblModel.getValueAt(selectedRow, 6);
			        String grade = String.valueOf(tblModel.getValueAt(selectedRow, 7));
			        String section = (String) tblModel.getValueAt(selectedRow, 8);
			        String address = (String) tblModel.getValueAt(selectedRow, 9);
			        String contactNo = (String) tblModel.getValueAt(selectedRow, 10);
			        String email = (String) tblModel.getValueAt(selectedRow, 11);

			        if(id != selectedStudentId) {
			            selectedStudentId = id;

			            //for user view
			            txtLrn.setText(lrn);
			            txtFirstName.setText(firstName);
			            txtLastName.setText(lastName);
			            txtMiddleName.setText(middleName);
			            cmbGender.setSelectedItem(gender);

			            // Match Department object by name
			            for (int i = 0; i < cmbDepartment.getItemCount(); i++) {
			                Department d = cmbDepartment.getItemAt(i);
			                if (d != null && d.getDepartmentName().equals(department)) {
			                    cmbDepartment.setSelectedItem(d);   // triggers itemListener -> repopulates cmbGrade
			                    break;
			                }
			            }

			            // Match Grade object by level
			            for (int i = 0; i < cmbGrade.getItemCount(); i++) {
			                Grade g = cmbGrade.getItemAt(i);
			                if (g != null && String.valueOf(g.getGradeLvl()).equals(grade)) {
			                    cmbGrade.setSelectedItem(g);
			                    break;
			                }
			            }

			            cmbSection.setText(section);
			            txtAddress.setText(address);
			            txtContactNo.setText(contactNo);
			            txtEmail.setText(email);
			            

			            //for dev view (FK)
			            StudentDisplay student = studentMaintenanceController.getStudentById(selectedStudentId);
			            selectedSectionId = student.getSectionId();

			            editMode();
			        }

			    }
			});
		   
		   btnUpdate.addActionListener(e ->{
			   updateStudent();
		   });
		   
		   btnDelete.addActionListener(e ->{
			   deleteStudent();
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
		   
		   txtLrn.setText("");
		   txtFirstName.setText("");
		   txtLastName.setText("");
		   txtMiddleName.setText("");
		   cmbGender.setSelectedItem(null);
		   cmbDepartment.setSelectedItem(null);
		   cmbGrade.setSelectedItem(null);
		   cmbSection.setText("");
		   txtAddress.setText("");
		   txtContactNo.setText("");
		   txtEmail.setText("");
		   
		    selectedStudentId = -1;
		   selectedSectionId = -1;
		   
		    tblStudent.clearSelection();

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
	   
	   
	   
	   
	// Add (existing mo, walang binago)
	   public void addStudent() {
	       String lrn = txtLrn.getText();
	       String firstName = txtFirstName.getText();
	       String lastName = txtLastName.getText();
	       String middleName = txtMiddleName.getText();
	       String gender = (String) cmbGender.getSelectedItem();
	       int sectionId = selectedSectionId;
	       String address = txtAddress.getText();
	       String contactNo = txtContactNo.getText();
	       String email = txtEmail.getText();


	       try {
	           studentMaintenanceController.addStudent(lrn, firstName, lastName, middleName,
	                                                     gender, sectionId, address, contactNo, email);
	           JOptionPane.showMessageDialog(this, "Successfully Added!");
	           loadStudent();
	       } catch(Exception e) {
	           JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	       }
	   }

	   // Update
	   public void updateStudent() {
	       String lrn = txtLrn.getText();
	       String firstName = txtFirstName.getText();
	       String lastName = txtLastName.getText();
	       String middleName = txtMiddleName.getText();
	       String gender = (String) cmbGender.getSelectedItem();
	       int sectionId = selectedSectionId;
	       String address = txtAddress.getText();
	       String contactNo = txtContactNo.getText();
	       String email = txtEmail.getText();

	       try {
	           studentMaintenanceController.updateStudent(selectedStudentId, lrn, firstName, lastName,
	                                                        middleName, gender, sectionId,
	                                                        address, contactNo, email);
	           JOptionPane.showMessageDialog(this, "Successfully Updated!");
	           loadStudent();
	       } catch(Exception e) {
	           JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	       }
	   }

	   // Delete
	   public void deleteStudent() {
	       try {
	           studentMaintenanceController.deleteStudent(selectedStudentId);
	           JOptionPane.showMessageDialog(this, "Successfully Deleted!");
	           loadStudent();
	       } catch(Exception e) {
	           JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	       }
	   }


	   
	   public void loadStudent() {
		
		   try {
		   tblModel.setRowCount(0);
		   ArrayList<StudentDisplay> studentList = studentMaintenanceController.loadStudentDisplay();
		   
		   for(StudentDisplay studentDisplay : studentList) {
			   	Object[] eachStudent = {studentDisplay.getStudentId(), 
							   			studentDisplay.getLrn(),
							   			studentDisplay.getFirstName(),
							   			studentDisplay.getLastName(),
							   			studentDisplay.getMiddleName(),
							   			studentDisplay.getGender(),
							   			studentDisplay.getDepartmentName(),
							   			studentDisplay.getGradeLvl(),
							   			studentDisplay.getSectionName(),
							   			studentDisplay.getAddress(),
							   			studentDisplay.getContactNo(),
							   			studentDisplay.getEmail()
			   						};
			   	tblModel.addRow(eachStudent);
		   }
		   }catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		   }
	   }
	   
	   

	   
	   
}
