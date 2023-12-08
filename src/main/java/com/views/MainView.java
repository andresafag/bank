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
		icon = new ImageIcon(this.getClass().getResource("../../download.png"));
		JLabel backgroundImage = new JLabel(icon);
		backgroundImage.setSize(500, 600);
		
		
		
		
		JPanel panel = new JPanel();
		JPanel panelChild = new JPanel();
		
		JButton btnToLogin = new JButton("Login");
		JButton btnToRegister = new JButton("Register");
		panelChild.add(btnToRegister);
		panelChild.add(btnToLogin);
		panelChild.setBorder(BorderFactory.createTitledBorder("Welcome"));
		panelChild.setLocation(500, 500);
		
		
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
		

	     
		
		add(backgroundImage);
		add(panel);
		panel.add(panelChild);
		setSize(500, 600);
		setVisible(true);
		setTitle("MainView");
		setLocationRelativeTo(null);
		setResizable(false);
//		panelChild.setSize(200, 100);
		backgroundImage.add(panelChild);
	}
	
}
