package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Carpentry;
import game.vilage.buldings.LumberJack;
import game.vilage.buldings.Market;
import game.vilage.files.FileReader;

public class Village {
	
	private HashMap<Byte, BasicBuilding> buildings = new HashMap<Byte, BasicBuilding>();
	private Market market;
	
	//CONSTRUCTORS
	/**
	 * 
	 */
	public Village(){
		buildings.put(Buildings.DREVORUBAC, new LumberJack(this));
		buildings.put(Buildings.TESAR, new Carpentry(this));

		
		market = new Market(this);
		market.showWindow();
		
		FileReader.loadData(this);
//		building.get(Buildings.DREVORUBAC).showWindow();
	}
	
	//OTHERS
	
	/**
	 * @param s
	 */
	public void appentNotice(String s){	//prilepí oznámenie do textarei
		market.appendNotice(s);	
	}
	
	/**
	 * @param type
	 */
	public void show(byte type){	//zobrazí okno pre konkrétnu budovu
		buildings.get(type).showWindow();
	}

	/**
	 * 
	 */
	public void saveData() {
		FileReader.saveData(this);
	}

	//GETTERS
	
	/**
	 * @param buildingType
	 * @return
	 */
	public BasicBuilding getBuilding(byte buildingType){
		return buildings.get(buildingType);
	}
	
	/**
	 * @return
	 */
	public boolean isAnyWindowOpened(){
		if(market.getWindow().isVisible())
			return true;
		
		return false;
	}

	/**
	 * @return
	 */
	public Market getMarket() {
		return market;
	}

	
	/**
	 * @return
	 */
	public HashMap<Byte, BasicBuilding> getBuildings() {
		return buildings;
	}	
}
