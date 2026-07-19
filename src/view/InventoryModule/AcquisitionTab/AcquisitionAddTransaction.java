package view.InventoryModule.AcquisitionTab;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.util.Date;

import javax.swing.border.LineBorder;

import controller.AcquisitionModule.AcquisitionController;
import controller.AcquisitionModule.DeadLayer;
import utility.AppContext;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class AcquisitionAddTransaction extends JDialog {
	
	private AcquisitionController acquisitionController = AppContext.getInstance().getAcquisitionController();



	private static final long serialVersionUID = 1L;
	private  JPanel contentPanel = new JPanel();
	
	//TEXTFIELD
	private JTextField txtTransaction;
	private JTextField txtContributor;
	private JTextField txtType;
	
	//BUTTON
	private JButton btnFindContributor;
	private JButton btnSave;

	//SENTINEL
    private int selectedContributorId = -1;
    private JDateChooser dateReceived;

	public AcquisitionAddTransaction() {
		execute();
	}
	
	public void execute() {
		setPanel();
		initComponent();
		initAction();
	}
	
	public void setPanel() {
		setBounds(100, 100, 458, 285);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(0, 0, 459, 230);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


	}
	
	public void initComponent() {
		
		JLabel lbkBorrower = new JLabel("Transaction Detail");
		lbkBorrower.setOpaque(true);
		lbkBorrower.setForeground(new Color(51, 102, 51));
		lbkBorrower.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbkBorrower.setBackground(UIManager.getColor("Button.background"));
		lbkBorrower.setBounds(46, 11, 160, 14);
		contentPanel.add(lbkBorrower);
		
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(null);
		borderPanel.setBorder(new LineBorder(new Color(0, 100, 0), 3, true));
		borderPanel.setBounds(22, 19, 398, 211);
		contentPanel.add(borderPanel);
		
		btnSave = new JButton("SAVE");
		btnSave.setBounds(40, 170, 92, 20);
		btnSave.setForeground(new Color(51, 102, 51));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setEnabled(true);
		borderPanel.add(btnSave);
		
		JPanel grayPanel = new JPanel();
		grayPanel.setBackground(Color.LIGHT_GRAY);
		grayPanel.setBounds(22, 24, 354, 156);
		borderPanel.add(grayPanel);
		grayPanel.setLayout(null);
		
		
		JLabel lblContributor = new JLabel("Contributor :");
		lblContributor.setBounds(32, 51, 82, 18);
		grayPanel.add(lblContributor);
		lblContributor.setForeground(new Color(0, 100, 0));
		lblContributor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTransaction = new JLabel("Transaction :");
		lblTransaction.setBounds(32, 22, 83, 18);
		grayPanel.add(lblTransaction);
		lblTransaction.setForeground(new Color(0, 100, 0));
		lblTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContributorType = new JLabel("Type:");
		lblContributorType.setBounds(76, 78, 36, 20);
		grayPanel.add(lblContributorType);
		lblContributorType.setForeground(new Color(0, 100, 0));
		lblContributorType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtContributor = new JTextField();
		txtContributor.setBounds(119, 51, 125, 20);
		grayPanel.add(txtContributor);
		txtContributor.setEditable(false);
		txtContributor.setColumns(10);
		
		txtTransaction = new JTextField();
		txtTransaction.setBounds(119, 22, 197, 20);
		grayPanel.add(txtTransaction);
		txtTransaction.setText((String) null);
		txtTransaction.setEditable(false);
		txtTransaction.setColumns(10);
		
		btnFindContributor = new JButton("FIND");
		btnFindContributor.setBounds(248, 52, 68, 19);
		grayPanel.add(btnFindContributor);
		btnFindContributor.setForeground(new Color(51, 102, 51));
		btnFindContributor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFindContributor.setEnabled(true);
		
		txtType = new JTextField();
		txtType.setBounds(119, 78, 197, 20);
		grayPanel.add(txtType);
		txtType.setEditable(false);
		txtType.setColumns(10);
		
		
		JLabel lblDateReceived = new JLabel("Date Received :");
		lblDateReceived.setForeground(new Color(0, 100, 0));
		lblDateReceived.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateReceived.setBounds(15, 105, 97, 20);
		grayPanel.add(lblDateReceived);
		
		dateReceived = new JDateChooser();
    	JTextField textField = (JTextField) dateReceived.getDateEditor().getUiComponent();
        textField.setEditable(false);
        dateReceived.setMaxSelectableDate(new Date()); // Hindi pwede pumili ng future date
		dateReceived.setBounds(119, 105, 197, 20);
		grayPanel.add(dateReceived);
		
		

	}
	
		public void initAction() {
			
			txtTransaction.setText(acquisitionController.generateNextTransactionNo());
			
			btnFindContributor.addActionListener( e ->{	 openContributorDialog(); 	});
			
			btnSave.addActionListener( e ->{	 saveAcquisition(); 	});

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
				txtType.setText(type);   }
			}
		
		
		public void saveAcquisition() {
			String transactionNo = txtTransaction.getText();
			int id = selectedContributorId;
			String contributorType = txtType.getText();	
	    	Date date = dateReceived.getDate();
	    	
			try {
				acquisitionController.addAcqTransaction(transactionNo, id, contributorType , date);
				JOptionPane.showMessageDialog(this, "Successfully added");
				this.dispose();
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(),"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
}
