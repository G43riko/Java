package game.vilage.buldings;

import game.vilage.resources.Resources;

import java.util.HashMap;

/**
 * 
 * @author Gabriel
 *
 */
public abstract class Buildings {
	
	/**
	 * vnorená trieda ktorá uskladnuje dáta o budovách
	 */
	private static class BuildingData{
		private String name;
		private HashMap<Byte, Integer> produce = new HashMap<Byte, Integer>();
		private HashMap<Byte, Integer> requiere = new HashMap<Byte, Integer>();
		
		private BuildingData(String name, HashMap<Byte, Integer> produce, HashMap<Byte, Integer> requiere) {
			this.name = name;
			this.produce = produce;
			this.requiere = requiere;
		}
	}
	
	public final static byte OBCHOD = 4;

	public final static byte FARMA = 1;
	public final static byte PEKAREN = 3;
	public final static byte CHATRC_LOVCA = 5;
	public final static byte CHOVATEL_ZVIERAT = 6;
	public final static byte ZELEZNA_BANA = 7;
	public final static byte DREVORUBAC = 8;
	public final static byte TESAR = 9;
	public final static byte MASIAR = 10;
	public final static byte UHELNA_BANA = 11;
	public final static byte KOVAC = 12;
	public final static byte KAMENOLOM = 13;
	public final static byte PIVOVAR = 14;
	public final static byte RYBAR = 15;
	public final static byte TAVIC = 16;
	public final static byte MLYN = 17;
	public final static byte KRAJCIR = 18;

	private static HashMap<Byte, BuildingData> datas = new HashMap<Byte, BuildingData>(); 
	
	static{	
		HashMap<Byte, Integer> products = new HashMap<Byte, Integer>();
		HashMap<Byte, Integer> requiereds = new HashMap<Byte, Integer>();
		
		datas.put(OBCHOD, new BuildingData("Obchod", products, requiereds));
		
		products = new HashMap<Byte, Integer>();
		products.put(Resources.OBILIE,4);
		requiereds = new HashMap<Byte, Integer>();
		requiereds.put(Resources.NASTROJ,4);
		datas.put(FARMA, new BuildingData("Farma", products, requiereds));
		
		products = new HashMap<Byte, Integer>();
		products.put(Resources.DREVO, 5);
		requiereds = new HashMap<Byte, Integer>();
		requiereds.put(Resources.NASTROJ, 3);
		datas.put(DREVORUBAC, new BuildingData("Drevorubaè", products, requiereds));
		
		products = new HashMap<Byte, Integer>();
		products.put(Resources.NASTROJ, 8);
		requiereds = new HashMap<Byte, Integer>();
		requiereds.put(Resources.DREVO, 3);
		datas.put(TESAR, new BuildingData("Tesár", products, requiereds));
		
	}
	public static String getName(byte type){
		if(datas.containsKey(type))
			return datas.get(type).name;
		
		return "Neznáma budova";
	}
	
	public static HashMap<Byte, Integer> getProduced(byte type){
		if(datas.containsKey(type))
			return datas.get(type).produce;
		
		return null;
	}
	
	public static HashMap<Byte, Integer> getRequired(byte type){
		if(datas.containsKey(type))
			return datas.get(type).requiere;
		
		return null;
	}

//	public static String getName(byte type){
//		switch(type){
//			case TAVIC: return "Taviè";
//			case FARMA: return "Farmár";
//			case PEKAREN: return "Pekáreò";
//			case OBCHOD: return "Obchod";
//			case CHATRC_LOVCA: return "Chatrè lovca";
//			case CHOVATEL_ZVIERAT: return "Chovatel zvierat";
//			case ZELEZNA_BANA: return "Železná baòa";
//			case DREVORUBAC: return "Drevorubaè";
//			case TESAR: return "Tesár";
//			case MASIAR: return "Mäsiar";
//			case KOVAC: return "Kováè";
//			case KAMENOLOM: return "kameòolom";
//			case PIVOVAR: return "Pivovar";
//			case RYBAR: return "Rybár";
//			case MLYN: return "Mlyn";
//			case KRAJCIR: return "Krajèír";
//			case UHELNA_BANA: return "Uhelná baòa";
//			default: return "Neznámy typ budovy";
//		}
//	}
	
//	public static HashMap<Byte, Integer> getProduced(byte type){
//		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
//		switch(type){
//			case DREVORUBAC:
//				res.put(Suroviny.DREVO, 5);
//				break;
//			case TESAR:
//				res.put(Suroviny.NASTROJ, 8);
//				break;
//			case FARMA:
//				res.put(Suroviny.OBILIE,4);
//				break;
//			case PEKAREN:
//				res.put(Suroviny.CHLIEB,4);
//				break;
//			case CHATRC_LOVCA:
//				res.put(Suroviny.MASO,3);
//				break;
//			case MASIAR:
//				res.put(Suroviny.KLOBASA,5);
//				break;
//			case KOVAC:
//				res.put(Suroviny.NASTROJ,5);
//				break;
//			case KAMENOLOM:
//				res.put(Suroviny.KAMEN,5);
//				break;
//			case PIVOVAR:
//				res.put(Suroviny.PIVO,5);
//				break;
//			case RYBAR:
//				res.put(Suroviny.KLOBASA,5);
//				break;
//			case TAVIC:
//				res.put(Suroviny.ZELEZO,5);
//				break;
//			case CHOVATEL_ZVIERAT:
//				res.put(Suroviny.VLNA,5);
//				break;
//			case ZELEZNA_BANA:
//				res.put(Suroviny.ZELEZNA_RUDA,5);
//				break;
//			case UHELNA_BANA:
//				res.put(Suroviny.UHLIE,5);
//				break;
//			case MLYN:
//				res.put(Suroviny.MUKA,5);
//				break;
//			case KRAJCIR:
//				res.put(Suroviny.SATY,5);
//				break;
//		}
//		return res;
//	}
//	
//	public static HashMap<Byte, Integer> getRequired(byte type){
//		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
//		switch(type){
//			case DREVORUBAC:
//				res.put(Suroviny.NASTROJ, 3);
//				break;
//			case TESAR:
//				res.put(Suroviny.DREVO, 3);
//				break;
//			case FARMA:
//				res.put(Suroviny.NASTROJ,4);
//				break;
//			case PEKAREN:
//				res.put(Suroviny.MUKA,4);
//				break;
//			case CHATRC_LOVCA:
//				res.put(Suroviny.NASTROJ,3);
//				break;
//			case MASIAR:
//				res.put(Suroviny.NASTROJ,5);
//				res.put(Suroviny.MASO,3);
//				break;
//			case KOVAC:
//				res.put(Suroviny.NASTROJ,5);
//				res.put(Suroviny.ZELEZO,5);
//				res.put(Suroviny.UHLIE,5);
//				break;
//			case KAMENOLOM:
//				res.put(Suroviny.NASTROJ,5);
//				break;
//			case PIVOVAR:
//				res.put(Suroviny.OBILIE,5);
//				break;
//			case RYBAR:
//				res.put(Suroviny.NASTROJ,5);
//				break;
//			case TAVIC:
//				res.put(Suroviny.ZELEZNA_RUDA,5);
//				res.put(Suroviny.UHLIE,5);
//				break;
//			case CHOVATEL_ZVIERAT:
//				res.put(Suroviny.OBILIE,5);
//				break;
//			case ZELEZNA_BANA:
//				res.put(Suroviny.NASTROJ,5);
//				break;
//			case UHELNA_BANA:
//				res.put(Suroviny.NASTROJ,5);
//				break;
//			case MLYN:
//				res.put(Suroviny.OBILIE,5);
//				res.put(Suroviny.KAMEN,5);
//				break;
//			case KRAJCIR:
//				res.put(Suroviny.VLNA,5);
//				break;
//		}
//		return res;
//	}
}
