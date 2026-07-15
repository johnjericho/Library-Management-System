package view.UserModule.StudentTab;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.UserModule.StudentTab.SectionController;
import model.UserModule.StudentTab.Section;
import utility.AppContext;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SectionPickerDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTable tblSection;
    private DefaultTableModel tblModel;
    private SectionController sectionController = AppContext.getInstance().getSectionController();

    private int gradeId;
    private int selectedSectionId = -1;
    private String selectedSectionName = "";
    private JButton btnAdd;
    private JTextField txtSearch;
    private JPanel tblBorder;
    private JScrollPane scrollPane;

    public SectionPickerDialog(int gradeId) {
        this.gradeId = gradeId;
        initialize();
    }

    public void initialize() {
    	setPanel();
        initComponents();
        initAction();
        loadSections();

    }
    
    public void setPanel() {    	
    	setModal(true); 
        setBounds(150, 150, 453, 458);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);


    }
    
    public void initComponents() {
         
    	
        btnAdd = new JButton("ADD");       
        btnAdd.setForeground(new Color(51, 102, 51));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBounds(37, 80, 81, 27);
        getContentPane().add(btnAdd);
        
        String[] columns = {"Section ID", "Section Name", "Action"};
        tblModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return col == 2; // BAGO: Action column (index 2) lang ang dapat "editable"
                                  // para gumana yung Update/Delete buttons; column 0 at 1 (ID, Name)
                                  // dapat read-only pa rin, kaya false sila
            }
        };
        
        tblBorder = new JPanel();
        tblBorder.setBorder(new LineBorder(new Color(0, 128, 0), 2, true));
        tblBorder.setBounds(10, 91, 419, 317);
        tblBorder.setLayout(null);
        getContentPane().add(tblBorder);
    	
    	tblSection = new JTable(tblModel);
    	tblSection.setRowHeight(30); // dagdagan pa kung kulang pa rin
    	TableColumn nameCol = tblSection.getColumnModel().getColumn(0); // Section Name
    	TableColumn actionCol = tblSection.getColumnModel().getColumn(1); // Action

    	nameCol.setPreferredWidth(300);   // mas malaki
    	actionCol.setPreferredWidth(150); // mas maliit
    	
        scrollPane = new JScrollPane(tblSection);
        scrollPane.setBounds(10, 26, 396, 280);
        tblBorder.add(scrollPane);
        
        tblSection.getColumnModel().getColumn(0).setMinWidth(0);
        tblSection.getColumnModel().getColumn(0).setMaxWidth(0);
        tblSection.getColumnModel().getColumn(0).setWidth(0);
        
        tblSection.getColumnModel().getColumn(2).setCellRenderer(new ActionCellRenderer());
        tblSection.getColumnModel().getColumn(2).setCellEditor(new ActionCellEditor());
       
        JLabel lblSearch = new JLabel("Search");
        lblSearch.setForeground(new Color(51, 102, 51));
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSearch.setBounds(219, 61, 64, 14);
        getContentPane().add(lblSearch);
        
        txtSearch = new JTextField();
        txtSearch.setColumns(10);
        txtSearch.setBounds(280, 57, 149, 23);
        getContentPane().add(txtSearch);
                         

    }



    private void initAction() {
    	
    	btnAdd.addActionListener(e ->{
    		SectionEditForm secEdit = new SectionEditForm(gradeId);
    		secEdit.setModal(true);
    		secEdit.setVisible(true);
    	});
    	
    	
    	
    	tblSection.addMouseListener(new MouseAdapter() {
    	    public void mouseClicked(MouseEvent e) {
    	        int row = tblSection.getSelectedRow();
    	        if (row == -1) return;

    	        int col = tblSection.columnAtPoint(e.getPoint());
    	        if (col == 2) return; // ignore Action column, hawak na ng cell editor

    	        int id = (int) tblModel.getValueAt(row, 0); // 0 is id
    	        String name = (String) tblModel.getValueAt(row, 1); // 1 is sectionName

    	        selectedSectionId = id;
    	        selectedSectionName = name;

    	        if (e.getClickCount() == 2) {
    	            dispose();
    	        }
    	    }
    	});
    }

    public int getSelectedSectionId() {
        return selectedSectionId;
    }

    public String getSelectedSectionName() {
        return selectedSectionName;
    }
    
    
    private void loadSections() {
    	 
        ArrayList<Section> sections = sectionController.getSectionsByGrade(gradeId);

        tblModel.setRowCount(0); // clear muna
        for (Section s : sections) {
            tblModel.addRow(new Object[]{ s.getSectionId(), s.getSectionName() });
        }
    }
    
    

    
	class ActionCellRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private JButton btnUpdate = new JButton("Update");
		private JButton btnDelete = new JButton("Delete");

		public ActionCellRenderer() { 
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
	        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0)); // ← DITO ILALAGAY
	        setOpaque(true); // BAGO: kailangan opaque para lumabas yung background color natin
			btnUpdate.setForeground(new Color(51, 102, 51));
			btnDelete.setForeground(Color.RED);
			add(btnUpdate);
			add(btnDelete);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			 // BAGO: dito talaga nangyayari yung pag-sync ng background sa selection state
		    if (isSelected) {
		        setBackground(table.getSelectionBackground());
		    } else {
		        setBackground(table.getBackground());
		    }
			return this;
		}
	}
	
	class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private JPanel panel = new JPanel();
		private JButton btnUpdate = new JButton("Update");
		private JButton btnDelete = new JButton("Delete");
		private int currentRow;

		public ActionCellEditor() {
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
			btnUpdate.setForeground(new Color(51, 102, 51));
			btnDelete.setForeground(Color.RED);
			panel.add(btnUpdate);
			panel.add(btnDelete);

			btnUpdate.addActionListener(e -> {
			    fireEditingStopped(); // 1. i-stop muna yung edit mode, para bumalik sa "picture mode"

			    int sectionId = (int) tblModel.getValueAt(currentRow, 0); // 2. kunin ID sa hidden column 0
			    String sectionName = (String) tblModel.getValueAt(currentRow, 1);

			    // 3. buksan yung parehong form na ginamit mo sa Add, pero sa "update mode"
			    SectionEditForm secEdit = new SectionEditForm(gradeId, sectionId, sectionName);
			    secEdit.setModal(true);
			    secEdit.setVisible(true);

			    loadSections(); // 4. i-refresh table pagbalik galing sa form (baka may nabago)
			});

			btnDelete.addActionListener(e -> {
			    fireEditingStopped();

			    int sectionId = (int) tblModel.getValueAt(currentRow, 0);
			    String sectionName = (String) tblModel.getValueAt(currentRow, 1);

			    int confirm = JOptionPane.showConfirmDialog(
			        panel,
			        "Delete section \"" + sectionName + "\"?",
			        "Confirm Delete",
			        JOptionPane.YES_NO_OPTION
			    );

			    if (confirm == JOptionPane.YES_OPTION) {
			        sectionController.deleteSection(sectionId); // adjust method name kung iba sa Controller mo
			        JOptionPane.showMessageDialog(null, "Successfully deleted!");

			        loadSections(); // refresh agad para mawala sa table yung na-delete
			    }
			    
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			currentRow = row;
			return panel;
		}

		@Override
		public Object getCellEditorValue() {
			return "";
		}
		
	}
	
    
    
    
}