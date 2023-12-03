package com.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Registered extends JFrame{
	
	
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
	
	
	public Registered() {
		JPanel panel = new JPanel();
		
		
		add(panel);
		setSize(500, 600);
		setVisible(true);
		setTitle("Registered");
		setLocationRelativeTo(null);
	}
}
