package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SetUp extends JFrame {

	private JPanel contentPane;
	public JTextField textField_IP,textField_PORT,textField_LOGIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetUp frame = new SetUp();
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
	public SetUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblIpport = new JLabel("IP/PORT:");
		panel_1.add(lblIpport);
		
		textField_IP = new JTextField();
		panel_1.add(textField_IP);
		textField_IP.setColumns(12);
		
		textField_PORT = new JTextField();
		panel_1.add(textField_PORT);
		textField_PORT.setColumns(4);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblLogin = new JLabel("LOGIN:");
		panel_2.add(lblLogin);
		
		textField_LOGIN = new JTextField();
		panel_2.add(textField_LOGIN);
		textField_LOGIN.setColumns(16);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				
			}
		});
		panel_3.add(btnLogin);
		
		JButton btnCencel = new JButton("CENCEL");
		btnCencel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		panel_3.add(btnCencel);
	}

}
