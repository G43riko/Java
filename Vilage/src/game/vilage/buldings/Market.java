package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.Resources;
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
		resources = Resources.getAllDefault();
		
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
	 * prilep� ozn�menie
	 * @param text
	 */
	public void appendNotice(String text){
		window.appendNotice(text);
	}
	
	/**
	 * uk�e okno
	 */
	public void showWindow(){
		window.setVisible(true);
	}

	/**
	 * zavol� funkciu na prid�vanie surov�n
	 * @param resource
	 * @param value
	 */
	public void addResource(byte resource, int value){
		addResource(resource, value, false);
	}
	
	/**
	 * prid� surovinu medzy vlastnen� suroviny
	 * @param resource
	 * @param value
	 * @param hideNotice
	 */
	public void addResource(byte resource, int value, boolean hideNotice){	//prid� suroviny
		if(resources.containsKey(resource))	//ak obsahuje u� surovinu
			resources.put(resource, resources.get(resource) + value);	//zva�� jej mno�stvo
		else	//in��
			resources.put(resource,value);	//ju prid�
		
		window.updateValue(resource);	//updatne okno
		if(!hideNotice)
			window.appendNotice(GOODES_RECEIVED, value, resource);	//prilep� spr�vu o doru�en�
	}

	/**
	 * odo�le �iados� o k�pu surovin
	 * @param type
	 * @param value
	 */
	public void wantBuy(byte typeOfResource, int value) {	//zist� �i m� dostatok surovin na sklade a ak nie tak si objedn� suroviny podla potreby
		if(resources.get(typeOfResource) >= value){	//ak je viac surov�n na sklade ako je objednan�ch
			window.appendNotice(GOODS_SHIPPED, value, typeOfResource);	//nap�e spr�vu o odoslan� surov�n
			addResource(typeOfResource,-value);	//odr�ta suroviny zo skladu
		}
		else{	//in��
			int missing = value - resources.get(typeOfResource);	//vypo��ta kolko surov�n ch�ba
			byte buildingType = Resources.getBuildingFromProduct(typeOfResource);
			if(buildingType == 0){
				village.appentNotice("ERROR: nepodarilo sa zisti� vyrobcu suroviny: "+Resources.getName(typeOfResource));
				return;
			}
			
			window.appendNotice(REQUEST_WAS_SENT, missing, typeOfResource);	//nap�e spr�vu o odoslan� po�iadavky na ch�baj�ce suroviny 
			BasicBuilding b = village.getBuilding(buildingType);	//najde budovu ktor� vyr�ba ch�baj�cu surovinu
			b.showWindow();	//zobraz� okno najdenej budovy
			b.addQuest(typeOfResource,Buildings.OBCHOD, missing);	//prid� quest danej budove
		}
	}

	/**
	 * vrat� v stringu zak�dovan� budovy a ich mno�stvo surov�n
	 * @return
	 */
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
