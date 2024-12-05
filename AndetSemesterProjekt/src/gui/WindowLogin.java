package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowLogin extends JFrame {

	
	private JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowLogin window = new WindowLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("\img\\BarberArkji.jpg"));
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/BarberArkji.jpg"));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage((WindowLogin.class.getResource("/BarberArkji.jpg"))));
		frame.setBounds(100, 100, 368, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(200, 110, 118, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAccess();
			}

		
		});
		btnLogin.setBounds(229, 141, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JFormattedTextField frmtdtxtfldUsername = new JFormattedTextField();
		frmtdtxtfldUsername.setText("");
		frmtdtxtfldUsername.setBounds(200, 79, 118, 20);
		frame.getContentPane().add(frmtdtxtfldUsername);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(131, 82, 59, 14);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setBounds(131, 113, 66, 14);
		frame.getContentPane().add(lblPassWord);
		
		JLabel lblBarberLogo = new JLabel("");
		
		lblBarberLogo.setIcon(new ImageIcon(WindowLogin.class.getResource("/BarberArkji.jpg")));
		lblBarberLogo.setBounds(-183, 0, 292, 237);
		frame.getContentPane().add(lblBarberLogo);
		
		JLabel lblNewLabel = new JLabel("BarberArkji");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(153, 11, 189, 57);
		frame.getContentPane().add(lblNewLabel);
	}
	
	private void loginAccess() {
		JFrameMainMenu wMenu = new JFrameMainMenu();
		wMenu.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		wMenu.setVisible(true);
		
	}


}
