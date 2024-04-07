package com.trainsystem.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trainsystem.backend.entity.User;
import com.trainsystem.service.UserService;

public class SingUpForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingUpForm frame = new SingUpForm();
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
	public SingUpForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 453);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 450, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setToolTipText("");
		tfUsername.setBounds(178, 192, 166, 38);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(178, 242, 166, 38);
		panel.add(tfPassword);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(104, 203, 66, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(106, 253, 66, 16);
		panel.add(lblNewLabel_1);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String surname=tfSurname.getText();
				String username=tfUsername.getText();
				String password=tfPassword.getText().toString();
				
					if(username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
			            JOptionPane.showMessageDialog(btnSignUp, "Please fill in all the text fields.");
					}else {
						UserService userService = new UserService();
						User user = userService.createUser(username, password,name,surname);
						JFrame parentFrame = new JFrame();
						parentFrame.setSize(300, 200);
				        
				        // Show a message dialog with a message, title, and message type
				        JOptionPane.showMessageDialog(parentFrame,
				                user.toString(),
				                "User created successfully!!!",
				                JOptionPane.INFORMATION_MESSAGE);
				        
				        // Close the parent frame
				        parentFrame.dispose();
					}
				
				
			}
		});
		btnSignUp.setForeground(new Color(0, 0, 51));
		btnSignUp.setBounds(176, 292, 85, 29);
		panel.add(btnSignUp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 450, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Sign Up Form");
		lblNewLabel_2.setForeground(new Color(255, 250, 250));
		lblNewLabel_2.setFont(new Font("PT Serif", Font.BOLD, 26));
		lblNewLabel_2.setBounds(6, 6, 228, 49);
		panel_1.add(lblNewLabel_2);
		
		tfName = new JTextField();
		tfName.setToolTipText("");
		tfName.setColumns(10);
		tfName.setBounds(178, 92, 166, 38);
		panel.add(tfName);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(255, 250, 250));
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblName.setBounds(130, 103, 40, 16);
		panel.add(lblName);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(178, 142, 166, 38);
		panel.add(tfSurname);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname:");
		lblNewLabel_1_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1_1.setBounds(112, 153, 58, 16);
		panel.add(lblNewLabel_1_1);
	}

}
