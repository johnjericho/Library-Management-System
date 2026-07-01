package view.BookModule;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.AuthorController;
import model.BookModule.Author;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSeparator;

import utility.AppContext;
import utility.TableRefresherHelper;

import java.lang.IllegalArgumentException;


 	
public class AuthorView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAuthor;
	private JTextField txtSearch;
	private JTable tblAuthor;
	private DefaultTableModel tblModel; 
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnAdd;
	  
	private AuthorController authorController = AppContext.getInstance().getAuthorController();
	private int selectedAuthorId = -1 ;
	private boolean isEditMode = false;
	
    private String selectedAuthorName; // use to carry the value to bookmaintenanceview

	
	public AuthorView() {		    
		execute();
	}
	 
	public void execute() {
		setPanel();
		initComponents();
		initActions();
		loadAuthor();
		}
	
	public void setPanel() {
		this.setBounds(100, 100, 900, 720);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(null);
		this.contentPanel.setLayout(null);
		this.setContentPane(contentPanel);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}
	
	public void initComponents() {
		
		   String[] columns = {"ID", "Author Name"}; // BAGO
			// ✅ PALITAN NG:
			   tblModel = new DefaultTableModel(columns, 0) {
			       @Override
			       public Class<?> getColumnClass(int columnIndex) {
			           if (columnIndex == 0) return Integer.class; // ✅ ID column = Integer lagi
			           return String.class;
			       }
			       
			       @Override
			       public boolean isCellEditable(int row, int column) {
			           return false; // ✅ bonus — hindi na maa-edit ang cells ng user directly
			       }
			   };
		
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(38, 76, 803, 120);
		contentPanel.add(componentsBorder);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setForeground(new Color(51, 102, 51));
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAuthor.setBounds(25, 21, 107, 20);
		componentsBorder.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(142, 21, 209, 23);
		componentsBorder.add(txtAuthor);
		
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 68, 80, 20);
		componentsBorder.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(133, 68, 95, 20);
		btnUpdate.setEnabled(false); 
		componentsBorder.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(256, 68, 95, 20);
		btnDelete.setEnabled(false);
		componentsBorder.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(38, 661, 803, 10);
		contentPanel.add(separator);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(573, 208, 64, 14);
		contentPanel.add(lblSearch);
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		txtSearch = new JTextField();
		txtSearch.setBounds(634, 204, 206, 23);
		contentPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		tblAuthor = new JTable();
		tblAuthor.setBounds(38, 238, 803, 412);
		tblAuthor.setModel(tblModel);                        // BAGO - i-connect ang model
		JScrollPane scrollPane = new JScrollPane(tblAuthor);
		scrollPane.setBounds(38, 238, 803, 412);             // BAGO - bounds sa scrollPane na
		contentPanel.add(scrollPane);

		// itago ang ID column para hindi makita ng user
		tblAuthor.getColumnModel().getColumn(0).setMinWidth(0);
		tblAuthor.getColumnModel().getColumn(0).setMaxWidth(0);
		tblAuthor.getColumnModel().getColumn(0).setWidth(0);
	}
	
	public void initActions() {
		btnAdd.addActionListener(e->{
			addAuthor();
			}); 
		
		TableRefresherHelper.tblRefresher(3000, () ->{ loadAuthor(); });
		
		//mouse selectionModel for update/delete function
		tblAuthor.getSelectionModel().addListSelectionListener(e -> {
		    if (e.getValueIsAdjusting()) return;

		    int selectedRow = tblAuthor.getSelectedRow();
		    if (selectedRow != -1) {

		        int id = (int) tblModel.getValueAt(selectedRow, 0);
		        String name = (String) tblModel.getValueAt(selectedRow, 1);

		        // ⭐ SAME PATTERN AS CATEGORY VIEW
		        if (selectedAuthorId != id) {
		            selectedAuthorId = id;
		            txtAuthor.setText(name);
		            enterEditMode();
		        }
		    }
		});
		
		//mouse listenert to Bookmaintenacview
		 tblAuthor.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) { // double-click = "pumili ako nito"
		                int row = tblAuthor.getSelectedRow();
		                if (row != -1) {
		                    selectedAuthorName = tblAuthor.getValueAt(row, 1).toString(); // column 1 = author name, halimbawa
		                    dispose(); // isasara ang dialog → bumabalik na control sa caller
		                }
		            }
		        }
		    });
		
		//ESC
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
		
		
		btnUpdate.addActionListener(e->{
			updateAuthor();
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAuthor();
			}
		});
		
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {	    
		    public void insertUpdate(DocumentEvent e) { searchAuthor(); }
		    public void removeUpdate(DocumentEvent e) { searchAuthor(); }
		    public void changedUpdate(DocumentEvent e) { searchAuthor(); }
		});
		
		
		SwingUtilities.invokeLater(() -> {
			restoreSelectedRow();
		});
	
		
	}
	
	
	private void restoreSelectedRow() {
		if(selectedAuthorId == -1) {
			return;
		}
		
		for(int i = 0; i < tblModel.getRowCount(); i++) {
			int rowId = (int) tblModel.getValueAt(i, 0); //get the value each i ->row from 0 ->column id
			if(rowId == selectedAuthorId) {
				tblAuthor.setRowSelectionInterval(i, i); // from i selected row end to to also i, start -> end highlight 
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
	    selectedAuthorId = -1;
	    txtAuthor.setText("");
	    btnUpdate.setEnabled(false);
	    btnDelete.setEnabled(false);
	    btnAdd.setEnabled(true);
	}
	
	private void tryExitEditMode() {
	    if (!isEditMode) return; // wala namang edit mode, wala sa gagawin

	    // hanapin ang original name sa table
	    String originalName = "";
	    for (int i = 0; i < tblModel.getRowCount(); i++) {
	        if ((int) tblModel.getValueAt(i, 0) == selectedAuthorId) {
	            originalName = (String) tblModel.getValueAt(i, 1);
	            break;
	        }
	    }

	    String currentText = txtAuthor.getText().trim();

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
	            tblAuthor.clearSelection();
	        }
	        // kung NO — manatili sa edit mode, walang mangyayari
	    } else {
	        // walang binago — exit agad, walang prompt
	        exitEditMode();
	        tblAuthor.clearSelection();
	    }
	}
	
	
	 
	public void addAuthor() {
		String authorName = txtAuthor.getText().trim().replaceAll("\\s+", " ");
		
	    try {		
			 authorController.addAuthor(authorName);
			 JOptionPane.showMessageDialog(this, "Successfully Added");
			 txtAuthor.setText("");
			 selectedAuthorId = -1;
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE); return;
		}catch(RuntimeException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);  return;
		}
	}
	
	
	public void loadAuthor() {
	 try {
	   tblAuthor.clearSelection(); // ✅ DAGDAG ITO — prevents stale selection event sa setRowCount(0)
	   tblModel.setRowCount(0);
	    ArrayList<Author> authorList = authorController.loadAuthor();

	    for(Author a : authorList) {
	        Object[] row = {a.getAuthorId(), a.getAuthorName()}; // ✅ ID + Name
	        tblModel.addRow(row);
	    }
	    
	    SwingUtilities.invokeLater(() -> {
			restoreSelectedRow();
		});
	 }catch(RuntimeException e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Database problem", JOptionPane.WARNING_MESSAGE);
		 return;
	 }
	}
	
	public void updateAuthor() {
	    
	    String editedName = txtAuthor.getText().trim().replaceAll("\\s+", " ");
	  // maganda pang debug  System.out.println("name " + editedName + " id " + selectedAuthorId );
	    
	    if(selectedAuthorId < 0) {
	    	JOptionPane.showMessageDialog(this, "Select Author first!", "WARNING", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    try { 
	        authorController.editAuthor(selectedAuthorId, editedName);
	        JOptionPane.showMessageDialog(this, "Successfully Updated");
	        exitEditMode();
	        
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
	        return;
	    }catch(RuntimeException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Database problem", JOptionPane.WARNING_MESSAGE);
			 return;
		 }
	}
	
	
	public void deleteAuthor() {
		int confirm = JOptionPane.showConfirmDialog(this, "Confirm delete category","WARNING",JOptionPane.YES_NO_OPTION);
		if(confirm != JOptionPane.YES_OPTION) return;
		
	    if(selectedAuthorId < 0) {
	    	JOptionPane.showMessageDialog(this, "Select Author first!", "WARNING", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
		try {
		int authorId = selectedAuthorId;
		authorController.deleteAuthor(authorId);	
		JOptionPane.showMessageDialog(this, "Successfully Deleted");
		loadAuthor();
		exitEditMode();
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error yah", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	
	 

	
	
	public void searchAuthor() {
	    // kunin ang text sa search box, alisin ang extra spaces
	    String keyword = txtSearch.getText().trim();
	    if(!keyword.isEmpty()) {
	    	TableRefresherHelper.stopRefresher();
	    }else {
	    	TableRefresherHelper.startRefresher();
	    }
	    
	    // i-clear muna ang table bago mag-load ng bagong results
	    tblModel.setRowCount(0);
     try {
	    // kung walang keyword → load lahat, may key word → i-filter
	    ArrayList<Author> list = keyword.isEmpty()
	        ? authorController.loadAuthor()       // walang keyword, load all
	        : authorController.searchAuthor(keyword); // may keyword, i-filter

	    // i-loop ang results at idagdag sa table row by row
	    for (Author a : list) {
	        tblModel.addRow(new Object[]{a.getAuthorId(), a.getAuthorName()});
	    }
	    
	    SwingUtilities.invokeLater(() -> {
			restoreSelectedRow();
		});
	    
     }catch(Exception e) {
    	 JOptionPane.showMessageDialog(this, e.getMessage());
     } 
   }
	
	  public String getSelectedAuthorName() {
	        return selectedAuthorName;
	    }
	  
	  public int getSelectedAuthorId() {
		    return selectedAuthorId;
		}
	
	

}