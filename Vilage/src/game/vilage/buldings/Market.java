package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.Suroviny;
import game.vilage.view.MarketWindow;

import java.util.HashMap;

public class Market {
	public final static byte GOODS_SHIPPED = 0;
	public final static byte REQUEST_WAS_SENT = 1;
	
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
		if(resources.get(type) >= value){
			window.appendNotice(GOODS_SHIPPED, value, type);
			addResource(type,-value);
		}
		else{
			int missing = value - resources.get(type);
			window.appendNotice(REQUEST_WAS_SENT, missing, type);
		}
		
	}

	public MarketWindow getWindow() {
		return window;
	}
}
