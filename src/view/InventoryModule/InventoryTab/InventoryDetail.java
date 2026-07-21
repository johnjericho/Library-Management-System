package view.InventoryModule.InventoryTab;


import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InventoryDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private  JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;



	public InventoryDetail() {
		execute();
	}

	public void execute() {
		setPanel();
	}
	
	public void setPanel() {
		setBounds(100, 100, 564, 440);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(0, 0, 548, 401);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lbkBorrower = new JLabel("Transaction Detail");
		lbkBorrower.setOpaque(true);
		lbkBorrower.setForeground(new Color(51, 102, 51));
		lbkBorrower.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbkBorrower.setBackground(UIManager.getColor("Button.background"));
		lbkBorrower.setBounds(34, 11, 160, 14);
		contentPanel.add(lbkBorrower);
		
		JPanel borrowerPanel = new JPanel();
		borrowerPanel.setLayout(null);
		borrowerPanel.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
		borrowerPanel.setBounds(11, 19, 528, 357);
		contentPanel.add(borrowerPanel);
		
		JLabel lblTransaction = new JLabel("Transaction :");
		lblTransaction.setForeground(new Color(0, 100, 0));
		lblTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTransaction.setBounds(22, 22, 83, 18);
		borrowerPanel.add(lblTransaction);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(109, 22, 174, 20);
		borrowerPanel.add(textField);
		
		JLabel lblContributor = new JLabel("Contributor :");
		lblContributor.setForeground(new Color(0, 100, 0));
		lblContributor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContributor.setBounds(22, 51, 82, 18);
		borrowerPanel.add(lblContributor);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(109, 51, 174, 20);
		borrowerPanel.add(textField_1);
		
		JLabel lblContributorType = new JLabel("Type:");
		lblContributorType.setForeground(new Color(0, 100, 0));
		lblContributorType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContributorType.setBounds(66, 77, 36, 20);
		borrowerPanel.add(lblContributorType);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(109, 77, 174, 20);
		borrowerPanel.add(textField_2);
		
		JLabel lblQuantity = new JLabel("quantity :");
		lblQuantity.setForeground(new Color(0, 100, 0));
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(42, 108, 60, 20);
		borrowerPanel.add(lblQuantity);
		
		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setColumns(10);
		textField_3.setBounds(109, 108, 174, 20);
		borrowerPanel.add(textField_3);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setForeground(new Color(0, 100, 0));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(64, 135, 38, 20);
		borrowerPanel.add(lblPrice);
		
		textField_4 = new JTextField();
		textField_4.setText("");
		textField_4.setColumns(10);
		textField_4.setBounds(109, 135, 174, 20);
		borrowerPanel.add(textField_4);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setEnabled(true);
		btnAdd.setBounds(200, 182, 83, 18);
		borrowerPanel.add(btnAdd);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


	}
	
	public static void main(String[] args) {
		try {
			InventoryDetail dialog = new InventoryDetail();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
