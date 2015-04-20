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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JSlider slider;
	private JLabel label;
	private Color backgroundColor;
	private String value,labelText;
	private JPanel top;
	private int maxValue = 1000, minValue = 0;
	private int width, height;
	
	public Slider(String text, int maxValue,int actValue){
		value = String.valueOf(actValue);
		labelText = text;
		this.maxValue = maxValue;
		backgroundColor = Color.red;
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
		label.setText(labelText);
		label.setPreferredSize(new Dimension(width/2,height/3));
		top.add(label);
	}
	
	private void initField(){
		text = new JFormattedTextField(NumberFormat.getNumberInstance());
		text.setText(value);
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
		slider = new JSlider(JSlider.HORIZONTAL,minValue,maxValue, Integer.parseInt(value));
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

	public void setDefaultValue(int i) {
		slider.setValue(i);
		text.setText(String.valueOf(i));
	}
}
