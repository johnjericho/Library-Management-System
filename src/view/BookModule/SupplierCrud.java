package view.BookModule;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.SupplierController;
import dao.BookModuleDAO.SupplierDAO;
import model.BookModule.Supplier;
import services.BookModuleServices.SupplierServices;
import utility.AppContext;
import utility.TableRefresherHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class SupplierCrud extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtSupplier;
	private JTextField txtNumber;

	private JButton btnSave;
	
	private SupplierController supplierController = AppContext.getInstance().getSupplierController();

	private DefaultTableModel tblModel;
	private int selectedSupplierId = -1;
	private boolean isEditMode = false;
	
	
	public SupplierCrud(SupplierController sc) {
	    this.supplierController = sc;

		execute();
	}
	
	public void execute() {
		 setPanel();
		 initComponent();
		 initAction();
	}
	
	public void setPanel() {
		setBounds(100, 100, 469, 599);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 453, 560);
		getContentPane().add(contentPanel);	
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	public void initComponent() {
		JPanel componentsBorder = new JPanel();
		componentsBorder.setBackground(Color.LIGHT_GRAY);
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3));
		componentsBorder.setBounds(14, 17, 430, 511);
		contentPanel.add(componentsBorder);
		
		JLabel lblSupplier = new JLabel("Supplier:");
		lblSupplier.setForeground(new Color(51, 102, 51));
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSupplier.setBounds(25, 10, 139, 36);
		componentsBorder.add(lblSupplier);
		
		txtSupplier = new JTextField();
		txtSupplier.setText("");
		txtSupplier.setColumns(10);
		txtSupplier.setBounds(142, 21, 209, 23);
		componentsBorder.add(txtSupplier);
		
		btnSave = new JButton("SAVE");
		btnSave.setForeground(new Color(51, 102, 51));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(488, 357, 99, 20);
		componentsBorder.add(btnSave);
		
		txtNumber = new JTextField();
		txtNumber.setText("");
		txtNumber.setColumns(10);
		txtNumber.setBounds(142, 55, 209, 23);
		componentsBorder.add(txtNumber);
		
		JLabel lblNumber = new JLabel("Number: ");
		lblNumber.setForeground(new Color(51, 102, 51));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNumber.setBounds(25, 46, 139, 36);
		componentsBorder.add(lblNumber);
		
		String[] column = {"ID","Supplier","Number"};
	    tblModel = new DefaultTableModel(column, 0) {
		public boolean  isCellEditable(int row, int column){ return false; }
	    };
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(14, 539, 427, 10);
		contentPanel.add(separator);
		
	
	}
	
		public void initAction() {
			loadSupplier();
			
		   btnSave.addActionListener(e ->{
			   addSupplier();
			   
		   });
		   

			
		}
	
	  public void addSupplier(){
		  String supplierName = txtSupplier.getText();
		  String supplierNum = txtNumber.getText();
		   
		   try {
		   supplierController.addSupplier(supplierName, supplierNum);
		   JOptionPane.showMessageDialog(this, "Successfully added!");
		   }catch(Exception e) {
			   JOptionPane.showMessageDialog(this, e.getMessage(),"WARNING", JOptionPane.WARNING_MESSAGE);
		   }
	  }
	  
		public void loadSupplier() {
			try {
			tblModel.setRowCount(0);
			ArrayList<Supplier> supplierList = supplierController.loadSupplier();
			for(Supplier supplier : supplierList) {
				Object[] eachRow = {supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierNumber()};
				tblModel.addRow(eachRow);
				}
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
			}
		}
	
		public void updateSupplier() {
		    String updateName = txtSupplier.getText();
		    String updateNumber = txtNumber.getText();

		    try {
		        supplierController.updateSupplier(selectedSupplierId, updateName, updateNumber);
		        JOptionPane.showMessageDialog(this, "Successfully updated");
		        exitEditMode();
		        loadSupplier();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, e.getMessage());
		    }
		}

		public void deleteSupplier() {
		    int confirm = JOptionPane.showConfirmDialog(this, "Confirm delete supplier", "WARNING", JOptionPane.YES_NO_OPTION);
		    if (confirm != JOptionPane.YES_OPTION) return;

		    try {
		        supplierController.deleteSupplier(selectedSupplierId);
		        JOptionPane.showMessageDialog(this, "Successfully deleted!");
		        loadSupplier();
		        exitEditMode();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, e.getMessage());
		    }
		}
		


		
		private void enterEditMode() {
			
			if(selectedSupplierId < 0) return;
			isEditMode = true;
			btnSave.setEnabled(false);
		}
		
		private void exitEditMode() {
		    isEditMode = false; 
		    selectedSupplierId = -1;
		    txtSupplier.setText("");   
		    txtNumber.setText("");    
		    btnSave.setEnabled(true);
		}
		
	
		
		public static void main(String[] args) {
		    SupplierController controller = new SupplierController(new SupplierServices(new SupplierDAO()));

		    SupplierCrud dialog = new SupplierCrud(controller);
		    dialog.setVisible(true);
		}
}
