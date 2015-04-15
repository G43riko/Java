package game.vilage.buldings;

import game.vilage.resources.Suroviny;

import java.util.HashMap;

public abstract class Buildings {
	public final static byte OBCHOD = 4;

	public final static byte FARMA = 1;
	public final static byte MLYNA = 2;
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

	public static String getName(byte type){
		switch(type){
			case TAVIC: return "Taviè";
			case FARMA: return "Farmár";
			case PEKAREN: return "Pekáreò";
			case OBCHOD: return "Obchod";
			case CHATRC_LOVCA: return "Chatrè lovca";
			case CHOVATEL_ZVIERAT: return "Chovatel zvierat";
			case ZELEZNA_BANA: return "Železná baòa";
			case DREVORUBAC: return "Drevorubaè";
			case TESAR: return "Tesár";
			case MASIAR: return "Mäsiar";
			case KOVAC: return "Kováè";
			case KAMENOLOM: return "kameòolom";
			case PIVOVAR: return "Pivovar";
			case RYBAR: return "Rybár";
			case MLYN: return "Mlyn";
			case KRAJCIR: return "Krajèír";
			case UHELNA_BANA: return "Uhelná baòa";
			default: return "Neznámy typ budovy";
		}
	}
	
	public static HashMap<Byte, Integer> getProduced(byte type){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		switch(type){
			case DREVORUBAC:
				res.put(Suroviny.DREVO, 5);
				break;
			case TESAR:
				res.put(Suroviny.NASTROJ, 8);
				break;
			case FARMA:
				res.put(Suroviny.OBILIE,4);
				break;
			case PEKAREN:
				res.put(Suroviny.CHLIEB,4);
				break;
			case CHATRC_LOVCA:
				res.put(Suroviny.MASO,3);
				break;
			case MASIAR:
				res.put(Suroviny.KLOBASA,5);
				break;
			case KOVAC:
				res.put(Suroviny.NASTROJ,5);
				break;
			case KAMENOLOM:
				res.put(Suroviny.KAMEN,5);
				break;
			case PIVOVAR:
				res.put(Suroviny.PIVO,5);
				break;
			case RYBAR:
				res.put(Suroviny.KLOBASA,5);
				break;
			case TAVIC:
				res.put(Suroviny.ZELEZO,5);
				break;
			case CHOVATEL_ZVIERAT:
				res.put(Suroviny.VLNA,5);
				break;
			case ZELEZNA_BANA:
				res.put(Suroviny.ZELEZNA_RUDA,5);
				break;
			case UHELNA_BANA:
				res.put(Suroviny.UHLIE,5);
				break;
			case MLYN:
				res.put(Suroviny.MUKA,5);
				break;
			case KRAJCIR:
				res.put(Suroviny.SATY,5);
				break;
		}
		return res;
	}
	
	public static HashMap<Byte, Integer> getRequired(byte type){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		switch(type){
			case DREVORUBAC:
				res.put(Suroviny.NASTROJ, 3);
				break;
			case TESAR:
				res.put(Suroviny.DREVO, 3);
				break;
			case FARMA:
				res.put(Suroviny.NASTROJ,4);
				break;
			case PEKAREN:
				res.put(Suroviny.MUKA,4);
				break;
			case CHATRC_LOVCA:
				res.put(Suroviny.NASTROJ,3);
				break;
			case MASIAR:
				res.put(Suroviny.NASTROJ,5);
				res.put(Suroviny.MASO,3);
				break;
			case KOVAC:
				res.put(Suroviny.NASTROJ,5);
				res.put(Suroviny.ZELEZO,5);
				res.put(Suroviny.UHLIE,5);
				break;
			case KAMENOLOM:
				res.put(Suroviny.NASTROJ,5);
				break;
			case PIVOVAR:
				res.put(Suroviny.OBILIE,5);
				break;
			case RYBAR:
				res.put(Suroviny.NASTROJ,5);
				break;
			case TAVIC:
				res.put(Suroviny.ZELEZNA_RUDA,5);
				res.put(Suroviny.UHLIE,5);
				break;
			case CHOVATEL_ZVIERAT:
				res.put(Suroviny.OBILIE,5);
				break;
			case ZELEZNA_BANA:
				res.put(Suroviny.NASTROJ,5);
				break;
			case UHELNA_BANA:
				res.put(Suroviny.NASTROJ,5);
				break;
			case MLYN:
				res.put(Suroviny.OBILIE,5);
				res.put(Suroviny.KAMEN,5);
				break;
			case KRAJCIR:
				res.put(Suroviny.VLNA,5);
				break;
		}
		return res;
	}
}
