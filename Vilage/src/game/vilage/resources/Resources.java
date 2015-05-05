package game.vilage.resources;

import game.vilage.buldings.Buildings;

import java.util.HashMap;

/**
 * @author Gabriel
 *
 */
public final class Resources {
	
	private static class ResourcesData{
		private String name;
		private byte producer;
		public ResourcesData(String name, byte producer) {
			this.name = name;
			this.producer = producer;
		}
	}
	
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
	
	private static HashMap<Byte, ResourcesData> datas = new HashMap<Byte, ResourcesData>();
	
	static{
		initData();
	}
	
	public static byte getRandResource(){
		byte[] possibles = new byte[]{1, 8};
		return possibles[(int)(Math.random() * possibles.length)];
	}
	
	private static void initData(){
		datas.put(DREVO, new ResourcesData("Drevo", Buildings.DREVORUBAC));
		datas.put(OBILIE, new ResourcesData("Obilie", Buildings.FARMA));
		datas.put(MUKA, new ResourcesData("Múka", Buildings.MLYN));
		datas.put(CHLIEB, new ResourcesData("Chlieb", Buildings.PEKAREN));
		datas.put(MASO, new ResourcesData("Mäso", Buildings.CHATRC_LOVCA));
		datas.put(VLNA, new ResourcesData("Vlna", Buildings.CHOVATEL_ZVIERAT));
		datas.put(KAMEN, new ResourcesData("Kameò", Buildings.KAMENOLOM));
		datas.put(NASTROJ, new ResourcesData("Nástroj", Buildings.TESAR));
		datas.put(UHLIE, new ResourcesData("Uhlie", Buildings.UHELNA_BANA));
		datas.put(ZELEZNA_RUDA, new ResourcesData("Železná ruda", Buildings.ZELEZNA_BANA));
		datas.put(ZELEZO, new ResourcesData("Železo", Buildings.TAVIC));
		datas.put(PIVO, new ResourcesData("Pivo", Buildings.PIVOVAR));
		datas.put(KLOBASA, new ResourcesData("Klobása", Buildings.MASIAR));
		datas.put(RYBA, new ResourcesData("Ryba", Buildings.RYBAR));
		datas.put(SATY, new ResourcesData("Šaty", Buildings.KRAJCIR));
	}
	
	public static byte getBuildingFromProduct(byte product){
		if(datas.containsKey(product))
			return datas.get(product).producer;
		return 0;
	}
	
	public static String getName(byte product){
		if(datas.containsKey(product))
			return datas.get(product).name;
		return "neznáma budova";
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
}

