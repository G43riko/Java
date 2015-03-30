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
		window = new OtherWindow(type, this);
		window.init();
		resources = new ResourceBase(new HashMap<Byte, Integer>(), new HashMap<Byte, Integer>());
	}
	
	//ADDERS
	
	public void addResource(HashMap<Byte, Integer> res){
		for(Entry<Byte, Integer> e : res.entrySet())
			resources.addResource(e.getKey(), e.getValue());
	}
	
	public void addResource(byte type, int value){
			resources.addResource(type, value);
	}

	public void addQuest(byte type, byte from, int value){
		while(value > 0){	//pokial neni vyprodukované množstvo vaèšie alebo rovné ako potrebné množstvo;
			for(Entry<Byte, Integer> e : resources.getProduce().entrySet()){	//pre každú z vyprodukovaných surovín
				quests.add(new Quest(Quests.getQuestFromProduct(type),from,e.getKey(),  e.getValue()));
				window.updateQuests();	//odošle žiados
				if(e.getKey() == type)	//ak je surovina taká istá ako tá ktorá sa vyžaduje
					value -= e.getValue();	//pripoèítam jej množstvo
			}
		}
	}
	
	//OTHERS
	
	public void showWindow(){
		window.setVisible(true);
	}

	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {
		if(success)
			village.appentNotice(sign()+"podarilo sa splni úlohu: "+SubQuests.getName(subQuest)+"");
		else
			village.appentNotice(sign()+"nepodarilo sa splni úlohu: "+SubQuests.getName(subQuest)+" z dôvodu: "+SubEvents.getName(subEvent));
	}
	
	public String sign(){
		return Buildings.getName(type)+": ";
	}

	public void finishQuest(int finishedQuest) {
		resources.build();
		Quest q = quests.get(finishedQuest);
		village.appentNotice(sign()+"bola odoslaná položka: "+Suroviny.getName(q.getResourceType())+" "+q.getValue()+"ks");
		
		if(q.getFrom() > 0){
			BasicBuilding b = village.getBuilding(q.getFrom());
			b.addResource(q.getResourceType(), q.getValue());
		}
		else{
			village.getMarket().addResource(q.getResourceType(), q.getValue());
		}
	}

	//GETTERS
	
	protected abstract HashMap<Byte, Integer> getRequeredResources();
	
	protected abstract HashMap<Byte, Integer> getProducesResources();

	public Village getVillage() {
		return village;
	}

	public ArrayList<Quest> getQuests() {
		return quests;
	}
}
