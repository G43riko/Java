package glib.data;

import glib.data.good.GTree;
import glib.data.good.interfaces.GCollection;

public class GTester {
	
	
	private static <T> String testujGTree (GTree<T> item){
		
		
		return null;
	}
	
	private static String testujGCollection(GCollection item){
		String result = "testovanie iteme: " + item + " \n";
		
		if(item.isEmpty() && item.getSize() > 0)
			result += "testovanie 1 neúspšené, item je prázdny ale poèet prvkov je vaèší ako 0 \n";
		if(item.getSize() == 0 && !item.isEmpty())
			result += "testovanie 2 neúspšené, item má poèet prvkov 0 ale nieje prázdny \n";
		
		item.clear();
		
		if(!item.isEmpty())
			result += "testovanie 3 neúspšené, po zmazaní item nieje prázdny \n";
		
		if(item.getSize() > 0)
			result += "testovanie 4 neúspšené, po zmazaní je poèet prvkov v iteme vaèší ako 0\n";
		
		return result;
	}
}
