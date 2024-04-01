package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
	private ImageIcon iconDoneBtn = new ImageIcon(getClass().getResource("../../done.png"));
	private Image imgDoneBtn = iconDoneBtn.getImage();
	private JOptionPane warning = new JOptionPane();
	private ImageIcon icon;
	private JPanel panelContainer = new JPanel();
	private JPanel elementPanel = new JPanel();
	private JPanel nameContainer = new JPanel();
	private JPanel lastNameContainer = new JPanel();
	private JPanel usernameContainer = new JPanel();
	private JPanel passwordContainer = new JPanel();
	private JPanel phoneNumberContainer = new JPanel();
	private JButton btnBck = new JButton();
	private ImageIcon iconBckBtn = new ImageIcon(getClass().getResource("../../girar-a-la-izquierda.png"));
	private Image imgBckBtn = iconBckBtn.getImage();
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Registration();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Registration() {
		icon = new ImageIcon(this.getClass().getResource("../../money-sign-pictures-gj4uo9l9ql4duys6.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		btnBck.setOpaque(false);
		btnBck.setBorderPainted(false);
		btnBck.setContentAreaFilled(false);
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		
		
		btnBck.setBounds(15, 20, 60, 60);
		btn.setText("Done");
		btn.setSize(80, 80);
		btn.setLocation(220, 380);
		
		
		Image newImg = imgBckBtn.getScaledInstance(btnBck.getWidth(), btnBck.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        btnBck.setIcon(newImc);
        
        Image newImgDone = imgDoneBtn.getScaledInstance(btn.getWidth(), btn.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImcDone = new ImageIcon(newImgDone);
        btn.setIcon(newImcDone);
        
        
		
		//Containers section
        nameLabel.setFont(new Font("Arial",  Font.BOLD, 15));
        nameLabel.setAlignmentX(nameContainer.CENTER_ALIGNMENT);
		nameContainer.add(nameLabel);
		nameContainer.add(nameField);
		nameContainer.setLayout(new BoxLayout(nameContainer, BoxLayout.Y_AXIS));
		nameContainer.setOpaque(false);
		
		
		lastNameLabel.setFont(new Font("Arial",  Font.BOLD, 15));
		lastNameLabel.setAlignmentX(lastNameContainer.CENTER_ALIGNMENT);
		lastNameContainer.add(lastNameLabel);
		lastNameContainer.add(lastNameField);
		lastNameContainer.setLayout(new BoxLayout(lastNameContainer, BoxLayout.Y_AXIS));
		lastNameContainer.setOpaque(false);
		lastNameContainer.setOpaque(false);
		
		usernameLabel.setFont(new Font("Arial",  Font.BOLD, 15));
		usernameLabel.setAlignmentX(usernameContainer.CENTER_ALIGNMENT);
		usernameContainer.add(usernameLabel);
		usernameContainer.add(usernameField);
		usernameContainer.setLayout(new BoxLayout(usernameContainer, BoxLayout.Y_AXIS));
		usernameContainer.setOpaque(false);
		
		passwdLabel.setFont(new Font("Arial",  Font.BOLD, 15));
		passwdLabel.setAlignmentX(passwordContainer.CENTER_ALIGNMENT);
		passwordContainer.add(passwdLabel);
		passwordContainer.add(passwordField);
		passwordContainer.setLayout(new BoxLayout(passwordContainer, BoxLayout.Y_AXIS));
		passwordContainer.setOpaque(false);
		
		PhoneNumberLabel.setFont(new Font("Arial",  Font.BOLD, 15));
		PhoneNumberLabel.setAlignmentX(phoneNumberContainer.CENTER_ALIGNMENT);
		phoneNumberContainer.add(PhoneNumberLabel);
		phoneNumberContainer.add(phoneNumberField);
		phoneNumberContainer.setLayout(new BoxLayout(phoneNumberContainer, BoxLayout.Y_AXIS));
		phoneNumberContainer.setOpaque(false);
		
		elementPanel.setOpaque(false);
		nameLabel.setText("First name: ");
		lastNameLabel.setText("Last name: ");
		usernameLabel.setText("Username: ");
		passwdLabel.setText("Password: ");
		PhoneNumberLabel.setText("Phone number:");

		elementPanel.add(nameContainer);
		elementPanel.add(lastNameContainer);
		elementPanel.add(usernameContainer);
		elementPanel.add(passwordContainer);
		elementPanel.add(phoneNumberContainer);
		

		
		add(backgroundImage);
		elementPanel.setLocation(150, 150);
		setTitle("Registration");
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		elementPanel.setSize(210, 250);
		backgroundImage.add(elementPanel);
		backgroundImage.add(btn);
		backgroundImage.add(btnBck);
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		btn.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
				UserService usrService = (UserService) appconfig.getBean(UserService.class);
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
								((ConfigurableApplicationContext)appconfig).close();
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
		
		btnBck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainView();
			}
		});
		
		

	}
}
