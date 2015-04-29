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
	public void addResource(HashMap<Byte, Integer> res){	//prid� hashMapu surov�n do skladu
		for(Entry<Byte, Integer> e : res.entrySet())	//prejde ka�dou surovinou
			resources.addResource(e.getKey(), e.getValue());	//prid� ka�d� jednu surovinu do skladu
	}
	
	/**
	 * @param type
	 * @param value
	 */
	public void addResource(byte type, int value){	//prid� konkr�tnu surovinu
		resources.addResource(type, value);	//pr�da surovinu
		window.updateResourcePanel();	//aktualizuje po�et surov�n v okne
	}

	/**
	 * @param type
	 * @param from
	 * @param value
	 */
	public void addQuest(byte type, byte from, int value){
		while(value > 0){	//pokial neni vyprodukovan� mno�stvo va�ie alebo rovn� ako potrebn� mno�stvo;
			for(Entry<Byte, Integer> e : resources.getProduce().entrySet()){	//pre ka�d� z vyprodukovan�ch surov�n
				quests.add(new Quest(Quests.getQuestFromProduct(type),from,e.getKey(),  e.getValue()));	//prid� quest do zoznamu questov
				window.updateQuests();	//odo�le �iados�
				if(e.getKey() == type)	//ak je surovina tak� ist� ako t� ktor� sa vy�aduje
					value -= e.getValue();	//pripo��tam jej mno�stvo
			}
		}
	}
	
	//OTHERS
	
	/**
	 * make window visible 
	 */
	public void showWindow(){	//zobraz� okno budovy
		window.setVisible(true);
	}

	/**
	 * @param success
	 * @param subQuest
	 * @param subEvent
	 */
	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {	//skon�� subquest
		if(success)	//ak dokon�il s �spechom
			village.appentNotice(sign()+"podarilo sa splni� �lohu: "+SubQuests.getName(subQuest)+"");	//nap�e �e to vy�lo
		else	//in��
			village.appentNotice(sign()+"nepodarilo sa splni� �lohu: "+SubQuests.getName(subQuest)+" z d�vodu: "+SubEvents.getName(subEvent));	//nap� �e to nevy�lo a d�vod
	}
	
	/**
	 * @return
	 */
	public String sign(){	//vr�ti meno budovy
		return Buildings.getName(type)+": ";	
	}

	/**
	 * @param finishedQuest
	 */
	public void finishQuest(int finishedQuest) {	//skon�� subquest
		resources.build();	//vyprodukuje �o by mal vyprodukova�
		window.updateResourcePanel();
		Quest quest = quests.get(finishedQuest);	//najkde dokon�en� quest v zozname questov
		village.appentNotice(sign()+"bola odoslan� polo�ka: "+Suroviny.getName(quest.getResourceType())+" "+quest.getValue()+"ks");	//prilep� info o uspechu
		
		if(quest.getFrom() == Buildings.OBCHOD)	//ak quest pri�iel z obchodu
			village.getMarket().addResource(quest.getResourceType(), quest.getValue());	//po�le suroviny do obchodu
		else{	//in��
			BasicBuilding building = village.getBuilding(quest.getFrom());	//najde budovu s ktorej pri�iel quest
			building.addResource(quest.getResourceType(), quest.getValue());	//po�le danej budove suroviny
		}
		quests.remove(quest);//vyma�e quest zo zoznamu questov
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
		village.appentNotice(sign()+"bola odoslan� �iados� o doru�enie "+value+" ks tovaru: "+Suroviny.getName(type));
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
