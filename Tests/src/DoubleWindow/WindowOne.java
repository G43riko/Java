package DoubleWindow;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class WindowOne extends JFrame{
	
	public JTextField pole;
	
	public WindowOne(){
		setTitle("Okno1");
		setSize(400, 400);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		pole = new JTextField();
		pole.setText("tu bude obsah");
		add(pole);
		
		setVisible(true);
	}
	
	public void vypocitajObsah(String a, String b){
		int ai = Integer.valueOf(a);
		int bi = Integer.valueOf(b);
		pole.setText(String.valueOf(ai*bi));
	}
}
