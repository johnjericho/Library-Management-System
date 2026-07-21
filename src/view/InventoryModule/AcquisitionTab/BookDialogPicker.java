package view.InventoryModule.AcquisitionTab;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.BookMaintenanceController;
import model.AcqusitionModule.AcquisitionDetail;
import model.dto.BookDisplay;
import utility.AppContext;
import utility.TableRefresherHelper;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

public class BookDialogPicker extends JDialog {

	//CONTAINER
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	
	
	//CONTROLLER
	private BookMaintenanceController bookMaintenanceController = AppContext.getInstance().getBookMaintenanceController();
		
	//TEXTFIELD
	private JTextField txtSearch;	
	
	//TABLE
	private DefaultTableModel tblModel;
	private JTable tblBookRecord;
	private JScrollPane scrollPane;
	
	//SENTINEL
	private int selectedBookId = -1;
	private String selectedBookIsbn = "";
	private String selectedBookTitle;
	private String selectedBookAuthor = "";
	private String selectedBookPublisher = "";
	private String selectedBookCategory = "";
	
	//ARRAYS
	private ArrayList<AcquisitionDetail> selectedBookList;

	public BookDialogPicker(ArrayList<AcquisitionDetail> bookDetail) {
		this.selectedBookList = bookDetail;
		initialize();
	}
	
	public void initialize() {
		setDialog();
		 initComponent();
		 initAction();
	}
	
	public void setDialog() {
		setBounds(100, 100, 726, 461);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 884, 429);
		getContentPane().add(contentPanel);	
		contentPanel.setLayout(null);
		
	}
	
	
	public void initComponent() {
		
		String[] column = {"BOOK ID","ISBN", "BOOK TITLE", "BOOK AUTHOR","BOOK PUBLISHER", "BOOK CATEGORY",};	
	    tblModel = new DefaultTableModel(column, 0) {
		public boolean  isCellEditable(int row, int column){ return false; }
	   };		
	   
	   tblBookRecord = new JTable(tblModel);
	   
		scrollPane = new JScrollPane(tblBookRecord);
		scrollPane.setBounds(10, 52, 688, 344);
		contentPanel.add(scrollPane);
		
		//HIDE COLUMN BOOK ID
		tblBookRecord.getColumnModel().getColumn(0).setMinWidth(0);
		tblBookRecord.getColumnModel().getColumn(0).setMaxWidth(0);
		tblBookRecord.getColumnModel().getColumn(0).setWidth(0);
		
		//HIDE COLUMN BOOK PUBLSIHER
		tblBookRecord.getColumnModel().getColumn(4).setMinWidth(0);
		tblBookRecord.getColumnModel().getColumn(4).setMaxWidth(0);
		tblBookRecord.getColumnModel().getColumn(4).setWidth(0);
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(10, 407, 688, 10);
		contentPanel.add(separator);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(431, 22, 64, 14);
		contentPanel.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(492, 18, 206, 23);
		contentPanel.add(txtSearch);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loadBook();
	}
	
	public void initAction() {
		loadBook();
		
		tblBookRecord.getSelectionModel().addListSelectionListener(e ->{
			 if (e.getValueIsAdjusting()) return;
			 
			 int selectedRow = tblBookRecord.getSelectedRow();
			 
			 if(selectedRow != -1) {
				 int idValue = (int) tblModel.getValueAt(selectedRow, 0);
				 String bookIsbn = (String) tblModel.getValueAt(selectedRow, 1);
				 String bookTitle = (String) tblModel.getValueAt(selectedRow, 2);
				 String bookAuthor = (String) tblModel.getValueAt(selectedRow, 3);
				 String bookPublisher = (String) tblModel.getValueAt(selectedRow, 4);
				 String bookCategory = (String) tblModel.getValueAt(selectedRow, 5);



			 
			 
			 if(idValue != selectedBookId) {
				 selectedBookId = idValue;
				 selectedBookIsbn = bookIsbn;
				 selectedBookTitle = bookTitle;
				 selectedBookAuthor = bookAuthor;
				 selectedBookPublisher = bookPublisher;
				 selectedBookCategory = bookCategory;
				 
			 }
		 }
		});
		
		
		tblBookRecord.addMouseListener(new MouseAdapter() {
			
			   public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) { 
		                int row = tblBookRecord.getSelectedRow();
		                if (row != -1) {
		                    dispose(); // isasara ang dialog → bumabalik na control sa caller
		                }
		            }
		        }
			
		});
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) {   searchBook(); }
		    public void removeUpdate(DocumentEvent e) {  searchBook(); }
		    public void changedUpdate(DocumentEvent e) { searchBook(); }	
	});
	}
	
	
	
	
	
	public void loadBook() {
		try {
		tblModel.setRowCount(0);

		ArrayList<BookDisplay> loadBook = bookMaintenanceController.loadBookForDisplay();
		for(BookDisplay book : loadBook) {		
			if(isAlreadyAdded(book.getBookId())) {
				continue;	
			}
			
		Object[] bookRow = {
				book.getBookId(),
				book.getBookIsbn(),
				book.getBookTitle(),
				book.getBookAuthor(),
				book.getBookPublisher(),
				book.getBookCategory()
				
				};
		tblModel.addRow(bookRow);
		}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	private void searchBook() {
        try {
            String keyword = txtSearch.getText().trim();
            tblModel.setRowCount(0);
            
    		if(!keyword.isEmpty()) {
    			TableRefresherHelper.stopRefresher();
    		}else { TableRefresherHelper.startRefresher(); }

            ArrayList<BookDisplay> bookList = keyword.isEmpty()
                    ? bookMaintenanceController.loadBookForDisplay()
                    : bookMaintenanceController.searchBook(keyword);

            for (BookDisplay book : bookList) {
            	
            	if(isAlreadyAdded(book.getBookId())) {
            		continue;
            	}
            	
                Object[] row = {
                        book.getBookId(),
                        book.getBookIsbn(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
        				book.getBookPublisher(),
        				book.getBookCategory()
        				
                   };
                tblModel.addRow(row);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	
	private boolean isAlreadyAdded(int bookId) {

	    for (AcquisitionDetail detail : selectedBookList) {

	        if (detail.getBookId() == bookId) {
	            return true;
	        }

	    }

	    return false;
	}
	
	
	public int getSelectedBookId() {
		return selectedBookId;
	}
	
	public String getSelectedBookIsbn() {
		return selectedBookIsbn;
	}
	
	public String getSelectedBookitle() {
		return selectedBookTitle;
	}
	

	public String getSelectedBookAuthor() {
		return selectedBookAuthor;
	}
	
	public String getSelectedBookPublisher() {
		return selectedBookPublisher;
	}

	public String getSelectedBookCategory() {
		return selectedBookCategory;
	}

}
