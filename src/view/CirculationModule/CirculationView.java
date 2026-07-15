package view.CirculationModule;

import javax.swing.JPanel;

import controller.CirculationModule.CirculationController;
import utility.AppContext;
import view.MainFrame;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class CirculationView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CirculationController circulationController = AppContext.getInstance().getCirculationController();
	private JPanel componentsBorder;
	private JButton btnVisitLog;
	private JButton btnCirculation;
	private JTextField txtSearch;
	private JPanel bookPanel;
	private JPanel borrowerPanel;
	private JPanel tabPanel;
	private DefaultTableModel tblModel;
	private JTable tblCirculation;
	private JScrollPane scrollPane;
	private JLabel lbkBorrower;
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtCategory;
	private JTextField txtTotalBook;
	private JTextField txtReserve;
	private JTextField txtLrn;
	private JButton btnFindBorrower;
	private JTextField txtFullName;
	private JTextField txtDepartment;
	private JTextField txtGrade;
	private JTextField txtSection;
	private JButton btnBorrow;
    private MainFrame mainFrame;

	   public CirculationView(MainFrame mainFrame) {
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
	       
	       componentsBorder = new JPanel();
	       componentsBorder.setLayout(null);
	       componentsBorder.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
	       componentsBorder.setBounds(123, 60, 887, 303);
	       add(componentsBorder);
	       
	       
	       


	    }
	   
	   public void initComponent() {
		   
		   String[] column = {"CIRCULATION NO", "BORROWER", "QUANTITY", "BORROW DATE", "DUE DATE"};
		   tblModel = new DefaultTableModel(column, 0);
		   tblCirculation = new JTable(tblModel);
	       scrollPane = new JScrollPane(tblCirculation);
	       scrollPane.setBounds(115, 401, 895, 316);
	       add(scrollPane);
		 
	       tabPanel = new JPanel();
	       tabPanel.setLayout(null);
	       tabPanel.setBorder(new LineBorder(new Color(192, 192, 192), 18, true));
	       tabPanel.setBackground(Color.LIGHT_GRAY);
	       tabPanel.setBounds(123, 0, 295, 42);
	       add(tabPanel);
	       
	       btnCirculation = new JButton("CIRCULATION");
	       btnCirculation.setForeground(new Color(51, 102, 51));
	       btnCirculation.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnCirculation.setEnabled(true);
	       btnCirculation.setBounds(10, 11, 132, 20);
	       tabPanel.add(btnCirculation);
	       
	       btnVisitLog = new JButton("VISITOR LOG");
	       btnVisitLog.setForeground(new Color(51, 102, 51));
	       btnVisitLog.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnVisitLog.setEnabled(true);
	       btnVisitLog.setBounds(152, 11, 132, 20);
	       tabPanel.add(btnVisitLog);
	       
	       JLabel lblDetail = new JLabel("Book Detail");
	       lblDetail.setBackground(UIManager.getColor("Button.background"));
	       lblDetail.setForeground(new Color(51, 102, 51));
	       lblDetail.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblDetail.setOpaque(true);
	       lblDetail.setBounds(48, 23, 99, 14);
	       
	       lbkBorrower = new JLabel("Borrower");
	       lbkBorrower.setOpaque(true);
	       lbkBorrower.setForeground(new Color(51, 102, 51));
	       lbkBorrower.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lbkBorrower.setBackground(UIManager.getColor("Button.background"));
	       lbkBorrower.setBounds(470, 23, 80, 14);
	       componentsBorder.add(lbkBorrower);
	       
	       componentsBorder.add(lblDetail);
	       bookPanel = new JPanel();
	       bookPanel.setForeground(UIManager.getColor("Button.background"));
	       bookPanel.setLayout(null);
	       bookPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
	       bookPanel.setBounds(26, 31, 367, 244);
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
	       
	       JButton btnFindBook = new JButton("FIND");
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
	       lblCategory.setBounds(46, 100, 68, 20);
	       bookPanel.add(lblCategory);
	       
	       txtCategory = new JTextField();
	       txtCategory.setEditable(false);
	       txtCategory.setColumns(10);
	       txtCategory.setBounds(117, 102, 174, 20);
	       bookPanel.add(txtCategory);
	       
	       JLabel lblTotalBook = new JLabel("Total Book :");
	       lblTotalBook.setForeground(new Color(0, 100, 0));
	       lblTotalBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblTotalBook.setBounds(38, 125, 74, 20);
	       bookPanel.add(lblTotalBook);
	       
	       JLabel lblReserveCopy = new JLabel("Reserve Copy :");
	       lblReserveCopy.setForeground(new Color(0, 100, 0));
	       lblReserveCopy.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblReserveCopy.setBounds(17, 150, 95, 20);
	       bookPanel.add(lblReserveCopy);
	       
	       txtTotalBook = new JTextField();
	       txtTotalBook.setEditable(false);
	       txtTotalBook.setColumns(10);
	       txtTotalBook.setBounds(117, 127, 174, 20);
	       bookPanel.add(txtTotalBook);
	       
	       txtReserve = new JTextField();
	       txtReserve.setEditable(false);
	       txtReserve.setColumns(10);
	       txtReserve.setBounds(117, 152, 174, 20);
	       bookPanel.add(txtReserve);
	       
	       borrowerPanel = new JPanel();
	       borrowerPanel.setLayout(null);
	       borrowerPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
	       borrowerPanel.setBounds(432, 31, 426, 244);
	       componentsBorder.add(borrowerPanel);
	       
	       JLabel lblBorrowerType = new JLabel("LRN / EmpNo :");
	       lblBorrowerType.setForeground(new Color(0, 100, 0));
	       lblBorrowerType.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblBorrowerType.setBounds(39, 22, 92, 18);
	       borrowerPanel.add(lblBorrowerType);
	       
	       txtLrn = new JTextField();
	       txtLrn.setEditable(false);
	       txtLrn.setColumns(10);
	       txtLrn.setBounds(138, 22, 103, 20);
	       borrowerPanel.add(txtLrn);
	       
	       btnFindBorrower = new JButton("FIND");
	       btnFindBorrower.setForeground(new Color(51, 102, 51));
	       btnFindBorrower.setFont(new Font("Tahoma", Font.BOLD, 13));
	       btnFindBorrower.setEnabled(true);
	       btnFindBorrower.setBounds(244, 22, 68, 19);
	       borrowerPanel.add(btnFindBorrower);
	       
	       JLabel lblFullName = new JLabel("Full Name :");
	       lblFullName.setForeground(new Color(0, 100, 0));
	       lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblFullName.setBounds(63, 51, 68, 18);
	       borrowerPanel.add(lblFullName);
	       
	       txtFullName = new JTextField();
	       txtFullName.setEditable(false);
	       txtFullName.setColumns(10);
	       txtFullName.setBounds(138, 51, 174, 20);
	       borrowerPanel.add(txtFullName);
	       
	       JLabel lblDepartment = new JLabel("Department :");
	       lblDepartment.setForeground(new Color(0, 100, 0));
	       lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblDepartment.setBounds(47, 75, 83, 20);
	       borrowerPanel.add(lblDepartment);
	       
	       txtDepartment = new JTextField();
	       txtDepartment.setEditable(false);
	       txtDepartment.setColumns(10);
	       txtDepartment.setBounds(138, 77, 174, 20);
	       borrowerPanel.add(txtDepartment);
	       
	       JLabel lblGrade = new JLabel("Grade :");
	       lblGrade.setForeground(new Color(0, 100, 0));
	       lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblGrade.setBounds(85, 100, 45, 20);
	       borrowerPanel.add(lblGrade);
	       
	       txtGrade = new JTextField();
	       txtGrade.setEditable(false);
	       txtGrade.setColumns(10);
	       txtGrade.setBounds(138, 102, 174, 20);
	       borrowerPanel.add(txtGrade);
	       
	       JLabel lblSection = new JLabel("Section:");
	       lblSection.setForeground(new Color(0, 100, 0));
	       lblSection.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblSection.setBounds(80, 125, 50, 20);
	       borrowerPanel.add(lblSection);
	       
	       txtSection = new JTextField();
	       txtSection.setEditable(false);
	       txtSection.setColumns(10);
	       txtSection.setBounds(138, 127, 174, 20);
	       borrowerPanel.add(txtSection);
	       
	       btnBorrow = new JButton("BORROW");
	       btnBorrow.setForeground(new Color(51, 102, 51));
	       btnBorrow.setFont(new Font("Tahoma", Font.BOLD, 13));
	       btnBorrow.setEnabled(true);
	       btnBorrow.setBounds(308, 215, 103, 18);
	       borrowerPanel.add(btnBorrow);
	       

	       
	       JLabel lblSearch = new JLabel("Search");
	       lblSearch.setForeground(new Color(51, 102, 51));
	       lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
	       lblSearch.setBounds(748, 373, 58, 14);
	       add(lblSearch);
	       
	       txtSearch = new JTextField();
	       txtSearch.setColumns(10);
	       txtSearch.setBounds(816, 370, 194, 20);
	       add(txtSearch);
	   }
	   
	   public void initAction() {
		   btnVisitLog.addActionListener(e ->{
			   mainFrame.showPanel(new VisitorLogView(mainFrame));
		   });
	   }
	   
}
