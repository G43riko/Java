package data;

import data.binaryThree.BinaryThree;

public class Tester {

	public static void main(String[] args) {
		BinaryThree strom = new BinaryThree();
		strom.add(20, "gabo");
		strom.add(10, "rene");
		strom.add(5, "mao");
		strom.add(40, "david");
		strom.add(35, "mišo");
		strom.add(50, "simon");
		
		int kde = 50;
		System.out.println("na "+kde+" mieste sa našiel: "+strom.get(kde));
	}

}
