package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bank.esterlinas.AppConfig;
import com.services.bank.UserService;

@SuppressWarnings("serial")
public class Registration extends JFrame{
	private JLabel nameLabel = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel usernameLabel = new JLabel();
	private JLabel passwdLabel = new JLabel();
	private JLabel PhoneNumberLabel = new JLabel();
	private JTextField nameField = new JTextField(10);
	private JTextField lastNameField = new JTextField(10);
	private JTextField usernameField = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField(10);
	private JTextField phoneNumberField = new JTextField(10);
	private JButton btn =  new JButton();
	private JOptionPane warning = new JOptionPane();
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Registration() {
			
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		nameLabel.setText("Enter your first name: ");
		lastNameLabel.setText("Enter your last name: ");
		usernameLabel.setText("Enter a username: ");
		passwdLabel.setText("Enter a password: ");
		PhoneNumberLabel.setText("Enter your phone number: ");
		btn.setText("Done");
		
		
		btn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("nombre " + nameField.getText() + " apellido " + lastNameField.getText() + " username " + usernameField.getText() + " contraseña " + passwordField.getText() + " numero de telefno " + phoneNumberField.getText());
				if(usrService.checkPhoneNumber(nameField.getText(),lastNameField.getText(), usernameField.getText(), passwordField.getText(), phoneNumberField.getText()) ==  true) {
					System.out.println("no existe ese numero");
					String msgSent = usrService.sendConfirmationSMS(phoneNumberField.getText());
					if(msgSent != "0") {
						String inputValue = JOptionPane.showInputDialog("Please enter a the code sent to your phone");
						if(msgSent.equalsIgnoreCase(inputValue)) {
							dispose();
							new Registered();
							usrService.saveUsr(nameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText(), phoneNumberField.getText());
						} else {
							warning.showMessageDialog(null,"Wrong code",
						               "Please enter the right code", JOptionPane.WARNING_MESSAGE);
						}
						
					}
					
					
				} else {
					System.out.println("ya existe ese numero");
					warning.showMessageDialog(null,"That phone number is already stored in the database",
				               "Phone number in used", JOptionPane.WARNING_MESSAGE);
				}
//				usrService.sendConfirmationSMS(phoneNumberField.getText());
			}
		});
		
		panel.add(nameLabel);
		panel.add(nameField);
		
		panel.add(lastNameLabel);
		panel.add(lastNameField);	
		
		panel.add(usernameLabel);
		panel.add(usernameField);	
		
		panel.add(passwdLabel);
		panel.add(passwordField);
		
		panel.add(PhoneNumberLabel);
		panel.add(phoneNumberField);
		
		panel.add(btn);
			
		add(panel);
		setTitle("Registration");
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
