package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Carpenter;
import game.vilage.buldings.LumberJack;
import game.vilage.buldings.Market;

public class Village {
	private HashMap<Byte, BasicBuilding> building = new HashMap<Byte, BasicBuilding>();
	private Market market;
	
	//CONSTRUCTORS
	
	public Village(){
		building.put(Buildings.DREVORUBAC, new LumberJack(this));
		building.put(Buildings.TESAR, new Carpenter(this));
		
		market = new Market(this);
		market.showWindow();
		
//		building.get(Buildings.DREVORUBAC).showWindow();
	}
	
	//OTHERS
	
	public void appentNotice(String s){
		market.appendNotice(s);
	}
	
	public void show(byte type){
		building.get(type).showWindow();
	}

	//GETTERS
	
	public BasicBuilding getBuilding(byte buildingType){
		return building.get(buildingType);
	}
	
	public boolean isAnyWindowOpened(){
		if(market.getWindow().isVisible())
			return true;
		
		return false;
	}

	public Market getMarket() {
		return market;
	}
	
}
