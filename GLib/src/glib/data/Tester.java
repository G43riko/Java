package glib.data;

import glib.data.oneDirList.ListO;


public class Tester {

	public static void main(String[] args) {
		testujList();
//		testujStrom();
	}

	private static void testujStrom() {
//		BinaryThree strom = new BinaryThree();
//		strom.add(20, "gabo");
//		strom.add(10, "rene");
//		strom.add(5, "mao");
//		strom.add(40, "david");
//		strom.add(35, "mišo");
//		strom.add(50, "simon");
//		strom.vypis();
////		int kde = 50;
////		System.out.println("na "+kde+" mieste sa našiel: "+strom.get(kde));
////		strom.set(50, "marian");
////		System.out.println("na "+kde+" mieste sa našiel: "+strom.get(kde));
//		strom.remove(20);
//		System.out.println("teraz sa mazalo");
//		strom.vypis();
	}
	
	private static void testujList(){
		ListO list = new ListO(1,2);
		list.add(20, "gabo");
		list.add(10, "rene");
		list.add(5, "mao");
		list.add(40, "david");
		list.add(35, "mišo");
		list.add(50, "simon");
		
		int kde = 40;
		System.out.println("na "+kde+" mieste sa našiel: "+list.get(kde));
		list.set(kde, "marian");
		System.out.println("na "+kde+" mieste sa našiel: "+list.get(kde));
		System.out.println(list);
		list.remove(20);
		System.out.println(list);
	}

}
