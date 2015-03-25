package game.vilage.buldings;

import java.util.HashMap;
import java.util.Map.Entry;

import game.vilage.Village;
import game.vilage.resources.ResourceBase;
import game.vilage.resources.Suroviny;
import game.vilage.view.OtherWindow;

public abstract class BasicBuilding {
	protected ResourceBase resources;
	protected Village village;
	protected OtherWindow window;
	
	public BasicBuilding(Village village, byte type){
		this.village = village;
		window = new OtherWindow(type);
		window.init();
	}
	
	public void produce(BasicBuilding forWho){
		if(!resources.canWork()){
			chechMissingResource();
			return;
		}
		resources.build();
	}
	
	public void add(HashMap<Byte, Integer> res){
		for(Entry<Byte, Integer> e : res.entrySet())
			resources.addResource(e.getKey(), e.getValue());
	}
	
	public void showWindow(){
		window.setVisible(true);
	}
	
	protected void chechMissingResource(){
		HashMap<Byte, Integer> missing = resources.getMissingResources();	//zístíme ktoré suroviny chýbajú
		
		for(Entry<Byte, Integer> e : missing.entrySet())	//pre každu surovinu ktorá chýba
			for(int i=0 ; i<e.getValue() ; i++)	//pre každý chýbajúci kus suroviny
				village.getBuilding(Suroviny.getBuildingFromProduct(e.getKey())).produce(this);	//objednáme 
	}
	
	protected abstract HashMap<Byte, Integer> getRequeredResources();
	
	protected abstract HashMap<Byte, Integer> getProducesResources();
}
