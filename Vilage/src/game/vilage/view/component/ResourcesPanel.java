package game.vilage.view.component;

import game.vilage.buldings.BasicBuilding;
import game.vilage.resources.ResourceBase;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class ResourcesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OtherResourceSelector> res = new ArrayList<OtherResourceSelector>();
	
	//CONSTRUCTORS
	
	public ResourcesPanel(BasicBuilding basicBuilding){
		init();
		
		ResourceBase resources = basicBuilding.getResources();
		
		for(Entry<Byte, Integer> e : resources.getAll().entrySet()){
			int need = 0;
			if(resources.getRequired().containsKey(e.getKey()))
				need = resources.getRequired().get(e.getKey());
			
			int have = 0;
			if(resources.getOwned().containsKey(e.getKey())){
				have = resources.getOwned().get(e.getKey());
			}
			OtherResourceSelector ors = new OtherResourceSelector(e.getKey(),need, have, basicBuilding);
			add(ors);
			res.add(ors);
		}
	}
	
	//OTHERS
	
	private void init(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(40,40));
		setVisible(true);
	}
	
	public void upateResources() {
		for(OtherResourceSelector ors : res){
			ors.updateValue();
		}
	}

}
