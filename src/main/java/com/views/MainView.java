package com.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setTitle("MainView");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainView () {
		JPanel panel = new JPanel();
		JButton btnToLogin = new JButton("Login");
		JButton btnToRegister = new JButton("Register");
		panel.add(btnToRegister);
		panel.add(btnToLogin);
		panel.setBorder(BorderFactory.createTitledBorder("Welcome"));	
		
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
		
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
}
