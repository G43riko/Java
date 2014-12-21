package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JPanel{
	private JTextField text;
	private JSlider slider;
	private JLabel label;
	private Color backgroundColor;
	private String value;
	private JPanel top;
	private int maxValue, minValue;
	private int width, height;
	
	public Slider(){
		maxValue = 1000;
		minValue = 0;
		backgroundColor = Color.green;
		width = 175;
		height = 80;
		setLayout(new BorderLayout());
		
		initTop();
		initLabel();
		initField();
		initSlider();
	}
	
	private void initTop(){
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setPreferredSize(new Dimension(width,height/3));
		top.setBackground(backgroundColor);
		add(top,BorderLayout.NORTH);
	}
	
	private void initLabel(){
		label = new JLabel();
		label.setText("nepriatelov");
		label.setPreferredSize(new Dimension(width/2,height/3));
		top.add(label);
	}
	
	private void initField(){
		text = new JFormattedTextField(NumberFormat.getNumberInstance());
		text.setText(String.valueOf(maxValue/2));
		text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){
					slider.setValue(Integer.valueOf(text.getText()));
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		text.setPreferredSize(new Dimension(width/5,height/4));
		top.add(text);
	}
	
	private void initSlider(){
		slider = new JSlider(JSlider.HORIZONTAL,minValue,maxValue, maxValue/2);
		slider.setPreferredSize(new Dimension(width,height/2));
		slider.setBackground(backgroundColor);
		slider.setMajorTickSpacing(maxValue);
		slider.setMinorTickSpacing(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				text.setText(String.valueOf(slider.getValue()));
			}
		});
		add(slider,BorderLayout.SOUTH);
	}
	
	public int getValue(){
		return Integer.valueOf(text.getText());
	}
}
