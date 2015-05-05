package game.vilage.quests;

import game.vilage.resources.Resources;

import java.util.HashMap;
import java.util.Map.Entry;

public class Quests {
	private static class QuestsDatas{
		private String name;
		private byte resource;
		public QuestsDatas(String name, byte resource) {
			this.name = name;
			this.resource = resource;
		}
	}
	
	public final static byte VYTAZIT_DREVO = 0;
	public final static byte VYTVORIT_NASTROJ = 1;
	public final static byte ZOZAT_OBILIE = 2;
	public final static byte POMLIET_OBILIE = 3;
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
	
	private static HashMap<Byte, QuestsDatas> datas = new HashMap<Byte, QuestsDatas>(); 
	
	static{
		initDatas();
	}
	
	private static void initDatas(){
		datas.put(VYTAZIT_DREVO, new QuestsDatas("vyta�i� drevo", Resources.DREVO));
		datas.put(VYTVORIT_NASTROJ, new QuestsDatas("Vytvori� n�stroj", Resources.NASTROJ));
		datas.put(ZOZAT_OBILIE, new QuestsDatas("Zo�a� obilie", Resources.OBILIE));
		datas.put(POMLIET_OBILIE, new QuestsDatas("Pomlie� obilie", Resources.MUKA));
		datas.put(UPIECT_CHLIEB, new QuestsDatas("Upiec� chlieb", Resources.CHLIEB));
		datas.put(SKOLIT_ZVIERA, new QuestsDatas("Skoli� zviera", Resources.MASO));
		datas.put(OSTRIHAT_OVECKU, new QuestsDatas("Ostriha� ove�ku", Resources.VLNA));
		datas.put(VYTAZIT_UHLIE, new QuestsDatas("Vyta�i� uhlie", Resources.UHLIE));
		datas.put(VYTAZIT_ZELEZNU_RUDU, new QuestsDatas("Vyta�i� �elezn� rudu", Resources.ZELEZNA_RUDA));
		datas.put(VYTAVIT_ZELEZO, new QuestsDatas("Vytavi� �elezo", Resources.ZELEZO));
		datas.put(UVARIT_PIVO, new QuestsDatas("Uvari� pivo", Resources.PIVO));
		datas.put(VYROBIT_KLOBASU, new QuestsDatas("Vyrobi� klob�su", Resources.KLOBASA));
		datas.put(UPLIEST_SATY, new QuestsDatas("Uplies� �aty", Resources.SATY));
		datas.put(ULOVIT_RYBU, new QuestsDatas("Ulovi� rybu", Resources.RYBA));
	}
	
	public static byte getQuestFromProduct(byte product){
		for(Entry<Byte, QuestsDatas> e: datas.entrySet()){
			if(e.getValue().resource == product)
				return e.getKey();
		}
		return 0;
	}
	public static String getName(byte type){
		if(datas.containsKey(type))
			return datas.get(type).name;
		
		return "Nezn�ma �loha";
	}
}
