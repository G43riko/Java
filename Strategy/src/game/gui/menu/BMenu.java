package game.gui.menu;

import game.component.GameComponent;
import game.main.MainStrategy;
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

public class BMenu extends JPanel{
	private static final long serialVersionUID = 1L;

	private final static int height = MainStrategy.HEIGHT/7;
	private HashMap<String, JSpinner> values = new HashMap<String, JSpinner>();
	private GameComponent selectedComponent;
	
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
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(value,-1000,1000,1));
		values.put(name, spinner);
		panel.add(values.get(name));
		return panel;
	}

	public void update(){
		if(selectedComponent != null){
			values.get("POS_X").setValue(selectedComponent.getPosition().getX());
			values.get("POS_Y").setValue(selectedComponent.getPosition().getY());
			values.get("POS_Z").setValue(selectedComponent.getPosition().getZ());
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
		selectedComponent = component;
//		System.out.println("selecte sa nastavil na: "+component+" == "+selectedComponent);
		removeAll();
//		values.clear();
		if(component != null){
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			
			addVector(0,"POS",panel,component.getPosition());
			addVector(1,"ROT",panel,component.getRotation());
			addVector(2,"SCALE",panel,component.getScale());
//			
			add(panel,BorderLayout.WEST);
		}
		
		System.out.println(values);
		updateUI();
	}
}
