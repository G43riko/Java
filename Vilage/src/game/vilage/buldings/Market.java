package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.Suroviny;
import game.vilage.view.MarketWindow;

import java.util.HashMap;

public class Market {
	private HashMap<Byte, Integer> resources;
	private Village village;
	private MarketWindow window;
	
	
	public Market(Village village){
		this.village = village;
		resources = Suroviny.getAllDefault();
		
		resources.put(Suroviny.CHLIEB, 20);
		resources.put(Suroviny.DREVO, 40);
		resources.put(Suroviny.KLOBASA, 5);
		resources.put(Suroviny.NASTROJ, 25);
		resources.put(Suroviny.KAMEN, 30);
		resources.put(Suroviny.UHLIE, 35);
		
		window = new MarketWindow(this);
	}
	
	public void showWindow(){
		window.setVisible(true);
	}

	public HashMap<Byte, Integer> getResources() {
		return resources;
	}
	
	public void addResource(byte resource, int value){
		if(resources.containsKey(resource))
			resources.put(resource, resources.get(resource) + value);
		else
			resources.put(resource,value);
		
		window.updateValue(resource);
	}

	public void wantBuy(byte type, int value) {
		window.appendNotice(0, value, type);
		addResource(type,-value);
	}
}
