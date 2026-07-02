package view.AcquisitionModule;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.AcquisitionModule.DonorController;
import controller.AcquisitionModule.SupplierController;
import model.dto.ContributorDisplay;
import utility.AppContext;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class ContributorEditForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;

	private SupplierController supplierController = AppContext.getInstance().getSupplierController();
	private DonorController donorController = AppContext.getInstance().getDonorController();

	private JTextField txtContributorName;
	private JTextField txtAddress;
	private JTextField txtContactPer;
	private JTextField txtContactNo;
	private JComboBox<String> cmbxContributorType;
	private JButton btnSave;

	// edit mode tracking - both id and type kailangan sabay dahil independent ang PKs ng supplier/donor
	private boolean isEditMode = false;
	private int selectedContributorId = -1;
	private String selectedContributorType = "";

	// ADD mode
	public ContributorEditForm() {
		execute();
	}

	// EDIT mode
	public ContributorEditForm(ContributorDisplay contributor) {
		execute();
		loadForUpdate(contributor);
	}

	public void execute() {
		setPanel();
		initComponent();
		initAction();
	}

	public void setPanel() {
		setBounds(100, 100, 433, 438);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 417, 424);
		getContentPane().add(contentPanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void initComponent() {
		JPanel componentsBorder = new JPanel();
		componentsBorder.setBackground(Color.WHITE);
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(0, 128, 0), 3));
		componentsBorder.setBounds(10, 11, 399, 366);
		contentPanel.add(componentsBorder);

		JLabel lblSupplier = new JLabel("Name / Company");
		lblSupplier.setForeground(new Color(51, 102, 51));
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupplier.setBounds(25, 73, 276, 36);
		componentsBorder.add(lblSupplier);

		txtContributorName = new JTextField();
		txtContributorName.setColumns(10);
		txtContributorName.setBounds(25, 103, 168, 23);
		componentsBorder.add(txtContributorName);

		JLabel lblContributorType = new JLabel("Contributor type");
		lblContributorType.setForeground(new Color(51, 102, 51));
		lblContributorType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContributorType.setBounds(25, 22, 276, 36);
		componentsBorder.add(lblContributorType);

		cmbxContributorType = new JComboBox<>();
		cmbxContributorType.setBounds(25, 51, 168, 22);
		cmbxContributorType.addItem("");
		cmbxContributorType.addItem("supplier");
		cmbxContributorType.addItem("donor");
		componentsBorder.add(cmbxContributorType);

		btnSave = new JButton("SAVE");
		btnSave.setForeground(new Color(51, 102, 51));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave.setBounds(25, 293, 87, 20);
		componentsBorder.add(btnSave);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(25, 226, 347, 56);
		componentsBorder.add(txtAddress);

		JLabel lblSupplierAddress = new JLabel("address");
		lblSupplierAddress.setForeground(new Color(51, 102, 51));
		lblSupplierAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSupplierAddress.setBounds(25, 198, 139, 36);
		componentsBorder.add(lblSupplierAddress);

		txtContactPer = new JTextField();
		txtContactPer.setColumns(10);
		txtContactPer.setBounds(25, 164, 168, 23);
		componentsBorder.add(txtContactPer);

		JLabel lblContactPerson = new JLabel("contact person");
		lblContactPerson.setForeground(new Color(51, 102, 51));
		lblContactPerson.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblContactPerson.setBounds(25, 135, 139, 36);
		componentsBorder.add(lblContactPerson);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(51, 204, 51));
		separator_1.setBounds(25, 135, 347, 10);
		componentsBorder.add(separator_1);

		txtContactNo = new JTextField();
		txtContactNo.setColumns(10);
		txtContactNo.setBounds(204, 165, 168, 23);
		componentsBorder.add(txtContactNo);

		JLabel lblNumber_1 = new JLabel("contact number");
		lblNumber_1.setForeground(new Color(51, 102, 51));
		lblNumber_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNumber_1.setBounds(203, 135, 139, 36);
		componentsBorder.add(lblNumber_1);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(10, 388, 399, 10);
		contentPanel.add(separator);
	}

	public void initAction() {
		btnSave.addActionListener(e -> {
			String type = (String) cmbxContributorType.getSelectedItem();


			if (isEditMode) {
				if (type.equals("supplier")) {
					updateSupplier();
				} else if (type.equals("donor")) {
					updateDonor();
				}
			} else {
				if (type.equals("supplier")) {
					addSupplier();
				} else if (type.equals("donor")) {
					addDonor();
				}
			}
		});
	}

	
	private void loadForUpdate(ContributorDisplay contributor) {
		isEditMode = true;
		selectedContributorId = contributor.getContributorId();
		selectedContributorType = contributor.getContributorType();

		cmbxContributorType.setSelectedItem(contributor.getContributorType());
	//	cmbxContributorType.setEnabled(false); // type locked once editing existing record

		txtContributorName.setText(contributor.getName());
		txtContactPer.setText(contributor.getContactPerson());
		txtContactNo.setText(contributor.getContactNumber());
		txtAddress.setText(contributor.getAddress());

		btnSave.setText("UPDATE");
		setTitle("Edit Contributor");
	}
	
	

	public void addSupplier() {
		String name = txtContributorName.getText();
		String contactPer = txtContactPer.getText();
		String number = txtContactNo.getText();
		String address = txtAddress.getText();
		String type = (String) cmbxContributorType.getSelectedItem();

		try {
			supplierController.addSupplier(type, name, contactPer, number, address);
			JOptionPane.showMessageDialog(this, "Successfully added!");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void addDonor() {
		String name = txtContributorName.getText();
		String contactPer = txtContactPer.getText();
		String number = txtContactNo.getText();
		String address = txtAddress.getText();

		try {
			donorController.addDonor(name, contactPer, number, address);
			JOptionPane.showMessageDialog(this, "Successfully added!");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void updateSupplier() {
		String name = txtContributorName.getText();
		String contactPer = txtContactPer.getText();
		String number = txtContactNo.getText();
		String address = txtAddress.getText();

		try {
			// TODO: siguraduhin tugma ang signature na ito sa SupplierController mo
			supplierController.updateSupplier(selectedContributorId, name, contactPer, number, address);
			JOptionPane.showMessageDialog(this, "Successfully updated!");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void updateDonor() {
		String name = txtContributorName.getText();
		String contactPer = txtContactPer.getText();
		String number = txtContactNo.getText();
		String address = txtAddress.getText();

		try {
			// TODO: siguraduhin tugma ang signature na ito sa DonorController mo
			donorController.updateDonor(selectedContributorId, name, contactPer, number, address);
			JOptionPane.showMessageDialog(this, "Successfully updated!");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
}