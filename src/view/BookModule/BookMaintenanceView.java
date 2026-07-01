
/*
 * wrapper tawag sa cinall ng method, wlang gaanong logic
 * todo ayusin ang dao , service , controller layer
 * gumawa ng class for Book- 
 * 
 * 
 * 
 * 
 * 
 */












package view.BookModule;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.BookModule.BookMaintenanceController;
import model.BookModule.Book;
import model.BookModule.dto.BookDisplay;
import utility.AppContext;
import utility.TableRefresherHelper;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// BAGO: FileMaintenancePanel extends JPanel
// dati si FileMaintenance extends JFrame — nagbubukas ng bagong window
// ngayon panel lang ito na nilo-load sa loob ng AdminDash
public class BookMaintenanceView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable tblBook;
    private JTextField txtBookIsbn;
    private JTextField txtBookTitle;
    private JTextField txtBookAuthor;
    private JTextField txtBookPublisher;
    private JTextField txtBookCategory;
    private JCheckBox chkbxIsbn;
    private JButton btnBrowseAuthor;
    private JButton btnBrowsePublisher;
    private JButton btnBrowseCategory;
    private JSpinner bookReserveCopy;
    private JDateChooser bookDatePublished;
    private  DefaultTableModel tblModel;
    private  JScrollPane scrollPane;
    private JTextField txtSearch;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    
	private BookMaintenanceController bookMaintenanceController = AppContext.getInstance().getBookMaintenanceController();

	 private int selectedAuthorId = -1;
     private int selectedPublisherId = -1;
     private int selectedCategoryId = -1;
     private int selectedBookId = -1;
     private boolean isEditMode = false;
     private JPanel componentsBorder;
     
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
    	
    	
   
        
        componentsBorder = new JPanel();
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
        lblBookTitle_4.setBounds(543, 110, 144, 23);
        componentsBorder.add(lblBookTitle_4);
        
        JLabel lblBookTitle_5 = new JLabel("Reserve Copy");
        lblBookTitle_5.setForeground(new Color(51, 102, 51));
        lblBookTitle_5.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBookTitle_5.setBounds(553, 142, 126, 20);
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
        
        txtBookIsbn = new JTextField();
        txtBookIsbn.setBounds(120, 79, 191, 20);
        txtBookIsbn.setColumns(10);
        componentsBorder.add(txtBookIsbn);
        
        chkbxIsbn = new JCheckBox("no isbn");
        chkbxIsbn.setForeground(new Color(51, 102, 51));
        chkbxIsbn.setFont(new Font("Tahoma", Font.BOLD, 13));
        chkbxIsbn.setBounds(120, 56, 97, 23);
        componentsBorder.add(chkbxIsbn);
        
        txtBookTitle = new JTextField();
        txtBookTitle.setColumns(10);
        txtBookTitle.setBounds(120, 108, 191, 20);
        componentsBorder.add(txtBookTitle);
        
        
        txtBookAuthor = new JTextField();
        txtBookAuthor.setColumns(10);
        txtBookAuthor.setBounds(120, 139, 191, 20);
        txtBookAuthor.setEditable(false);
        componentsBorder.add(txtBookAuthor);
        
        txtBookPublisher = new JTextField();
        txtBookPublisher.setColumns(10);
        txtBookPublisher.setBounds(120, 170, 191, 20);
        txtBookPublisher.setEditable(false);
        componentsBorder.add(txtBookPublisher);
        
        txtBookCategory = new JTextField();
        txtBookCategory.setColumns(10);
        txtBookCategory.setBounds(685, 79, 191, 20);
        txtBookCategory.setEditable(false);
        componentsBorder.add(txtBookCategory);
        
        bookReserveCopy = new JSpinner( 
        new SpinnerNumberModel(0, 0, 100, 1));
        bookReserveCopy.setBounds(685, 139, 191, 20);
        componentsBorder.add(bookReserveCopy);
        
        bookDatePublished = new JDateChooser();
        JTextField textField = (JTextField) bookDatePublished.getDateEditor().getUiComponent();
        textField.setEditable(false);
        bookDatePublished.setMaxSelectableDate(new Date()); // Hindi pwede pumili ng future date
        bookDatePublished.setBounds(685, 108, 191, 20);
        componentsBorder.add(bookDatePublished);
        
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
            
        String[] columns = {"Book ID", "ISBN", "Title", "Author", "Publisher", "Category", "Date published", "Reserve copy"};
        tblModel = new DefaultTableModel(columns, 0){
    		public boolean  isCellEditable(int row, int column){ return false; }
 	   };
        tblBook = new JTable(tblModel);
        
        scrollPane = new JScrollPane(tblBook);
        scrollPane.setBounds(20, 329, 1083, 383);
        this.add(scrollPane);
        
     	tblBook.getColumnModel().getColumn(0).setMinWidth(0);
     	tblBook.getColumnModel().getColumn(0).setMaxWidth(0);
     	tblBook.getColumnModel().getColumn(0).setWidth(0);
        
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
    			txtBookIsbn.setText("");
    			txtBookIsbn.setEditable(false);
    		}else if(!chkbxIsbn.isSelected()) {
    			txtBookIsbn.setEditable(true);
    			txtBookIsbn.setText("");
    		}
    		
    	});
    	
    	btnBrowseAuthor.addActionListener( e -> {
    		
    		TableRefresherHelper.stopRefresher();
    		
    		AuthorView authorView = new AuthorView();
    		authorView.setModal(true); // important , need to close the pop window , before accessing ng main window
    		authorView.setVisible(true);
    		
    		  // pagdating dito, ibig sabihin nadispose na si author (may napili o nag-cancel)
    	    String selectedAuthorName = authorView.getSelectedAuthorName();
    	      selectedAuthorId = authorView.getSelectedAuthorId();
    	    if (selectedAuthorName != null) {
    	        txtBookAuthor.setText(selectedAuthorName);
    	    }   
    	    
    	    TableRefresherHelper.tblRefresher(3000, () ->{ loadBookDisplay(); });
    	});
    	
    	btnBrowsePublisher.addActionListener(e -> {
    		TableRefresherHelper.stopRefresher();

    		
    		PublisherView publisherView = new PublisherView();
    		publisherView.setModal(true);
    		publisherView.setVisible(true);
    		
    		selectedPublisherId = publisherView.getSelectedPublisherId();
    		String selectedPublisherName = publisherView.getSelectedPublisherName();
    		if(selectedPublisherName != null) {
    			txtBookPublisher.setText(selectedPublisherName);
    		}
    		
    	    TableRefresherHelper.tblRefresher(3000, () ->{ loadBookDisplay(); });
    	});
    	
    	btnBrowseCategory.addActionListener(e ->{
    		TableRefresherHelper.stopRefresher();

           CategoryView categoryView = new CategoryView();
           categoryView.setModal(true);
           categoryView.setVisible(true);
           
           selectedCategoryId = categoryView.getSelectedCategoryId();
          String selectedCategoryName = categoryView.getSelectedCategoryName();
           if(selectedCategoryName != null) {
        	   txtBookCategory.setText(selectedCategoryName);
           }
   	    TableRefresherHelper.tblRefresher(3000, () ->{ loadBookDisplay(); });

    	});
    	
    	
    	//CRUD
    	
    	btnAdd.addActionListener(e -> {
    		addBook();
    	});
    	
    	loadBookDisplay();
    	
    	TableRefresherHelper.tblRefresher(3000, () -> {loadBookDisplay();
    	});
    	
    	
    	tblBook.getSelectionModel().addListSelectionListener(e ->{
    		if(e.getValueIsAdjusting()) return; //mouse is long press
    		int selectedRow = tblBook.getSelectedRow();
    		
    		if(selectedRow != -1) {
    			
    			int id = (int) tblModel.getValueAt(selectedRow, 0);
    			
    			String isbn = (String) tblModel.getValueAt(selectedRow, 1);
    			String title = (String) tblModel.getValueAt(selectedRow, 2);
    			String author = (String) tblModel.getValueAt(selectedRow, 3);
    			String publisher = (String) tblModel.getValueAt(selectedRow, 4);
    			String category = (String) tblModel.getValueAt(selectedRow, 5);
                Date DatePublished = (Date) tblModel.getValueAt(selectedRow, 6);
                int bookReserve = (int) tblModel.getValueAt(selectedRow, 7);

    			
    			if(id != selectedBookId) {
    				selectedBookId = id;
    				
    				//for user view
    				txtBookIsbn.setText(isbn);
        			txtBookTitle.setText(title);
        			txtBookAuthor.setText(author);
        			txtBookPublisher.setText(publisher);
        			txtBookCategory.setText(category);
        			bookDatePublished.setDate(DatePublished);
        			bookReserveCopy.setValue(bookReserve);
        			
        			//for dev view
        			Book book = bookMaintenanceController.getBookById(selectedBookId);
        			selectedAuthorId = book.getAuthorId();
        			selectedPublisherId = book.getPublisherId();
        			selectedCategoryId = book.getCategoryId();  
        			
        			if(txtBookIsbn.getText().equals("-")) {
        			    chkbxIsbn.setSelected(true);
        			    txtBookIsbn.setEditable(false);
        			}else{
        			    chkbxIsbn.setSelected(false);
        			    txtBookIsbn.setEditable(true);
        			}
        			
        			enterEditMode();
    			}
    			

    		}
    	});
    	
    	// ESC key
    	// Sa initAction() — naka-scoped sa loob ng BookMaintenanceView panel lang
    	this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
    	    .put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "escapeAction");

    	this.getActionMap().put("escapeAction", new javax.swing.AbstractAction() {
    	    public void actionPerformed(java.awt.event.ActionEvent e) {
    	    	 tryExitEditMode();    	    }
    	});

    	// Empty area click
    	this.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseClicked(java.awt.event.MouseEvent e) {
    	    	 tryExitEditMode();	    }
    	});
    	
    	btnUpdate.addActionListener(e ->{
    		updateBook();
    	});
    
    	btnDelete.addActionListener(e ->{
    		deleteBook();
    	});
    	
    	txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    	    public void insertUpdate(javax.swing.event.DocumentEvent e) { searchBook(); }
    	    public void removeUpdate(javax.swing.event.DocumentEvent e) { searchBook(); }
    	    public void changedUpdate(javax.swing.event.DocumentEvent e) { searchBook(); }
    	});
    	
    }
    
       private void restoreSelectedRow() {
    	  if(selectedBookId == -1) return;
    	  
    	  for(int i = 0; i < tblModel.getRowCount(); i++) {
    		  int rowId = (int) tblModel.getValueAt(i, 0);
    		  if(rowId == selectedBookId) {
    			  tblBook.setRowSelectionInterval(i, i);
    			  break;
    		  }
    	  } 	  
       }
       
       private void enterEditMode() {
    	       isEditMode = true;
    		   btnAdd.setEnabled(false);
    		   btnUpdate.setEnabled(true);
    		   btnDelete.setEnabled(true);
       }
       
       private void exitEditMode() {
    	   isEditMode = false;
		   btnAdd.setEnabled(true);
		   btnUpdate.setEnabled(false);
		   btnDelete.setEnabled(false);
		   
		   txtBookIsbn.setText("");
		   txtBookTitle.setText("");
		   txtBookAuthor.setText("");
		   txtBookPublisher.setText("");
		   txtBookCategory.setText("");
		   bookDatePublished.setDate(null);
		   bookReserveCopy.setValue(0);
		   
		   chkbxIsbn.setSelected(false);
		   selectedBookId = -1;
		   selectedAuthorId = -1;
		   selectedPublisherId = -1;
		   selectedCategoryId = -1;
       }
       
       
       private boolean hasChanges() {
    	    String originalIsbn = "";
    	    String originalTitle = "";
    	    String originalAuthor = "";
    	    String originalPublisher = "";
    	    String originalCategory = "";
    	    Date originalDate = null;
    	    int originalReserve = 0;

    	    for (int i = 0; i < tblModel.getRowCount(); i++) {
    	        if ((int) tblModel.getValueAt(i, 0) == selectedBookId) {
    	            originalIsbn = (String) tblModel.getValueAt(i, 1);
    	            originalTitle = (String) tblModel.getValueAt(i, 2);
    	            originalAuthor = (String) tblModel.getValueAt(i, 3);
    	            originalPublisher = (String) tblModel.getValueAt(i, 4);
    	            originalCategory = (String) tblModel.getValueAt(i, 5);
    	            originalDate = (Date) tblModel.getValueAt(i, 6);
    	            originalReserve = (int) tblModel.getValueAt(i, 7);
    	            break;
    	        }
    	    }

    	    return !txtBookIsbn.getText().trim().equals(originalIsbn) ||
    	           !txtBookTitle.getText().trim().equals(originalTitle) ||
    	           !txtBookAuthor.getText().trim().equals(originalAuthor) ||
    	           !txtBookPublisher.getText().trim().equals(originalPublisher) ||
    	           !txtBookCategory.getText().trim().equals(originalCategory) ||
    	           (bookDatePublished.getDate() != null && !bookDatePublished.getDate().equals(originalDate)) ||
    	           (Integer) bookReserveCopy.getValue() != originalReserve;
    	}
       
       private void tryExitEditMode() {
    	    if (!isEditMode) return;

    	    if (hasChanges()) {
    	        int confirm = JOptionPane.showConfirmDialog(
    	            this, "Discard changes?", "Unsaved Changes", JOptionPane.YES_NO_OPTION
    	        );
    	        if (confirm == JOptionPane.YES_OPTION) {
    	            exitEditMode();
    	            tblBook.clearSelection();
    	        }
    	    } else {
    	        exitEditMode();
    	        tblBook.clearSelection();
    	    }
    	}
       
    
    public void addBook() {
    	String isbn = txtBookIsbn.getText();
    	String title = txtBookTitle.getText();
    	int author = selectedAuthorId;
    	int publisher = selectedPublisherId;
    	int category = selectedCategoryId;
    	Date date = bookDatePublished.getDate();
    	int reserve = (Integer) bookReserveCopy.getValue();
    	
    	try {
    	bookMaintenanceController.addBook(isbn, title, author, publisher, category, date, reserve);
    	JOptionPane.showMessageDialog(this, "Succesfully added!");
    	loadBookDisplay();
    	exitEditMode();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(this, e.getMessage(), " Warning", JOptionPane.WARNING_MESSAGE);
    	}

    }

    
    
    private void loadBookDisplay() {
    	try {
        tblModel.setRowCount(0);
        ArrayList<BookDisplay> bookList = bookMaintenanceController.loadBookForDisplay();

        for (BookDisplay book : bookList) {
        Object[] row = {
	                    book.getBookId(),
	                    book.getBookIsbn(),
	                    book.getBookTitle(),
	                    book.getBookAuthor(),
	                    book.getBookPublisher(),
	                    book.getBookCategory(),
	                    book.getBookDatePublished(),
	                    book.getBookReserveCopy()
         		};	
        	tblModel.addRow(row);
        	
        	SwingUtilities.invokeLater(() -> {
    			restoreSelectedRow();
    		});
        }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(this, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public void updateBook() {
    	
        if (!hasChanges()) {
            JOptionPane.showMessageDialog(this, "No changes detected.");
            return;
        }
    	
        String isbn = txtBookIsbn.getText();
        String title = txtBookTitle.getText();
        int author = selectedAuthorId;
        int publisher = selectedPublisherId;
        int category = selectedCategoryId;
        Date date = bookDatePublished.getDate();
        int reserve = (Integer) bookReserveCopy.getValue();
        int bookId = selectedBookId;

        try {
            bookMaintenanceController.updateBook(bookId, isbn, title, author, publisher, category, date, reserve);
            JOptionPane.showMessageDialog(this, "Successfully updated!");
            loadBookDisplay();
            exitEditMode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void deleteBook() {
    	int selectedRow = selectedBookId;
    	try {
    	bookMaintenanceController.deleteBook(selectedRow);
    	JOptionPane.showMessageDialog(this, "Successfully deleted!");
    	exitEditMode();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
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
                Object[] row = {
                        book.getBookId(),
                        book.getBookIsbn(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
                        book.getBookPublisher(),
                        book.getBookCategory(),
                        book.getBookDatePublished(),
                        book.getBookReserveCopy()
                };
                tblModel.addRow(row);
            }

            SwingUtilities.invokeLater(this::restoreSelectedRow);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}