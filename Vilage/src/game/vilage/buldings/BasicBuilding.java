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
	
	public void addResource(HashMap<Byte, Integer> res){
		for(Entry<Byte, Integer> e : res.entrySet())
			resources.addResource(e.getKey(), e.getValue());
	}
	
	private void addResource(byte type, int value){
		resources.addResource(type, value);
		window.updateResourcePanel();
	}

	public void addQuest(byte type, byte from, int value){
		while(value > 0){	//pokial neni vyprodukovanÈ mnoûstvo vaËöie alebo rovnÈ ako potrebnÈ mnoûstvo;
			for(Entry<Byte, Integer> e : resources.getProduce().entrySet()){	//pre kaûd˙ z vyprodukovan˝ch surovÌn
				quests.add(new Quest(Quests.getQuestFromProduct(type),from,e.getKey(),  e.getValue()));
				window.updateQuests();	//odoöle ûiadosù
				if(e.getKey() == type)	//ak je surovina tak· ist· ako t· ktor· sa vyûaduje
					value -= e.getValue();	//pripoËÌtam jej mnoûstvo
			}
		}
	}
	
	//OTHERS
	
	public void showWindow(){
		window.setVisible(true);
	}

	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {
		if(success)
			village.appentNotice(sign()+"podarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+"");
		else
			village.appentNotice(sign()+"nepodarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+" z dÙvodu: "+SubEvents.getName(subEvent));
	}
	
	public String sign(){
		return Buildings.getName(type)+": ";
	}

	public void finishQuest(int finishedQuest) {
		resources.build();
		Quest q = quests.get(finishedQuest);
		village.appentNotice(sign()+"bola odoslan· poloûka: "+Suroviny.getName(q.getResourceType())+" "+q.getValue()+"ks");
		
		if(q.getFrom() == Buildings.OBCHOD)
			village.getMarket().addResource(q.getResourceType(), q.getValue());
		else{
			BasicBuilding b = village.getBuilding(q.getFrom());
			b.addResource(q.getResourceType(), q.getValue());
		}
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
		village.appentNotice(sign()+"bola odoslan· ûiadosù o doruËenie "+value+" ks tovaru: "+Suroviny.getName(type));
		BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));
		b.showWindow();
		b.addQuest(type,this.type, value);
	}

	public byte getType() {
		return type;
	}
}
