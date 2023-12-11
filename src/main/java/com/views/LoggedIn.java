package com.views;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bank.esterlinas.AppConfig;
import com.services.bank.AccountService;
import com.services.bank.UserService;


@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
	
	private ImageIcon icon;
	private JPanel usernameContainer = new JPanel();
	private JButton btnUser = new JButton("icon del muñeco");
	private JPanel notificationContainer = new JPanel();
	private JButton btnNotification = new JButton("campana icono");
	private JPanel balanceContainer = new JPanel();
	private JPanel transactionsContainer =  new JPanel();
	private JButton btnDeposit = new JButton("Deposit");
	private JButton btnWithdraw = new JButton("Withdraw");
	private JLabel UsrnameLabel =  new JLabel();
	private JLabel availableLabel = new JLabel("Available");
	
	
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
		
		String usrname = (String) param.get("username");
		Long balanceNumber = (Long) param.get("balance");
		String balanceString = String.valueOf(balanceNumber);
		String name = (String) param.get("name");
		String lastName = (String) param.get("lastname");
		Long phoneNumber = (Long) param.get("phonenumber");
		String phoneNumberString = String.valueOf(phoneNumber);
	
		
		
		
		// Set the background
		icon = new ImageIcon(this.getClass().getResource("../../bank-clipart-cartoon-10.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		
		//Box containing the username items
		usernameContainer.setOpaque(false);
		btnUser.setSize(100, 50);
		UsrnameLabel.setText(usrname);
		Font labelFont = UsrnameLabel.getFont();
		int fontSizeToUse = Math.min(20, 20);
		UsrnameLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		usernameContainer.add(btnUser);
		usernameContainer.add(UsrnameLabel);
		usernameContainer.setSize(200, 50);
		usernameContainer.setBounds(20, 1, 150, 150);
		
		//Box containing the notifications
		notificationContainer.setOpaque(false);
		btnNotification.setSize(50, 50);
		notificationContainer.add(btnNotification);
		notificationContainer.setSize(150, 50);
		notificationContainer.setBounds(370, 1, 100, 50);

		
		//Box showing the money available
		balanceContainer.setOpaque(false);
		JLabel balanceLabel = new JLabel(String.format("$ %s",balanceString));
//		balanceContainer.add(balanceLabel);
//		balanceContainer.setBounds(200, 170, 50, 50);
	
		availableLabel.setBounds(200, 160, 100, 50);
		balanceLabel.setBounds(210, 170, 50, 50);
		
		
		
		//Box container to transactions
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		transactionsContainer.setBorder( BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		transactionsContainer.setSize(200, 200);
		transactionsContainer.setBounds(100, 300, 250, 60);
		transactionsContainer.setOpaque(false);
				
		
		//transactions buttons
		transactionsContainer.add(btnDeposit);
		transactionsContainer.add(btnWithdraw);
		
		
		add(backgroundImage);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("LoggedIn");
		backgroundImage.add(usernameContainer);
		backgroundImage.add(notificationContainer);
		backgroundImage.add(availableLabel);
		backgroundImage.add(balanceLabel);
		backgroundImage.add(transactionsContainer);
		
		
		
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		btnDeposit.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputPhoneNumber = JOptionPane.showInputDialog("Please enter the phone number to transfer the money to");
				
				if (inputPhoneNumber.length() <= 0) {
					JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
					
				} else {
					String inputAmount = JOptionPane.showInputDialog("Please enter amount to transfer");
					
					if (inputAmount.length() <= 0) {
						JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
					} else {
						@SuppressWarnings("resource")
						ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
						AccountService accntService = (AccountService) appconfig.getBean(AccountService .class);
						
						
						if (accntService.transferMoney(inputPhoneNumber, phoneNumberString, inputAmount) == -1) {
							JOptionPane.showMessageDialog(null, "That number does not exist",  "Wrong information", JOptionPane.ERROR_MESSAGE);
						} else if(accntService.transferMoney(inputPhoneNumber, phoneNumberString, inputAmount) > 0) {
							JOptionPane.showMessageDialog(null, "You just transferred %s sucessfully".format(inputAmount),  "Done", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Your have insufficient funds" , "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		
		btnWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Sacar");
			}
		});
		
		
		
		
		
		
		
	}
}
