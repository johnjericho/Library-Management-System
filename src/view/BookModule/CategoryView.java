package view.BookModule;
import utility.AppContext;
import utility.TableRefresherHelper;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.CategoryController;
import model.BookModule.Category;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class CategoryView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtCategory;
	private JTextField txtSearch;
	private JPanel contentPanel;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	private DefaultTableModel tblModel;
	private JTable tblCategory;
	private JScrollPane scrollPane;

	private CategoryController categoryController = AppContext.getInstance().getCategoryController();
	
	private int selectedCategoryId = -1;
	private String selectedCategoryName;
	private boolean isEditMode = false; // dagdag na field sa taas kasama ng selectedCategoryId


	public CategoryView() {
		
		execute();	
	}
	
	public void execute() {
		 setPanel();
		 initComponent();	  
		 initAction();
		 loadCategory();
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
		componentsBorder.setBounds(38, 76, 803, 120);
		contentPanel.add(componentsBorder);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(51, 102, 51));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCategory.setBounds(25, 10, 139, 36);
		componentsBorder.add(lblCategory);
		
		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(163, 20, 209, 23);
		componentsBorder.add(txtCategory);
		
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 68, 80, 20);
		componentsBorder.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(133, 68, 95, 20);
		btnUpdate.setEnabled(false); // disabled by default
		componentsBorder.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(256, 68, 95, 20);
		btnDelete.setEnabled(false); // disabled by default
		componentsBorder.add(btnDelete);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(573, 211, 64, 14);
		contentPanel.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(634, 207, 206, 23);
		contentPanel.add(txtSearch);
		
		String[] column = {"ID", "Category"};
	    tblModel = new DefaultTableModel(column, 0) {
		public boolean  isCellEditable(int row, int column){ return false; }
	   };  
	   
	   tblCategory = new JTable(tblModel);
			
	   scrollPane = new JScrollPane(tblCategory);
	   scrollPane.setBounds(38, 241, 803, 412);
	   contentPanel.add(scrollPane);
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(38, 664, 803, 10);
		contentPanel.add(separator);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public void initAction() {
		btnAdd.addActionListener( e -> {
			addCategory();
		});
		
		TableRefresherHelper.tblRefresher(3000, () ->{ loadCategory(); });
		
		
		//mouse selectionModel for update/delete function
		tblCategory.getSelectionModel().addListSelectionListener(e -> {
		    if (e.getValueIsAdjusting()) return;

		    int selectedRow = tblCategory.getSelectedRow();
		    if (selectedRow != -1) {

		        int id = (int) tblModel.getValueAt(selectedRow, 0);
		        String name = (String) tblModel.getValueAt(selectedRow, 1);

		        // ⭐ SAME PATTERN AS CATEGORY VIEW
		        if (selectedCategoryId != id) {
		            selectedCategoryId = id;
		            txtCategory.setText(name);
		            enterEditMode();
		        }
		    }
		});
		
		//mouse listenert to Bookmaintenacview
		 tblCategory.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) { // double-click = "pumili ako nito"
		                int row = tblCategory.getSelectedRow();
		                if (row != -1) {
		                    selectedCategoryName = tblCategory.getValueAt(row, 1).toString(); // column 1 = author name, halimbawa
		                    dispose(); // isasara ang dialog → bumabalik na control sa caller
		                }
		            }
		        }
		    });
		
		// ESC key
		// Sa initAction() — globally nakikinig sa ESC kahit saan naka-focus
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
		
		btnUpdate.addActionListener( e -> {
			updateCategory();
		});
		
		btnDelete.addActionListener(e ->{
			deleteCategory();		});
		

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			    public void insertUpdate(DocumentEvent e) { searchCategory(); }
			    public void removeUpdate(DocumentEvent e) {searchCategory(); }
			    public void changedUpdate(DocumentEvent e) { searchCategory(); }	
		});
		
		
		// itago ang ID column para hindi makita ng user
		tblCategory.getColumnModel().getColumn(0).setMinWidth(0);
		tblCategory.getColumnModel().getColumn(0).setMaxWidth(0);
		tblCategory.getColumnModel().getColumn(0).setWidth(0);
		
		SwingUtilities.invokeLater(() -> {
			restoreSelectedRow();
		});
	}
	
	
	
	private void restoreSelectedRow() {
		if(selectedCategoryId == -1) {
			return;
		}
		
		for(int i = 0; i < tblModel.getRowCount(); i++) {
			int rowId = (int) tblModel.getValueAt(i, 0); //get the value each i ->row from 0 ->column id
			if(rowId == selectedCategoryId) {
				tblCategory.setRowSelectionInterval(i, i); // from i selected row end to to also i, start -> end highlight 
				break;
			}
		}
		
	}
	

	
	private void enterEditMode() {
	    isEditMode = true;
	    btnUpdate.setEnabled(true);
	    btnDelete.setEnabled(true);
	    btnAdd.setEnabled(false); // optional — para hindi makalito
	}

	private void exitEditMode() {
	    isEditMode = false;
	    selectedCategoryId = -1;
	    txtCategory.setText("");
	    btnUpdate.setEnabled(false);
	    btnDelete.setEnabled(false);
	    btnAdd.setEnabled(true);
	}
	
	private void tryExitEditMode() {
	    if (!isEditMode) return; // wala namang edit mode, wala sa gagawin

	    // hanapin ang original name sa table
	    String originalName = "";
	    for (int i = 0; i < tblModel.getRowCount(); i++) {
	        if ((int) tblModel.getValueAt(i, 0) == selectedCategoryId) {
	            originalName = (String) tblModel.getValueAt(i, 1);
	            break;
	        }
	    }

	    String currentText = txtCategory.getText().trim();

	    if (!currentText.equals(originalName)) {
	        // may binago ang user — mag-prompt
	        int confirm = JOptionPane.showConfirmDialog(
	            this,
	            "Discard changes?",
	            "Unsaved Changes",
	            JOptionPane.YES_NO_OPTION
	        );
	        if (confirm == JOptionPane.YES_OPTION) {
	            exitEditMode();
	            tblCategory.clearSelection();
	        }
	        // kung NO — manatili sa edit mode, walang mangyayari
	    } else {
	        // walang binago — exit agad, walang prompt
	        exitEditMode();
	        tblCategory.clearSelection();
	    }
	}
	
	public void addCategory() {
		String inputedCategory = txtCategory.getText();
		try {
		categoryController.addCategory(inputedCategory);	
		JOptionPane.showMessageDialog(this, "Successfully added");
		txtCategory.setText("");
		selectedCategoryId = -1;
       }catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
			return;
		}	
	}
	
	public void loadCategory() {
		try {
		tblModel.setRowCount(0);
		ArrayList<Category> categoryList = categoryController.loadCategory();
		for(Category cat : categoryList) {
			Object[] eachRow = {cat.getCategoryId(),cat.getCategoryName()};
			tblModel.addRow(eachRow);
			}
		// DITO LANG IDAGDAG — after ma-load ang rows
        SwingUtilities.invokeLater(() ->restoreSelectedRow());
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateCategory() {
			
	String updateCategory = txtCategory.getText();
	
    try {
      categoryController.updateCategory(selectedCategoryId, updateCategory);
      JOptionPane.showMessageDialog(this, "Successfully updated");
      exitEditMode(); // ← PALITAN yung manual na setText/selectedCategoryId
      loadCategory();		
    }catch(Exception e) {
    	JOptionPane.showMessageDialog(this, e.getMessage());
    	}
	} 
	
	
	public void deleteCategory() {
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirm delete category","WARNING",JOptionPane.YES_NO_OPTION);
		if(confirm != JOptionPane.YES_OPTION) return;
		
		try {
		categoryController.deleteCategory(selectedCategoryId);
		JOptionPane.showMessageDialog(this, "Successfully deleted!");
	      loadCategory();
	      exitEditMode(); // ← PALITAN yung manual na setText/selectedCategoryId		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		};
	}
	
	public void searchCategory() {			
		String keyword = txtSearch.getText();
		if(!keyword.isEmpty()) {
			TableRefresherHelper.stopRefresher();
		}else { TableRefresherHelper.startRefresher(); }

		try {
		tblModel.setRowCount(0);
		
		ArrayList<Category> list = keyword.isEmpty()
		? categoryController.loadCategory()
		: categoryController.searchCategory(keyword);
		
		for (Category a : list) {
	        tblModel.addRow(new Object[]{a.getCategoryId(), a.getCategoryName()});
	    }
		
        // DAGDAG — i-restore ang selection after mag-search
        SwingUtilities.invokeLater(() -> restoreSelectedRow());

		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
	
	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}
	
	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}
	
	
}
