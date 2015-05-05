package game.vilage.quests;

import java.util.List;

import util.MultiHashMap;

public class SubEvents {
//	private static HashMap<Byte, List<Byte>> subEventsToSubQuest = new HashMap<Byte, List<Byte>>();
	private static MultiHashMap<Byte, Byte> subEventsToSubQuest = new MultiHashMap<Byte, Byte>();
	
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
	
	public final static byte ODOSLANIE_NA_ZLU_ADRESU = 15;
	public final static byte ODOSLANIE_ZLEJ_OBJEDNAVKY = 16;
	
	static{
		setSubEventsToSubQuests();
		
	}
	
	private static void setSubEventsToSubQuests(){
		subEventsToSubQuest.add(SubQuests.ODOSLAT_OBJEDNAVKU,ODOSLANIE_NA_ZLU_ADRESU);
		subEventsToSubQuest.add(SubQuests.ODOSLAT_OBJEDNAVKU,ODOSLANIE_ZLEJ_OBJEDNAVKY);
		
		//DREVORUBAC
		
		subEventsToSubQuest.add(SubQuests.NABRUSIT_SEKERU,REZNE_PORANENIE);
		
		subEventsToSubQuest.add(SubQuests.IST_DO_LESA,STRATENIE_SA_V_LESE);
		subEventsToSubQuest.add(SubQuests.IST_DO_LESA,UTOK_VLKA);
		subEventsToSubQuest.add(SubQuests.IST_DO_LESA,UTOK_DIVIAKA);
		subEventsToSubQuest.add(SubQuests.IST_DO_LESA,POZIAR);
		
		subEventsToSubQuest.add(SubQuests.NAJST_VHODNY_STROM,STRATENIE_SA_V_LESE);
		subEventsToSubQuest.add(SubQuests.NAJST_VHODNY_STROM,UTOK_VLKA);
		subEventsToSubQuest.add(SubQuests.NAJST_VHODNY_STROM,UTOK_DIVIAKA);
		subEventsToSubQuest.add(SubQuests.NAJST_VHODNY_STROM,POZIAR);
		
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,UTOK_VLKA);
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,UTOK_DIVIAKA);
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,POZIAR);
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,ZAVALENIE_STROMOM);
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,SECNE_PORANENIE);
		subEventsToSubQuest.add(SubQuests.ZOTAT_STROM,ZLOMENA_SEKERA);
		
		subEventsToSubQuest.add(SubQuests.DOTIAHNUT_STROM,STRATENIE_SA_V_LESE);
		subEventsToSubQuest.add(SubQuests.DOTIAHNUT_STROM,UTOK_VLKA);
		subEventsToSubQuest.add(SubQuests.DOTIAHNUT_STROM,UTOK_DIVIAKA);
		subEventsToSubQuest.add(SubQuests.DOTIAHNUT_STROM,POZIAR);
		
		subEventsToSubQuest.add(SubQuests.ROZSEKAT_STROM,SECNE_PORANENIE);
		
		//TESAR
		
		subEventsToSubQuest.add(SubQuests.PRIPRAVIT_NASTROJE,ZLODEJI);
		subEventsToSubQuest.add(SubQuests.PRIPRAVIT_NASTROJE,ZLOMENY_NASTROJ);
		
		subEventsToSubQuest.add(SubQuests.VYTVORIT_NAKRES,ZIADNY_NASTROJ_NA_PISANIE);
		
		subEventsToSubQuest.add(SubQuests.VYTVORI_KONSTRUKCIU,ZLOMENY_NASTROJ);
		subEventsToSubQuest.add(SubQuests.VYTVORI_KONSTRUKCIU,KONCTRUKCIA_NESEDI);
		subEventsToSubQuest.add(SubQuests.VYTVORI_KONSTRUKCIU,KONSTRUKCIA_SA_NEPODOBA_NA_NAKRES);
		subEventsToSubQuest.add(SubQuests.VYTVORI_KONSTRUKCIU,ZNIECENIE_KONSTRUKCIE);
		
		subEventsToSubQuest.add(SubQuests.VYTVORIT_MODEL,ZLOMENY_NASTROJ);
		subEventsToSubQuest.add(SubQuests.VYTVORIT_MODEL,MODEL_NESEDI_KU_KONSTRUKCII);
		subEventsToSubQuest.add(SubQuests.VYTVORIT_MODEL,MODEL_SA_NEPODOBA_NA_NAKRES);
		subEventsToSubQuest.add(SubQuests.VYTVORIT_MODEL,ZNIECENIE_KONSTRUKCIE);
		
		subEventsToSubQuest.add(SubQuests.DOLADIT_DETAILY,ZLOMENY_NASTROJ);
		subEventsToSubQuest.add(SubQuests.DOLADIT_DETAILY,ZNICENIE_MODELU);
		
		subEventsToSubQuest.add(SubQuests.NALAKOVAT,ZLOMENY_NASTROJ);
		subEventsToSubQuest.add(SubQuests.NALAKOVAT,ZNICENIE_MODELU);
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


