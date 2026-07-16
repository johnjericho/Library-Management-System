package view.InventoryModule;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import view.MainFrame;
import view.InventoryModule.InventoryTab.InventoryView;

import javax.swing.JButton;
import java.awt.Font;

public class AcqusitionView1 extends JPanel {
	private MainFrame mainFrame;
	
	private static final long serialVersionUID = 1L;
	private JButton btnInventory;
	private JButton btnAcquisition;


	   public AcqusitionView1(MainFrame mainFrame) {
		   this.mainFrame = mainFrame;
		   initialize();
		   initComponent();
		   initAction();
		   }    

	    
	    public void initialize() {
	    	setPanel();
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
	       panel.setBounds(34, 0, 309, 42);
	       add(panel);
	       
	       btnAcquisition = new JButton("ACQUISITION");
	       btnAcquisition.setForeground(new Color(51, 102, 51));
	       btnAcquisition.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnAcquisition.setEnabled(true);
	       btnAcquisition.setBounds(158, 11, 138, 20);
	       panel.add(btnAcquisition);
	       
	       btnInventory = new JButton("INVENTORY");
	       btnInventory.setForeground(new Color(51, 102, 51));
	       btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnInventory.setEnabled(true);
	       btnInventory.setBounds(10, 11, 138, 20);
	       panel.add(btnInventory);
		   
	   }
	   
	   
	   public void initAction() {
		   btnInventory.addActionListener(e ->{
			   InventoryView inv = new InventoryView(mainFrame);
			   mainFrame.showPanel(inv);
		   });	  
	   
	   }
	   
	   
	   
	   
}
