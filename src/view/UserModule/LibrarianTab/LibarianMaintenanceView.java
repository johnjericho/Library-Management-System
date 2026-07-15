package view.UserModule.LibrarianTab;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.UserModule.LibrarianTab.LibrarianMaintenanceController;
import model.dto.LibrarianDisplay;
import utility.AppContext;
import view.MainFrame;
import view.UserModule.FacultyTab.FacultyMaintenanceView;
import view.UserModule.StudentTab.StudentMaintenanceView;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class LibarianMaintenanceView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JButton btnFaculty;
	private MainFrame mainFrame;
	private JButton btnStudent;
	private JButton btnLibrarian;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtContactNo;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JRadioButton rbAssistant;
	private JRadioButton rbLibrarian;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JPasswordField pfPassword;
	private JRadioButton rbShow;

	private JTable tblLibrarian;
	private DefaultTableModel tblModel;
	private int selectedLibrarianId = -1;

	private LibrarianMaintenanceController librarianMaintenanceController = AppContext.getInstance().getLibrarianMaintenanceController();

	public LibarianMaintenanceView(MainFrame mainFrame) {
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

		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(18, 65, 1083, 216);
		add(componentsBorder);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(51, 204, 51));
		separator_1.setBounds(525, 11, 24, 194);
		componentsBorder.add(separator_1);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(new Color(51, 102, 51));
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName.setBounds(51, 66, 80, 14);
		componentsBorder.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setForeground(new Color(51, 102, 51));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastName.setBounds(51, 95, 80, 14);
		componentsBorder.add(lblLastName);

		JLabel lblMiddleName = new JLabel("Middle name");
		lblMiddleName.setForeground(new Color(51, 102, 51));
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMiddleName.setBounds(34, 127, 97, 14);
		componentsBorder.add(lblMiddleName);

		txtMiddleName = new JTextField();
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(139, 123, 191, 20);
		componentsBorder.add(txtMiddleName);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(139, 92, 191, 20);
		componentsBorder.add(txtLastName);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(139, 63, 191, 20);
		componentsBorder.add(txtFirstName);

		rbAssistant = new JRadioButton("Assistant Librarian");
		rbAssistant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		rbAssistant.setForeground(new Color(0, 100, 0));
		rbAssistant.setBounds(79, 11, 143, 23);
		componentsBorder.add(rbAssistant);

		rbLibrarian = new JRadioButton("Librarian");
		rbLibrarian.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		rbLibrarian.setForeground(new Color(0, 100, 0));
		rbLibrarian.setBounds(6, 11, 76, 23);
		componentsBorder.add(rbLibrarian);

		ButtonGroup positionGroup = new ButtonGroup();
		positionGroup.add(rbLibrarian);
		positionGroup.add(rbAssistant);
		
		txtContactNo = new JTextField();
		txtContactNo.setColumns(10);
		txtContactNo.setBounds(635, 65, 191, 20);
		componentsBorder.add(txtContactNo);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(635, 95, 191, 20);
		componentsBorder.add(txtEmail);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(139, 152, 191, 39);
		componentsBorder.add(txtAddress);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(new Color(51, 102, 51));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(552, 122, 78, 20);
		componentsBorder.add(lblPassword);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(51, 102, 51));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(67, 154, 65, 20);
		componentsBorder.add(lblAddress);

		JLabel lblContactNo = new JLabel("Phone");
		lblContactNo.setForeground(new Color(51, 102, 51));
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContactNo.setBounds(571, 68, 54, 20);
		componentsBorder.add(lblContactNo);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(51, 102, 51));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(581, 97, 46, 20);
		componentsBorder.add(lblEmail);

		rbShow = new JRadioButton("show");
		rbShow.setForeground(new Color(0, 100, 0));
		rbShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		rbShow.setBounds(632, 144, 65, 15);
		componentsBorder.add(rbShow);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(635, 123, 191, 20);
		componentsBorder.add(pfPassword);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 330, 1083, 383);
		add(scrollPane);

		tblModel = new DefaultTableModel( new Object[] { "ID", "Role", "First Name", "Last Name", "Middle Name", "Contact No", "Email", "Address" }, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblLibrarian = new JTable(tblModel);
		scrollPane.setViewportView(tblLibrarian);

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

	}

	public void initAction() {
		btnStudent.addActionListener(e -> mainFrame.showPanel(new StudentMaintenanceView(mainFrame)));

		btnFaculty.addActionListener(e -> mainFrame.showPanel(new FacultyMaintenanceView(mainFrame)));


		rbShow.addActionListener(e -> togglePasswordVisibility());

		btnAdd.addActionListener(e -> addLibrarian());

		loadLibrarianForDisplay();
		
		btnUpdate.addActionListener(e -> updateLibrarian());

		btnDelete.addActionListener(e -> deleteLibrarian());

		tblLibrarian.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) return;
			selectRow();
		});

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { searchLibrarian(); }
			public void removeUpdate(DocumentEvent e) { searchLibrarian(); }
			public void changedUpdate(DocumentEvent e) { searchLibrarian(); }
		});
	}
	

	private void togglePasswordVisibility() {
		if (rbShow.isSelected()) {
			pfPassword.setEchoChar((char) 0);
		} else {
			pfPassword.setEchoChar('*');
		}
	}

	private void addLibrarian() {
		try {
			String positionType = rbLibrarian.isSelected() 
					? "Librarian"
					: rbAssistant.isSelected() ? "Assistant librarian" : "";

			librarianMaintenanceController.addLibrarian(
											positionType, 
											txtFirstName.getText(), 
											txtLastName.getText(),
											txtMiddleName.getText(),
											txtAddress.getText(),
											txtContactNo.getText(),
											txtEmail.getText(),
											new String(pfPassword.getPassword()));
			loadLibrarianForDisplay();
			clearFields();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void updateLibrarian() {
		if (selectedLibrarianId == -1) return;
		try {
			String positionType = rbLibrarian.isSelected() ? "Librarian"
					: rbAssistant.isSelected() ? "Assistant librarian" : "";

			librarianMaintenanceController.updateLibrarian(selectedLibrarianId, positionType, txtFirstName.getText(),
					txtLastName.getText(), txtMiddleName.getText(), txtContactNo.getText(), txtEmail.getText(),
					txtAddress.getText(), new String(pfPassword.getPassword()));
			loadLibrarianForDisplay();
			exitEditMode();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void deleteLibrarian() {
		if (selectedLibrarianId == -1) return;
		int confirm = JOptionPane.showConfirmDialog(this, "Delete this librarian?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;
		try {
			librarianMaintenanceController.deleteLibrarian(selectedLibrarianId);
			loadLibrarianForDisplay();
			exitEditMode();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void searchLibrarian() {
		try {
			String keyword = txtSearch.getText().trim();
			List<LibrarianDisplay> results = keyword.isEmpty()
					? librarianMaintenanceController.getAllLibrarians()
					: librarianMaintenanceController.searchLibrarians(keyword);
			populateTable(results);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void loadLibrarianForDisplay() {
		try {
			populateTable(librarianMaintenanceController.getAllLibrarians());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void populateTable(List<LibrarianDisplay> list) {
		tblModel.setRowCount(0);
		for (LibrarianDisplay ld : list) {
			tblModel.addRow(new Object[] {
					ld.getLibrarianId(), ld.getPositionType(), ld.getFirstName(), ld.getLastName(),
					ld.getMiddleName(), ld.getEmail(), ld.getContactNo(),  ld.getAddress()
			});
		}
	}

	private void selectRow() {
		int selectedRow = tblLibrarian.getSelectedRow();
		if (selectedRow == -1) return;

		selectedLibrarianId = (int) tblModel.getValueAt(selectedRow, 0);
		String positionType = (String) tblModel.getValueAt(selectedRow, 1);
		rbLibrarian.setSelected("Librarian".equals(positionType));
		rbAssistant.setSelected("Assistant librarian".equals(positionType));
		txtFirstName.setText((String) tblModel.getValueAt(selectedRow, 2));
		txtLastName.setText((String) tblModel.getValueAt(selectedRow, 3));
		txtMiddleName.setText((String) tblModel.getValueAt(selectedRow, 4));
		txtContactNo.setText((String) tblModel.getValueAt(selectedRow, 5));
		txtEmail.setText((String) tblModel.getValueAt(selectedRow, 6));
		txtAddress.setText((String) tblModel.getValueAt(selectedRow, 7));
		pfPassword.setText(""); // laging blangko, security

		enterEditMode();
	}

	private void enterEditMode() {
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
	}

	private void exitEditMode() {
		selectedLibrarianId = -1;
		tblLibrarian.clearSelection();
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		clearFields();
	}

	private void clearFields() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtMiddleName.setText("");
		txtContactNo.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		pfPassword.setText("");
		rbLibrarian.setSelected(false);
		rbAssistant.setSelected(false);
	}
}