package com.views;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bank.esterlinas.AppConfig;
import com.services.bank.AccountService;
import com.services.bank.UserService;


@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
	
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("../../seamless-money.jpg"));
	private JLabel backgroundImage = new JLabel();
	private JLabel labelUsername = new JLabel();
	private ImageIcon usrIcon = new ImageIcon(getClass().getResource("../../user.png"));
	private Image usrImg = usrIcon.getImage();
	private JLabel balanceLabel = new JLabel();
	private JLabel labelBell = new JLabel();
	private ImageIcon bellIcon= new ImageIcon(getClass().getResource("../../notification.png"));
	private Image bellImage = bellIcon.getImage();
	private JPanel usernameContainer = new JPanel();
	private JPanel notificationContainer = new JPanel();
	private JButton btnNotification = new JButton("campana icono");
	private JPanel panel =  new JPanel();
	private JPanel panel2 =  new JPanel();
	private JPanel transactionsContainer =  new JPanel();
	private JButton btnDeposit = new JButton("Deposit");
	private JButton btnWithdraw = new JButton("Withdraw");
	private JLabel UsrnameLabel =  new JLabel();
	private JLabel availableLabel = new JLabel("Available");
	private JButton btnExit = new JButton();
	private ImageIcon exitIcon = new ImageIcon(getClass().getResource("../../logout2.png"));
	private Image exitImage = exitIcon.getImage();
	private int xMouse = 0;
	private int yMouse = 0;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoggedIn(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoggedIn(Map<String,Object> param) {
		setUndecorated(true);
		
		// Set the background
		backgroundImage.setSize(500,600);
		Image nnn = icon.getImage().getScaledInstance(backgroundImage.getWidth(), backgroundImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon nn = new ImageIcon(nnn);
		backgroundImage.setIcon(nn);
		
		//username icon
		labelUsername.setSize(50,50);
		Image newImg = usrImg.getScaledInstance(labelUsername.getWidth(), labelUsername.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
		labelUsername.setIcon(newImc);
		
		//logout icon
		btnExit.setSize(80, 80);
		Image newImgLogout = exitImage.getScaledInstance(btnExit.getWidth(), btnExit.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImcLogout = new ImageIcon(newImgLogout);
        btnExit.setIcon(newImcLogout);
        
        //main icon
        ImageIcon mainIcon = new ImageIcon(getClass().getResource("../../dollar-symbol.png"));
		Image symbol = mainIcon.getImage();
		
		//user information
		String usrname = (String) param.get("username");
		Long balanceNumber = (Long) param.get("balance");
		String balanceString = String.valueOf(balanceNumber);
		String name = (String) param.get("name");
		String lastName = (String) param.get("lastname");
		Long phoneNumber = (Long) param.get("phonenumber");
		String phoneNumberString = String.valueOf(phoneNumber);
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();	
		
//		String name = "andy";
//		String lastName = "acosta";
//		String phoneNumberString = "8888888888";
//		String balanceString = "300";
		
		
		
		UsrnameLabel.setText(usrname);
		UsrnameLabel.setForeground(new Color(102-255-102));
		UsrnameLabel.setFont(new Font("Serif", Font.BOLD, 27));
		
		
		//Box containing the username items
		usernameContainer.setOpaque(false);
		usernameContainer.add(labelUsername);
		usernameContainer.add(UsrnameLabel);
//		usernameContainer.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));

//		labelBell.setSize(50,50);
		btnExit.setBounds(5, 420, 95, 100);
		btnExit.setOpaque(false);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		


//		Image newImgNotification = bellImage.getScaledInstance(labelBell.getWidth(), labelBell.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon newImcNotification = new ImageIcon(newImgNotification);
//        labelBell.setIcon(newImcNotification);
//		
		
//		//Box containing the notifications
//		notificationContainer.setOpaque(false);
//		notificationContainer.add(labelBell);
//		notificationContainer.setSize(150, 50);
//		notificationContainer.setBounds(430, 5, 100,200);

		
		backgroundImage.add(transactionsContainer);
		
		//Box showing the money available
		availableLabel.setFont(new Font("Algerian", Font.BOLD, 25));
		availableLabel.setForeground(new Color(102-255-102));
//		availableLabel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		//--------------------------------------------------------
		balanceLabel.setText(phoneNumberString.format("$%s",balanceString));
		balanceLabel.setForeground(new Color(200-255-204));
		balanceLabel.setFont(new Font("Alegerian", Font.BOLD, 25));
			
			
		
		//transactions buttons
		btnDeposit.setBackground(new Color(102-255-102));
		btnDeposit.setForeground(Color.black);
		btnDeposit.setFont(new Font("Ariel", Font.BOLD, 20));
		//----------------------------------------------------------
		btnWithdraw.setBackground(new Color(102-255-102));
		btnWithdraw.setForeground(Color.black);
		btnWithdraw.setFont(new Font("Ariel", Font.BOLD, 20));
		//----------------------------------------------------------
		transactionsContainer.add(btnDeposit);
		transactionsContainer.add(btnWithdraw);
		transactionsContainer.setLayout(new BoxLayout(transactionsContainer, BoxLayout.X_AXIS));
//		transactionsContainer.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));

		transactionsContainer.setOpaque(false);
		
		panel.add(usernameContainer);
		panel2.add(transactionsContainer);	
		
		// size and location of window elements
		btnDeposit.setBounds(0, 0, 100, 100);
		btnWithdraw.setBounds(0, 0, 100, 100);
		usernameContainer.setBounds(10, 5, 180, 100);
		transactionsContainer.setBounds(150,300,300,100);
		availableLabel.setBounds(200, 160, 200, 50);
		balanceLabel.setBounds(200, 190, 300, 50);
		//		backgroundImage.add(notificationContainer);
		
		backgroundImage.add(usernameContainer);
		backgroundImage.add(availableLabel);
		backgroundImage.add(balanceLabel);
		backgroundImage.add(btnExit);
		backgroundImage.add(transactionsContainer);
		
		//main window additions
		setIconImage(symbol);
		add(backgroundImage);
		add(panel2);
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("LoggedIn");

		

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
		btnDeposit.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnValue = 0;
				String inputPhoneNumber = JOptionPane.showInputDialog("Please enter the phone number to transfer the money to");
				
				if (inputPhoneNumber.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Wrong input", "Enter an actual phone number", JOptionPane.ERROR_MESSAGE);
					
				} else {
					String inputAmount = JOptionPane.showInputDialog("Please enter amount to transfer");
					
					if (inputAmount.length() <= 0) {
						JOptionPane.showMessageDialog(null, "alert", "Enter the correct amount", JOptionPane.ERROR_MESSAGE);
						inputAmount = "0";
					} else {
						ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
						AccountService accntService = (AccountService) appconfig.getBean(AccountService .class);
						returnValue =  accntService.transferMoney(inputPhoneNumber, phoneNumberString, inputAmount);
						
						if (returnValue == -1) {
							JOptionPane.showMessageDialog(null, "That number does not exist",  "Wrong information", JOptionPane.ERROR_MESSAGE);
							returnValue=0;
							
						} else if(returnValue > 0) {
							JOptionPane.showMessageDialog(null, String.format("You transferred successfully $%s", inputAmount),  "Done", JOptionPane.INFORMATION_MESSAGE);							
							returnValue=0;
							balanceLabel.setText(String.format("$%s",accntService.returnBalance(phoneNumberString)));
						} else {
							JOptionPane.showMessageDialog(null, "Your have insufficient funds" , "Error", JOptionPane.ERROR_MESSAGE);
							returnValue=0;
						}
						((ConfigurableApplicationContext)appconfig).close();
					}
				}
			}
		});
		
		
		btnWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
				UserService usrtService = (UserService) appconfig.getBean(UserService .class);
				AccountService accntService = (AccountService) appconfig.getBean(AccountService .class);
				
		
				String sendConfirmationToWithdraw = usrtService.sendConfirmationSMS(phoneNumberString);
				System.out.println("este es el numero "+ sendConfirmationToWithdraw);
				
				if(!sendConfirmationToWithdraw.equals("0")) {
					String inputConfirmationNumber = JOptionPane.showInputDialog("Please enter the number you were sent to your phone");
					
					if(inputConfirmationNumber.equals(sendConfirmationToWithdraw)) {
						System.out.println("Same number");
						String inputConfirmationAmount = JOptionPane.showInputDialog("Please enter the amount to withdraw");
						
						if (accntService.checkWithdrawalAmount(inputConfirmationAmount, phoneNumberString) > 0) {
							JOptionPane.showMessageDialog(null, String.format("You withdrew successfully $%s", inputConfirmationAmount),  "Withdrawn", JOptionPane.INFORMATION_MESSAGE);
							balanceLabel.setText(String.format("$%s",accntService.returnBalance(phoneNumberString)));
						}
					} else {
						System.out.println("not the code you were sent");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "That number does not match",  "Wrong number entered", JOptionPane.ERROR_MESSAGE);
				}
				
				
				((ConfigurableApplicationContext)appconfig).close();
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
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
