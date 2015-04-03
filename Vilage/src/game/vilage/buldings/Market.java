package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.Suroviny;
import game.vilage.view.MarketWindow;

import java.util.HashMap;

public class Market {
	public final static byte GOODS_SHIPPED = 0;
	public final static byte REQUEST_WAS_SENT = 1;
	public final static byte QUEST_FAILURE = 2;
	public final static byte GOODES_RECEIVED = 3;
	
	private HashMap<Byte, Integer> resources;
	private Village village;
	private MarketWindow window;
	
	//CONSTRUCTORS
	
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

	//OTHERS
	
	public void appendNotice(String s){
		window.appendNotice(s);
	}
	
	public void showWindow(){
		window.setVisible(true);
	}

	public void addResource(byte resource, int value){
		if(resources.containsKey(resource))
			resources.put(resource, resources.get(resource) + value);
		else
			resources.put(resource,value);
		
		window.updateValue(resource);
		window.appendNotice(GOODES_RECEIVED, value, resource);
	}

	public void wantBuy(byte type, int value) {
		if(resources.get(type) >= value){
			window.appendNotice(GOODS_SHIPPED, value, type);
			addResource(type,-value);
		}
		else{
			int missing = value - resources.get(type);
			window.appendNotice(REQUEST_WAS_SENT, missing, type);
			BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));
			b.showWindow();
			b.addQuest(type,Buildings.OBCHOD, missing);
		}
	}

	//GETTERS
	
	public HashMap<Byte, Integer> getResources() {
		return resources;
	}
	
	public MarketWindow getWindow() {
		return window;
	}
}
