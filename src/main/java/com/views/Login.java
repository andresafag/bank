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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
	
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("../../seamless-money.jpg"));
	private JLabel backgroundImage = new JLabel();
	private JOptionPane alert = new JOptionPane();
	private JPanel elementsContainer = new JPanel();
	private JPanel PhonenumberContainer = new JPanel();
	private JPanel passwordContainer = new JPanel();
	private JPanel btnContainer = new JPanel();
	private JLabel phoneNumberLabel =  new JLabel("Phone number");
	private JTextField fieldPhoneNumber = new JTextField();
	private JLabel loadingLabel =  new JLabel();
	private JLabel passwdLabel =  new JLabel("Password");
	private JPasswordField passwdField = new JPasswordField();
	private JButton loginBtn = new JButton();
	private ImageIcon iconLogin = new ImageIcon(getClass().getResource("../../login.png"));
	private Image imgLoginBtn = iconLogin.getImage();
	private JPanel panel = new JPanel();
	private JButton btnBck = new JButton();
	private ImageIcon iconBckBtn = new ImageIcon(getClass().getResource("../../girar-a-la-izquierda.png"));
	private Image imgBckBtn = iconBckBtn.getImage();	
	private ImageIcon gifIcon = new ImageIcon(getClass().getResource("../../loading.gif"));
	private int xMouse,yMouse;
	
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
		btnBck.setOpaque(false);
		btnBck.setBorderPainted(false);
		btnBck.setContentAreaFilled(false);
		loginBtn.setOpaque(false);
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		btnBck.setBounds(10, 10, 60, 60);
		loginBtn.setSize(100, 100);
		loginBtn.setLocation(190, 270);
		
		// Set the background
		backgroundImage.setSize(500,600);
		Image nnn = icon.getImage().getScaledInstance(backgroundImage.getWidth(), backgroundImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon nn = new ImageIcon(nnn);
		backgroundImage.setIcon(nn);
		
		gifIcon = new ImageIcon(gifIcon.getImage().getScaledInstance(100, 100, Image.SCALE_REPLICATE));
		loadingLabel.setIcon(gifIcon);
		loadingLabel.setBounds(200, 350, 100, 100);
		
		
		Image newImg = imgBckBtn.getScaledInstance(btnBck.getWidth(), btnBck.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        btnBck.setIcon(newImc);
        
        Image newImgLogin = imgLoginBtn.getScaledInstance(loginBtn.getWidth(), loginBtn.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImcLogin = new ImageIcon(newImgLogin);
        loginBtn.setIcon(newImcLogin);
        
		
		//Containers section
		phoneNumberLabel.setFont(new Font("Algerian", Font.BOLD, 20));
		phoneNumberLabel.setForeground(new Color(102-255-102));
		phoneNumberLabel.setAlignmentX(CENTER_ALIGNMENT);
		passwdLabel.setFont(new Font("Algerian", Font.BOLD, 20));
		passwdLabel.setForeground(new Color(102-255-102));
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
		backgroundImage.add(loadingLabel);
		loadingLabel.setVisible(false);

		
		
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
		
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------;	
		if(backgroundImage.isShowing()) {
			fieldPhoneNumber.requestFocus();
		}
		
		fieldPhoneNumber.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method 
				if( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					launch();
				} 
				
			}
		});
		
		passwdField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					launch();
				} 
				
			}
		});
		
		
		
		loginBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				launch();
			  } 
			});
		
		
		
		//Go back 
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
    
    private void launch() {  
    	loadingLabel.setVisible(true);
		  try {
		    	
			  new java.util.Timer().schedule(new java.util.TimerTask() {
				    @Override
				    public void run() {
				    	ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
						UserService usrService = (UserService) appconfig.getBean(UserService.class);
						  if (usrService.verifyUser(fieldPhoneNumber.getText(),passwdField.getText()) == false) {
							  loadingLabel.setVisible(false);
								dispose();
								new LoggedIn(usrService.retriveUsrInfo(fieldPhoneNumber.getText()));
							((ConfigurableApplicationContext)appconfig).close();
						  } else {
							  alert.showMessageDialog(null,"Please enter the right information","User might not exist", JOptionPane.WARNING_MESSAGE);
							  ((ConfigurableApplicationContext)appconfig).close();
							  loadingLabel.setVisible(false);
						  }
				    }
				}, 2000);
			  Long verifyString = Long.parseLong(fieldPhoneNumber.getText());

			 
		} catch (NumberFormatException e2) {
			if(fieldPhoneNumber.getText().length() == 0 && passwdField.getText().length() > 0) {
				alert.showMessageDialog(null,"Please enter a number","No phone number entered", JOptionPane.WARNING_MESSAGE);
				passwdField.setText("");
				loadingLabel.setVisible(false);
			} else if (passwdField.getText().length() == 0 && fieldPhoneNumber.getText().length() > 0) {
				alert.showMessageDialog(null,"Please enter password","No password entered", JOptionPane.WARNING_MESSAGE);
				fieldPhoneNumber.setText("");
				loadingLabel.setVisible(false);
			} else if(fieldPhoneNumber.getText().length() == 0 && passwdField.getText().length() == 0) {
				alert.showMessageDialog(null,"Please enter something","No data entered", JOptionPane.WARNING_MESSAGE);
				loadingLabel.setVisible(false);
			} 
			else {
				JOptionPane.showMessageDialog(null, "Enter only numbers in the username field" ,"ALERT" , JOptionPane.ERROR_MESSAGE);
				loadingLabel.setVisible(false);
			}
			
			
		}
    
    }
	
}
