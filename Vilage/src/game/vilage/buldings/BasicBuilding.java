package game.vilage.buldings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import game.vilage.Village;
import game.vilage.quests.Quest;
import game.vilage.quests.Quests;
import game.vilage.quests.SubEvents;
import game.vilage.quests.SubQuests;
import game.vilage.resources.ResourceBase;
import game.vilage.resources.Suroviny;
import game.vilage.view.OtherWindow;

/**
 * @author Gabriel
 *
 */
public abstract class BasicBuilding {
	protected ResourceBase resources;
	protected Village village;
	protected OtherWindow window;
	protected byte type;
	protected ArrayList<Quest> quests = new ArrayList<Quest>(); 
	
	//CONSTRUCTORS
	
	/**
	 * @param village
	 * @param type
	 */
	public BasicBuilding(Village village, byte type){
		this.village = village;
		this.type = type;
		resources = new ResourceBase(Buildings.getRequired(type),Buildings.getProduced(type));
		window = new OtherWindow(this);
		window.init();
	}
	
	//ADDERS
	
	/**
	 * @param res
	 */
	public void addResource(HashMap<Byte, Integer> res){	//prid· hashMapu surovÌn do skladu
		for(Entry<Byte, Integer> e : res.entrySet())	//prejde kaûdou surovinou
			resources.addResource(e.getKey(), e.getValue());	//prid· kaûd˙ jednu surovinu do skladu
	}
	
	/**
	 * @param type
	 * @param value
	 */
	public void addResource(byte type, int value){	//pridÌ konkrÈtnu surovinu
		resources.addResource(type, value);	//prÌda surovinu
		window.updateResourcePanel();	//aktualizuje poËet surovÌn v okne
	}

	/**
	 * @param type
	 * @param from
	 * @param value
	 */
	public void addQuest(byte type, byte from, int value){
		while(value > 0){	//pokial neni vyprodukovanÈ mnoûstvo vaËöie alebo rovnÈ ako potrebnÈ mnoûstvo;
			for(Entry<Byte, Integer> e : resources.getProduce().entrySet()){	//pre kaûd˙ z vyprodukovan˝ch surovÌn
				quests.add(new Quest(Quests.getQuestFromProduct(type),from,e.getKey(),  e.getValue()));	//prid· quest do zoznamu questov
				window.updateQuests();	//odoöle ûiadosù
				if(e.getKey() == type)	//ak je surovina tak· ist· ako t· ktor· sa vyûaduje
					value -= e.getValue();	//pripoËÌtam jej mnoûstvo
			}
		}
	}
	
	//OTHERS
	
	/**
	 * make window visible 
	 */
	public void showWindow(){	//zobrazÌ okno budovy
		window.setVisible(true);
	}

	/**
	 * @param success
	 * @param subQuest
	 * @param subEvent
	 */
	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {	//skonËÌ subquest
		if(success)	//ak dokonËil s ˙spechom
			village.appentNotice(sign()+"podarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+"");	//napÌöe ûe to vyölo
		else	//in·Ë
			village.appentNotice(sign()+"nepodarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+" z dÙvodu: "+SubEvents.getName(subEvent));	//napÌö ûe to nevyölo a dÙvod
	}
	
	/**
	 * @return
	 */
	public String sign(){	//vrÌti meno budovy
		return Buildings.getName(type)+": ";	
	}

	/**
	 * @param finishedQuest
	 */
	public void finishQuest(int finishedQuest) {	//skonËÌ subquest
		resources.build();	//vyprodukuje Ëo by mal vyprodukovaù
		window.updateResourcePanel();
		Quest quest = quests.get(finishedQuest);	//najkde dokonËen˝ quest v zozname questov
		village.appentNotice(sign()+"bola odoslan· poloûka: "+Suroviny.getName(quest.getResourceType())+" "+quest.getValue()+"ks");	//prilepÌ info o uspechu
		
		if(quest.getFrom() == Buildings.OBCHOD)	//ak quest priöiel z obchodu
			village.getMarket().addResource(quest.getResourceType(), quest.getValue());	//poöle suroviny do obchodu
		else{	//in·Ë
			BasicBuilding building = village.getBuilding(quest.getFrom());	//najde budovu s ktorej priöiel quest
			building.addResource(quest.getResourceType(), quest.getValue());	//poöle danej budove suroviny
		}
		quests.remove(quest);//vymaûe quest zo zoznamu questov
	}

	/**
	 * @return
	 */
	public String toFile() {
		StringBuilder result = new StringBuilder();
		resources.getOwned().forEach((key, value) -> 
			result.append(type+" "+key+" "+value+"\n")
		);
		return result.toString();
	}
	
	//GETTERS
	
	/**
	 * @return
	 */
	public Village getVillage() {
		return village;
	}

	/**
	 * @return
	 */
	public ArrayList<Quest> getQuests() {
		return quests;
	}

	/**
	 * @return
	 */
	public ResourceBase getResources() {
		return resources;
	}

	/**
	 * @param type
	 * @param value
	 */
	public void wantBuy(byte type, int value) {
		village.appentNotice(sign()+"bola odoslan· ûiadosù o doruËenie "+value+" ks tovaru: "+Suroviny.getName(type));
		BasicBuilding building = village.getBuilding(Suroviny.getBuildingFromProduct(type));
		building.showWindow();
		building.addQuest(type,this.type, value);
	}

	/**
	 * @return
	 */
	public byte getType() {
		return type;
	}
}
