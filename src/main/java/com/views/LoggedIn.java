package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.geom.RoundRectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.validation.DefaultMessageCodesResolver.Format;


@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
	private ImageIcon icon;
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
		
		String name = (String) param.get("username");
		Long balanceNumber = (Long) param.get("balance");
		String balanceString = String.valueOf(balanceNumber);
		
//		Iterator item = param.keySet().iterator();
//		while(item.hasNext()){
//		  Object key = item.next();
//		  System.out.println("Clave: " + key + " -> Valor: " + param.get(key));
//		}
		
		
		// Set the background
		icon = new ImageIcon(this.getClass().getResource("../../bank-clipart-cartoon-10.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		
		//Box containing the username items
		JPanel usernameContainer = new JPanel();
		JButton btnUser = new JButton("icon del muñeco");
		btnUser.setSize(100, 50);
		JLabel UsrnameLabel =  new JLabel(name);
		usernameContainer.add(btnUser);
		usernameContainer.add(UsrnameLabel);
		usernameContainer.setSize(200, 50);
		usernameContainer.setBounds(20, 1, 150, 50);
		
		//Box containing the notifications
		JPanel notificationContainer = new JPanel();
		JButton btnNotification = new JButton("campana icono");
		btnNotification.setSize(50, 50);
		notificationContainer.add(btnNotification);
		notificationContainer.setSize(150, 50);
		notificationContainer.setBounds(370, 1, 100, 50);

		
		//Box showing the money available
		JPanel balanceContainer = new JPanel();
		JLabel balanceLabel = new JLabel(String.format("$ %s",balanceString));
//		balanceContainer.add(balanceLabel);
//		balanceContainer.setBounds(200, 170, 50, 50);
		JLabel availableLabel = new JLabel("Available");	
		availableLabel.setBounds(200, 160, 100, 50);
		balanceLabel.setBounds(210, 170, 50, 50);
		
		//Explore button
		JButton btnExplore = new JButton("Explore");	
		btnExplore.setBounds(350, 250, 100, 50);
		
		
		add(backgroundImage);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("LoggedIn");
		backgroundImage.add(usernameContainer);
		backgroundImage.add(notificationContainer);
		backgroundImage.add(availableLabel);
		backgroundImage.add(balanceLabel);
		backgroundImage.add(btnExplore);
		
	}
}
