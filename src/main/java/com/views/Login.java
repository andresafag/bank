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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bank.esterlinas.AppConfig;
import com.services.bank.UserService;


@SuppressWarnings("serial")
public class Login extends JFrame {
	private ImageIcon icon;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		icon = new ImageIcon(this.getClass().getResource("../../bank-clipart-cartoon-10.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);
		JOptionPane alert = new JOptionPane();
		
		//Containers section
		JPanel elementsContainer = new JPanel();
		JPanel PhonenumberContainer = new JPanel();
		JPanel passwordContainer = new JPanel();
		JPanel btnContainer = new JPanel();
		
		//Username section
		JLabel phoneNumberLabel =  new JLabel("Type in your phone number");
		JTextField fieldPhoneNumber = new JTextField();
		JLabel passwdLabel =  new JLabel("Type in your password");
		JPasswordField passwdField = new JPasswordField();
		
		PhonenumberContainer.add(phoneNumberLabel);
		PhonenumberContainer.add(fieldPhoneNumber);
		
		passwordContainer.add(passwdLabel);
		passwordContainer.add(passwdField);
		
		
		//Button Element
		JButton loginBtn = new JButton();
		loginBtn.setText("Login");
		loginBtn.setSize(150, 50);
		loginBtn.setLocation(160, 200);
//		btnContainer.add(loginBtn);
		
		
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
		
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		elementsContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		elementsContainer.add(PhonenumberContainer);
		elementsContainer.add(passwordContainer);
//		elementsContainer.add(btnContainer);
		panel.add(elementsContainer);


	
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
		elementsContainer.setSize(450, 130);
		elementsContainer.setLocation(15, 10);
		backgroundImage.add(elementsContainer);
		backgroundImage.add(loginBtn);
	}
	
}
