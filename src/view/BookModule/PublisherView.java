package view.BookModule;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utility.AppContext;
import utility.TableRefresherHelper;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BookModule.PublisherController;
import model.BookModule.Publisher;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTable;


public class PublisherView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
 
	private JTextField txtPublisher;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JScrollPane scrollPane;
	private JSeparator separator;
	private JTable tblPublisher;
	private DefaultTableModel tblModel; 
	private int selectedPublisherId = -1 ;
	private String selectedPublisherName;
	private boolean isEditMode = false;
	
	private PublisherController publisherController = AppContext.getInstance().getPublisherController();
    


	
	public PublisherView() {
		execute();
	}
	 
	
	 public void execute(){
		 setPanel();
		 initComponent();
		 initAction();
	   }
	 
	 public void setPanel(){
			this.setBounds(100, 100, 900, 720);
			contentPanel.setBackground(Color.LIGHT_GRAY);
			contentPanel.setBorder(null);
			this.contentPanel.setLayout(null);
			this.setContentPane(contentPanel);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	   }
	 
	 public void initComponent() {
		  
		 
			JPanel componentsBorder = new JPanel();
			componentsBorder.setLayout(null);
			componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
			componentsBorder.setBounds(38, 76, 803, 120);
			contentPanel.add(componentsBorder);
			
			JLabel lblPublisher = new JLabel("Publisher :");
			lblPublisher.setForeground(new Color(51, 102, 51));
			lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblPublisher.setBounds(25, 21, 139, 20);
			componentsBorder.add(lblPublisher);
			
			txtPublisher = new JTextField();
			txtPublisher.setColumns(10);
			txtPublisher.setBounds(174, 23, 209, 23);
			componentsBorder.add(txtPublisher);
			
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
			
			lblSearch = new JLabel("Search");
			lblSearch.setForeground(new Color(51, 102, 51));
			lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblSearch.setBounds(573, 211, 64, 14);
			contentPanel.add(lblSearch);
			
			txtSearch = new JTextField();
			txtSearch.setColumns(10);
			txtSearch.setBounds(634, 207, 206, 23);
			contentPanel.add(txtSearch);
			
			scrollPane = new JScrollPane((Component) null);
			scrollPane.setBounds(38, 241, 803, 412);
			contentPanel.add(scrollPane);
			
		    String[] column = { "id", "Publisher" };
		    tblModel = new DefaultTableModel(column, 0){
		        public boolean isCellEditable(int row, int column) {
		            return false; // hindi na maaari mag-edit ng kahit anong cell
		        }	};
			tblPublisher = new JTable(tblModel);
			scrollPane.setViewportView(tblPublisher);
			
		
			// itago ang ID column para hindi makita ng user
			tblPublisher.getColumnModel().getColumn(0).setMinWidth(0);
			tblPublisher.getColumnModel().getColumn(0).setMaxWidth(0);
			tblPublisher.getColumnModel().getColumn(0).setWidth(0);
			
			separator = new JSeparator();
			separator.setBackground(new Color(51, 204, 51));
			separator.setBounds(38, 664, 803, 10);
			contentPanel.add(separator);
			
	 }
		
	 
	public void initAction() {
		btnAdd.addActionListener( e ->{
			addPublisher();
		});	
		
		TableRefresherHelper.tblRefresher(3000, () ->{ loadPublisher(); });

		//mouse selectionModel for update/delete function
		tblPublisher.getSelectionModel().addListSelectionListener(e -> {
		    if (e.getValueIsAdjusting()) return;

		    int selectedRow = tblPublisher.getSelectedRow();
		    if (selectedRow != -1) {

		        int id = (int) tblModel.getValueAt(selectedRow, 0);
		        String name = (String) tblModel.getValueAt(selectedRow, 1);

		        // ⭐ SAME PATTERN AS CATEGORY VIEW
		        if (selectedPublisherId != id) {
		            selectedPublisherId = id;
		            txtPublisher.setText(name);
		            enterEditMode();
		        }
		    }
		});
		
		//mouse listenert to Bookmaintenacview
		 tblPublisher.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) { // double-click = "pumili ako nito"
		                int row = tblPublisher.getSelectedRow();
		                if (row != -1) {
		                	selectedPublisherName = tblPublisher.getValueAt(row, 1).toString(); // column 1 = author name, halimbawa
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
		
		btnUpdate.addActionListener(e -> {
			updatePublisher();
		});
		
		btnDelete.addActionListener(e ->{
			deletePublisher();
		});
		
		 searchPublisher();
		
				txtSearch.getDocument().addDocumentListener(new DocumentListener() {
				    public void insertUpdate(DocumentEvent e) { searchPublisher(); }
				    public void removeUpdate(DocumentEvent e) { searchPublisher(); }
				    public void changedUpdate(DocumentEvent e) { searchPublisher(); }
				});
		
				SwingUtilities.invokeLater(() -> {
					restoreSelectedRow();
				});
	} 
	

	private void restoreSelectedRow() {
		if(selectedPublisherId == -1) {
			return;
		}
		
		for(int i = 0; i < tblModel.getRowCount(); i++) {
			int rowId = (int) tblModel.getValueAt(i, 0); //get the value each i ->row from 0 ->column id
			if(rowId == selectedPublisherId) {
				tblPublisher.setRowSelectionInterval(i, i); // from i selected row end to to also i, start -> end highlight 
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
	    selectedPublisherId = -1;
	    txtPublisher.setText("");
	    btnUpdate.setEnabled(false);
	    btnDelete.setEnabled(false);
	    btnAdd.setEnabled(true);
	}
	
	private void tryExitEditMode() {
	    if (!isEditMode) return; // wala namang edit mode, wala sa gagawin

	    // hanapin ang original name sa table
	    String originalName = "";
	    for (int i = 0; i < tblModel.getRowCount(); i++) {
	        if ((int) tblModel.getValueAt(i, 0) == selectedPublisherId) {
	            originalName = (String) tblModel.getValueAt(i, 1);
	            break;
	        }
	    }

	    String currentText = txtPublisher.getText().trim();

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
	            tblPublisher.clearSelection();
	        }
	        // kung NO — manatili sa edit mode, walang mangyayari
	    } else {
	        // walang binago — exit agad, walang prompt
	        exitEditMode();
	        tblPublisher.clearSelection();
	    }
	}
	
	 
	 public void addPublisher() {
		 try {
		String publisherName = txtPublisher.getText().trim().replaceAll("\\s+", " ");
		publisherController.addPublisher(publisherName);
		JOptionPane.showMessageDialog(this, "Successfully added!");
		txtPublisher.setText("");
		selectedPublisherId = -1;
		 }catch(Exception e) {	 
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warninggg" , JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 public void loadPublisher() {
		
		 tblModel.setRowCount(0); //prevent row stacking		 
		 try {
		 List<Publisher> publisherList = publisherController.loadPublisher();
		 for(Publisher publisher : publisherList) {
			 Object[] list = {publisher.getPublisherId(), publisher.getPublisherName() };
			 tblModel.addRow(list);
		 }
		 SwingUtilities.invokeLater(() -> {
				restoreSelectedRow();
			});
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
		  
	 }
	 
	 public void updatePublisher() {
		 try {
		 String editedPublisher = txtPublisher.getText().trim().replaceAll("\\s+", " ");
		 try {
		 publisherController.updatePublisher(selectedPublisherId, editedPublisher);
		 JOptionPane.showMessageDialog(this, "Successfully updated!");
	      exitEditMode();
		 }catch(Exception e) {
		     JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 
	 public void deletePublisher() {
			int confirm = JOptionPane.showConfirmDialog(this, "Confirm delete category","WARNING",JOptionPane.YES_NO_OPTION);
			if(confirm != JOptionPane.YES_OPTION) return;
		 try {
		 int publisherid = selectedPublisherId;
		 publisherController.deletePublisher(publisherid);
		 JOptionPane.showMessageDialog(this, "Successfully deleted!");
	       exitEditMode();
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 public void searchPublisher(){
		   
		    // kunin ang text sa search box, alisin ang extra spaces
		    String keyword = txtSearch.getText().trim();
		    if(!keyword.isEmpty()) TableRefresherHelper.stopRefresher();
		    else TableRefresherHelper.startRefresher();
		    
		    // i-clear muna ang table bago mag-load ng bagong results
		    tblModel.setRowCount(0);
      try {
		    // kung walang keyword → load lahat, may keyword → i-filter
		    ArrayList<Publisher> list = keyword.isEmpty()
		        ? publisherController.loadPublisher()       // walang keyword, load all
		        : publisherController.searchPublisher(keyword); // may keyword, i-filter

		    // i-loop ang results at idagdag sa table row by row
		    for (Publisher a : list) {
		        tblModel.addRow(new Object[]{a.getPublisherId(), a.getPublisherName()});
		    }
		    SwingUtilities.invokeLater(() -> {
				restoreSelectedRow();
			});
      }catch(Exception e) {
    	  JOptionPane.showMessageDialog(this, e.getMessage());
      } 
	 }
	
	 
	 public int getSelectedPublisherId() {
		 return selectedPublisherId;
	 }
	 
		public String getSelectedPublisherName() {
			return selectedPublisherName;
		}
	 
}
