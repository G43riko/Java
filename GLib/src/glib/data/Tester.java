package glib.data;

import glib.data.oneDirList.ListO;
import glib.util.GLog;
import glib.util.vector.GVector3f;


public class Tester {

	public static void main(String[] args) {
//		testujList();
//		testujStrom();
		testujColisiu();
	}

	private static void testujStrom() {
//		BinaryThree strom = new BinaryThree();
//		strom.add(20, "gabo");
//		strom.add(10, "rene");
//		strom.add(5, "maùo");
//		strom.add(40, "david");
//		strom.add(35, "miöo");
//		strom.add(50, "simon");
//		strom.vypis();
////		int kde = 50;
////		System.out.println("na "+kde+" mieste sa naöiel: "+strom.get(kde));
////		strom.set(50, "marian");
////		System.out.println("na "+kde+" mieste sa naöiel: "+strom.get(kde));
//		strom.remove(20);
//		System.out.println("teraz sa mazalo");
//		strom.vypis();
	}
	
	private static void testujList(){
		ListO list = new ListO(1,2);
		list.add(20, "gabo");
		list.add(10, "rene");
		list.add(5, "maùo");
		list.add(40, "david");
		list.add(35, "miöo");
		list.add(50, "simon");
		
		int kde = 40;
		System.out.println("na "+kde+" mieste sa naöiel: "+list.get(kde));
		list.set(kde, "marian");
		System.out.println("na "+kde+" mieste sa naöiel: "+list.get(kde));
		System.out.println(list);
		list.remove(20);
		System.out.println(list);
	}

	private static void testujColisiu(){
		GVector3f a = new GVector3f(0,0,0);
		GVector3f b = new GVector3f(0,2,0);
		GVector3f c = new GVector3f(2,0,0);
		
		GVector3f d = new GVector3f(1,3,1);
		GVector3f f = new GVector3f(1,2,-1);
		GLog.write(GVector3f.intersectRayWithSquare(d, f, a, b, c)+"");
	}
}
