package game.vilage.quests;

import java.util.List;

import util.MultiHashMap;

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
	
	private static MultiHashMap<Byte, Byte> subQuestsToQuest = new MultiHashMap<Byte, Byte>(); 
	
	static{
		setSubQuestsToQuestS();
	}
	
	private static void setSubQuestsToQuestS(){
		
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,NABRUSIT_SEKERU);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,IST_DO_LESA);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,NAJST_VHODNY_STROM);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,ZOTAT_STROM);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,DOTIAHNUT_STROM);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,ROZSEKAT_STROM);
		subQuestsToQuest.add(Quests.VYTAZIT_DREVO,ODOSLAT_OBJEDNAVKU);
		
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,PRIPRAVIT_NASTROJE);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,VYTVORIT_NAKRES);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,VYTVORI_KONSTRUKCIU);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,VYTVORIT_MODEL);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,DOLADIT_DETAILY);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,NALAKOVAT);
		subQuestsToQuest.add(Quests.VYTVORIT_NASTROJ,ODOSLAT_OBJEDNAVKU);
		
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,NABRUSIT_KOSU);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,IST_NA_POLE);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,POKOSIT_OBYLIE);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,POZBIERAT_OBYLIE);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,ZVIAZAT_OBYLIE);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,DONIEST_DOMOU);
		subQuestsToQuest.add(Quests.ZOZAT_OBILIE,ODOSLAT_OBJEDNAVKU);
		
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,DONIEST_OBYLIE);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,ROZVIAZAT_OBYLIE);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,VYMENIT_KAMEN);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,POMLIET_OBYLIE);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,POZBIERAT_MUKU);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,ZABALIT_MUKU);
		subQuestsToQuest.add(Quests.POMLIET_OBILIE,ODOSLAT_OBJEDNAVKU);
		
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,PRIPRAVIT_INGREDIENCIE);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,ZAMIESAT_CESTO);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,NECHAT_VYKYSNUT_CESTO);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,PRIPRAVIT_PEC);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,UPIECT_CHLIEB);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,ZABALIT_CHLIEB);
		subQuestsToQuest.add(Quests.UPIECT_CHLIEB,ODOSLAT_OBJEDNAVKU);
		
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,PRIPRAVIT_KROMPAC);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,IST_DO_KAMENOLOMU);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,NAJST_VHODNY_KAMEN);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,VYTAZIT_KAMEN);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,DOTIAHNUT_KAMEN_DOMOU);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,UPRAVIT_KAMEN);
		subQuestsToQuest.add(Quests.VYTAZIT_KAMEN,ODOSLAT_OBJEDNAVKU);
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
			case ZOTAT_STROM: return "Zoùaù strom";
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
