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

public abstract class BasicBuilding {
	protected ResourceBase resources;
	protected Village village;
	protected OtherWindow window;
	protected byte type;
	protected ArrayList<Quest> quests = new ArrayList<Quest>(); 
	
	//CONSTRUCTORS
	
	public BasicBuilding(Village village, byte type){
		this.village = village;
		this.type = type;
		resources = new ResourceBase(Buildings.getRequired(type),Buildings.getProduced(type));
		window = new OtherWindow(this);
		window.init();
	}
	
	//ADDERS
	
	public void addResource(HashMap<Byte, Integer> res){	//prid� hashMapu surov�n do skladu
		for(Entry<Byte, Integer> e : res.entrySet())	//prejde ka�dou surovinou
			resources.addResource(e.getKey(), e.getValue());	//prid� ka�d� jednu surovinu do skladu
	}
	
	public void addResource(byte type, int value){	//prid� konkr�tnu surovinu
		resources.addResource(type, value);	//pr�da surovinu
		window.updateResourcePanel();	//aktualizuje po�et surov�n v okne
	}

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
	
	public void showWindow(){	//zobraz� okno budovy
		window.setVisible(true);
	}

	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {	//skon�� subquest
		if(success)	//ak dokon�il s �spechom
			village.appentNotice(sign()+"podarilo sa splni� �lohu: "+SubQuests.getName(subQuest)+"");	//nap�e �e to vy�lo
		else	//in��
			village.appentNotice(sign()+"nepodarilo sa splni� �lohu: "+SubQuests.getName(subQuest)+" z d�vodu: "+SubEvents.getName(subEvent));	//nap� �e to nevy�lo a d�vod
	}
	
	public String sign(){	//vr�ti meno budovy
		return Buildings.getName(type)+": ";	
	}

	public void finishQuest(int finishedQuest) {	//skon�� subquest
		resources.build();	//vyprodukuje �o by mal vyprodukova�
		window.updateResourcePanel();
		Quest q = quests.get(finishedQuest);	//najkde dokon�en� quest v zozname questov
		village.appentNotice(sign()+"bola odoslan� polo�ka: "+Suroviny.getName(q.getResourceType())+" "+q.getValue()+"ks");	//prilep� info o uspechu
		
		if(q.getFrom() == Buildings.OBCHOD)	//ak quest pri�iel z obchodu
			village.getMarket().addResource(q.getResourceType(), q.getValue());	//po�le suroviny do obchodu
		else{	//in��
			BasicBuilding b = village.getBuilding(q.getFrom());	//najde budovu s ktorej pri�iel quest
			b.addResource(q.getResourceType(), q.getValue());	//po�le danej budove suroviny
		}
		quests.remove(q);//vyma�e quest zo zoznamu questov
	}

	public String toFile() {
		String res = "";
		for(Entry<Byte, Integer> e : resources.getOwned().entrySet()){
			res += type+" "+e.getKey()+" "+e.getValue()+"\n";
		}
		return res;
	}
	
	//GETTERS
	
	public Village getVillage() {
		return village;
	}

	public ArrayList<Quest> getQuests() {
		return quests;
	}

	public ResourceBase getResources() {
		return resources;
	}

	public void wantBuy(byte type, int value) {
		village.appentNotice(sign()+"bola odoslan� �iados� o doru�enie "+value+" ks tovaru: "+Suroviny.getName(type));
		BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));
		b.showWindow();
		b.addQuest(type,this.type, value);
	}

	public byte getType() {
		return type;
	}
}
