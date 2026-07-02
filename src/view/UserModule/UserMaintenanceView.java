package view.UserModule;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

public class UserMaintenanceView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
	public UserMaintenanceView() {
		initialize();
	}
	
	  public void initialize() {
	    	setPanel();
	    	initComponent();
	    }
	
	   public void setPanel(){
	       this.setLayout(null);
	       this.setSize(1126,743);
	    }
	   
	   
	   public void initComponent() {
		   
	       JPanel componentsBorder = new JPanel();
	       componentsBorder.setLayout(null);
	       componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
	       componentsBorder.setBounds(10, 11, 1083, 252);
	       add(componentsBorder);
	       
	       JLabel lblNewLabel = new JLabel("LRN");
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
	       
	       JSeparator separator_1 = new JSeparator();
	       separator_1.setOrientation(SwingConstants.VERTICAL);
	       separator_1.setForeground(new Color(51, 204, 51));
	       separator_1.setBounds(523, 11, 24, 229);
	       componentsBorder.add(separator_1);
	       
	       textField = new JTextField();
	       textField.setColumns(10);
	       textField.setBounds(120, 79, 191, 20);
	       componentsBorder.add(textField);
	       
	       JCheckBox chkbxIsbn = new JCheckBox("no isbn");
	       chkbxIsbn.setForeground(new Color(51, 102, 51));
	       chkbxIsbn.setFont(new Font("Tahoma", Font.BOLD, 13));
	       chkbxIsbn.setBounds(120, 56, 97, 23);
	       componentsBorder.add(chkbxIsbn);
	       
	       textField_1 = new JTextField();
	       textField_1.setColumns(10);
	       textField_1.setBounds(120, 108, 191, 20);
	       componentsBorder.add(textField_1);
	       
	       textField_2 = new JTextField();
	       textField_2.setEditable(false);
	       textField_2.setColumns(10);
	       textField_2.setBounds(120, 139, 191, 20);
	       componentsBorder.add(textField_2);
	       
	       textField_3 = new JTextField();
	       textField_3.setEditable(false);
	       textField_3.setColumns(10);
	       textField_3.setBounds(120, 170, 191, 20);
	       componentsBorder.add(textField_3);
	       
	       textField_4 = new JTextField();
	       textField_4.setEditable(false);
	       textField_4.setColumns(10);
	       textField_4.setBounds(685, 79, 191, 20);
	       componentsBorder.add(textField_4);
	       
	       JSpinner bookReserveCopy = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
	       bookReserveCopy.setBounds(685, 139, 191, 20);
	       componentsBorder.add(bookReserveCopy);
	       
	       JDateChooser bookDatePublished = new JDateChooser();
	       bookDatePublished.setBounds(685, 108, 191, 20);
	       componentsBorder.add(bookDatePublished);
	       
	       JButton btnBrowseAuthor = new JButton("browse");
	       btnBrowseAuthor.setForeground(new Color(51, 102, 51));
	       btnBrowseAuthor.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowseAuthor.setBounds(321, 140, 80, 20);
	       componentsBorder.add(btnBrowseAuthor);
	       
	       JButton btnBrowsePublisher = new JButton("browse");
	       btnBrowsePublisher.setForeground(new Color(51, 102, 51));
	       btnBrowsePublisher.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowsePublisher.setBounds(321, 169, 80, 20);
	       componentsBorder.add(btnBrowsePublisher);
	       
	       JButton btnBrowseCategory = new JButton("browse");
	       btnBrowseCategory.setForeground(new Color(51, 102, 51));
	       btnBrowseCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
	       btnBrowseCategory.setBounds(886, 84, 80, 20);
	       componentsBorder.add(btnBrowseCategory);
		   
	   }
}
