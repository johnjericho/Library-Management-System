package view.BookModule;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.DonorController;
import model.BookModule.Category;
import model.BookModule.Donor;
import utility.AppContext;
import utility.TableRefresherHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class DonorView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtDonor;
	private JTextField txtNumber;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JTextField txtSearch;

	private DonorController donorController = AppContext.getInstance().getDonorController();

	private DefaultTableModel tblModel;
	private JTable tblDonor;
	private int selectedDonorId = -1;
	private boolean isEditMode = false;

	public DonorView() {
		execute();
	}

	public void execute() {
		setPanel();
		initComponent();
		initAction();
	}

	public void setPanel() {
		setBounds(100, 100, 900, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 884, 681);
		getContentPane().add(contentPanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void initComponent() {
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(46, 54, 803, 141);
		contentPanel.add(componentsBorder);

		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setForeground(new Color(51, 102, 51));
		lblDonor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDonor.setBounds(25, 10, 139, 36);
		componentsBorder.add(lblDonor);

		txtDonor = new JTextField();
		txtDonor.setText("");
		txtDonor.setColumns(10);
		txtDonor.setBounds(142, 21, 209, 23);
		componentsBorder.add(txtDonor);

		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 92, 80, 20);
		componentsBorder.add(btnAdd);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(133, 92, 95, 20);
		componentsBorder.add(btnUpdate);

		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(256, 92, 95, 20);
		componentsBorder.add(btnDelete);

		txtNumber = new JTextField();
		txtNumber.setText("");
		txtNumber.setColumns(10);
		txtNumber.setBounds(142, 55, 209, 23);
		componentsBorder.add(txtNumber);

		JLabel lblNumber = new JLabel("Number: ");
		lblNumber.setForeground(new Color(51, 102, 51));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNumber.setBounds(25, 46, 139, 36);
		componentsBorder.add(lblNumber);

		String[] column = {"ID", "Donor", "Number"};
		tblModel = new DefaultTableModel(column, 0) {
			public boolean isCellEditable(int row, int column) { return false; }
		};

		tblDonor = new JTable(tblModel);

		JScrollPane scrollPane = new JScrollPane(tblDonor);
		scrollPane.setBounds(46, 237, 803, 412);
		contentPanel.add(scrollPane);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(582, 206, 64, 14);
		contentPanel.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setText("");
		txtSearch.setColumns(10);
		txtSearch.setBounds(643, 203, 209, 23);
		contentPanel.add(txtSearch);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(46, 664, 803, 10);
		contentPanel.add(separator);
	}

	public void initAction() {
		loadDonor();

		btnAdd.addActionListener(e -> {
			addDonor();
		});

		TableRefresherHelper.tblRefresher(3000, () -> loadDonor());

		tblDonor.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) return;

			int selectedRow = tblDonor.getSelectedRow();
			if (selectedRow != -1) {

				int id = (int) tblModel.getValueAt(selectedRow, 0);
				String name = (String) tblModel.getValueAt(selectedRow, 1);
				String number = (String) tblModel.getValueAt(selectedRow, 2);

				if (selectedDonorId != id) {
					selectedDonorId = id;
					txtDonor.setText(name);
					txtNumber.setText(number);
					enterEditMode();
				}
			}
		});

		// ESC key
		getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "escapeAction");

		getRootPane().getActionMap().put("escapeAction", new javax.swing.AbstractAction() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tryExitEditMode();
			}
		});

		// Empty area click
		contentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				tryExitEditMode();
			}
		});

		btnUpdate.addActionListener(e -> {
			updateDonor();
			SwingUtilities.invokeLater(() -> {
				restoreSelectedRow();
			});
		});

		btnDelete.addActionListener(e -> {
			deleteDonor();
		});
		
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) { searchDonor(); }
		    public void removeUpdate(DocumentEvent e) {searchDonor(); }
		    public void changedUpdate(DocumentEvent e) { searchDonor(); }	
	});

		tblDonor.getColumnModel().getColumn(0).setMinWidth(0);
		tblDonor.getColumnModel().getColumn(0).setMaxWidth(0);
		tblDonor.getColumnModel().getColumn(0).setWidth(0);

		SwingUtilities.invokeLater(() -> {
			restoreSelectedRow();
		});
	}

	public void addDonor() {
		String donorName = txtDonor.getText();
		String donorNum = txtNumber.getText();

		try {
			donorController.addDonor(donorName, donorNum);
			JOptionPane.showMessageDialog(this, "Successfully added!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void loadDonor() {
		try {
			tblModel.setRowCount(0);
			ArrayList<Donor> donorList = donorController.loadDonor();
			for (Donor donor : donorList) {
				Object[] eachRow = {donor.getDonorId(), donor.getDonorName(), donor.getDonorNumber()};
				tblModel.addRow(eachRow);
			}
			SwingUtilities.invokeLater(() -> restoreSelectedRow());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateDonor() {
		String updateName = txtDonor.getText();
		String updateNumber = txtNumber.getText();

		try {
			donorController.updateDonor(selectedDonorId, updateName, updateNumber);
			JOptionPane.showMessageDialog(this, "Successfully updated");
			exitEditMode();
			loadDonor();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void deleteDonor() {
		int confirm = JOptionPane.showConfirmDialog(this, "Confirm delete donor", "WARNING", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;

		try {
			donorController.deleteDonor(selectedDonorId);
			JOptionPane.showMessageDialog(this, "Successfully deleted!");
			loadDonor();
			exitEditMode();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	public void searchDonor() {			
		String keyword = txtSearch.getText();
		if(!keyword.isEmpty()) {
			TableRefresherHelper.stopRefresher();
		}else { TableRefresherHelper.startRefresher(); }

		try {
		tblModel.setRowCount(0);
		
		ArrayList<Donor> list = keyword.isEmpty()
		? donorController.loadDonor()
		: donorController.searchDonor(keyword);
		
		for (Donor a : list) {
	        tblModel.addRow(new Object[]{a.getDonorId(), a.getDonorName(), a.getDonorNumber()});
	    }
		
        // DAGDAG — i-restore ang selection after mag-search
        SwingUtilities.invokeLater(() -> restoreSelectedRow());

		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}

	private void restoreSelectedRow() {
		if (selectedDonorId == -1) {
			return;
		}

		for (int i = 0; i < tblModel.getRowCount(); i++) {
			int rowId = (int) tblModel.getValueAt(i, 0);
			if (rowId == selectedDonorId) {
				tblDonor.setRowSelectionInterval(i, i);
				break;
			}
		}
	}

	private void enterEditMode() {
		if (selectedDonorId < 0) return;
		isEditMode = true;
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
	}

	private void exitEditMode() {
		isEditMode = false;
		selectedDonorId = -1;
		txtDonor.setText("");
		txtNumber.setText("");
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}

	private void tryExitEditMode() {
		if (!isEditMode) return;

		String originalName = "";
		String originalNumber = "";
		for (int i = 0; i < tblModel.getRowCount(); i++) {
			if ((int) tblModel.getValueAt(i, 0) == selectedDonorId) {
				originalName = (String) tblModel.getValueAt(i, 1);
				originalNumber = (String) tblModel.getValueAt(i, 2);
				break;
			}
		}

		String currentName = txtDonor.getText().trim();
		String currentNumber = txtNumber.getText().trim();

		if (!currentName.equals(originalName) || !currentNumber.equals(originalNumber)) {
			int confirm = JOptionPane.showConfirmDialog(
					this,
					"Discard changes?",
					"Unsaved Changes",
					JOptionPane.YES_NO_OPTION
			);
			if (confirm == JOptionPane.YES_OPTION) {
				exitEditMode();
				tblDonor.clearSelection();
			}
		} else {
			exitEditMode();
			tblDonor.clearSelection();
		}
	}
}