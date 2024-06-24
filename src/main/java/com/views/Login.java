package com.views;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
	private JLabel phoneNumberLabel =  new JLabel("Phone number");
	private JTextField fieldPhoneNumber = new JTextField();
	private JLabel passwdLabel =  new JLabel("Password");
	private JPasswordField passwdField = new JPasswordField();
	private JButton loginBtn = new JButton();
	private ImageIcon iconLogin = new ImageIcon(getClass().getResource("../../login.png"));
	private Image imgLoginBtn = iconLogin.getImage();
	private JPanel panel = new JPanel();
	private JButton btnBck = new JButton();
	private ImageIcon iconBckBtn = new ImageIcon(getClass().getResource("../../girar-a-la-izquierda.png"));
	private Image imgBckBtn = iconBckBtn.getImage();
	private int xMouse = 0;
	private int yMouse = 0;
	
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
		setUndecorated(true);
		icon = new ImageIcon(this.getClass().getResource("../../money-sign-pictures-gj4uo9l9ql4duys6.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		btnBck.setOpaque(false);
		btnBck.setBorderPainted(false);
		btnBck.setContentAreaFilled(false);
		loginBtn.setOpaque(false);
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		btnBck.setBounds(10, 10, 60, 60);
		loginBtn.setSize(100, 100);
		loginBtn.setLocation(190, 270);
		
		Image newImg = imgBckBtn.getScaledInstance(btnBck.getWidth(), btnBck.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        btnBck.setIcon(newImc);
        
        Image newImgLogin = imgLoginBtn.getScaledInstance(loginBtn.getWidth(), loginBtn.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImcLogin = new ImageIcon(newImgLogin);
        loginBtn.setIcon(newImcLogin);
        
        
        
        
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		UserService usrService = (UserService) appconfig.getBean(UserService.class);

		
		//Containers section
		phoneNumberLabel.setFont(new Font("Ariel", Font.BOLD, 15));
		phoneNumberLabel.setAlignmentX(CENTER_ALIGNMENT);
		passwdLabel.setFont(new Font("Ariel", Font.BOLD, 15));
		passwdLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		PhonenumberContainer.add(phoneNumberLabel);
		PhonenumberContainer.add(fieldPhoneNumber);
		PhonenumberContainer.setOpaque(false);
		
		passwordContainer.add(passwdLabel);
		passwordContainer.add(passwdField);
		passwordContainer.setOpaque(false);

		//Panel element
		elementsContainer.setOpaque(false);
		elementsContainer.add(PhonenumberContainer);
		elementsContainer.add(passwordContainer);
//		elementsContainer.add(btnContainer);
		panel.add(elementsContainer);
	
	
		//Container frame
		ImageIcon mainIcon = new ImageIcon(getClass().getResource("../../dollar-symbol.png"));
		Image symbol = mainIcon.getImage();
		setIconImage(symbol);
		add(backgroundImage);
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setTitle("Login");
		setLocationRelativeTo(null);
		setResizable(false);
		elementsContainer.setBorder(BorderFactory.createEmptyBorder());
		elementsContainer.setLayout(new BoxLayout(elementsContainer, BoxLayout.Y_AXIS));
		PhonenumberContainer.setLayout(new BoxLayout(PhonenumberContainer, BoxLayout.Y_AXIS));
		passwordContainer.setLayout(new BoxLayout(passwordContainer, BoxLayout.Y_AXIS));
		elementsContainer.setSize(250, 100);
		elementsContainer.setLocation(120, 150);
		backgroundImage.add(elementsContainer);
		backgroundImage.add(loginBtn);
		backgroundImage.add(btnBck);

		
		//Barra para controlar la ventana 
		JPanel header = new JPanel();
		header.setBounds(0, 0, 500, 10);
		header.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		
		
		
	
		header.setLayout(null);
		header.setBackground(new Color(0,0,0,0));
		backgroundImage.add(header);
		
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		loginBtn.addActionListener(new ActionListener() { 
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e) { 
				
				  try {
					  Long verifyString = Long.parseLong(fieldPhoneNumber.getText());
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
					} else {
						JOptionPane.showMessageDialog(null, "Enter only numbers in the username field" ,"ALERT" , JOptionPane.ERROR_MESSAGE);
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
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
}
	
}
