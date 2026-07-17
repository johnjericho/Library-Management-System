package view.InventoryModule.AcquisitionTab;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.AcquisitionModule.AcquisitionController;
import controller.BookModule.BookMaintenanceController;
import utility.AppContext;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AcquisitionAddEditForm extends JDialog {
	
	private BookMaintenanceController bookMaintenanceController = AppContext.getInstance().getBookMaintenanceController();
	private AcquisitionController acquisitionController = AppContext.getInstance().getAcquisitionController();


	private static final long serialVersionUID = 1L;
	private  JPanel contentPanel = new JPanel();
	//TEXTFIELDS
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtCategory;
	private JTextField txtTransaction;
	private JTextField txtContributor;
	private JTextField txtContributorType;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtPublisher;
	private JTextField textField;
	
	//BUTTON
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnFindBook;
	private JButton btnFindContributor;
	
	//TABLE
	private DefaultTableModel tblModel;
	private JTable tblAcquItem;
	private JScrollPane scrollPane;

	//SENTINEL
	private int selectedBookId = -1;
	private int selectedContributorId = -1;


	public AcquisitionAddEditForm() {
		execute() ;
	}
	
	public void execute() {
		setPanel();
		initComponent();
		initAction();
	}
	
	public void setPanel() {
		setBounds(100, 100, 776, 620);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(0, 0, 754, 580);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


	}
	
	public void initComponent() {
		
		JPanel componentsBorder = new JPanel();
		componentsBorder.setBounds(13, 11, 734, 271);
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		contentPanel.add(componentsBorder);
		
		JLabel lbkBorrower = new JLabel("Transaction Detail");
		lbkBorrower.setOpaque(true);
		lbkBorrower.setForeground(new Color(51, 102, 51));
		lbkBorrower.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbkBorrower.setBackground(UIManager.getColor("Button.background"));
		lbkBorrower.setBounds(407, 23, 160, 14);
		componentsBorder.add(lbkBorrower);
		
		JLabel lblDetail = new JLabel("Book Detail");
		lblDetail.setOpaque(true);
		lblDetail.setForeground(new Color(51, 102, 51));
		lblDetail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDetail.setBackground(UIManager.getColor("Button.background"));
		lblDetail.setBounds(48, 23, 99, 14);
		componentsBorder.add(lblDetail);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setLayout(null);
		bookPanel.setForeground(UIManager.getColor("Button.background"));
		bookPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
		bookPanel.setBounds(26, 31, 327, 211);
		componentsBorder.add(bookPanel);
		
		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setForeground(new Color(0, 100, 0));
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(72, 22, 44, 18);
		bookPanel.add(lblIsbn);
		
		txtIsbn = new JTextField();
		txtIsbn.setEditable(false);
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(117, 22, 103, 20);
		bookPanel.add(txtIsbn);
		
		JLabel lblBookTitle = new JLabel("Book Title :");
		lblBookTitle.setForeground(new Color(0, 100, 0));
		lblBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookTitle.setBounds(42, 51, 68, 18);
		bookPanel.add(lblBookTitle);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setForeground(new Color(0, 100, 0));
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthor.setBounds(60, 75, 51, 20);
		bookPanel.add(lblAuthor);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setColumns(10);
		txtTitle.setBounds(117, 51, 174, 20);
		bookPanel.add(txtTitle);
		
		btnFindBook = new JButton("FIND");
		btnFindBook.setForeground(new Color(51, 102, 51));
		btnFindBook.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFindBook.setEnabled(true);
		btnFindBook.setBounds(223, 22, 68, 19);
		bookPanel.add(btnFindBook);
		
		txtAuthor = new JTextField();
		txtAuthor.setEditable(false);
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(117, 77, 174, 20);
		bookPanel.add(txtAuthor);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setForeground(new Color(0, 100, 0));
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategory.setBounds(46, 136, 68, 20);
		bookPanel.add(lblCategory);
		
		txtCategory = new JTextField();
		txtCategory.setEditable(false);
		txtCategory.setColumns(10);
		txtCategory.setBounds(117, 138, 174, 20);
		bookPanel.add(txtCategory);
		
		txtPublisher = new JTextField();
		txtPublisher.setEditable(false);
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(117, 108, 174, 20);
		bookPanel.add(txtPublisher);
		
		JLabel lblPublisher = new JLabel("Publisher :");
		lblPublisher.setForeground(new Color(0, 100, 0));
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(48, 106, 63, 20);
		bookPanel.add(lblPublisher);
		
		JPanel borrowerPanel = new JPanel();
		borrowerPanel.setLayout(null);
		borrowerPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
		borrowerPanel.setBounds(383, 31, 320, 211);
		componentsBorder.add(borrowerPanel);
		
		JLabel lblTransaction = new JLabel("Transaction :");
		lblTransaction.setForeground(new Color(0, 100, 0));
		lblTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTransaction.setBounds(22, 22, 83, 18);
		borrowerPanel.add(lblTransaction);
		
		txtTransaction = new JTextField();
		txtTransaction.setEditable(false);
		txtTransaction.setColumns(10);
		txtTransaction.setBounds(109, 22, 174, 20);
		borrowerPanel.add(txtTransaction);
		
		JLabel lblContributor = new JLabel("Contributor :");
		lblContributor.setForeground(new Color(0, 100, 0));
		lblContributor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContributor.setBounds(22, 51, 82, 18);
		borrowerPanel.add(lblContributor);
		
		txtContributor = new JTextField();
		txtContributor.setEditable(false);
		txtContributor.setColumns(10);
		txtContributor.setBounds(109, 51, 103, 20);
		borrowerPanel.add(txtContributor);
		
		JLabel lblContributorType = new JLabel("Type:");
		lblContributorType.setForeground(new Color(0, 100, 0));
		lblContributorType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContributorType.setBounds(66, 77, 36, 20);
		borrowerPanel.add(lblContributorType);
		
		txtContributorType = new JTextField();
		txtContributorType.setEditable(false);
		txtContributorType.setColumns(10);
		txtContributorType.setBounds(109, 77, 174, 20);
		borrowerPanel.add(txtContributorType);
		
		JLabel lblQuantity = new JLabel("quantity :");
		lblQuantity.setForeground(new Color(0, 100, 0));
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(42, 108, 60, 20);
		borrowerPanel.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(109, 108, 174, 20);
		borrowerPanel.add(txtQuantity);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setForeground(new Color(0, 100, 0));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(64, 135, 38, 20);
		borrowerPanel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(109, 135, 174, 20);
		borrowerPanel.add(txtPrice);
		
		btnAdd = new JButton("ADD");

		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setEnabled(true);
		btnAdd.setBounds(200, 182, 83, 18);
		borrowerPanel.add(btnAdd);
		
		btnFindContributor = new JButton("FIND");
		btnFindContributor.setForeground(new Color(51, 102, 51));
		btnFindContributor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFindContributor.setEnabled(true);
		btnFindContributor.setBounds(215, 51, 68, 19);
		borrowerPanel.add(btnFindContributor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(550, 298, 179, 20);
		contentPanel.add(textField);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setOpaque(true);
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(482, 298, 58, 14);
		contentPanel.add(lblSearch);
		
		btnSave = new JButton("SAVE");
		btnSave.setForeground(new Color(51, 102, 51));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setEnabled(true);
		btnSave.setBounds(37, 297, 86, 20);
		contentPanel.add(btnSave);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 100, 0), 2));
		panel.setBounds(13, 306, 734, 263);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		String[] column = {"BOOK TITLE", "QUANTITY","UNIT PRICE","TOTAL"};
		tblModel = new DefaultTableModel(column, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblAcquItem = new JTable(tblModel);
		
		scrollPane = new JScrollPane(tblAcquItem);
		scrollPane.setBounds(10, 22, 714, 210);
		panel.add(scrollPane);
		
		JLabel lblGrandTotal = new JLabel("Grand Total :");
		lblGrandTotal.setOpaque(true);
		lblGrandTotal.setForeground(new Color(51, 102, 51));
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrandTotal.setBounds(10, 238, 96, 14);
		panel.add(lblGrandTotal);
		
		
		
	}
	
	public void initAction() {
		
		txtTransaction.setText(acquisitionController.generateNextTransactionNo());
		
		btnFindBook.addActionListener(e ->{		openBookDialog();    });
		btnFindContributor.addActionListener(e -> {  openContributorDialog(); });
		btnAdd.addActionListener(e ->{  addBook();  });
	}
	
	public void openBookDialog() {
		BookDialogPicker bookList = new BookDialogPicker();
		bookList.setModal(true);
		bookList.setVisible(true);
		
		selectedBookId = bookList.getSelectedBookId();
		
		if(selectedBookId != -1) {
			
			String selectedBookIsbn = bookList.getSelectedBookIsbn();
			String selectedTitle = bookList.getSelectedBookitle();
			String selectedBookAuthor = bookList.getSelectedBookAuthor();
			String selectedBookPublisher = bookList.getSelectedBookPublisher();
			String selectedBookCategory = bookList.getSelectedBookCategory();
			
			txtIsbn.setText(selectedBookIsbn);
			txtTitle.setText(selectedTitle);
			txtAuthor.setText(selectedBookAuthor);
			txtPublisher.setText(selectedBookPublisher);
			txtCategory.setText(selectedBookCategory);
		}
			
	}
	
	
	public void openContributorDialog() {
		ContributorDialogPicker contributorDialogPicker = new ContributorDialogPicker();
		contributorDialogPicker.setModal(true);
		contributorDialogPicker.setVisible(true);
		
		selectedContributorId = contributorDialogPicker.getSelectedContributorId();
		
		if(selectedContributorId != -1) {
			String contributor = contributorDialogPicker.getSelectedContributorName();
			String type = contributorDialogPicker.getSelectedContributorType();
			
			txtContributor.setText(contributor);
			txtContributorType.setText(type);
			
		}

	}
	
	public void  addBook() {
		
	}
	
	
	
	
}
