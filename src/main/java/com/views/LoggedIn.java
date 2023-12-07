package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bank.esterlinas.AppConfig;
import com.services.bank.UserService;

@SuppressWarnings("serial")
public class LoggedIn extends JFrame{
	private ImageIcon icon;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedIn frame = new LoggedIn(null);
					frame.setTitle("LoggedIn");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoggedIn(List<Object> param) {
		
		for (Iterator iterator = param.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		
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
