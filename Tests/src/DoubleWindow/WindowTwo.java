package DoubleWindow;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WindowTwo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField a;
	public JTextField b;
	private WindowOne j;
	
	public WindowTwo(WindowOne j){
		this.j = j;
		setTitle("Okno1");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		setLocation(410, 0);
		
		a = new JTextField();
		a.setText("3");
		add(a);
		
		b = new JTextField();
		b.setText("4");
		add(b);
		
		JButton but = new JButton();
		but.setText("Vypoèítaj");
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				j.vypocitajObsah(a.getText(), b.getText());
			}
		});
		add(but);
		
		setVisible(true);
	}
}
