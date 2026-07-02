package view.AcquisitionModule;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class AcquisitionView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtAccession;
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

   private int selectedBookId = -1;
   private String selectedBookTitle;

	public AcquisitionView() {
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
		   
		   JPanel componentsBorder = new JPanel();
	       componentsBorder.setLayout(null);
	       componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
	       componentsBorder.setBounds(22, 25, 1078, 211);
	       add(componentsBorder);
		   
		   JLabel lblAccession = new JLabel("Accession #");
	       lblAccession.setForeground(new Color(51, 102, 51));
	       lblAccession.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblAccession.setBounds(10, 79, 113, 14);
	       componentsBorder.add(lblAccession);
	       
	       JLabel lblBookTitle = new JLabel("Book title");
	       lblBookTitle.setForeground(new Color(51, 102, 51));
	       lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblBookTitle.setBounds(10, 110, 97, 14);
	       componentsBorder.add(lblBookTitle);
	       
	       JLabel lblContributor = new JLabel("Contributor");
	       lblContributor.setForeground(new Color(51, 102, 51));
	       lblContributor.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblContributor.setBounds(10, 135, 113, 24);
	       componentsBorder.add(lblContributor);
	       
	       JLabel lblPrice = new JLabel("Price");
	       lblPrice.setForeground(new Color(51, 102, 51));
	       lblPrice.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblPrice.setBounds(667, 110, 97, 14);
	       componentsBorder.add(lblPrice);
	       
	       JLabel lblQuantity = new JLabel("Quantity");
	       lblQuantity.setForeground(new Color(51, 102, 51));
	       lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblQuantity.setBounds(638, 76, 97, 20);
	       componentsBorder.add(lblQuantity);
	       
	       JLabel lblDateRecieved = new JLabel("Date Receive");
	       lblDateRecieved.setForeground(new Color(51, 102, 51));
	       lblDateRecieved.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblDateRecieved.setBounds(609, 135, 126, 14);
	       componentsBorder.add(lblDateRecieved);
	       
	       JLabel lblSearch = new JLabel("Search");
	       lblSearch.setForeground(new Color(51, 102, 51));
	       lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblSearch.setBounds(858, 266, 58, 14);
	       add(lblSearch);
	       
	       //textfield
	       
	       txtAccession = new JTextField();
	       txtAccession.setColumns(10);
	       txtAccession.setBounds(120, 79, 191, 20);
	       txtAccession.setEnabled(false);
	       componentsBorder.add(txtAccession);
	       
	       txtBookTitle = new JTextField();
	       txtBookTitle.setColumns(10);
	       txtBookTitle.setBounds(120, 108, 191, 20);
	       txtBookTitle.setEditable(false);
	       componentsBorder.add(txtBookTitle);
	       
	       txtContributor = new JTextField();
	       txtContributor.setColumns(10);
	       txtContributor.setBounds(120, 135, 191, 20);
	       txtContributor.setEditable(false);
	       componentsBorder.add(txtContributor);
	       
	       calDateReceive = new JDateChooser();
	       calDateReceive.setBounds(732, 135, 191, 20);
	       JTextField textField = (JTextField) calDateReceive.getDateEditor().getUiComponent();
	        textField.setEditable(false);	     
	        componentsBorder.add(calDateReceive);
	       
	       btnBrowseContributor = new JButton("browse");
	       btnBrowseContributor.setForeground(new Color(51, 102, 51));
	       btnBrowseContributor.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowseContributor.setBounds(321, 135, 80, 20);
	       componentsBorder.add(btnBrowseContributor);
	       
	       txtPrice = new JTextField();
	       txtPrice.setColumns(10);
	       txtPrice.setBounds(732, 108, 191, 20);
	       componentsBorder.add(txtPrice);
	       
	       txtQuantity = new JTextField();
	       txtQuantity.setColumns(10);
	       txtQuantity.setBounds(732, 79, 191, 20);
	       componentsBorder.add(txtQuantity);

	       JSeparator separator_1 = new JSeparator();
	       separator_1.setOrientation(SwingConstants.VERTICAL);
	       separator_1.setForeground(new Color(51, 204, 51));
	       separator_1.setBounds(506, 11, 24, 189);
	       componentsBorder.add(separator_1);
	       
	       btnBrowseBookTitle = new JButton("browse");
	       btnBrowseBookTitle.setForeground(new Color(51, 102, 51));
	       btnBrowseBookTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowseBookTitle.setBounds(321, 109, 80, 20);
	       componentsBorder.add(btnBrowseBookTitle);
	       	       
	       txtSearch = new JTextField();
	       txtSearch.setColumns(10);
	       txtSearch.setBounds(926, 263, 174, 20);
	       add(txtSearch);
	       
	       String[] columns = {"Acquisition Id", "Transaction #", "Book Title", "Contributor", "Quantity", "Price", "Date Receive"};
	        tblModel = new DefaultTableModel(columns, 0){
	    		public boolean  isCellEditable(int row, int column){ return false; }
	 	   };
	        tblAcquisition = new JTable(tblModel);
	        
	        scrollPane = new JScrollPane(tblAcquisition);
	        scrollPane.setBounds(22, 307, 1083, 383);
	        this.add(scrollPane);
	        
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
	   }
	   
	   public void initAction() {
		   btnBrowseBookTitle.addActionListener(e ->{
			   BookDialogPicker bookDialog = new BookDialogPicker();
			   bookDialog.setModal(true);
			   bookDialog.setVisible(true);
			   
			   selectedBookTitle = bookDialog.getSelectedBookitle();

			   if(selectedBookTitle != null) {
			   selectedBookId = bookDialog.getSelectedBookId();
			   txtBookTitle.setText(selectedBookTitle);
			   }
		   });
		   
		   btnBrowseContributor.addActionListener(e ->{
			   ContributorDialogPicker contributorDialog = new ContributorDialogPicker();
			   contributorDialog.setModal(true);
			   contributorDialog.setVisible(true);
		   });
		   
	   }
	   
	   public void addAcquisiton() {
		   
	   }
	   
}
