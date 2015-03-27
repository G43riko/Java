package game.vilage.quests;

import game.vilage.buldings.Buildings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubEvents {
	private static HashMap<Byte, List<Byte>> subEventsToSubQuest = new HashMap<Byte, List<Byte>>();
	
	public final static byte REZNE_PORANENIE = 1;
	public final static byte STATENIE_SA_V_LESE = 2;
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
	public final static byte MODEL_NESEDI_KU_KONSTRUKCII = 13;
	public final static byte ZNICENIE_MODELU = 14;
	
	static{
		//drevorubaè
		
		List<Byte> list = new ArrayList<Byte>();
		list.add(REZNE_PORANENIE);
		subEventsToSubQuest.put(SubQuests.NABRUSIT_SEKERU, list);
		
		list = new ArrayList<Byte>();
		list.add(STATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.IST_DO_LESA, list);
		
		list = new ArrayList<Byte>();
		list.add(STATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.NAJST_VHODNY_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(STATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		list.add(ZAVALENIE_STROMOM);
		list.add(SECNE_PORANENIE);
		list.add(ZLOMENA_SEKERA);
		subEventsToSubQuest.put(SubQuests.ZOTAT_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(STATENIE_SA_V_LESE);
		list.add(UTOK_VLKA);
		list.add(UTOK_DIVIAKA);
		list.add(POZIAR);
		subEventsToSubQuest.put(SubQuests.DOTIAHNUT_STROM, list);
		
		list = new ArrayList<Byte>();
		list.add(SECNE_PORANENIE);
		subEventsToSubQuest.put(SubQuests.ROZSEKAT_STROM, list);
		
		//TESAR
		
		list = new ArrayList<Byte>();
		list.add(SECNE_PORANENIE);
		subEventsToSubQuest.put(SubQuests.ROZSEKAT_STROM, list);
	}
}


