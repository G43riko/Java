package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Carpenter;
import game.vilage.buldings.LumberJack;
import game.vilage.buldings.Market;
import game.vilage.view.OtherWindow;

public class Village {
	private HashMap<Byte, BasicBuilding> building = new HashMap<Byte, BasicBuilding>();
	private Market market;
	
	
	public Village(){
		building.put(Buildings.DREVORUBAC, new LumberJack(this));
		building.put(Buildings.TESAR, new Carpenter(this));
		
		market = new Market(this);
		market.showWindow();
		
		building.get(Buildings.DREVORUBAC).showWindow();
	}
	
	public BasicBuilding getBuilding(byte buildingType){
		return building.get(buildingType);
	}
	
	public void show(byte type){
		building.get(type).showWindow();
	}
	
	public boolean isAnyWindowOpened(){
		if(market.getWindow().isVisible())
			return true;
		
		return false;
	}
	
}
