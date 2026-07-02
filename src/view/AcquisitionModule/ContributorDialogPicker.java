package view.AcquisitionModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import controller.AcquisitionModule.DonorController;
import controller.AcquisitionModule.SupplierController;
import model.dto.ContributorDisplay;
import model.BookModule.Supplier;
import utility.AppContext;
// TODO: adjust package kung saan talaga nakalagay ang Donor model mo
import model.BookModule.Donor;

public class ContributorDialogPicker extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private DefaultTableModel tblModel;
	private JTable tblContributor;
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	private JButton btnAdd;

	private SupplierController supplierController = AppContext.getInstance().getSupplierController();
	private DonorController donorController = AppContext.getInstance().getDonorController();

	private ArrayList<ContributorDisplay> contributorList = new ArrayList<>();

	// selection mode - para magamit din 'to bilang picker sa AcquisitionForm, parang BookPickerDialog
	private boolean selectionMode = false;
	private int selectedContributorId = -1;
	private String selectedContributorType = "";
	private String selectedContributorName = "";

	public ContributorDialogPicker() {
		this(false);
	}

	public ContributorDialogPicker(boolean selectionMode) {
		this.selectionMode = selectionMode;
		initialize();
	}

	public void initialize() {
		setDialog();
		initComponent();
		initAction();
		loadContributor();
	}

	public void setDialog() {
		setBounds(100, 100, 990, 525);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 974, 486);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
	}

	public void initComponent() {
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(37, 50, 90, 25);
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPanel.add(btnAdd);

		JPanel tblPanel = new JPanel();
		tblPanel.setBorder(new LineBorder(new Color(0, 128, 0), 3, true));
		tblPanel.setBounds(10, 63, 954, 391);
		contentPanel.add(tblPanel);
		tblPanel.setLayout(null);

		// col 0 = hidden ID
		String[] column = { "ID", "CONTRIBUTOR TYPE", "NAME", "CONTACT PERSON", "CONTACT NUMBER", "ACTION" };
		tblModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return column == 5; // buttons lang ang editable, para gumana ang cell editor
			}
		};

		tblContributor = new JTable(tblModel);
		tblContributor.setRowHeight(30);

		scrollPane = new JScrollPane(tblContributor);
		scrollPane.setBounds(10, 36, 934, 344);
		tblPanel.add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(10, 465, 954, 10);
		contentPanel.add(separator);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(697, 33, 64, 14);
		contentPanel.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(758, 29, 206, 23);
		contentPanel.add(txtSearch);

		// hide ID column - matching hidden PK column convention mo
		tblContributor.getColumnModel().getColumn(0).setMinWidth(0);
		tblContributor.getColumnModel().getColumn(0).setMaxWidth(0);
		tblContributor.getColumnModel().getColumn(0).setWidth(0);

		// action column buttons
		tblContributor.getColumnModel().getColumn(5).setCellRenderer(new ActionCellRenderer());
		tblContributor.getColumnModel().getColumn(5).setCellEditor(new ActionCellEditor());
	}

	public void initAction() {
		btnAdd.addActionListener(e -> {
			ContributorEditForm editForm = new ContributorEditForm();
			editForm.setModal(true);
			editForm.setVisible(true);
			loadContributor(); // refresh pagkatapos mag-add
		});

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { filter(); }
			public void removeUpdate(DocumentEvent e) { filter(); }
			public void changedUpdate(DocumentEvent e) { filter(); }
		});

		// row selection para sa selection mode (picker)
		tblContributor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblContributor.getSelectedRow();
				if (row == -1) return;

				int col = tblContributor.columnAtPoint(e.getPoint());
				if (col == 5) return; // ignore, hawak na ng action editor

				int id = (int) tblModel.getValueAt(row, 0);
				String type = (String) tblModel.getValueAt(row, 1);
				String name = (String) tblModel.getValueAt(row, 2);

				// guard - iwas re-trigger sa parehong row
				if (selectedContributorId != id || !selectedContributorType.equals(type)) {
					selectedContributorId = id;
					selectedContributorType = type;
					selectedContributorName = name;
				}

				if (selectionMode && e.getClickCount() == 2) {
					dispose();
				}
			}
		});
	}

	public void loadContributor() {
		contributorList.clear();

		try {
			ArrayList<Supplier> suppliers = supplierController.loadSupplier();
			for (Supplier s : suppliers) {
				contributorList.add(new ContributorDisplay(
						s.getSupplierId(), "supplier", s.getSupplierName(),
						s.getSupplierContactPerson(), s.getSupplierNumber(), s.getSupplierAddress()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to load suppliers: " + e.getMessage());
		}

		try {
			ArrayList<Donor> donors = donorController.loadDonor();
			for (Donor d : donors) {
				contributorList.add(new ContributorDisplay(
						d.getDonorId(), "donor", d.getDonorName(),
						d.getDonorContactPerson(), d.getDonorNumber(), d.getDonorAddress()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to load donors: " + e.getMessage());
		}

		populateTable(contributorList);
	}

	private void populateTable(ArrayList<ContributorDisplay> list) {
		tblModel.setRowCount(0);
		for (ContributorDisplay cd : list) {
			tblModel.addRow(new Object[] {
					cd.getContributorId(),
					cd.getContributorType(),
					cd.getName(),
					cd.getContactPerson(),
					cd.getContactNumber(),
					""
			});
		}
	}

	private void filter() {
		String keyword = txtSearch.getText().trim().toLowerCase();
		if (keyword.isEmpty()) {
			populateTable(contributorList);
			return;
		}
		ArrayList<ContributorDisplay> filtered = new ArrayList<>();
		for (ContributorDisplay cd : contributorList) {
			if (cd.getName().toLowerCase().contains(keyword)
					|| cd.getContributorType().toLowerCase().contains(keyword)) {
				filtered.add(cd);
			}
		}
		populateTable(filtered);
	}

	private void handleUpdate(int row) {
		int id = (int) tblModel.getValueAt(row, 0);
		String type = (String) tblModel.getValueAt(row, 1);

		ContributorDisplay target = null;
		for (ContributorDisplay cd : contributorList) {
			if (cd.getContributorId() == id && cd.getContributorType().equals(type)) {
				target = cd;
				break;
			}
		}
		if (target == null) return;

		ContributorEditForm editForm = new ContributorEditForm(target);
		editForm.setModal(true);
		editForm.setVisible(true);
		loadContributor();
	}

	private void handleDelete(int row) {
		int id = (int) tblModel.getValueAt(row, 0);
		String type = (String) tblModel.getValueAt(row, 1);
		String name = (String) tblModel.getValueAt(row, 2);

		int confirm = JOptionPane.showConfirmDialog(this,
				"Delete " + type + " \"" + name + "\"?", "Confirm Delete",
				JOptionPane.YES_NO_OPTION);

		if (confirm != JOptionPane.YES_OPTION) return;

		try {
			if (type.equals("supplier")) {
				// TODO: siguraduhin tugma ang signature na ito sa SupplierController mo
				supplierController.deleteSupplier(id);
			} else if (type.equals("donor")) {
				// TODO: siguraduhin tugma ang signature na ito sa DonorController mo
				donorController.deleteDonor(id);
			}
			JOptionPane.showMessageDialog(this, "Successfully deleted!");
			loadContributor();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public int getSelectedId() { return selectedContributorId; }
	public String getSelectedType() { return selectedContributorType; }
	public String getSelectedName() { return selectedContributorName; }

	// ---------- action column renderer/editor ----------

	class ActionCellRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private JButton btnUpdate = new JButton("Update");
		private JButton btnDelete = new JButton("Delete");

		public ActionCellRenderer() { 
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
			btnUpdate.setForeground(new Color(51, 102, 51));
			btnDelete.setForeground(Color.RED);
			add(btnUpdate);
			add(btnDelete);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			return this;
		}
	}

	class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private JPanel panel = new JPanel();
		private JButton btnUpdate = new JButton("Update");
		private JButton btnDelete = new JButton("Delete");
		private int currentRow;

		public ActionCellEditor() {
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
			btnUpdate.setForeground(new Color(51, 102, 51));
			btnDelete.setForeground(Color.RED);
			panel.add(btnUpdate);
			panel.add(btnDelete);

			btnUpdate.addActionListener(e -> {
				fireEditingStopped();
				handleUpdate(currentRow);
			});

			btnDelete.addActionListener(e -> {
				fireEditingStopped();
				handleDelete(currentRow);
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			currentRow = row;
			return panel;
		}

		@Override
		public Object getCellEditorValue() {
			return "";
		}
	}
}