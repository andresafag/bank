package com.views;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Registration {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Welcome User");
		
		//Panel element
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		
		//Container frame
		frame.add(panel);
		frame.setSize(560, 650);
		frame.setVisible(true);
		frame.setLayout(null);
		}
}
