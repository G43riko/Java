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
			result += "testovanie 1 ne�sp�en�, item je pr�zdny ale po�et prvkov je va�� ako 0 \n";
		if(item.getSize() == 0 && !item.isEmpty())
			result += "testovanie 2 ne�sp�en�, item m� po�et prvkov 0 ale nieje pr�zdny \n";
		
		item.clear();
		
		if(!item.isEmpty())
			result += "testovanie 3 ne�sp�en�, po zmazan� item nieje pr�zdny \n";
		
		if(item.getSize() > 0)
			result += "testovanie 4 ne�sp�en�, po zmazan� je po�et prvkov v iteme va�� ako 0\n";
		
		return result;
	}
}
