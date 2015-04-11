package game.vilage.view.component;

import game.vilage.buldings.BasicBuilding;
import game.vilage.resources.ResourceBase;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class ResourcesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private HashMap<Byte, OtherResourceViewer> res = new HashMap<Byte, OtherResourceViewer>(); 
	private BasicBuilding parent;
	
	//CONSTRUCTORS
	
	public ResourcesPanel(BasicBuilding basicBuilding){
		this.parent = basicBuilding;
		init();
		upateResources();
	}
	
	//OTHERS
	
	private void init(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(40,40));
		setVisible(true);
	}
	
	public void upateResources() {
		ResourceBase resources = parent.getResources();
		
		for(Entry<Byte, Integer> e : resources.getAll().entrySet()){	//prejde všetkými surovinamy
			if(res.containsKey(e.getKey()))	//ak už je vypísaná
				res.get(e.getKey()).updateValue();	//aktualizuje ju to
			else{	//ináè
				int need = resources.getRequired(e.getKey());
				int have = resources.getOwned(e.getKey());
				
				res.put(e.getKey(),new OtherResourceViewer(e.getKey(),need, have, parent));	//vytvorí nový viewer a pridá ho do zoznamu viewerov
				add(res.get(e.getKey()));	//aj do panelu
			}
		}
	}

}
