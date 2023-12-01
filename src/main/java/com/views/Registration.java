package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Registration extends JFrame{
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setTitle("Registration");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Registration() {
			
			//Panel element
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			add(panel);
			setSize(500, 600);
			setVisible(true);
			setLocationRelativeTo(null);
	}
}
