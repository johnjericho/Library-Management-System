package view.InventoryModule.InventoryTab;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.AcquisitionModule.InventoryController;
import model.AcqusitionModule.InventoryDisplay;
import utility.AppContext;
import utility.TableRefresherHelper;
import view.MainFrame;
import view.InventoryModule.AcquisitionTab.AcquisitionView;

import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class InventoryView extends JPanel {

	private MainFrame mainFrame;
	private AcquisitionView acquisition;
	 
	private static final long serialVersionUID = 1L;

	//TEXTFIELD
	private JTextField txtSearch;

	//BUTTON
	private JButton btnInventory;
	private JButton btnAcquisition;
	
	//TABLE
	private DefaultTableModel tblModel;
	private JTable tblInventory;
	private JScrollPane scrollPane;
	
	//CONTROLLER
	private InventoryController inventoryController = AppContext.getInstance().getInventoryController();
	
	//SENTINEL
	private int selectedInventoriId = -1;


	   public InventoryView(MainFrame mainFrame) {
		   this.mainFrame = mainFrame;
		   initialize();
		   }    

	    
	    public void initialize() {
	    	setPanel();
	    	initComponent();
	    	initAction();
	    }
	    
	   public void setPanel(){
	       this.setLayout(null);
	       this.setSize(1126,743);
	    }
	   
	   public void initComponent() {
		   
	       JLabel lblSearch = new JLabel("Search");
	       lblSearch.setOpaque(true);
	       lblSearch.setForeground(new Color(51, 102, 51));
	       lblSearch.setFont(new Font("Tahoma", Font.BOLD, 19));
	       lblSearch.setBounds(751, 93, 64, 20);
	       add(lblSearch);
	       
	       txtSearch = new JTextField();
	       txtSearch.setColumns(10);
	       txtSearch.setBounds(841, 91, 227, 26);
	       add(txtSearch);
		   
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
	       btnInventory.setBounds(10, 11, 138, 20);
	       btnInventory.setForeground(new Color(51, 102, 51));
	       btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnInventory.setEnabled(true);
	       panel.add(btnInventory);
	       
	       JPanel tableBorder = new JPanel();
	       tableBorder.setLayout(null);
	       tableBorder.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
	       tableBorder.setBounds(39, 104, 1058, 567);
	       add(tableBorder);
	       
	       String[] column = {"INVENTORY ID","ACCESSION NO.", "BOOK TITLE","CONDITION","STATUS"};
			tblModel = new DefaultTableModel(column, 0) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
					
			tblInventory = new JTable(tblModel);
					      
	       scrollPane = new JScrollPane(tblInventory);
	       scrollPane.setBounds(33, 27, 995, 529);
	       tableBorder.add(scrollPane);
	       
	       JSeparator separator = new JSeparator();
	       separator.setFont(new Font("Dialog", Font.PLAIN, 15));
	       separator.setBackground(new Color(0, 100, 0));
	       separator.setBounds(39, 700, 1058, 17);
	       add(separator);
	       
	       tblInventory.getColumnModel().getColumn(0).setMinWidth(0);
	       tblInventory.getColumnModel().getColumn(0).setMaxWidth(0);
	       tblInventory.getColumnModel().getColumn(0).setWidth(0);

	   }

	   public void initAction() {		   
		   btnAcquisition.addActionListener(e ->{
		        mainFrame.showPanel(acquisition);
		   });		   
		   
		   loadInventoryDisplay();
		   
			txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			    public void insertUpdate(DocumentEvent e) { searchInventory(); }
			    public void removeUpdate(DocumentEvent e) {searchInventory(); }
			    public void changedUpdate(DocumentEvent e) { searchInventory(); }	
		});
		   
			TableRefresherHelper.tblRefresher(3000, () ->{ loadInventoryDisplay(); });
			

			tblInventory.getSelectionModel().addListSelectionListener(e -> {
			    if (e.getValueIsAdjusting()) return;

			    int selectedRow = tblInventory.getSelectedRow();
			    if (selectedRow != -1) {
			        int id = (int) tblModel.getValueAt(selectedRow, 0);

			        if (selectedInventoriId != id) {
			        	selectedInventoriId = id;
			        }
			    }
			});
			
	   }
	   
	   
		private void restoreSelectedRow() {
			if(selectedInventoriId == -1) {
				return;
			}
			
			for(int i = 0; i < tblModel.getRowCount(); i++) {
				int rowId = (int) tblModel.getValueAt(i, 0);
				if(rowId == selectedInventoriId) {
					tblInventory.setRowSelectionInterval(i, i); 
					break;
				}
			}
			
		}
		
	   
	   public void loadInventoryDisplay() {
		   tblModel.setRowCount(0);
		   
		   ArrayList<InventoryDisplay> inventoryList = inventoryController.loadInventoryDisplay();
		   
		   for(InventoryDisplay inv :inventoryList ) {
			   Object[] eachRow = {
					   			inv.getInventoryId(),
					   			inv.getAccessionNo(),
					   			inv.getBookTitle(),
					   			inv.getCondition(),
					   			inv.getStatus()
					   			};
			   	tblModel.addRow(eachRow);		   
		   }
		   restoreSelectedRow();
		   
	   }
	   
	   
	   public void searchInventory() {
		     
		   tblModel.setRowCount(0);
		   
		   String keyword = txtSearch.getText();	
		   
		   if(!keyword.isEmpty()) {
			   TableRefresherHelper.stopRefresher();
		   }else TableRefresherHelper.startRefresher();
		   
		  try { 
		   ArrayList<InventoryDisplay> result =  inventoryController.searchInventory(keyword);
		  
		   for(InventoryDisplay inv : result) {
			   Object[] eachRow = {
			   			inv.getInventoryId(),
			   			inv.getAccessionNo(),
			   			inv.getBookTitle(),
			   			inv.getCondition(),
			   			inv.getStatus()
			   			};
			   tblModel.addRow(eachRow);		
		   }
		  }catch(Exception e) {
			  JOptionPane.showMessageDialog(this, e.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
		  }
		   
	   }
	   
	   
	   
	// setter para sa shared instance mula sa Modules
	   public void setAcquisitionView(AcquisitionView acquisition) {
	       this.acquisition = acquisition;
	   }
}
