package view.InventoryModule.InventoryTab;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import model.AcqusitionModule.Acquisition;
import view.MainFrame;
import view.InventoryModule.AcquisitionTab.AcquisitionView;

import javax.swing.JButton;
import java.awt.Font;

public class InventoryView extends JPanel {

	private MainFrame mainFrame;
	private AcquisitionView acquisition;
	 
	private static final long serialVersionUID = 1L;
	private JButton btnInventory;
	private JButton btnAcquisition;

	   public InventoryView(MainFrame mainFrame) {
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
	    }
	   
	   public void initComponent() {
	       JPanel panel = new JPanel();
	       panel.setLayout(null);
	       panel.setBorder(new LineBorder(new Color(192, 192, 192), 18, true));
	       panel.setBackground(Color.LIGHT_GRAY);
	       panel.setBounds(62, 0, 309, 42);
	       add(panel);
	       
	       btnAcquisition = new JButton("ACQUISITION");
	       btnAcquisition.setForeground(new Color(51, 102, 51));
	       btnAcquisition.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnAcquisition.setEnabled(true);
	       btnAcquisition.setBounds(158, 11, 138, 20);
	       panel.add(btnAcquisition);
	       
	       btnInventory = new JButton("INVENTORY");
	       btnInventory.setBounds(10, 11, 138, 20);
	       btnInventory.setForeground(new Color(51, 102, 51));
	       btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnInventory.setEnabled(true);
	       panel.add(btnInventory);

	   }

	   public void initAction() {		   
		   btnAcquisition.addActionListener(e ->{
		        mainFrame.showPanel(acquisition);
		   });
		   
		   
	   }
	   
	// BAGO: setter para sa shared instance mula sa Modules
	   public void setAcquisitionView(AcquisitionView acquisition) {
	       this.acquisition = acquisition;
	   }
}
