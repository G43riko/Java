package game.gui.windows;

import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.engine.physics.Enviroment;

public class PhysicsWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	private JSlider speed;
	private JSlider gravity;
	private JPanel parent;
	
	//ACTIONS
	
	private ChangeListener changeGravity = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			Enviroment.GRAVITY.setY(-((float)((JSlider)e.getSource()).getValue()-100)/333.333f);
		}
	};
	
	private ChangeListener changeSpeed = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			Enviroment.SPEED = (float)(((JSlider)e.getSource()).getValue())/100;
		}
	};
	
	//CONSTRUCTORS
	
	public PhysicsWindow(JPanel parent){
		this.parent = parent;
		setPreferredSize(parent.getPreferredSize());
		
		createGravitySlider();
		createSpeedSlider();
	}
	
	//CREATORS
	
	private void createGravitySlider(){
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(0,new JLabel("-1.0"));
		labelTable.put(50,new JLabel("-0.5"));
		labelTable.put(100,new JLabel("0.0"));
		labelTable.put(150,new JLabel("0.5"));
		labelTable.put(200,new JLabel("1.0"));
		
		gravity = new JSlider(JSlider.HORIZONTAL, 0, 200, 200);
		gravity.setMajorTickSpacing(50);
		gravity.setMinorTickSpacing(0);
		gravity.setPaintTicks(true);
		gravity.setPaintLabels(true);
		gravity.setPreferredSize(new Dimension(parent.getPreferredSize().width-30,50));
		gravity.setLabelTable(labelTable);
		gravity.addChangeListener(changeGravity);
		add(new JLabel("gravity"));
		add(gravity);
	}
	
	private void createSpeedSlider(){
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(0,new JLabel("0.0"));
		labelTable.put(50,new JLabel("0.5"));
		labelTable.put(100,new JLabel("1.0"));
		labelTable.put(150,new JLabel("1.5"));
		labelTable.put(200,new JLabel("2.0"));
		
		speed = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
		speed.setMajorTickSpacing(50);
		speed.setMinorTickSpacing(0);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		speed.setPreferredSize(new Dimension(parent.getPreferredSize().width-30,50));
		speed.setLabelTable(labelTable);
		speed.addChangeListener(changeSpeed);
		add(new JLabel("speed"));
		add(speed);
	}
}
