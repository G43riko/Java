package glib.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class GFloatEditor extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner value;
	private float maxVal;
	private float minVal;
	
	public GFloatEditor(String name, float maxVal, float minVal, float step, float defaultValue){
		this.maxVal = maxVal;
		this.minVal = minVal;
		setPreferredSize(new Dimension(120,40));
		setLayout(new FlowLayout());
		
		add(new JLabel(name));
		
		Dimension size = new Dimension(50,20);
		value = new JSpinner(new SpinnerNumberModel(defaultValue, minVal, maxVal, step));
		value.setPreferredSize(size);
		add(value);
		validate();
		
	}
	
	public void addChangeLister(ChangeListener listener){
		value.addChangeListener(listener);
	}
	
	public float getValue() {
		return Float.valueOf(value.getValue().toString());
	}
	
	public void setValue(Object val){
		setValue(Float.parseFloat(val.toString()));
	}
	
	public void setValue(double val){
		if(val<=maxVal && val>=minVal)
			value.setValue(val);
	}
	
	public void addChangeListener(ChangeListener c){
		value.addChangeListener(c);
	}
}
