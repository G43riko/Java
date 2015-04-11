package game.vilage.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubEvents {
	private static HashMap<Byte, List<Byte>> subEventsToSubQuest = new HashMap<Byte, List<Byte>>();
	
	public final static byte REZNE_PORANENIE = 1;
	public final static byte STRATENIE_SA_V_LESE = 2;
	public final static byte UTOK_VLKA = 3;
	public final static byte UTOK_DIVIAKA = 4;
	public final static byte POZIAR = 5;
	public final static byte ZAVALENIE_STROMOM = 6;
	public final static byte SECNE_PORANENIE = 7;
	public final static byte ZLOMENA_SEKERA = 8;
	
	public final static byte ZLODEJI = 9;
	public final static byte ZLOMENY_NASTROJ = 10;
	public final static byte ZIADNY_NASTROJ_NA_PISANIE = 11;
	public final static byte KONCTRUKCIA_NESEDI = 12;
	public final static byte ZNIECENIE_KONSTRUKCIE = 19;
	public final static byte KONSTRUKCIA_SA_NEPODOBA_NA_NAKRES = 17;
	public final static byte MODEL_NESEDI_KU_KONSTRUKCII = 13;
	public final static byte MODEL_SA_NEPODOBA_NA_NAKRES = 18;
	public final static byte ZNICENIE_MODELU = 14;
	
//	public final static byte ZNICENIE_MODELU = 20;
	
	public final static byte ODOSLANIE_NA_ZLU_ADRESU = 15;
	public final static byte ODOSLANIE_ZLEJ_OBJEDNAVKY = 16;
	
	static{
		setSubEventsToSubQuests();
		
	}
	
	private static void setSubEventsToSubQuests(){
		List<Byte> list = new ArrayList<Byte>();
		list.add(ODOSLANIE_NA_ZLU_ADRESU);
		list.add(ODOSLANIE_ZLEJ_OBJEDNAVKY);
		subEventsToSubQuest.put(SubQuests.ODOSLAT_OBJEDNAVKU, list);
		
		//DREVORUBAC
		
		list = new ArrayList<Byte>();
		list.add(REZNE_PORANENIE);
		subEventsToSubQuest.put(SubQuests.NABRUSIT_SEKERU, list);
		
		list = new ArrayList<Byte>();
		list.add(STRATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.IST_DO_LESA, list);
		
		list = new ArrayList<Byte>();
		list.add(STRATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.NAJST_VHODNY_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		list.add(ZAVALENIE_STROMOM);
		list.add(SECNE_PORANENIE);
		list.add(ZLOMENA_SEKERA);
		subEventsToSubQuest.put(SubQuests.ZOTAT_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(STRATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.DOTIAHNUT_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(SECNE_PORANENIE);
		subEventsToSubQuest.put(SubQuests.ROZSEKAT_STROM, list);
		
		//TESAR
		
		list = new ArrayList<Byte>();
		list.add(ZLODEJI);
		list.add(ZLOMENY_NASTROJ);
		subEventsToSubQuest.put(SubQuests.PRIPRAVIT_NASTROJE, list);
		
		list = new ArrayList<Byte>();
		list.add(ZIADNY_NASTROJ_NA_PISANIE);
		subEventsToSubQuest.put(SubQuests.VYTVORIT_NAKRES, list);
		
		list = new ArrayList<Byte>();
		list.add(ZLOMENY_NASTROJ);
		list.add(KONCTRUKCIA_NESEDI);
		list.add(KONSTRUKCIA_SA_NEPODOBA_NA_NAKRES);
		list.add(ZNIECENIE_KONSTRUKCIE);
		subEventsToSubQuest.put(SubQuests.VYTVORI_KONSTRUKCIU, list);
		
		list = new ArrayList<Byte>();
		list.add(ZLOMENY_NASTROJ);
		list.add(MODEL_NESEDI_KU_KONSTRUKCII);
		list.add(MODEL_SA_NEPODOBA_NA_NAKRES);
		list.add(ZNIECENIE_KONSTRUKCIE);
		subEventsToSubQuest.put(SubQuests.VYTVORIT_MODEL, list);
		
		list = new ArrayList<Byte>();
		list.add(ZLOMENY_NASTROJ);
		list.add(ZNICENIE_MODELU);
		subEventsToSubQuest.put(SubQuests.DOLADIT_DETAILY, list);
		
		list = new ArrayList<Byte>();
		list.add(ZLOMENY_NASTROJ);
		list.add(ZNICENIE_MODELU);
		subEventsToSubQuest.put(SubQuests.NALAKOVAT, list);
	}

	public static List<Byte> getSubEventsfromsubQuest(byte subQuest){
		return subEventsToSubQuest.get(subQuest);
	}
	
	public static String getName(byte type){
		switch(type){
			case ODOSLANIE_NA_ZLU_ADRESU: return "Objedn�vka bola odoslan� na zl� adresu";
			case ODOSLANIE_ZLEJ_OBJEDNAVKY: return "Bola odoslan� zl� objedn�vka";
					
			case REZNE_PORANENIE: return "Stalo sa v�ne rezn� poranienie";
			case STRATENIE_SA_V_LESE: return "Stratil som sa v lese asi to nezvl�dnem";
			case UTOK_VLKA: return "Napadol ma vlk";
			case UTOK_DIVIAKA: return "Napadol ma obrovsk� diviak";
			case POZIAR: return "Hor� tu! nies� �iadne stromy na r�banie";
			case ZAVALENIE_STROMOM: return "Padol na m�a strom. Pomoooooc";
			case SECNE_PORANENIE: return "Sekol som sa sekerov.";
			case ZLOMENA_SEKERA: return "Zlomila sa mi sekera a rukami strom neskol�m";
			
			case ZLODEJI: return "Zlodeji ukradli v�etk� n�stroje";
			case ZLOMENY_NASTROJ: return "Zlomil sa n�stroj";
			case ZIADNY_NASTROJ_NA_PISANIE: return "Nies� n�stroje potrebn� na p�sanie";
			case KONCTRUKCIA_NESEDI: return "Kon�tukcia nesed� k modelu";
			case KONSTRUKCIA_SA_NEPODOBA_NA_NAKRES: return "Kon�trukcie sa v�bec nepodob� na n�kres";
			case MODEL_NESEDI_KU_KONSTRUKCII: return "Model nesed� na kon�trukciu";
			case MODEL_SA_NEPODOBA_NA_NAKRES: return "Model sa nepodob� na n�kres";
			case ZNICENIE_MODELU: return "Model bol nechtiac zni�en�";
			case ZNIECENIE_KONSTRUKCIE: return "Zlomila sa kon�trukcia";
			
			default: return "nezn�my typ udalosti";
		}
	}
}


