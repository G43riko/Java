package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.Suroviny;
import game.vilage.view.MarketWindow;

import java.util.HashMap;
import java.util.Map.Entry;

public class Market {
	public final static byte GOODS_SHIPPED = 0;
	public final static byte REQUEST_WAS_SENT = 1;
	public final static byte QUEST_FAILURE = 2;
	public final static byte GOODES_RECEIVED = 3;
	
	private HashMap<Byte, Integer> resources;
	private Village village;
	private MarketWindow window;
	
	//CONSTRUCTORS
	
	/**
	 * @param village
	 */
	public Market(Village village){
		this.village = village;
		resources = Suroviny.getAllDefault();
		
//		resources.put(Suroviny.CHLIEB, 20);
//		resources.put(Suroviny.DREVO, 40);
//		resources.put(Suroviny.KLOBASA, 5);
//		resources.put(Suroviny.NASTROJ, 25);
//		resources.put(Suroviny.KAMEN, 30);
//		resources.put(Suroviny.UHLIE, 35);
		
		window = new MarketWindow(this);
	}

	//OTHERS
	
	/**
	 * @param s
	 */
	public void appendNotice(String s){	//prilepí oznámenie
		window.appendNotice(s);
	}
	
	/**
	 * 
	 */
	public void showWindow(){	//ukáže okno
		window.setVisible(true);
	}

	/**
	 * @param resource
	 * @param value
	 */
	public void addResource(byte resource, int value){
		addResource(resource, value, false);
	}
	
	/**
	 * @param resource
	 * @param value
	 * @param hideNotice
	 */
	public void addResource(byte resource, int value, boolean hideNotice){	//pridá suroviny
		if(resources.containsKey(resource))	//ak obsahuje už surovinu
			resources.put(resource, resources.get(resource) + value);	//zvaèší jej množstvo
		else	//ináè
			resources.put(resource,value);	//ju pridá
		
		window.updateValue(resource);	//updatne okno
		if(!hideNotice)
			window.appendNotice(GOODES_RECEIVED, value, resource);	//prilepí správu o doruèení
	}

	/**
	 * @param type
	 * @param value
	 */
	public void wantBuy(byte type, int value) {	//zistí èi má dostatok surovin na sklade a ak nie tak si objedná suroviny podla potreby
		if(resources.get(type) >= value){	//ak je viac surovín na sklade ako je objednaných
			window.appendNotice(GOODS_SHIPPED, value, type);	//napíše správu o odoslaní surovín
			addResource(type,-value);	//odráta suroviny zo skladu
		}
		else{	//ináè
			int missing = value - resources.get(type);	//vypoèíta kolko surovín chýba
			window.appendNotice(REQUEST_WAS_SENT, missing, type);	//napíše správu o odoslaní požiadavky na chýbajúce suroviny 
			BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));	//najde budovu ktorá vyrába chýbajúcu surovinu
			b.showWindow();	//zobrazí okno najdenej budovy
			b.addQuest(type,Buildings.OBCHOD, missing);	//pridá quest danej budove
		}
	}

	/**
	 * @return
	 */
	public String toFile() {
		String res = "";
		for(Entry<Byte, Integer> e : resources.entrySet()){
			res += Buildings.OBCHOD+" "+e.getKey()+" "+e.getValue()+"\n";
		}
		return res;
	}
	
	//GETTERS
	
	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getResources() {
		return resources;
	}
	
	/**
	 * @return
	 */
	public MarketWindow getWindow() {
		return window;
	}

	/**
	 * @return
	 */
	public Village getVillage() {
		return village;
	}
}
