package view.AcquisitionModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.BookMaintenanceController;
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
import javax.swing.SwingUtilities;

public class BookDialogPicker extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtSearch;	
	private DefaultTableModel tblModel;
	private JTable tblBookRecord;
	private JScrollPane scrollPane;
	
	//outside class connection
	private BookMaintenanceController bookMaintenanceController = AppContext.getInstance().getBookMaintenanceController();
	
	private int selectedBookId = -1;
	private String selectedBookTitle;

	public BookDialogPicker() {
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
		
		String[] column = {"BOOK ID","ISBN", "BOOK TITLE", "BOOK AUTHOR"};	
	    tblModel = new DefaultTableModel(column, 0) {
		public boolean  isCellEditable(int row, int column){ return false; }
	   };		
	   
	   tblBookRecord = new JTable(tblModel);
	   
		scrollPane = new JScrollPane(tblBookRecord);
		scrollPane.setBounds(10, 52, 688, 344);
		contentPanel.add(scrollPane);
		
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
				 String bookTitle = (String) tblModel.getValueAt(selectedRow, 2);
			 
			 
			 if(idValue != selectedBookId) {
				 selectedBookId = idValue;
				 selectedBookTitle = bookTitle;
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
		Object[] bookRow = {
				book.getBookId(),
				book.getBookIsbn(),
				book.getBookTitle(),
				book.getBookAuthor()
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
                Object[] row = {
                        book.getBookId(),
                        book.getBookIsbn(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
                   };
                tblModel.addRow(row);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public int getSelectedBookId() {
		return selectedBookId;
	}
	
	public String getSelectedBookitle() {
		return selectedBookTitle;
	}

}
