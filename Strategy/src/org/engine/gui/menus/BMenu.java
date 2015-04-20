package org.engine.gui.menus;

import glib.util.vector.GVector3f;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.MainStrategy;
import org.engine.component.GameComponent;
import org.engine.light.PointLight;

public class BMenu extends JPanel{
	private static final long serialVersionUID = 1L;

	private final static int height = MainStrategy.HEIGHT/7;
	private HashMap<String, JSpinner> values = new HashMap<String, JSpinner>();
	private GameComponent selectedComponent;
	private boolean ready;
	
	//ACTIONS
	
	private ChangeListener changeValueEvent = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			if(ready){
				
				float posX = 0;
				if(values.get("POS_X").getValue() instanceof Float )
					posX = (float)values.get("POS_X").getValue();
				if(values.get("POS_X").getValue() instanceof Double )
					posX = (float)(double)values.get("POS_X").getValue();
				
				float posY = 0;
				if(values.get("POS_Y").getValue() instanceof Float )
					posY = (float)values.get("POS_Y").getValue();
				if(values.get("POS_Y").getValue() instanceof Double )
					posY = (float)(double)values.get("POS_Y").getValue();
				
				float posZ = 0;
				if(values.get("POS_Z").getValue() instanceof Float )
					posZ = (float)values.get("POS_Z").getValue();
				if(values.get("POS_Z").getValue() instanceof Double )
					posZ = (float)(double)values.get("POS_Z").getValue();
				
				selectedComponent.setPosition(new GVector3f(posX, posY, posZ));
				
				
				float rotX = 0;
				if(values.get("ROT_X").getValue() instanceof Float )
					rotX = (float)values.get("ROT_X").getValue();
				if(values.get("ROT_X").getValue() instanceof Double )
					rotX = (float)(double)values.get("ROT_X").getValue();

				float rotY = 0;
				if(values.get("ROT_Y").getValue() instanceof Float )
					rotY = (float)values.get("ROT_Y").getValue();
				if(values.get("ROT_Y").getValue() instanceof Double )
					rotY = (float)(double)values.get("ROT_Y").getValue();

				float rotZ = 0;
				if(values.get("ROT_Z").getValue() instanceof Float )
					rotZ = (float)values.get("ROT_Z").getValue();
				if(values.get("ROT_Z").getValue() instanceof Double )
					rotZ = (float)(double)values.get("ROT_Z").getValue();
				
				selectedComponent.setRotation(new GVector3f(rotX, rotY, rotZ));
			}
		}
	};
	
	
	
	private ChangeListener changeAtten = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			if(ready && selectedComponent instanceof PointLight){
				PointLight light = (PointLight)selectedComponent;
				
				light.setAttenuation(new GVector3f());
			}
		}
	};
	
	//CONSTRUCTORS
	
	public BMenu(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(height,height));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
		
	}
	
	//OTHERS
	
	private JPanel createSelector(String name, float value){
		JPanel panel = new JPanel();
		panel.add(new JLabel(name));
		JSpinner spinner = new JSpinner(new SpinnerNumberModel((float)value,-1000,1000,1));
		spinner.addChangeListener(changeValueEvent);
		values.put(name, spinner);
		panel.add(values.get(name));
		return panel;
	}

	public void update(){
		if(selectedComponent != null && !values.isEmpty()){
			try{
				values.get("POS_X").setValue(selectedComponent.getPosition().getX());
				values.get("POS_Y").setValue(selectedComponent.getPosition().getY());
				values.get("POS_Z").setValue(selectedComponent.getPosition().getZ());
				
				values.get("ROT_X").setValue(selectedComponent.getRotation().getX());
				values.get("ROT_Y").setValue(selectedComponent.getRotation().getY());
				values.get("ROT_Z").setValue(selectedComponent.getRotation().getZ());
				
				values.get("SCALE_X").setValue(selectedComponent.getScale().getX());
				values.get("SCALE_Y").setValue(selectedComponent.getScale().getY());
				values.get("SCALE_Z").setValue(selectedComponent.getScale().getZ());
			}
			catch(NullPointerException e){
				update();
			}
			
			updateUI();
		}
	}
	
	private void addVector(int line, String name, JPanel addTo, GVector3f vector){
		GridBagConstraints c;
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = line;
		c.gridy = 0;
		addTo.add(createSelector(name+"_X",vector.getX()),c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = line;
		c.gridy = 1;
		addTo.add(createSelector(name+"_Y",vector.getY()),c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = line;
		c.gridy = 2;
		addTo.add(createSelector(name+"_Z",vector.getZ()),c);
	}
	
	public void addSelectComponent(GameComponent component) {
		ready = false;
		selectedComponent = component;
		removeAll();
		values.clear();
		if(component != null){
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			
			addVector(0,"POS",panel,component.getPosition());
			addVector(1,"ROT",panel,component.getRotation());
			addVector(2,"SCALE",panel,component.getScale());
			add(panel,BorderLayout.WEST);
		}
		
		updateUI();
		ready = true;
	}

	//PANELS
	
	public JPanel createPointLightPanel(PointLight light){
		JPanel panel = new JPanel();
		
		JSpinner atten1 = new JSpinner(new SpinnerNumberModel(light.getAttenuation().getX(),-1000,1000,1));
		atten1.addChangeListener(changeAtten);
		JSpinner atten2 = new JSpinner(new SpinnerNumberModel(light.getAttenuation().getY(),-1000,1000,1));
		atten2.addChangeListener(changeAtten);
		JSpinner atten3 = new JSpinner(new SpinnerNumberModel(light.getAttenuation().getZ(),-1000,1000,1));
		atten3.addChangeListener(changeAtten);
		
		return panel;
	}
}
