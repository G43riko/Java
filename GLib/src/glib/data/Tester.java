package glib.data;

import glib.data.oneDirList.ListO;


public class Tester {

	public static void main(String[] args) {
		testujList();
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
