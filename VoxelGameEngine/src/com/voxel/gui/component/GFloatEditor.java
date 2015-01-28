package com.voxel.gui.component;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GFloatEditor extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner value;
//	private float maxVal;
	public GFloatEditor(String name,float maxVal,float step,float normal){
//		this.maxVal = maxVal;
		
		setPreferredSize(new Dimension(120,40));
		setLayout(new FlowLayout());
		
		add(new JLabel(name));
		
		Dimension size = new Dimension(50,20);
		value = new JSpinner(new SpinnerNumberModel(normal,0,maxVal,step));
		value.setPreferredSize(size);
		add(value);
	}
	public float getValue() {
		return Float.valueOf(value.getValue().toString());
	}
}
