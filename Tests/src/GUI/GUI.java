package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		GUI gui = new GUI();
	}

	public GUI(){
		
		JPanel contentPane = new JPanel();
		JPanel hLista = new JPanel();
		JPanel dLista = new JPanel();
		JTextField text = new JTextField("/"); 
		JButton hladaj = new JButton("Hladaj");
		JLabel vypis = new JLabel("tu bude obsah prieèinka");
		
		this.setTitle("Moje prvá Gui Aplikácia");
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(GUI.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowsListener(this));
		
		this.add(contentPane);contentPane.setLayout(new BorderLayout());
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(hLista, BorderLayout.NORTH);
		contentPane.add(dLista, BorderLayout.CENTER);
		
		text.setColumns(63);
		
		hLista.setLayout(new BorderLayout());
		hLista.add(hladaj,BorderLayout.EAST);
		hLista.add(text,BorderLayout.WEST);

		//dLista.setLayout(new BorderLayout());
		dLista.add(vypis/*, BorderLayout.NORTH*/);
		hladaj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String obsah="";
				for(int i=0 ; i< 50 ; i++){
					obsah=obsah+"malalalalal \n\r";
				}
				vypis.setText(obsah);
			}
		});
		
	}
}
