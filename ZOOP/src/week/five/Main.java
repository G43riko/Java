package week.five;

import java.util.ArrayList;

public class Main {
	public static ArrayList<O> ocka = new ArrayList<O>();
	
	public static void main(String[] args) {
		Obyvatel prvy = new Obyvatel("Gabo",192,83);
		Obyvatel druhy = new Obyvatel();
		Obyvatel treti = new Obyvatel(prvy);
		
		Clovek first = new Clovek("Gabo",1993);
		Clovek second = new Clovek("Juro",1994);
		Clovek third = new Clovek("Fero",1995);
		Clovek.vypisInformacie(first);
		
		new S();
		
		for(int i=0 ; i<args.length ; i++){
			Main.ocka.add(new O(args[i],Integer.valueOf(args[++i])));
		}
	}

}
