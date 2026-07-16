package view.InventoryModule.AcquisitionTab;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import controller.AcquisitionModule.AcquisitionController;
import model.dto.AcquisitionDisplay;
import utility.AppContext;
import view.MainFrame;
import view.InventoryModule.InventoryTab.InventoryView;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class AcquisitionView extends JPanel {
	
	private MainFrame mainFrame;
	private InventoryView inv;

	private static final long serialVersionUID = 1L;
	private JTextField txtTransaction;
	private JTextField txtBookTitle;
	private JTextField txtContributor;
	private JTextField txtPrice;
	private JTextField txtSearch;
	private DefaultTableModel tblModel;
	private JTable tblAcquisition;
	private JScrollPane scrollPane;
	private JDateChooser calDateReceive;
	private JTextField txtQuantity;
	private JButton btnBrowseBookTitle;
	private JButton btnBrowseContributor;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField txtAccession;

	private AcquisitionController acquisitionController = AppContext.getInstance().getAcquisitionController();

	private int selectedBookId = -1;
	private int selectedContributorId = -1;
	private String selectedContributorType; // "Supplier" o "Donor"
	private int selectedAcquisitionId = -1;
	private boolean isEditMode = false;
	private String currentTransactionNo;
	private JButton btnNew;
	private JButton btnInventory;
	private JButton btnAcquisition;

	public AcquisitionView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		initialize();
	}

	public void initialize() {
		setPanel();
		initComponents();
		initActions();
	}

	public void setPanel() {
		this.setLayout(null);
		this.setSize(1126, 743);
	}

	public void initComponents() {

		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(22, 61, 1078, 175);
		add(componentsBorder);

		JLabel lblTransaction = new JLabel("Transaction #");
		lblTransaction.setForeground(new Color(51, 102, 51));
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTransaction.setBounds(20, 25, 120, 14);
		componentsBorder.add(lblTransaction);

		JLabel lblBookTitle = new JLabel("Book title");
		lblBookTitle.setForeground(new Color(51, 102, 51));
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBookTitle.setBounds(20, 90, 97, 14);
		componentsBorder.add(lblBookTitle);

		JLabel lblContributor = new JLabel("Contributor");
		lblContributor.setForeground(new Color(51, 102, 51));
		lblContributor.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblContributor.setBounds(20, 115, 113, 24);
		componentsBorder.add(lblContributor);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(51, 102, 51));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrice.setBounds(637, 59, 97, 14);
		componentsBorder.add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(51, 102, 51));
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQuantity.setBounds(608, 25, 97, 20);
		componentsBorder.add(lblQuantity);

		JLabel lblDateRecieved = new JLabel("Date Receive");
		lblDateRecieved.setForeground(new Color(51, 102, 51));
		lblDateRecieved.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDateRecieved.setBounds(579, 84, 126, 14);
		componentsBorder.add(lblDateRecieved);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(858, 266, 58, 14);
		add(lblSearch);

		txtTransaction = new JTextField();
		txtTransaction.setColumns(10);
		txtTransaction.setBounds(140, 25, 126, 20);
		txtTransaction.setEditable(false);
		componentsBorder.add(txtTransaction);

		txtAccession = new JTextField();
		txtAccession.setEditable(false);
		txtAccession.setColumns(10);
		txtAccession.setBounds(140, 56, 191, 20);
		componentsBorder.add(txtAccession);

		txtBookTitle = new JTextField();
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(140, 90, 191, 20);
		txtBookTitle.setEditable(false);
		componentsBorder.add(txtBookTitle);

		txtContributor = new JTextField();
		txtContributor.setColumns(10);
		txtContributor.setBounds(140, 120, 191, 20);
		txtContributor.setEditable(false);
		componentsBorder.add(txtContributor);

		calDateReceive = new JDateChooser();
		calDateReceive.setBounds(702, 84, 191, 20);
		JTextField textField = (JTextField) calDateReceive.getDateEditor().getUiComponent();
		textField.setEditable(false);
		calDateReceive.setMaxSelectableDate(new Date());
		componentsBorder.add(calDateReceive);

		btnBrowseContributor = new JButton("browse");
		btnBrowseContributor.setForeground(new Color(51, 102, 51));
		btnBrowseContributor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowseContributor.setBounds(341, 120, 80, 20);
		componentsBorder.add(btnBrowseContributor);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(702, 57, 191, 20);
		componentsBorder.add(txtPrice);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(702, 28, 191, 20);
		componentsBorder.add(txtQuantity);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(51, 204, 51));
		separator_1.setBounds(506, 11, 24, 148);
		componentsBorder.add(separator_1);

		btnBrowseBookTitle = new JButton("browse");
		btnBrowseBookTitle.setForeground(new Color(51, 102, 51));
		btnBrowseBookTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowseBookTitle.setBounds(341, 90, 80, 20);
		componentsBorder.add(btnBrowseBookTitle);

		JLabel lblAccession = new JLabel("Accession #");
		lblAccession.setForeground(new Color(51, 102, 51));
		lblAccession.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAccession.setBounds(20, 56, 120, 14);
		componentsBorder.add(lblAccession);
		
		btnNew = new JButton("NEW");
		btnNew.setForeground(new Color(51, 102, 51));
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNew.setBounds(271, 25, 60, 20);
		componentsBorder.add(btnNew);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(926, 263, 174, 20);
		add(txtSearch);

		String[] columns = {
				"ACQUISITION ID", "TRANSACTION #", "ACCESSION #",
				"BOOK TITLE", "CONTRIBUTOR", "PRICE", "DATE RECEIVED"
		};
		tblModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAcquisition = new JTable(tblModel);

		scrollPane = new JScrollPane(tblAcquisition);
		scrollPane.setBounds(22, 307, 1083, 383);
		this.add(scrollPane);

		tblAcquisition.getColumnModel().getColumn(0).setMinWidth(0);
		tblAcquisition.getColumnModel().getColumn(0).setMaxWidth(0);
		tblAcquisition.getColumnModel().getColumn(0).setWidth(0);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(22, 715, 1083, 6);
		add(separator);

		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setEnabled(true);
		btnAdd.setBounds(22, 260, 80, 20);
		add(btnAdd);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(112, 260, 95, 20);
		add(btnUpdate);

		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(217, 260, 95, 20);
		add(btnDelete);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 18, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(62, 0, 309, 42);
		add(panel);
		
		btnAcquisition = new JButton("ACQUISITION");
		btnAcquisition.setForeground(new Color(51, 102, 51));
		btnAcquisition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAcquisition.setEnabled(true);
		btnAcquisition.setBounds(158, 11, 138, 20);
		panel.add(btnAcquisition);
		
		btnInventory = new JButton("INVENTORY");
		btnInventory.setForeground(new Color(51, 102, 51));
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInventory.setEnabled(true);
		btnInventory.setBounds(10, 11, 138, 20);
		panel.add(btnInventory);
	}

	// BAGO: setter para sa shared instance mula sa Modules
	public void setInventoryView(InventoryView inv) {
	    this.inv = inv;
	}
	
	public void initActions() {
		
		btnInventory.addActionListener(e ->{
		    mainFrame.showPanel(inv);
		}); 
		btnBrowseBookTitle.addActionListener(e -> {

			BookDialogPicker bookDialog = new BookDialogPicker();
			bookDialog.setModal(true);
			bookDialog.setVisible(true);

			String pickedTitle = bookDialog.getSelectedBookitle();
			if (pickedTitle != null) {
				selectedBookId = bookDialog.getSelectedBookId();
				txtBookTitle.setText(pickedTitle);
			}

		});

		btnBrowseContributor.addActionListener(e -> {

		    ContributorDialogPicker contributorDialog = new ContributorDialogPicker(true); // selectionMode = true
		    contributorDialog.setModal(true);
		    contributorDialog.setVisible(true);

		    String contributorName = contributorDialog.getSelectedContributorName();
		    if (contributorName != null && !contributorName.isEmpty()) {
		        selectedContributorId = contributorDialog.getSelectedContributorId();
		        selectedContributorType = contributorDialog.getSelectedType(); // <-- dating kulang, idinagdag na
		        txtContributor.setText(contributorName);

		    }

		});

		// CRUD
		btnAdd.addActionListener(e -> addAcquisition());
		btnUpdate.addActionListener(e -> updateAcquisition());
		btnDelete.addActionListener(e -> deleteAcquisition());

		startNewTransactionBatch();
		loadAcquisition();


		tblAcquisition.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) return;
			int selectedRow = tblAcquisition.getSelectedRow();

			if (selectedRow != -1) {
				int id = (int) tblModel.getValueAt(selectedRow, 0); 

				String transactionNo = (String) tblModel.getValueAt(selectedRow, 1);
				String accessionNo = (String) tblModel.getValueAt(selectedRow, 2);
				String bookTitle = (String) tblModel.getValueAt(selectedRow, 3);
				String contributorName = (String) tblModel.getValueAt(selectedRow, 4);
				int price = (int) tblModel.getValueAt(selectedRow, 5);
				Date dateAcquired = (Date) tblModel.getValueAt(selectedRow, 6);

				if (id != selectedAcquisitionId) {
					selectedAcquisitionId = id;
 
					txtTransaction.setText(transactionNo);
					txtAccession.setText(accessionNo);
					txtBookTitle.setText(bookTitle);
					txtContributor.setText(contributorName);
					txtPrice.setText(String.valueOf(price));
					calDateReceive.setDate(dateAcquired);
					


					// NOTE: display text lang ang laman ng table (hindi tunay na ID),
					// kaya kailangan i-browse ulit ang book/contributor kung gustong palitan
					// ito bago i-click ang UPDATE.

					enterEditMode();
				}
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

		txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
		});
		
		
		btnNew.addActionListener(e ->{
			
			int option = JOptionPane.showConfirmDialog(this, "Create New Transaction No?", "warning", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			clearItemFields();
			startNewTransactionBatch();
		}
		
			});
	}

	private void restoreSelectedRow() {
		if (selectedAcquisitionId == -1) return;

		for (int i = 0; i < tblModel.getRowCount(); i++) {
			int rowId = (int) tblModel.getValueAt(i, 0);
			if (rowId == selectedAcquisitionId) {
				tblAcquisition.setRowSelectionInterval(i, i);
				break;
			}
		}
	}

	private void enterEditMode() {
		isEditMode = true;
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		txtQuantity.setEnabled(false);
		txtQuantity.setText("");
	}

	private void exitEditMode() {
		isEditMode = false;
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtQuantity.setEnabled(true);

		clearItemFields();
		selectedAcquisitionId = -1;

		// ibalik sa kasalukuyang batch para sa susunod na Add
		txtTransaction.setText(currentTransactionNo);
	}

	private void clearItemFields() {
		txtBookTitle.setText("");
		txtContributor.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
		txtAccession.setText("");
		calDateReceive.setDate(null);

		selectedBookId = -1;
		selectedContributorId = -1;
		selectedContributorType = null;
	}

	private boolean hasChanges() {
	    if (selectedAcquisitionId == -1) return false;

	    String originalTransactionNo = "";
	    String originalAccessionNo = "";
	    String originalBookTitle = "";
	    String originalContributorName = "";
	    int originalPrice = 0;
	    Date originalDate = null;

	    for (int i = 0; i < tblModel.getRowCount(); i++) {
	        if ((int) tblModel.getValueAt(i, 0) == selectedAcquisitionId) {
	            originalTransactionNo = (String) tblModel.getValueAt(i, 1);
	            originalAccessionNo = (String) tblModel.getValueAt(i, 2);
	            originalBookTitle = (String) tblModel.getValueAt(i, 3);
	            originalContributorName = (String) tblModel.getValueAt(i, 4);
	            originalPrice = (int) tblModel.getValueAt(i, 5);
	            originalDate = (Date) tblModel.getValueAt(i, 6);
	            break;
	        }
	    }

	    return !txtTransaction.getText().trim().equals(originalTransactionNo) ||
	           !txtAccession.getText().trim().equals(originalAccessionNo) ||
	           !txtBookTitle.getText().trim().equals(originalBookTitle) ||
	           !txtContributor.getText().trim().equals(originalContributorName) ||
	           !txtPrice.getText().trim().equals(String.valueOf(originalPrice)) ||
	           (calDateReceive.getDate() != null && !calDateReceive.getDate().equals(originalDate));
	}

	private void tryExitEditMode() {
	    if (!isEditMode) return;

	    if (hasChanges()) {
	        int confirm = JOptionPane.showConfirmDialog(
	            this, "Discard changes?", "Unsaved Changes", JOptionPane.YES_NO_OPTION
	        );
	        if (confirm == JOptionPane.YES_OPTION) {
	            exitEditMode();
	            tblAcquisition.clearSelection();
	        }
	    } else {
	        exitEditMode();
	        tblAcquisition.clearSelection();
	    }
	}

	private void startNewTransactionBatch() {
		try {
			currentTransactionNo = acquisitionController.generateNextTransactionNo();
			txtTransaction.setText(currentTransactionNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addAcquisition() {

	    int bookPrice;
	    int quantity;

	    try {
	        bookPrice = Integer.parseInt(txtPrice.getText().trim());
	        quantity = Integer.parseInt(txtQuantity.getText().trim());
	    } catch (NumberFormatException nfe) {
	        JOptionPane.showMessageDialog(this, "Price at Quantity ay dapat numero lang.", "Warning", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    int bookId = selectedBookId;
	    int supplierId = "supplier".equalsIgnoreCase(selectedContributorType) ? selectedContributorId : -1;
	    int donorId = "donor".equalsIgnoreCase(selectedContributorType) ? selectedContributorId : -1;
	    Date bookDateAcquired = calDateReceive.getDate();

	    try {
	        acquisitionController.addAcquisition(currentTransactionNo, bookId, supplierId, donorId, bookPrice, bookDateAcquired, quantity);
	        JOptionPane.showMessageDialog(this, quantity + " item(s) added!");
	        loadAcquisition();
	        clearItemFields();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	}

	public void updateAcquisition() {

	    int bookPrice;
	    try {
	        bookPrice = Integer.parseInt(txtPrice.getText().trim());
	    } catch (NumberFormatException nfe) {
	        JOptionPane.showMessageDialog(this, "Price ay dapat numero lang.", "Warning", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    int bookId = selectedBookId;
	    int supplierId = "supplier".equalsIgnoreCase(selectedContributorType) ? selectedContributorId : -1;
	    int donorId = "donor".equalsIgnoreCase(selectedContributorType) ? selectedContributorId : -1;
	    Date bookDateAcquired = calDateReceive.getDate();
	    int acquisitionId = selectedAcquisitionId;

	    try {
	        acquisitionController.updateAcquisition(acquisitionId, bookId, supplierId, donorId, bookPrice, bookDateAcquired);
	        JOptionPane.showMessageDialog(this, "Successfully updated!");
	        loadAcquisition();
	        exitEditMode();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	public void deleteAcquisition() {

		int confirm = JOptionPane.showConfirmDialog(this, "Delete this record?", "Warning", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;

		try {
			acquisitionController.deleteAcquisition(selectedAcquisitionId);
			JOptionPane.showMessageDialog(this, "Successfully deleted!");
			loadAcquisition();
			exitEditMode();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadAcquisition() {
		try {
			tblModel.setRowCount(0);
			ArrayList<AcquisitionDisplay> loadAcqu = acquisitionController.loadAcquisition();

			for (AcquisitionDisplay acqu : loadAcqu) {
				Object[] row = {
						acqu.getAcquisitionId(),
						acqu.getTransactionNo(),
						acqu.getAccessionNo(),
						acqu.getBookTitle(),
						acqu.getContributorName(),
						acqu.getBookPrice(),
						acqu.getDateAcquired()
				};
				tblModel.addRow(row);
			}

			SwingUtilities.invokeLater(() -> { restoreSelectedRow(); });

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void searchAcquisition() {
	    try {
	        String keyword = txtSearch.getText().trim();
	        tblModel.setRowCount(0);

	        ArrayList<AcquisitionDisplay> acquisitionList = keyword.isEmpty()
	                ? acquisitionController.loadAcquisition()
	                : acquisitionController.searchAcquisition(keyword);

	        for (AcquisitionDisplay acqu : acquisitionList) {
	            Object[] row = {
	                    acqu.getAcquisitionId(),
	                    acqu.getTransactionNo(),
	                    acqu.getAccessionNo(),
	                    acqu.getBookTitle(),
	                    acqu.getContributorName(),
	                    acqu.getBookPrice(),
	                    acqu.getDateAcquired()
	            };
	            tblModel.addRow(row);
	        }

	        SwingUtilities.invokeLater(this::restoreSelectedRow);

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}