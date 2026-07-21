package view.InventoryModule.AcquisitionTab;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTable;


import controller.AcquisitionModule.AcquisitionController;
import model.AcqusitionModule.AcquisitionDisplay;
import utility.AppContext;
import view.MainFrame;
import view.InventoryModule.InventoryTab.InventoryView;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class AcquisitionView extends JPanel {
	
	private MainFrame mainFrame;
	private InventoryView inv;

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private DefaultTableModel tblModel;
	private JTable tblAcquisition;
	private JScrollPane scrollPane;
	private JButton btnAdd;

	//CONTROLLER
	private AcquisitionController acquisitionController = AppContext.getInstance().getAcquisitionController();

	
	private int selectedAcquisitionId = -1;
	private JButton btnInventory;
	private JButton btnAcquisition;
	private JPanel componentsBorder_1;

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

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setOpaque(true);
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSearch.setBounds(754, 110, 64, 20);
		add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(844, 108, 227, 26);
		add(txtSearch);

		String[] columns = {
				"ACQUISITION ID", "TRANSACTION #", "CONTRIBUTOR","TYPE", "DATE RECEIVED"
		};
		tblModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JSeparator separator = new JSeparator();
		separator.setFont(new Font("Dialog", Font.PLAIN, 15));
		separator.setBackground(new Color(0, 100, 0));
		separator.setBounds(42, 703, 1058, 17);
		add(separator);

		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdd.setEnabled(true);
		btnAdd.setBounds(74, 107, 101, 26);
		add(btnAdd);
		
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
		
		componentsBorder_1 = new JPanel();
		componentsBorder_1.setLayout(null);
		componentsBorder_1.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		componentsBorder_1.setBounds(42, 121, 1058, 555);
		add(componentsBorder_1);
		tblAcquisition = new JTable(tblModel);
		
	    scrollPane = new JScrollPane(tblAcquisition);
		scrollPane.setBounds(33, 27, 995, 517);
		componentsBorder_1.add(scrollPane);
				
						tblAcquisition.getColumnModel().getColumn(0).setMinWidth(0);
						tblAcquisition.getColumnModel().getColumn(0).setMaxWidth(0);
						tblAcquisition.getColumnModel().getColumn(0).setWidth(0);
	}


	
	public void initActions() {
		
		btnInventory.addActionListener(e ->{
		    mainFrame.showPanel(inv);
		}); 

		// CRUD
		btnAdd.addActionListener(e -> addTransaction());

		loadAcquisition();

		tblAcquisition.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	
				int selectedRow = tblAcquisition.getSelectedRow();
				
					if(selectedRow != -1) {	
						
						int selectedId = (int) tblModel.getValueAt(selectedRow, 0);
						
						 if (e.getClickCount() == 2) { 
							 
							 AcquisitionDetailView acquisitionAddEditForm = new AcquisitionDetailView(selectedId);
							 acquisitionAddEditForm.setModal(true);
							 acquisitionAddEditForm.setVisible(true);
							 
				        	} } } });



		txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { searchAcquisition(); }
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

	


	public void addTransaction() {
		AcquisitionAddTransaction acquisitionAddTransaction = new  AcquisitionAddTransaction();
		acquisitionAddTransaction.setModal(true);
		acquisitionAddTransaction.setVisible(true);
		loadAcquisition();
	}



	private void loadAcquisition() {
		try {
			tblModel.setRowCount(0);
			ArrayList<AcquisitionDisplay> loadAcqu = acquisitionController.loadAcquisition();

			for (AcquisitionDisplay acqu : loadAcqu) {
				Object[] row = {
						acqu.getAcquisitionId(),
						acqu.getTransactionNo(),
						acqu.getContributorName(),
						acqu.getContributorType(),
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
	                    acqu.getContributorName(),
	                    acqu.getContributorType(),
	                    acqu.getDateAcquired()
	            };
	            tblModel.addRow(row);
	        }

	        SwingUtilities.invokeLater(this::restoreSelectedRow);

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	public void setInventoryView(InventoryView inv) {
	    this.inv = inv;
	}
	
}