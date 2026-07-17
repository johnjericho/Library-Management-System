package view;

import controller.LoginController;
import utility.AppContext;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    //PANEL
    private JPanel contentPane;
    
    //TEXTFIELD
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    
    //BUTTON
    private JButton btnLogIn; 
    private JButton btn;
    private JRadioButton rbtnShow;

    //CONTROLLER
    private LoginController loginController = AppContext.getInstance().getLoginController();
    

    
    public LogIn() {
        initialize();
    }

    public void initialize() {
        setFrame();
        initComponents();
        initActions();
    }

    public void setFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 347, 381);
        setLocationRelativeTo(null);
    }
        
    
    public void initComponents() {
    	 contentPane = new JPanel();
         contentPane.setBackground(Color.LIGHT_GRAY);
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
    	
    	txtUserName = new JTextField();
        txtUserName.setBounds(49, 189, 247, 20);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(49, 213, 247, 20);
        contentPane.add(txtPassword);

        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(112, 282, 104, 20);
        contentPane.add(btnLogIn);

        rbtnShow = new JRadioButton("show");
        rbtnShow.setBounds(49, 240, 65, 23);
        contentPane.add(rbtnShow);

        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(
        MainFrame.class.getResource("/img/images__1_-removebg-preview.png"));
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(185, 148, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(resized));
        logo.setBounds(73, 11, 198, 181);
        contentPane.add(logo);
        
        btn = new JButton("Shortcut");
        btn.setLocation(0, 11);
        btn.setSize(85, 20);
        contentPane.add(btn);
    
    }

    public void initActions() {
    	
    	  btn.addActionListener(e -> {
          	MainFrame mainFrame = new MainFrame();
          	mainFrame.setVisible(true);
          	this.dispose();          });
    	
        btnLogIn.addActionListener(e -> logIn());

        rbtnShow.addActionListener(e ->{ 
        	 if (rbtnShow.isSelected()) {
                 txtPassword.setEchoChar((char) 0);
             } else {
                 txtPassword.setEchoChar('*');
             } });   
    	}

    private void logIn() {
    	
        String userName = txtUserName.getText();
        String password = new String(txtPassword.getPassword());

  try {
        boolean success = loginController.login(userName, password);
        if (success) {
            JOptionPane.showMessageDialog(this,"Login success!","Validated!",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new MainFrame().setVisible(true);;
        } else {
            JOptionPane.showMessageDialog(this,"Invalid username or password.","Invalid!",JOptionPane.ERROR_MESSAGE);
        }
  }catch(RuntimeException e) {
	  e.printStackTrace();
	  JOptionPane.showMessageDialog(this, e.getMessage());
  }
    }
    
}