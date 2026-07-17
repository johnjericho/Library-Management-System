package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    
    //PANEL
    private JPanel contentPane;
    private JPanel currentPanel;
    
    //COMPOSITION
    private Modules modules; 
  
    
    public MainFrame() {    	
        execute();
    }

    public void execute() {
        setupFrame();
        showPanel(new DashboardView()); 
        modules = new Modules(this);
        modules.setBounds(0, 0, 240, 768);
        contentPane.add(modules);
    }

    public void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1366, 768);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.contentPane = new JPanel();
        this.contentPane.setLayout(null);
        this.setContentPane(contentPane);
    }

 
    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            contentPane.remove(currentPanel);
        }
        currentPanel = panel;
        currentPanel.setBounds(234, 0, getWidth() - 240, getHeight());
        contentPane.add(currentPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }
    

}