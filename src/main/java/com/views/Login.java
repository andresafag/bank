package com.views;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setTitle("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);
		JOptionPane alert = new JOptionPane();
		
		//Username section
		JLabel phoneNumberLabel =  new JLabel("Type in your phone number");
		JTextField fieldPhoneNumber = new JTextField(10);
		//Username section
		JLabel passwdLabel =  new JLabel("Type in your password");
		JPasswordField passwdField = new JPasswordField(10);
		
		//Button Element
		JButton loginBtn = new JButton();
		loginBtn.setText("Login");
		loginBtn.addActionListener(new ActionListener() { 
			  @SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e) { 
				  System.out.println("Your name is " + fieldPhoneNumber.getText() + " and your password is " + passwdField.getText());
				  usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText());
				  System.out.println(usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText()));
				  if (usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText()) == false) {
					dispose();
					new LoggedIn();
				  } else {
					  alert.showMessageDialog(null,"Please enter the right information",
				               "User might not exist", JOptionPane.WARNING_MESSAGE);
				  }
			  } 
			});
		
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(phoneNumberLabel);
		panel.add(fieldPhoneNumber);
		panel.add(passwdLabel);
		panel.add(passwdField);
		panel.add(loginBtn);

	
		//Container frame
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
}
