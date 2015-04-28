package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Market;
import game.vilage.buldings.named.Carpentry;
import game.vilage.buldings.named.LumberJack;
import game.vilage.files.FileReader;

public class Village {
	private HashMap<Byte, BasicBuilding> buildings = new HashMap<Byte, BasicBuilding>();
	private Market market;
	
	//CONSTRUCTORS
	public Village(){
		buildings.put(Buildings.DREVORUBAC, new LumberJack(this));
		buildings.put(Buildings.TESAR, new Carpentry(this));

		
		market = new Market(this);
		market.showWindow();
		
		FileReader.loadData(this);
	}
	
	//OTHERS
	
	public void appentNotice(String s){	//prilepí oznámenie do textarei
		market.appendNotice(s);	
	}
	
	public void show(byte type){	//zobrazí okno pre konkrétnu budovu
		buildings.get(type).showWindow();
	}

	public void saveData() {
		FileReader.saveData(this);
	}

	//GETTERS
	
	public BasicBuilding getBuilding(byte buildingType){
		return buildings.get(buildingType);
	}
	
	public boolean isAnyWindowOpened(){
		return market.getWindow().isVisible();
	}

	public Market getMarket() {
		return market;
	}

	public HashMap<Byte, BasicBuilding> getBuildings() {
		return buildings;
	}	
}
