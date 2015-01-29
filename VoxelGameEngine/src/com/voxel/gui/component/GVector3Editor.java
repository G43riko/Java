package com.voxel.gui.component;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class GVector3Editor extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner x, y, z;
//	private String name;
	private float maxVal;
	private float minVal;
	
	public GVector3Editor(String name,float maxVal, float xx, float yy, float zz){
//		this.name = name;
		this.maxVal = maxVal;
		this.minVal = 0;
		
		setPreferredSize(new Dimension(200,40));
		setLayout(new FlowLayout());
		
		add(new JLabel(name));
		
		setSpinners(xx,yy,zz);
	}
	
	public void addChangeListener(ChangeListener c){
		x.addChangeListener(c);
		y.addChangeListener(c);
		z.addChangeListener(c);
	}
	
	private void setSpinners(float xx, float yy, float zz){
		Dimension size = new Dimension(40,20);
		
		x = new JSpinner(new SpinnerNumberModel(xx,minVal,maxVal,maxVal/51));
		x.setPreferredSize(size);
		add(x);
		
		y = new JSpinner(new SpinnerNumberModel(yy,minVal,255,maxVal/51));
		y.setPreferredSize(size);
		add(y);
		
		z = new JSpinner(new SpinnerNumberModel(zz,minVal,255,maxVal/51));
		z.setPreferredSize(size);
		add(z);
	}

	public float getValX() {return Float.valueOf(x.getValue().toString());}

	public float getValY() {return Float.valueOf(y.getValue().toString());}

	public float getValZ() {return Float.valueOf(z.getValue().toString());}
	
	public void setValX(String val){
		setValX(Float.parseFloat(val));
	}
	
	public void setValY(String val){
		setValY(Float.parseFloat(val));
	}
	
	public void setValZ(String val){
		setValZ(Float.parseFloat(val));
	}
	
	public void setValX(double val){
		if(val<=maxVal && val>=minVal)
			x.setValue(val);
	}
	
	public void setValY(double val){
		if(val<=maxVal && val>=minVal)
			y.setValue(val);
	}
	
	public void setValZ(double val){
		if(val<=maxVal && val>=minVal)
			z.setValue(val);
	}
}
