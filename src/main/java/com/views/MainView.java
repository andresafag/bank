package com.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
//	private ImageIcon icon;
	private JPanel panel = new JPanel();
	private JPanel panelChild = new JPanel();
	private JButton btnToLogin = new JButton("Login");
	private JButton btnToRegister = new JButton("Register");
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("../../money-sign-pictures-gj4uo9l9ql4duys6.jpg"));
	private ImageIcon iconBtn = new ImageIcon(getClass().getResource("../../exit.png"));
	private JLabel backgroundImage = new JLabel(icon);
	private TitledBorder title;
	private Image img = iconBtn.getImage();
	private JButton btnExit = new JButton();
	
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
	
	public MainView (){
		setUndecorated(true);
		btnToLogin.setFont(new Font("Arial", Font.BOLD, 15));
		btnToRegister.setFont(new Font("Arial", Font.BOLD, 15));
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setBorderPainted(false);
		btnToLogin.setOpaque(false);
		btnToLogin.setBorderPainted(false);
		btnToLogin.setContentAreaFilled(false);
		btnToRegister.setOpaque(false);
		btnToRegister.setBorderPainted(false);
		btnToRegister.setContentAreaFilled(false);
		
	
		backgroundImage.setSize(500, 600);

		title = BorderFactory.createTitledBorder("Welcome");
		
		title.setTitleFont(new Font("Arial", Font.BOLD, 30));
		title.setTitleJustification(TitledBorder.CENTER);
		Color colorTitle = new Color(10, 100, 80); 
		title.setTitleColor(colorTitle);
		
		panelChild.add(btnToRegister);
		panelChild.add(btnToLogin);
		panelChild.setBorder(title);
		panel.setSize(200, 100);
		panel.setLocation(150, 200);
		panel.setOpaque(false);
		panelChild.setOpaque(false);
		btnExit.setBounds(20, 20, 60, 60);
		
		
		Image newImg = img.getScaledInstance(btnExit.getWidth(), btnExit.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        btnExit.setIcon(newImc);
    
		
		
		add(backgroundImage);
		panel.add(panelChild);
		backgroundImage.add(panel);
		backgroundImage.add(btnExit);
		setSize(500, 600);
		setVisible(true);
		setTitle("MainView");
		setLocationRelativeTo(null);
		
		
		
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
