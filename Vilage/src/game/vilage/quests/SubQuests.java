package game.vilage.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubQuests {
	public final static byte NABRUSIT_SEKERU = 0;
	public final static byte IST_DO_LESA = 1;
	public final static byte NAJST_VHODNY_STROM = 2;
	public final static byte ZOTAT_STROM = 3;
	public final static byte DOTIAHNUT_STROM = 4;
	public final static byte ROZSEKAT_STROM = 5;
	
	public final static byte PRIPRAVIT_NASTROJE = 6;
	public final static byte VYTVORIT_NAKRES = 7;
	public final static byte VYTVORI_KONSTRUKCIU = 8;
	public final static byte VYTVORIT_MODEL = 9;
	public final static byte DOLADIT_DETAILY = 10;
	public final static byte NALAKOVAT = 11;
	
	public final static byte ODOSLAT_OBJEDNAVKU = 12;
	public final static byte ZACAT_PRACU_NA_OBJEDNAVKU = 13;
	
	private static HashMap<Byte, List<Byte>> subQuestsToQuest = new HashMap<Byte, List<Byte>>();
	
	static{
		setSubQuestsToQuestS();
	}
	
	private static void setSubQuestsToQuestS(){
		List<Byte> list = new ArrayList<Byte>();
		list.add(NABRUSIT_SEKERU);
		list.add(IST_DO_LESA);
		list.add(NAJST_VHODNY_STROM);
		list.add(ZOTAT_STROM);
		list.add(DOTIAHNUT_STROM);
		list.add(ROZSEKAT_STROM);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.VYTAZIT_DREVO, list);
		
		list = new ArrayList<Byte>();
		list.add(PRIPRAVIT_NASTROJE);
		list.add(VYTVORIT_NAKRES);
		list.add(VYTVORI_KONSTRUKCIU);
		list.add(VYTVORIT_MODEL);
		list.add(DOLADIT_DETAILY);
		list.add(NALAKOVAT);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.VYTVORIT_NASTROJ, list);
	}
	
	public static List<Byte> getSubquestsFromQuest(byte quest){
		return subQuestsToQuest.get(quest);
	}
	
	public static String getName(byte type){
		switch(type){
			case NABRUSIT_SEKERU: return "Nabr˙siù sekeru";
			case IST_DO_LESA: return "Õsù do lesa";
			case NAJST_VHODNY_STROM: return "N·jsù vhodn˝ strom";
			case DOTIAHNUT_STROM: return "Datiahnuù strom domou";
			case ROZSEKAT_STROM: return "Rozsekaù strom";
		
			case PRIPRAVIT_NASTROJE: return "Pripraviù si n·stroje";
			case VYTVORIT_NAKRES: return "Vytvoriù n·kres";
			case VYTVORI_KONSTRUKCIU: return "Vytvoriù z·kladn˙ konötrukciu";
			case VYTVORIT_MODEL: return "Vytvoriù model";
			case DOLADIT_DETAILY: return "Doladiù detaily";
			case NALAKOVAT: return "Nalakovaù n·stroj";
			
			case ODOSLAT_OBJEDNAVKU: return "Odoslaù objedn·vku";
			case ZACAT_PRACU_NA_OBJEDNAVKU: return "ZaËaù pracovaù na objedn·vke";
			default: return "Nezn·my typ ˙lohy";
		}
	}
}
