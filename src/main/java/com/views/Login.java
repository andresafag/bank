package com.views;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bank.esterlinas.AppConfig;
import com.services.bank.UserService;


@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private ImageIcon icon;
	private JOptionPane alert = new JOptionPane();
	private JPanel elementsContainer = new JPanel();
	private JPanel PhonenumberContainer = new JPanel();
	private JPanel passwordContainer = new JPanel();
	private JPanel btnContainer = new JPanel();
	private JLabel phoneNumberLabel =  new JLabel("Type in your phone number");
	private JTextField fieldPhoneNumber = new JTextField();
	private JLabel passwdLabel =  new JLabel("Type in your password");
	private JPasswordField passwdField = new JPasswordField();
	private JButton loginBtn = new JButton();
	private JPanel panel = new JPanel();
	private JButton btnBck = new JButton("BACK");
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		icon = new ImageIcon(this.getClass().getResource("../../money-sign-pictures-gj4uo9l9ql4duys6.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);

		
		//Containers section
		PhonenumberContainer.add(phoneNumberLabel);
		PhonenumberContainer.add(fieldPhoneNumber);
		PhonenumberContainer.setOpaque(false);
		passwordContainer.add(passwdLabel);
		passwordContainer.add(passwdField);
		passwordContainer.setOpaque(false);
		
		
		//Button Element
		loginBtn.setText("Login");
		loginBtn.setSize(100, 50);
		loginBtn.setLocation(200, 300);
//		btnContainer.add(loginBtn);
		
		
		
		//Panel element
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		elementsContainer.setOpaque(false);
		elementsContainer.add(PhonenumberContainer);
		elementsContainer.add(passwordContainer);
//		elementsContainer.add(btnContainer);
		panel.add(elementsContainer);
		
		btnBck.setBounds(10, 10, 110, 50);
	
		//Container frame
		add(backgroundImage);
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setTitle("Login");
		setLocationRelativeTo(null);
		setResizable(false);
		elementsContainer.setLayout(new BoxLayout(elementsContainer, BoxLayout.Y_AXIS));
		PhonenumberContainer.setLayout(new BoxLayout(PhonenumberContainer, BoxLayout.Y_AXIS));
		passwordContainer.setLayout(new BoxLayout(passwordContainer, BoxLayout.Y_AXIS));
		elementsContainer.setSize(190, 110);
		elementsContainer.setLocation(150, 150);
		backgroundImage.add(elementsContainer);
		backgroundImage.add(loginBtn);
		backgroundImage.add(btnBck);
		
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		loginBtn.addActionListener(new ActionListener() { 
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e) { 
				  try {
					  System.out.println("Your name is " + fieldPhoneNumber.getText() + " and your password is " + passwdField.getText());
					  usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText());
					  System.out.println(usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText()));
					  if (usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText()) == false) {
						dispose();
						new LoggedIn(usrService.retriveUsrInfo(fieldPhoneNumber.getText()));
						((ConfigurableApplicationContext)appconfig).close();
					  } else {
						  alert.showMessageDialog(null,"Please enter the right information",
					               "User might not exist", JOptionPane.WARNING_MESSAGE);
					  }
				} catch (NumberFormatException e2) {
					if(fieldPhoneNumber.getText().length() == 0 && passwdField.getText().length() > 0) {
						alert.showMessageDialog(null,"Please enter a number",
					               "No phone number entered", JOptionPane.WARNING_MESSAGE);
					} else if (passwdField.getText().length() == 0 && fieldPhoneNumber.getText().length() > 0) {
						alert.showMessageDialog(null,"Please enter password",
					               "No password entered", JOptionPane.WARNING_MESSAGE);
					} else if(fieldPhoneNumber.getText().length() == 0 && passwdField.getText().length() == 0) {
						alert.showMessageDialog(null,"Please enter something",
					               "No data entered", JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
			  } 
			});
		
		btnBck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainView();
			}
		});
	}
	
}
