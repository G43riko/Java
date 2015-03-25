package game.vilage.buldings;

import java.util.HashMap;

public abstract class Buildings {
	private static HashMap<Byte, String> buildingsNames = new HashMap<Byte, String>();
	
	public final static byte TAVIC = 0;
	public final static byte FARMA = 1;
	public final static byte MLYNA = 2;
	public final static byte PEKAREN = 3;
	public final static byte OBCHOD = 4;
	public final static byte CHATRC_LOVCA = 5;
	public final static byte CHOVATEL_ZVIERAT = 6;
	public final static byte BANA = 7;
	public final static byte DREVORUBAC = 8;
	public final static byte TESAR = 9;
	public final static byte MASIAR = 10;
	public final static byte KOZUSINAR = 11;
	public final static byte KOVAC = 12;
	public final static byte KAMENOLOM = 13;
	public final static byte PIVOVAR = 14;
	public final static byte RYBAR = 15;
	
	static{
		setNames();
	}

	private static void setNames() {
		buildingsNames.put(TAVIC, "Taviè");
		buildingsNames.put(FARMA, "Farmár");
		buildingsNames.put(PEKAREN, "Pekáreò");
		buildingsNames.put(OBCHOD, "Obchod");
		buildingsNames.put(CHATRC_LOVCA, "Chatrè lovca");
		buildingsNames.put(CHOVATEL_ZVIERAT, "Chovatel zvierat");
		buildingsNames.put(BANA, "Baòa");
		buildingsNames.put(DREVORUBAC, "Drevorubaè");
		buildingsNames.put(TESAR, "Tesár");
		buildingsNames.put(MASIAR, "Mäsiar");
		buildingsNames.put(KOZUSINAR, "Kožušinár");
		buildingsNames.put(KOVAC, "Kováè");
		buildingsNames.put(KAMENOLOM, "kameòolom");
		buildingsNames.put(PIVOVAR, "Pivovar");
		buildingsNames.put(RYBAR, "Rybár");
		
	}

	public static String getName(byte type){
		return buildingsNames.get(type);
	}
}
