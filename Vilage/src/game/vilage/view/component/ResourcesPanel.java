package game.vilage.view.component;

import game.vilage.buldings.BasicBuilding;
import game.vilage.resources.ResourceBase;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JPanel;

public class ResourcesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private HashMap<Byte, OtherResourceViewer> resources = new HashMap<Byte, OtherResourceViewer>(); 
	private BasicBuilding parent;
	
	//CONSTRUCTORS
	
	/**
	 * @param basicBuilding
	 */
	public ResourcesPanel(BasicBuilding basicBuilding){
		this.parent = basicBuilding;
		init();
		upateResources();
	}
	
	//OTHERS
	
	/**
	 * inicializuje panel zo surovinamy
	 */
	private void init(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(40,40));
		setVisible(true);
	}
	
	/**
	 * updatne penyli zo surovinamy
	 */
	public void upateResources() {
		ResourceBase resourcesBase = parent.getResources();
		resourcesBase.getAll().forEach((key, value) -> {
			if(resources.containsKey(key))	//ak u� je vyp�san�
				resources.get(key).updateValue();	//aktualizuje ju to
			else{	//in��
				int need = resourcesBase.getRequired(key);
				int have = resourcesBase.getOwned(key);
				OtherResourceViewer newViewer = new OtherResourceViewer(key,need, have, parent);	//vytvor� nov� viewer 
				resources.put(key, newViewer);	//prid� ho do zoznamu viewerov
				add(newViewer);	//aj do panelu
			}
		});
	}

}
