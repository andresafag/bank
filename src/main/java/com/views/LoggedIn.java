package com.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedIn frame = new LoggedIn();
					frame.setTitle("LoggedIn");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoggedIn() {
			
			//Panel element
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel label =  new JLabel("pacha");
			
			
			add(panel);
			panel.add(label);
			setSize(500, 600);
			setVisible(true);
			setLocationRelativeTo(null);
			setTitle("LoggedIn");
	}
}
