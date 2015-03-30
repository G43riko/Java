package game.vilage.quests;

import game.vilage.resources.Suroviny;

import java.util.HashMap;

public class Quests {
	public final static byte VYTAZIT_DREVO = 0;
	public final static byte VYTVORIT_NASTROJ = 1;
	
	private static HashMap<Byte, Byte> resourcesAndQuests = new HashMap<Byte, Byte>();
	static{
		setResourcesAndQuests();
	}
	
	private static void setResourcesAndQuests(){
		resourcesAndQuests.put(Suroviny.DREVO, VYTAZIT_DREVO);
		resourcesAndQuests.put(Suroviny.NASTROJ, VYTVORIT_NASTROJ);
	}
	
	public static byte getQuestFromProduct(byte product){
		return resourcesAndQuests.get(product);
	}
	
	public static String getName(byte type){
		switch(type){
			case VYTAZIT_DREVO: return "Vytaûiù drevo";
			case VYTVORIT_NASTROJ: return "Vytvoriù n·stroj";
			default: return "Nezn·my typ questu";
		}
	}
}
