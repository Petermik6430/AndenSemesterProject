package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameMainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMainMenu frame = new JFrameMainMenu();
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
	public JFrameMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Salg");
		btnNewButton.setBounds(0, 209, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBooking = new JButton("Booking");
		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opensBooking();
			}
		});
		btnBooking.setBounds(0, 238, 89, 23);
		contentPane.add(btnBooking);
		
		JButton btnKalender = new JButton("Kalender");
		btnKalender.setBounds(99, 209, 89, 23);
		contentPane.add(btnKalender);
		
		JButton btnAdmin = new JButton("admin");
		btnAdmin.setBounds(345, 238, 89, 23);
		contentPane.add(btnAdmin);
		
		JLabel lblBarberLogo = new JLabel("");
		lblBarberLogo.setIcon(new ImageIcon(JFrameMainMenu.class.getResource("/BarberArkji.jpg")));
		lblBarberLogo.setBounds(-14, 0, 459, 206);
		contentPane.add(lblBarberLogo);
	}
	
	
	private void opensBooking() {
		JDialogBooking jdab= new JDialogBooking();
		jdab.setDefaultCloseOperation(jdab.DISPOSE_ON_CLOSE);
		jdab.setVisible(true);
		
	}

}
