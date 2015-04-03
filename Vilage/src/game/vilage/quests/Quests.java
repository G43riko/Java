package game.vilage.quests;

import game.vilage.resources.Suroviny;

import java.util.HashMap;

public class Quests {
	public final static byte VYTAZIT_DREVO = 0;
	public final static byte VYTVORIT_NASTROJ = 1;
	public final static byte ZOZAT_OBYLIE = 2;
	public final static byte POMLIET_OBYLIE = 3;
	public final static byte UPIECT_CHLIEB = 4;
	public final static byte VYTAZIT_KAMEN = 5;
	public final static byte SKOLIT_ZVIERA = 6;
	public final static byte OSTRIHAT_OVECKU = 7;
	public final static byte VYTAZIT_UHLIE = 8;
	public final static byte VYTAZIT_ZELEZNU_RUDU = 9;
	public final static byte VYTAVIT_ZELEZO = 10;
	public final static byte UVARIT_PIVO = 11;
	public final static byte VYROBIT_KLOBASU = 12;
	public final static byte UPLIEST_SATY = 13;
	public final static byte ULOVIT_RYBU = 14;
	
	private static HashMap<Byte, Byte> resourcesAndQuests = new HashMap<Byte, Byte>();
	static{
		setResourcesAndQuests();
	}
	
	private static void setResourcesAndQuests(){
		resourcesAndQuests.put(Suroviny.DREVO, VYTAZIT_DREVO);
		resourcesAndQuests.put(Suroviny.NASTROJ, VYTVORIT_NASTROJ);
		resourcesAndQuests.put(Suroviny.OBILIE, ZOZAT_OBYLIE);
		resourcesAndQuests.put(Suroviny.MUKA, POMLIET_OBYLIE);
		resourcesAndQuests.put(Suroviny.CHLIEB, UPIECT_CHLIEB);
		resourcesAndQuests.put(Suroviny.KAMEN, VYTAZIT_KAMEN);
		resourcesAndQuests.put(Suroviny.MASO, SKOLIT_ZVIERA);
		resourcesAndQuests.put(Suroviny.VLNA, OSTRIHAT_OVECKU);
		resourcesAndQuests.put(Suroviny.UHLIE, VYTAZIT_UHLIE);
		resourcesAndQuests.put(Suroviny.ZELEZNA_RUDA, VYTAZIT_ZELEZNU_RUDU);
		resourcesAndQuests.put(Suroviny.ZELEZO, VYTAVIT_ZELEZO);
		resourcesAndQuests.put(Suroviny.PIVO, UVARIT_PIVO);
		resourcesAndQuests.put(Suroviny.KLOBASA, VYROBIT_KLOBASU);
		resourcesAndQuests.put(Suroviny.SATY, UPLIEST_SATY);
		resourcesAndQuests.put(Suroviny.RYBA, ULOVIT_RYBU);
	}
	
	public static byte getQuestFromProduct(byte product){
		return resourcesAndQuests.get(product);
	}
	
	public static String getName(byte type){
		switch(type){
			case VYTAZIT_DREVO: return "Vyta�i� drevo";
			case VYTVORIT_NASTROJ: return "Vytvori� n�stroj";
			case ZOZAT_OBYLIE: return "Zo�a� obylie";
			case POMLIET_OBYLIE: return "Pomlie� obylie";
			case UPIECT_CHLIEB: return "Upiec� chlieb";
			case VYTAZIT_KAMEN: return "Vyta�i� kame�";
			case SKOLIT_ZVIERA: return "Skoli� zviera";
			case OSTRIHAT_OVECKU: return "Ostriha� Ove�ku";
			case VYTAZIT_UHLIE: return "Vyta�i� uhlie";
			case VYTAZIT_ZELEZNU_RUDU: return "Vyta�i� �elezn� rudu";
			case VYTAVIT_ZELEZO: return "Vytavi� �elezo";
			case UVARIT_PIVO: return "Uvari� pivo";
			case VYROBIT_KLOBASU: return "Vyrobi� klob�sku";
			case UPLIEST_SATY: return "Uplies� �aty";
			case ULOVIT_RYBU: return "Ulovi� rybu";
			default: return "Nezn�my typ questu";
		}
	}
}
