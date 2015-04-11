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
	
	public void addResource(HashMap<Byte, Integer> res){	//prid· hashMapu surovÌn do skladu
		for(Entry<Byte, Integer> e : res.entrySet())	//prejde kaûdou surovinou
			resources.addResource(e.getKey(), e.getValue());	//prid· kaûd˙ jednu surovinu do skladu
	}
	
	public void addResource(byte type, int value){	//pridÌ konkrÈtnu surovinu
		resources.addResource(type, value);	//prÌda surovinu
		window.updateResourcePanel();	//aktualizuje poËet surovÌn v okne
	}

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
	
	public void showWindow(){	//zobrazÌ okno budovy
		window.setVisible(true);
	}

	public void finishSubQuest(boolean success, byte subQuest, byte subEvent) {	//skonËÌ subquest
		if(success)	//ak dokonËil s ˙spechom
			village.appentNotice(sign()+"podarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+"");	//napÌöe ûe to vyölo
		else	//in·Ë
			village.appentNotice(sign()+"nepodarilo sa splniù ˙lohu: "+SubQuests.getName(subQuest)+" z dÙvodu: "+SubEvents.getName(subEvent));	//napÌö ûe to nevyölo a dÙvod
	}
	
	public String sign(){	//vrÌti meno budovy
		return Buildings.getName(type)+": ";	
	}

	public void finishQuest(int finishedQuest) {	//skonËÌ subquest
		resources.build();	//vyprodukuje Ëo by mal vyprodukovaù
		window.updateResourcePanel();
		Quest q = quests.get(finishedQuest);	//najkde dokonËen˝ quest v zozname questov
		village.appentNotice(sign()+"bola odoslan· poloûka: "+Suroviny.getName(q.getResourceType())+" "+q.getValue()+"ks");	//prilepÌ info o uspechu
		
		if(q.getFrom() == Buildings.OBCHOD)	//ak quest priöiel z obchodu
			village.getMarket().addResource(q.getResourceType(), q.getValue());	//poöle suroviny do obchodu
		else{	//in·Ë
			BasicBuilding b = village.getBuilding(q.getFrom());	//najde budovu s ktorej priöiel quest
			b.addResource(q.getResourceType(), q.getValue());	//poöle danej budove suroviny
		}
		quests.remove(q);//vymaûe quest zo zoznamu questov
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
		village.appentNotice(sign()+"bola odoslan· ûiadosù o doruËenie "+value+" ks tovaru: "+Suroviny.getName(type));
		BasicBuilding b = village.getBuilding(Suroviny.getBuildingFromProduct(type));
		b.showWindow();
		b.addQuest(type,this.type, value);
	}

	public byte getType() {
		return type;
	}
}
