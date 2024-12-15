package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JPasswordField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 107, 94, 20);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(220, 76, 94, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Brugernavn");
		lblUsername.setBounds(146, 79, 64, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Adgangskode");
		lblPassword.setBounds(146, 110, 73, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseCliked();
			}
		});
		btnLogIn.setBounds(225, 141, 89, 23);
		contentPane.add(btnLogIn);
		
		JLabel lblBarberArkji = new JLabel("BarberArkji");
		lblBarberArkji.setForeground(Color.BLACK);
		lblBarberArkji.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblBarberArkji.setBounds(153, 8, 189, 57);
		contentPane.add(lblBarberArkji);
		
		JLabel lblBarberLogo = new JLabel("");
		lblBarberLogo.setIcon(new ImageIcon(MainFrame.class.getResource("/BarberArkji.jpg")));
		lblBarberLogo.setBounds(-158, 0, 292, 237);
		contentPane.add(lblBarberLogo);
	}

	protected void mouseCliked() {
		
		dispose();
		JFrameMainMenu wMenu = new JFrameMainMenu();
		wMenu.setVisible(true);
		
	}
	
	
	

}
