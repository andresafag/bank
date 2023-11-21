package com.views;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
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

public class MainView implements ActionListener {
	
	
	public static void main(String[] args)  {
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);
		
		JFrame frame = new JFrame("Main window");
		
		JOptionPane alert = new JOptionPane();
		
		
		//Username section
		JLabel usernameLabel =  new JLabel("Type in your username");
		JTextField fieldUsername = new JTextField(10);

	
		
		//Username section
		JLabel passwdLabel =  new JLabel("Type in your password");
		JPasswordField passwdField = new JPasswordField(10);
		
		
		//Button Element
		JButton loginBtn = new JButton();
		loginBtn.setText("Login");
		loginBtn.addActionListener(new ActionListener() { 
			  @SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e) { 
				  System.out.println("Your name is " + fieldUsername.getText() + " and your password is " + passwdField.getText());
				  System.out.println(usrService.verifyUser(fieldUsername.getText(),passwdField.getText()));
				  if (usrService.verifyUser(fieldUsername.getText(),passwdField.getText()) == false) {
					frame.dispose();
					LoginScreen loginScreen = new LoginScreen();
					loginScreen.main(args);
					
				  } else {
					  alert.showMessageDialog(frame, "Please enter the right information",
				               "User might not exist", JOptionPane.WARNING_MESSAGE);
				  }
			  } 
			});
		
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(usernameLabel);
		panel.add(fieldUsername);
		panel.add(passwdLabel);
		panel.add(passwdField);
		panel.add(loginBtn);
//		Image image = Toolkit.getDefaultToolkit().getImage("bank-clipart-cartoon-10.jpg");
//		panel.drawImage(image, 0, 0, MainView);
//		
		//Container frame
		frame.add(panel);
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setLayout(null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		
	}
}
