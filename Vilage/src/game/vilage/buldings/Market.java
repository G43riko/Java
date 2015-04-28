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
		
//		resources.put(Suroviny.CHLIEB, 20);
//		resources.put(Suroviny.DREVO, 40);
//		resources.put(Suroviny.KLOBASA, 5);
//		resources.put(Suroviny.NASTROJ, 25);
//		resources.put(Suroviny.KAMEN, 30);
//		resources.put(Suroviny.UHLIE, 35);
		
		window = new MarketWindow(this);
	}

	//OTHERS
	
	public void appendNotice(String s){	//prilep� ozn�menie
		window.appendNotice(s);
	}
	
	public void showWindow(){	//uk�e okno
		window.setVisible(true);
	}

	public void addResource(byte resource, int value){
		addResource(resource, value, false);
	}
	
	public void addResource(byte resource, int value, boolean hideNotice){	//prid� suroviny
		if(resources.containsKey(resource))	//ak obsahuje u� surovinu
			resources.put(resource, resources.get(resource) + value);	//zva�� jej mno�stvo
		else	//in��
			resources.put(resource,value);	//ju prid�
		
		window.updateValue(resource);	//updatne okno
		if(!hideNotice)
			window.appendNotice(GOODES_RECEIVED, value, resource);	//prilep� spr�vu o doru�en�
	}

	public void wantBuy(byte type, int value) {	//zist� �i m� dostatok surovin na sklade a ak nie tak si objedn� suroviny podla potreby
		if(resources.get(type) >= value){	//ak je viac surov�n na sklade ako je objednan�ch
			window.appendNotice(GOODS_SHIPPED, value, type);	//nap�e spr�vu o odoslan� surov�n
			addResource(type,-value);	//odr�ta suroviny zo skladu
		}
		else{	//in��
			int missing = value - resources.get(type);	//vypo��ta kolko surov�n ch�ba
			window.appendNotice(REQUEST_WAS_SENT, missing, type);	//nap�e spr�vu o odoslan� po�iadavky na ch�baj�ce suroviny 
			BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));	//najde budovu ktor� vyr�ba ch�baj�cu surovinu
			b.showWindow();	//zobraz� okno najdenej budovy
			b.addQuest(type,Buildings.OBCHOD, missing);	//prid� quest danej budove
		}
	}

	public String toFile() {
		StringBuilder res = new StringBuilder();
		resources.forEach((key, value) -> res.append(Buildings.OBCHOD+" "+key+" "+value+"\n"));
		return res.toString();
	}
	
	//GETTERS
	
	public HashMap<Byte, Integer> getResources() {
		return resources;
	}
	
	public MarketWindow getWindow() {
		return window;
	}

	public Village getVillage() {
		return village;
	}
}
