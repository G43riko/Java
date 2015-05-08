package game.vilage;

import java.util.HashMap;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.buldings.Market;
import game.vilage.buldings.named.Carpentry;
import game.vilage.buldings.named.LumberJack;
import game.vilage.files.FileReader;
import game.vilage.view.GodsWindow;

/**
 * @author Gabriel
 *
 */
public class Village {
	private HashMap<Byte, BasicBuilding> buildings = new HashMap<Byte, BasicBuilding>();
	private Market market;
	private Thread godsThread;
	private GodsWindow godsWindow;
	private Village toto;
	private int delay = 1000;
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

	//CONSTRUCTORS
	public Village(){
		toto = this;
		godsThread = new Thread (new Runnable(){
			public void run() {
				godsWindow = new GodsWindow(toto);
				while(true){
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					godsWindow.update();
				}
			}
		});
		
		buildings.put(Buildings.DREVORUBAC, new LumberJack(this));
		buildings.put(Buildings.TESAR, new Carpentry(this));
		
		market = new Market(this);
		market.showWindow();
		
		FileReader.loadData(this);	//naèíta dáta
		godsThread.start();
	}
	
	//OTHERS
	
	public void appentNotice(String s){	//prilepí oznámenie do textarei
		market.appendNotice(s);	
	}
	
	public void show(byte type){	//zobrazí okno pre konkrétnu budovu
		buildings.get(type).showWindow();
	}

	public void saveData() {	//uloží akutalny stav dedinky do suboru
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
