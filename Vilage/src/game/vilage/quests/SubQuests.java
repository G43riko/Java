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
	
	public final static byte NABRUSIT_KOSU = 14;
	public final static byte IST_NA_POLE = 15;
	public final static byte POKOSIT_OBYLIE = 16;
	public final static byte POZBIERAT_OBYLIE = 17;
	public final static byte ZVIAZAT_OBYLIE = 18;
	public final static byte DONIEST_DOMOU = 19;
	
	public final static byte DONIEST_OBYLIE = 20;
	public final static byte ROZVIAZAT_OBYLIE = 20;
	public final static byte VYMENIT_KAMEN = 21;
	public final static byte POMLIET_OBYLIE = 22;
	public final static byte POZBIERAT_MUKU = 23;
	public final static byte ZABALIT_MUKU = 24;
	
	public final static byte PRIPRAVIT_INGREDIENCIE = 25;
	public final static byte ZAMIESAT_CESTO = 26;
	public final static byte NECHAT_VYKYSNUT_CESTO = 27;
	public final static byte PRIPRAVIT_PEC = 28;
	public final static byte UPIECT_CHLIEB = 29;
	public final static byte ZABALIT_CHLIEB = 30;
	
	public final static byte PRIPRAVIT_KROMPAC = 31;
	public final static byte IST_DO_KAMENOLOMU = 32;
	public final static byte NAJST_VHODNY_KAMEN = 33;
	public final static byte VYTAZIT_KAMEN = 34;
	public final static byte DOTIAHNUT_KAMEN_DOMOU = 35;
	public final static byte UPRAVIT_KAMEN = 36;
	
	public final static byte PRIPRAVIT_LUK = 37;
	//public final static byte IST_DO_LESA = 1;
	public final static byte VYSTOPOVAT_ZVIERA = 39;
	public final static byte ULOVIT_ZVIERA = 40;
	public final static byte DOTIAHNUT_ZVIERA_DOMOU = 41;
	public final static byte VYKUCHAT_ZVIERA = 42;
	
	public final static byte NABRUSIT_NOZNICE = 43;
	public final static byte NAJST_VHODNU_OVECKU = 44;
	public final static byte PRIVIAZAT_OVECKU = 45;
	public final static byte OSTRIHAT_OVECKU = 46;
	public final static byte DOLADIT_OVECKYN_VZHLAD = 47;
	public final static byte POZBIERAT_VLNU = 48;
	
	//public final static byte PRIPRAVIT_KROMPAC = 31;
	public final static byte IST_DO_BANE = 49;
	public final static byte NAJST_ZASOBY_UHLIA = 20;
	public final static byte VYTAZIT_UHLIE = 51;
	public final static byte VYNIEST_UHLIE_Z_BANE = 52;
	public final static byte ZABALIT_UHLIE = 53;
	
	//public final static byte PRIPRAVIT_KROMPAC = 31;
	//public final static byte IST_DO_BANE = 49;
	public final static byte NAJST_ZELEZNU_ZILU = 54;
	public final static byte VYTAZIT_ZELEZO = 55;
	public final static byte VYNIEST_ZELEZO_Z_BANE = 56;
	public final static byte ZABALIT_ZELEZO = 57;

	public final static byte NASIPAT_DO_PECE_UHLIE = 58;	
	public final static byte ROZZERAVIT_PEC = 59;
	public final static byte ROZTAVIT_ZELEZNU_RUDU = 60;
	public final static byte VLIAT_ZELEZO_DO_FORMY = 61;
	public final static byte NECHAT_ZELEZO_VYCHLADNUT = 62;
	public final static byte VYBRAT_ZELEZO_Z_FORMY = 63;
	
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
		
		list = new ArrayList<Byte>();
		list.add(NABRUSIT_KOSU);
		list.add(IST_NA_POLE);
		list.add(POKOSIT_OBYLIE);
		list.add(POZBIERAT_OBYLIE);
		list.add(ZVIAZAT_OBYLIE);
		list.add(DONIEST_DOMOU);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.ZOZAT_OBYLIE, list);
		
		list = new ArrayList<Byte>();
		list.add(DONIEST_OBYLIE);
		list.add(ROZVIAZAT_OBYLIE);
		list.add(VYMENIT_KAMEN);
		list.add(POMLIET_OBYLIE);
		list.add(POZBIERAT_MUKU);
		list.add(ZABALIT_MUKU);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.POMLIET_OBYLIE, list);
		
		list = new ArrayList<Byte>();
		list.add(PRIPRAVIT_INGREDIENCIE);
		list.add(ZAMIESAT_CESTO);
		list.add(NECHAT_VYKYSNUT_CESTO);
		list.add(PRIPRAVIT_PEC);
		list.add(UPIECT_CHLIEB);
		list.add(ZABALIT_CHLIEB);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.UPIECT_CHLIEB, list);
		
		list = new ArrayList<Byte>();
		list.add(PRIPRAVIT_KROMPAC);
		list.add(IST_DO_KAMENOLOMU);
		list.add(NAJST_VHODNY_KAMEN);
		list.add(VYTAZIT_KAMEN);
		list.add(DOTIAHNUT_KAMEN_DOMOU);
		list.add(UPRAVIT_KAMEN);
		list.add(ODOSLAT_OBJEDNAVKU);
		subQuestsToQuest.put(Quests.VYTAZIT_KAMEN, list);
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
