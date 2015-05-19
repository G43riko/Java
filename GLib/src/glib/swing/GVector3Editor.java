package glib.swing;

import glib.util.vector.GVector3f;

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
	
	private Dimension size = new Dimension(300, 40);
	
	//CONSTRUCTORS
	
	public GVector3Editor(String name,float maxVal, float minVal, GVector3f values){
		this(name, maxVal, minVal, values.getX(), values.getY(), values.getZ());
	}
	
	public GVector3Editor(String name,float maxVal, float minVal, float xx, float yy, float zz){
//		this.name = name;
		this.maxVal = maxVal;
		this.minVal = minVal;
		
		setPreferredSize(size);
		setLayout(new FlowLayout());
		
		add(new JLabel(name));
		
		setSpinners(xx,yy,zz);
	}
	
	//OTHERS
	
	public void addChangeListener(ChangeListener c){
		x.addChangeListener(c);
		y.addChangeListener(c);
		z.addChangeListener(c);
	}
	
	//GETTERS

	public float getValX() {return Float.valueOf(x.getValue().toString());}

	public float getValY() {return Float.valueOf(y.getValue().toString());}

	public float getValZ() {return Float.valueOf(z.getValue().toString());}
	
	public GVector3f getValues(){return new GVector3f(getValX(), getValY(), getValZ());}
	
	//SETTERS

	private void setSpinners(float xx, float yy, float zz){
		Dimension size = new Dimension(40,20);
		
		float average = (maxVal - minVal) / 100;
		
		x = new JSpinner(new SpinnerNumberModel(xx,minVal,maxVal,average));
		x.setPreferredSize(size);
		add(x);
		
		y = new JSpinner(new SpinnerNumberModel(yy,minVal,maxVal,average));
		y.setPreferredSize(size);
		add(y);
		
		z = new JSpinner(new SpinnerNumberModel(zz,minVal,maxVal,average));
		z.setPreferredSize(size);
		add(z);
	}
	
	public void setSize(Dimension size){
		setPreferredSize(size);
	}
	
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
