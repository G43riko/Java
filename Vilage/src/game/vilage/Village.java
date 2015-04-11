package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Carpenter;
import game.vilage.buldings.LumberJack;
import game.vilage.buldings.Market;
import game.vilage.files.Model;

public class Village {
	
	private HashMap<Byte, BasicBuilding> buildings = new HashMap<Byte, BasicBuilding>();
	private Market market;
	
	//CONSTRUCTORS
	
	public Village(){
		buildings.put(Buildings.DREVORUBAC, new LumberJack(this));
		buildings.put(Buildings.TESAR, new Carpenter(this));

		
		market = new Market(this);
		market.showWindow();
		
		for(int i=0 ; i<5 ; i++){
			try {
				Thread.sleep(800);
				market.appendNotice("èáv");
				System.out.println("muhahaa");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		Model.loadData(this);
//		building.get(Buildings.DREVORUBAC).showWindow();
	}
	
	//OTHERS
	
	public void appentNotice(String s){	//prilepí oznámenie do textarei
		market.appendNotice(s);	
	}
	
	public void show(byte type){	//zobrazí okno pre konkrétnu budovu
		buildings.get(type).showWindow();
	}

	public void saveData() {
		Model.saveData(this);
	}

	//GETTERS
	
	public BasicBuilding getBuilding(byte buildingType){
		return buildings.get(buildingType);
	}
	
	public boolean isAnyWindowOpened(){
		if(market.getWindow().isVisible())
			return true;
		
		return false;
	}

	public Market getMarket() {
		return market;
	}

	
	public HashMap<Byte, BasicBuilding> getBuildings() {
		return buildings;
	}	
}
