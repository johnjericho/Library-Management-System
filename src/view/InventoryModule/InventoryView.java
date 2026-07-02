package view.InventoryModule;

import javax.swing.JPanel;

public class InventoryView extends JPanel {

	private static final long serialVersionUID = 1L;

	   public InventoryView() {
		   initialize();
		   }    

	    
	    public void initialize() {
	    	setPanel();
	    }
	    
	   public void setPanel(){
	       this.setLayout(null);
	       this.setSize(1126,743);

	    }
}
