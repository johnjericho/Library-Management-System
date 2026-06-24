package view.BookModule;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.BookModule.BookMaintenanceController;
import utility.AppContext;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// BAGO: FileMaintenancePanel extends JPanel
// dati si FileMaintenance extends JFrame — nagbubukas ng bagong window
// ngayon panel lang ito na nilo-load sa loob ng AdminDash
public class BookMaintenanceView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable tblBook;
    private JTextField txtIsbn;
    private JTextField txtBookName;
    private JTextField txtAuthor;
    private JTextField txtPublisher;
    private JTextField txtCategory;
    private JCheckBox chkbxIsbn;
    private JButton btnBrowseAuthor;
    private JButton btnBrowsePublisher;
    private JButton btnBrowseCategory;
    private JButton btnBrowseContributor;
    private JComboBox cbContributor;
    private JSpinner spinReserveCopy;
    private JDateChooser datePublished;
    private  DefaultTableModel tblModel;
    private  JScrollPane scrollPane;
    private JTextField txtSearch;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    
	private BookMaintenanceController bookMaintenanceController = AppContext.getInstance().getBookMaintenanceController();

	 private int selectedAuthorId = -1;
     
	 
   public BookMaintenanceView() {
	   initialize();
	   }    

    
    public void initialize() {
    	setPanel();
    	initComponents();
    	initActions();
    }
    
   public void setPanel(){
       this.setLayout(null);
       this.setSize(1126,743);

    }
    
    public void initComponents() {
    	
    	
   
        
        JPanel componentsBorder = new JPanel();
        componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
        componentsBorder.setBounds(20, 21, 1083, 252);
        componentsBorder.setLayout(null);
        add(componentsBorder);
        
           
        // Labels
        
        JLabel lblNewLabel = new JLabel("ISBN");
        lblNewLabel.setForeground(new Color(51, 102, 51));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(43, 79, 46, 14);
        componentsBorder.add(lblNewLabel);
        
        JLabel lblBookTitle = new JLabel("Book title");
        lblBookTitle.setForeground(new Color(51, 102, 51));
        lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle.setBounds(10, 110, 97, 14);
        componentsBorder.add(lblBookTitle);
        
        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setForeground(new Color(51, 102, 51));
        lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblAuthor.setBounds(30, 145, 64, 14);
        componentsBorder.add(lblAuthor);
        
        JLabel lblBookTitle_2 = new JLabel("Publisher");
        lblBookTitle_2.setForeground(new Color(51, 102, 51));
        lblBookTitle_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle_2.setBounds(10, 180, 97, 14);
        componentsBorder.add(lblBookTitle_2);
        
        JLabel lblBookTitle_3 = new JLabel("Category");
        lblBookTitle_3.setForeground(new Color(51, 102, 51));
        lblBookTitle_3.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle_3.setBounds(590, 79, 97, 27);
        componentsBorder.add(lblBookTitle_3);
        
        JLabel lblBookTitle_4 = new JLabel("Date published");
        lblBookTitle_4.setForeground(new Color(51, 102, 51));
        lblBookTitle_4.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle_4.setBounds(543, 145, 144, 23);
        componentsBorder.add(lblBookTitle_4);
        
        JLabel lblBookTitle_5 = new JLabel("Reserve Copy");
        lblBookTitle_5.setForeground(new Color(51, 102, 51));
        lblBookTitle_5.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle_5.setBounds(553, 177, 126, 20);
        componentsBorder.add(lblBookTitle_5);
        
        
        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(51, 204, 51));
        separator.setBounds(20, 719, 1083, 6);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(new Color(51, 204, 51));
        separator_1.setBounds(523, 11, 24, 229);
        componentsBorder.add(separator_1);
        
        // Textfields
        
        txtIsbn = new JTextField();
        txtIsbn.setBounds(120, 79, 191, 20);
        componentsBorder.add(txtIsbn);
        txtIsbn.setColumns(10);
        
        chkbxIsbn = new JCheckBox("no isbn");
        chkbxIsbn.setForeground(new Color(51, 102, 51));
        chkbxIsbn.setFont(new Font("Tahoma", Font.BOLD, 13));
        chkbxIsbn.setBounds(120, 56, 97, 23);
        componentsBorder.add(chkbxIsbn);
        
        txtBookName = new JTextField();
        txtBookName.setColumns(10);
        txtBookName.setBounds(120, 108, 191, 20);
        componentsBorder.add(txtBookName);
        
        
        txtAuthor = new JTextField();
        txtAuthor.setColumns(10);
        txtAuthor.setBounds(120, 139, 191, 20);
        txtAuthor.setEditable(false);
        componentsBorder.add(txtAuthor);
        
        txtPublisher = new JTextField();
        txtPublisher.setColumns(10);
        txtPublisher.setBounds(120, 170, 191, 20);
        //txtPublisher.setEditable(false);
        componentsBorder.add(txtPublisher);
        
        txtCategory = new JTextField();
        txtCategory.setColumns(10);
        txtCategory.setBounds(685, 85, 191, 20);
       // txtCategory.setEditable(false);
        componentsBorder.add(txtCategory);
        
        spinReserveCopy = new JSpinner( 
        new SpinnerNumberModel(0, 0, 100, 1));
        spinReserveCopy.setBounds(685, 180, 191, 20);
        componentsBorder.add(spinReserveCopy);
        
        datePublished = new JDateChooser();
        JTextField textField = (JTextField) datePublished.getDateEditor().getUiComponent();
        textField.setEditable(false);
        datePublished.setMaxSelectableDate(new Date()); // Hindi pwede pumili ng future date
        datePublished.setBounds(685, 148, 191, 20);
        componentsBorder.add(datePublished);
        
        //button
        
        btnBrowseAuthor = new JButton("browse");
        btnBrowseAuthor.setForeground(new Color(51, 102, 51));
        btnBrowseAuthor.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBrowseAuthor.setBounds(321, 140, 80, 20);
        componentsBorder.add(btnBrowseAuthor);
        
        btnBrowsePublisher = new JButton("browse");
        btnBrowsePublisher.setForeground(new Color(51, 102, 51));
        btnBrowsePublisher.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBrowsePublisher.setBounds(321, 169, 80, 20);
        componentsBorder.add(btnBrowsePublisher);
        
        btnBrowseCategory = new JButton("browse");
        btnBrowseCategory.setForeground(new Color(51, 102, 51));
        btnBrowseCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBrowseCategory.setBounds(886, 84, 80, 20);
        componentsBorder.add(btnBrowseCategory);
        
        cbContributor = new JComboBox();
        cbContributor.setBounds(685, 116, 191, 22);
        cbContributor.addItem("");
        cbContributor.addItem("Supplier");
        cbContributor.addItem("Donor");
        componentsBorder.add(cbContributor);
        
        JLabel lblContributor = new JLabel("Contributor");
        lblContributor.setForeground(new Color(51, 102, 51));
        lblContributor.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblContributor.setBounds(570, 113, 117, 27);
        componentsBorder.add(lblContributor);
        
        btnBrowseContributor = new JButton("browse");
        btnBrowseContributor.setForeground(new Color(51, 102, 51));
        btnBrowseContributor.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBrowseContributor.setBounds(886, 118, 80, 20);
        componentsBorder.add(btnBrowseContributor);
            
        String[] columns = {"Book ID", "ISBN", "Title", "Author", "Publisher", "Category", "Contributor", "Date published", "Reserve copy"};
        tblModel = new DefaultTableModel(columns, 0){
    		public boolean  isCellEditable(int row, int column){ return false; }
 	   };
        tblBook = new JTable(tblModel);
        
        scrollPane = new JScrollPane(tblBook);
        scrollPane.setBounds(20, 331, 1083, 383);
        this.add(scrollPane);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(929, 298, 174, 20);
        add(txtSearch);
        txtSearch.setColumns(10);
        
        JLabel lblSearch = new JLabel("Search");
        lblSearch.setForeground(new Color(51, 102, 51));
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSearch.setBounds(861, 301, 58, 14);
        add(lblSearch);
        
        btnAdd = new JButton("ADD");
        btnAdd.setForeground(new Color(51, 102, 51));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setEnabled(true);
        btnAdd.setBounds(20, 296, 80, 20);
        add(btnAdd);
        
        btnUpdate = new JButton("UPDATE");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnUpdate.setForeground(new Color(51, 102, 51));
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(110, 296, 95, 20);
        add(btnUpdate);
        
        btnDelete = new JButton("DELETE");
        btnDelete.setForeground(new Color(51, 102, 51));
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDelete.setEnabled(false);
        btnDelete.setBounds(215, 296, 95, 20);
        add(btnDelete);
        
    }
    
    public void initActions() {
    	chkbxIsbn.addActionListener( e ->{
    		if(chkbxIsbn.isSelected()) {
    			txtIsbn.setText("");
    			txtIsbn.setEditable(false);
    		} else txtIsbn.setEditable(true);
    	});
    	
    	btnBrowseAuthor.addActionListener( e -> {
    		AuthorView author = new AuthorView();
    		author.setModal(true); // important , need to close the pop window , before accessing ng main window
    		author.setVisible(true);
    		
    		  // pagdating dito, ibig sabihin nadispose na si author (may napili o nag-cancel)
    	    String selectedAuthorName = author.getSelectedAuthorName();
    	      selectedAuthorId = author.getSelectedAuthorId();
    	    if (selectedAuthorName != null) {
    	        txtAuthor.setText(selectedAuthorName);
    	    }
    	});
    	
    	btnBrowsePublisher.addActionListener(e -> {
    		PublisherView publisher = new PublisherView();
    		publisher.setModal(true);
    		publisher.setVisible(true);
    		
    	});
    	
    	btnBrowseCategory.addActionListener(e ->{
           CategoryView category = new CategoryView();
           category.setModal(true);
           category.setVisible(true);
    	});
    	
    	btnBrowseContributor.addActionListener( e ->{
    		if(cbContributor.getSelectedItem().equals("Supplier")) {
        		SupplierView supplier = new SupplierView();
        		supplier.setModal(true);
        		supplier.setVisible(true);
    		}
    		if(cbContributor.getSelectedItem().equals("Donor")) {
        		DonorView donor = new DonorView();
                donor.setModal(true);
        		donor.setVisible(true);
    		}
    	});
    	
    	//CRUD
    	
    	btnAdd.addActionListener(e -> {
;
    	});
    }
    
    
    public void addBook() {
    	String isbn = txtIsbn.getText();
    	String title = txtBookName.getText();
    //	String author = txtAuthor.getText();
    	String publisher = txtPublisher.getText();
    	String category = txtCategory.getText();
    	String contributor = cbContributor.getSelectedItem().toString();
    	Date date = datePublished.getDate();
    	int reserve = (Integer) spinReserveCopy.getValue();
    	
    	try {
    	bookMaintenanceController.addBook(isbn, title, selectedAuthorId, publisher, category, contributor, date, reserve);
    	JOptionPane.showMessageDialog(this, "Succesfully added!");
    	txtIsbn.setText("");
    	txtBookName.setText("");
    	txtAuthor.setText("");
    	txtPublisher.setText("");
    	txtCategory.setText("");
   // 	txtIsbn.setText("");
    //	datePublished.setText("");
    //	spinReserveCopy.setText("");
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(this, e.getMessage(), " Warning", JOptionPane.WARNING_MESSAGE);
    	}

    }
    
    public void loadBook() {
    	
    }
    
    
    
    
}