package com.trainsystem.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.trainsystem.backend.entity.User;
import com.trainsystem.service.UserService;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;
   
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					// Get the screen dimensions
			        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			        int screenWidth = screenSize.width;
			        int screenHeight = screenSize.height;
			        
			        // Calculate the position of the frame
			        int frameWidth = frame.getWidth();
			        int frameHeight = frame.getHeight();
			        int x = (screenWidth - frameWidth) / 2;
			        int y = (screenHeight - frameHeight) / 2;
			        
			        // Set the position of the frame
			        frame.setLocation(x, y);
			        
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 450, 272);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setToolTipText("");
		tfUsername.setBounds(180, 103, 166, 38);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(180, 153, 166, 38);
		panel.add(tfPassword);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(102, 114, 66, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(108, 164, 64, 16);
		panel.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			String username=tfUsername.getText();
			String password=tfPassword.getText().toString();
			
				if(username.isEmpty() || password.isEmpty()) {
		            JOptionPane.showMessageDialog(btnLogin, "Please fill in all the text fields.");
				}else {
					UserService userService = new UserService();
					User user = userService.finUserByNameAndSurname(username, password);
					JFrame parentFrame = new JFrame();
			        parentFrame.setSize(300, 200);
			        
			        // Show a message dialog with a message, title, and message type
			        JOptionPane.showMessageDialog(parentFrame,
			                user.toString(),
			                "User logged in successfully!!!",
			                JOptionPane.INFORMATION_MESSAGE);
			        
			        // Close the parent frame
			        parentFrame.dispose();
				}
			
			}
		});
		btnLogin.setForeground(new Color(0, 0, 51));
		btnLogin.setBounds(261, 203, 85, 29);
		panel.add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(0, 0, 51));
		btnSignUp.setBounds(180, 203, 85, 29);
		panel.add(btnSignUp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 450, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setForeground(new Color(255, 250, 250));
		lblNewLabel_2.setFont(new Font("PT Serif", Font.BOLD, 26));
		lblNewLabel_2.setBounds(6, 6, 228, 49);
		panel_1.add(lblNewLabel_2);
	}
}
