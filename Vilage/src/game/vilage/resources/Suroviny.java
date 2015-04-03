package game.vilage.resources;

import game.vilage.buldings.Buildings;

import java.util.HashMap;

public final class Suroviny {
	private static HashMap<Byte, Byte> resourcesAndProducers = new HashMap<Byte, Byte>();
	private static HashMap<Byte, String> resourcesNames = new HashMap<Byte, String>();
	
	public final static byte DREVO = 1;
	public final static byte OBILIE = 2;
	public final static byte MUKA = 3;
	public final static byte CHLIEB = 4;
	public final static byte KAMEN = 5;
	public final static byte MASO = 6;
	public final static byte NASTROJ = 8;
	public final static byte UHLIE = 9;
	public final static byte ZELEZNA_RUDA = 10;
	public final static byte ZELEZO = 11;
	public final static byte PIVO = 12;
	public final static byte KLOBASA = 13;
	public final static byte VLNA = 14;
	public final static byte SATY = 15;
	public final static byte RYBA = 16;
	
	static{
		setResourcesAndProducers();
		setNames();
	}
	
	public static HashMap<Byte, Integer> getAllDefault(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(DREVO, 0);
		res.put(OBILIE, 0);
		res.put(MUKA, 0);
		res.put(CHLIEB, 0);
		res.put(MASO, 0);
		res.put(VLNA, 0);
		res.put(KAMEN, 0);
		res.put(NASTROJ, 0);
		res.put(UHLIE, 0);
		res.put(ZELEZNA_RUDA, 0);
		res.put(ZELEZO, 0);
		res.put(PIVO, 0);
		res.put(KLOBASA, 0);
		res.put(RYBA, 0);
		res.put(SATY, 0);
		
		return res;
	}
	
	private static void setResourcesAndProducers(){
		resourcesAndProducers.put(DREVO, Buildings.DREVORUBAC);
		resourcesAndProducers.put(OBILIE, Buildings.FARMA);
		resourcesAndProducers.put(MUKA, Buildings.MLYNA);
		resourcesAndProducers.put(CHLIEB, Buildings.PEKAREN);
		resourcesAndProducers.put(KAMEN, Buildings.KAMENOLOM);
		resourcesAndProducers.put(MASO, Buildings.CHATRC_LOVCA);
		resourcesAndProducers.put(NASTROJ, Buildings.TESAR);
		resourcesAndProducers.put(UHLIE, Buildings.UHELNA_BANA);
		resourcesAndProducers.put(ZELEZNA_RUDA, Buildings.KOVAC);
		resourcesAndProducers.put(ZELEZO, Buildings.ZELEZNA_BANA);
		resourcesAndProducers.put(PIVO, Buildings.PIVOVAR);
		resourcesAndProducers.put(KLOBASA, Buildings.MASIAR);
		resourcesAndProducers.put(VLNA, Buildings.CHOVATEL_ZVIERAT);
		resourcesAndProducers.put(RYBA, Buildings.RYBAR);
		resourcesAndProducers.put(SATY, Buildings.KRAJCIR);
	}
	
	private static void setNames(){
		resourcesNames.put(DREVO, "Drevo");
		resourcesNames.put(OBILIE, "Obilie");
		resourcesNames.put(MUKA, "Múka");
		resourcesNames.put(CHLIEB, "Chlieb");
		resourcesNames.put(KAMEN, "Kameò");
		resourcesNames.put(MASO, "Mäso");
		resourcesNames.put(NASTROJ, "Nástroje");
		resourcesNames.put(UHLIE, "Uhlie");
		resourcesNames.put(ZELEZNA_RUDA, "Železná ruda");
		resourcesNames.put(ZELEZO, "Železo");
		resourcesNames.put(PIVO, "Pivo");
		resourcesNames.put(KLOBASA, "KLOBASA");
		resourcesNames.put(VLNA, "Vlna");
		resourcesNames.put(SATY, "Šaty");
		resourcesNames.put(RYBA, "Ryba");
	}
	
	public static byte getBuildingFromProduct(byte product){
		return resourcesAndProducers.get(product);
	}
	
	public static String getName(byte product){
		return resourcesNames.get(product);
	}
}

