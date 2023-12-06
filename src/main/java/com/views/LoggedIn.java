package com.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
	private ImageIcon icon;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedIn frame = new LoggedIn("","","");
					frame.setTitle("LoggedIn");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoggedIn(String name, String phonenumber, String balance) {
		icon = new ImageIcon(this.getClass().getResource("../../bank-clipart-cartoon-10.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		add(backgroundImage);
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("LoggedIn");
	}
}
