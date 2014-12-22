package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class TwoFields extends JPanel {
	private JSpinner variation;
	private JSpinner value;
	private JLabel title;
	private JLabel help;
	
	public TwoFields(String name,int defaultVal,int maxVal){
		int width = 175;
		int height = 40;
		
		setLayout(new FlowLayout());
		setBackground(Color.red);
		setPreferredSize(new Dimension(width,height));
		
		title = new JLabel();
		title.setText(name);
		add(title);
		
		value = new JSpinner(new SpinnerNumberModel(defaultVal,0,maxVal,1));;
		value.setPreferredSize(new Dimension(40,20));
		add(value);
		
		title = new JLabel();
		title.setText("var");
		add(title);
		
		variation = new JSpinner(new SpinnerNumberModel(50,0,100,5));
		variation.setPreferredSize(new Dimension(40,20));
		add(variation);
	}
	
	public float getValue(){
		float rand = (float)(int)value.getValue()/100*(int)variation.getValue();
		return (int)value.getValue()+(rand*2*(float)Math.random()-rand);
	}
	
}
