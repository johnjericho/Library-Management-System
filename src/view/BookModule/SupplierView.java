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
import model.Supplier;
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

public class SupplierView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtSupplier;
	private JTextField txtNumber;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JTextField txtSearch;
	
	private SupplierController supplierController = AppContext.getInstance().getSupplierController();

	private DefaultTableModel tblModel;
	private JTable tblSupplier;
	private int selectedSupplierId = -1;
	private boolean isEditMode = false;
	
	public SupplierView() {
		execute();
	}
	
	public void execute() {
		 setPanel();
		 initComponent();
		 initAction();
	}
	
	public void setPanel() {
		setBounds(100, 100, 900, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 884, 681);
		getContentPane().add(contentPanel);	
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	public void initComponent() {
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(46, 54, 803, 141);
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
		
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 92, 80, 20);
		componentsBorder.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(133, 92, 95, 20);
		componentsBorder.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(256, 92, 95, 20);
		componentsBorder.add(btnDelete);
		
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

	    tblSupplier = new JTable(tblModel);
		
		JScrollPane scrollPane = new JScrollPane(tblSupplier);
		scrollPane.setBounds(46, 237, 803, 412);
		contentPanel.add(scrollPane);
				
		
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(582, 206, 64, 14);
		contentPanel.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setText("");
		txtSearch.setColumns(10);
		txtSearch.setBounds(643, 203, 209, 23);
		contentPanel.add(txtSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(46, 664, 803, 10);
		contentPanel.add(separator);
		
	
	}
	
		public void initAction() {
			loadSupplier();
			
		   btnAdd.addActionListener(e ->{
			   addSupplier();
			   
		   });
		   
		   TableRefresherHelper.tblRefresher(3000,() -> loadSupplier());
		   
		   
			tblSupplier.getSelectionModel().addListSelectionListener(e -> {
			    if (e.getValueIsAdjusting()) return;

			    int selectedRow = tblSupplier.getSelectedRow();
			    if (selectedRow != -1) {

			        int id = (int) tblModel.getValueAt(selectedRow, 0);
			        String name = (String) tblModel.getValueAt(selectedRow, 1);
			        String number = (String) tblModel.getValueAt(selectedRow, 2);


			        // ⭐ SAME PATTERN AS CATEGORY VIEW
			        if (selectedSupplierId != id) {
			        	selectedSupplierId = id;
			            txtSupplier.setText(name);
			            txtNumber.setText(number);
			            enterEditMode();
			        }
			    }
			});
			
			// ESC key
			getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
			    .put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "escapeAction");

			getRootPane().getActionMap().put("escapeAction", new javax.swing.AbstractAction() {
			    public void actionPerformed(java.awt.event.ActionEvent e) {
			        tryExitEditMode();
			    }
			});

			// Empty area click
			contentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseClicked(java.awt.event.MouseEvent e) {
			        tryExitEditMode();
			    }
			});
			
			btnUpdate.addActionListener(e -> {
			    updateSupplier();
				SwingUtilities.invokeLater(() -> {
					restoreSelectedRow();
				});
			});

			btnDelete.addActionListener(e -> {
			    deleteSupplier();
			});
			
			
			txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			    public void insertUpdate(DocumentEvent e) { searchSupplier(); }
			    public void removeUpdate(DocumentEvent e) {searchSupplier(); }
			    public void changedUpdate(DocumentEvent e) { searchSupplier(); }	
		});
			
			
			tblSupplier.getColumnModel().getColumn(0).setMinWidth(0);
			tblSupplier.getColumnModel().getColumn(0).setMaxWidth(0);
			tblSupplier.getColumnModel().getColumn(0).setWidth(0);
		   
			SwingUtilities.invokeLater(() -> {
				restoreSelectedRow();
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
			// DITO LANG IDAGDAG — after ma-load ang rows
	        SwingUtilities.invokeLater(() ->restoreSelectedRow());
			
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
		
		public void searchSupplier() {			
			String keyword = txtSearch.getText();
			if(!keyword.isEmpty()) {
				TableRefresherHelper.stopRefresher();
			}else { TableRefresherHelper.startRefresher(); }

			try {
			tblModel.setRowCount(0);
			
			ArrayList<Supplier> list = keyword.isEmpty()
			? supplierController.loadSupplier()
			: supplierController.searchSupplier(keyword);
			
			for (Supplier a : list) {
		        tblModel.addRow(new Object[]{a.getSupplierId(), a.getSupplierName(), a.getSupplierNumber()});
		    }
			
	        // DAGDAG — i-restore ang selection after mag-search
	        SwingUtilities.invokeLater(() -> restoreSelectedRow());

			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
			
		}

		private void restoreSelectedRow() {
			if(selectedSupplierId == -1) {
				return;
			}
			
			for(int i = 0; i < tblModel.getRowCount(); i++) {
				int rowId = (int) tblModel.getValueAt(i, 0); //get the value each i ->row from 0 ->column id
				if(rowId == selectedSupplierId) {
					tblSupplier.setRowSelectionInterval(i, i); // from i selected row end to to also i, start -> end highlight 
					break;
				}
			}
			
		}
		
		private void enterEditMode() {
			
			if(selectedSupplierId < 0) return;
			isEditMode = true;
			btnAdd.setEnabled(false);
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
		}
		
		private void exitEditMode() {
		    isEditMode = false; 
		    selectedSupplierId = -1;
		    txtSupplier.setText("");   
		    txtNumber.setText("");    
		    btnAdd.setEnabled(true);  
		    btnUpdate.setEnabled(false);
		    btnDelete.setEnabled(false);
		}
		
		private void tryExitEditMode() {
		    if (!isEditMode) return; // wala namang edit mode, wala sa gagawin

		    // hanapin ang original name sa table
		    String originalName = "";
		    String originalNumber = "";
		    for (int i = 0; i < tblModel.getRowCount(); i++) {
		        if ((int) tblModel.getValueAt(i, 0) == selectedSupplierId) {
		            originalName = (String) tblModel.getValueAt(i, 1);
		            originalNumber = (String) tblModel.getValueAt(i, 2);
		            break;
		        }
		    }

		    String currentText = txtSupplier.getText().trim();
		    String currentNumber = txtNumber.getText().trim();

		    if (!currentText.equals(originalName) || !currentNumber.equals(originalNumber)) {
		        // may binago ang user — mag-prompt
		        int confirm = JOptionPane.showConfirmDialog(
		            this,
		            "Discard changes?",
		            "Unsaved Changes",
		            JOptionPane.YES_NO_OPTION
		        );
		        if (confirm == JOptionPane.YES_OPTION) {
		            exitEditMode();
		            tblSupplier.clearSelection();
		        }
		        // kung NO — manatili sa edit mode, walang mangyayari
		    } else {
		        // walang binago — exit agad, walang prompt
		        exitEditMode();
		        tblSupplier.clearSelection();
		    }
		}
}
