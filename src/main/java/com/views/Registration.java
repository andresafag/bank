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
	private ImageIcon icon;
	
	
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
		icon = new ImageIcon(this.getClass().getResource("../../bank-clipart-cartoon-10.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);
		//Main element
		JPanel panelContainer = new JPanel();
		JPanel elementPanel = new JPanel();
		
		
		//Containers section
		JPanel nameContainer = new JPanel();
		JPanel lastNameContainer = new JPanel();
		JPanel usernameContainer = new JPanel();
		JPanel passwordContainer = new JPanel();
		JPanel phoneNumberContainer = new JPanel();
		
		nameContainer.add(nameLabel);
		nameContainer.add(nameField);
		nameContainer.setLayout(new BoxLayout(nameContainer, BoxLayout.Y_AXIS));
		
		lastNameContainer.add(lastNameLabel);
		lastNameContainer.add(lastNameField);
		lastNameContainer.setLayout(new BoxLayout(lastNameContainer, BoxLayout.Y_AXIS));
		
		usernameContainer.add(usernameLabel);
		usernameContainer.add(usernameField);
		usernameContainer.setLayout(new BoxLayout(usernameContainer, BoxLayout.Y_AXIS));
		
		passwordContainer.add(passwdLabel);
		passwordContainer.add(passwordField);
		passwordContainer.setLayout(new BoxLayout(passwordContainer, BoxLayout.Y_AXIS));
		
		phoneNumberContainer.add(PhoneNumberLabel);
		phoneNumberContainer.add(phoneNumberField);
		phoneNumberContainer.setLayout(new BoxLayout(phoneNumberContainer, BoxLayout.Y_AXIS));
		
		elementPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		nameLabel.setText("Enter your first name: ");
		lastNameLabel.setText("Enter your last name: ");
		usernameLabel.setText("Enter a username: ");
		passwdLabel.setText("Enter a password: ");
		PhoneNumberLabel.setText("Enter your phone number: ");
		btn.setText("Done");
		btn.setSize(150, 30);
		btn.setLocation(170, 400);
		
		btn.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("nombre " + nameField.getText() + " apellido " + lastNameField.getText() + " username " + usernameField.getText() + " contraseña " + passwordField.getText() + " numero de telefno " + phoneNumberField.getText());
					if(usrService.checkPhoneNumber(nameField.getText(),lastNameField.getText(), usernameField.getText(), passwordField.getText(), phoneNumberField.getText()) ==  true) {
						System.out.println("no existe ese numero");
						String msgSent = usrService.sendConfirmationSMS(phoneNumberField.getText());
						if(msgSent != "0") {
							String inputValue = JOptionPane.showInputDialog("Please enter a the code sent to your phone");
							if(msgSent.equalsIgnoreCase(inputValue)) {
								dispose();
								new MainView();
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
					
				} catch (NumberFormatException e2) {
					if( nameField.getText().length() == 0 && lastNameField.getText().length() == 0 && usernameField.getText().length() == 0 && phoneNumberField.getText().length() == 0 && phoneNumberField.getText().length() == 0) {
						warning.showMessageDialog(null,"Please enter a something",
								"No data entered", JOptionPane.WARNING_MESSAGE);
					}
					else if(lastNameField.getText().length() == 0) {
						warning.showMessageDialog(null,"Please enter a last name",
								"No phone number entered", JOptionPane.WARNING_MESSAGE);
					}
					else if(usernameField.getText().length() == 0) {
						warning.showMessageDialog(null,"Please enter a username",
								"No phone number entered", JOptionPane.WARNING_MESSAGE);
					}
					else if(passwordField.getText().length() == 0) {
						warning.showMessageDialog(null,"Please enter a password",
								"No phone number entered", JOptionPane.WARNING_MESSAGE);
					}
					else if(phoneNumberField.getText().length() == 0) {
						warning.showMessageDialog(null,"Please enter a phone number",
								"No phone number entered", JOptionPane.WARNING_MESSAGE);
					}
					else if(nameField.getText().length() == 0   ) {
						warning.showMessageDialog(null,"Please enter a name",
					               "No phone number entered", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		});
		
		elementPanel.add(nameContainer);
		elementPanel.add(lastNameContainer);
		elementPanel.add(usernameContainer);
		elementPanel.add(passwordContainer);
		elementPanel.add(phoneNumberContainer);
//		elementPanel.add(btn);
		
		add(backgroundImage);
		add(panelContainer);
//		panelContainer.add(elementPanel);
		elementPanel.setLocation(80, 10);
		setTitle("Registration");
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
		elementPanel.setLayout(new BoxLayout(elementPanel, BoxLayout.Y_AXIS));
		elementPanel.setSize(300, 280);
		backgroundImage.add(elementPanel);
		backgroundImage.add(btn);
	}
}
