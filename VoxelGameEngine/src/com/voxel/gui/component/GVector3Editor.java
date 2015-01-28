package com.voxel.gui.component;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GVector3Editor extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner x, y, z;
//	private String name;
	private float maxVal;
	
	public GVector3Editor(String name,float maxVal){
//		this.name = name;
		this.maxVal = maxVal;
		
		setPreferredSize(new Dimension(200,40));
		setLayout(new FlowLayout());
		
		add(new JLabel(name));
		
		setSpinners();
	}
	
	private void setSpinners(){
		Dimension size = new Dimension(40,20);
		
		x = new JSpinner(new SpinnerNumberModel(maxVal/51*25,0,maxVal,maxVal/51));
		x.setPreferredSize(size);
		add(x);
		
		y = new JSpinner(new SpinnerNumberModel(maxVal/51*25,0,255,maxVal/51));
		y.setPreferredSize(size);
		add(y);
		
		z = new JSpinner(new SpinnerNumberModel(maxVal/51*25,0,255,maxVal/51));
		z.setPreferredSize(size);
		add(z);
	}

	public float getValX() {
		return Float.valueOf(x.getValue().toString());
	}

	public float getValY() {
		return Float.valueOf(y.getValue().toString());
	}

	public float getValZ() {
		return Float.valueOf(z.getValue().toString());
	}
}
