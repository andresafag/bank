package com.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	private ImageIcon icon;
	private JPanel panel = new JPanel();
	private JPanel panelChild = new JPanel();
	private JButton btnToLogin = new JButton("Login");
	private JButton btnToRegister = new JButton("Register");
	private JButton btnExit = new JButton("EXIT");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainView () {
		icon = new ImageIcon(this.getClass().getResource("../../money-sign-pictures-gj4uo9l9ql4duys6.jpg"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		panelChild.add(btnToRegister);
		panelChild.add(btnToLogin);
		panelChild.setBorder(BorderFactory.createTitledBorder("Welcome"));
		panel.setSize(200, 100);
		panel.setLocation(150, 200);
//		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setOpaque(false);
		panelChild.setOpaque(false);
		btnExit.setBounds(10, 10, 60, 50);
		
		add(backgroundImage);
		panel.add(panelChild);
		setSize(500, 600);
		setVisible(true);
		setTitle("MainView");
		setLocationRelativeTo(null);
		setResizable(false);
		backgroundImage.add(panel);
		backgroundImage.add(btnExit);
		
		
		//Events --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		btnToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
		});
		
		btnToRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Registration();
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		

	   
	}
	
}
