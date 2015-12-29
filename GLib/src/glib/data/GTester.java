package glib.data;

import glib.data.good.GBuffer;
import glib.data.good.GFront;
import glib.data.good.GTree;
import glib.data.good.avlTree.GBinaryTree;
import glib.data.good.interfaces.GCollection;
import glib.data.good.interfaces.GMap;
import glib.data.good.interfaces.GSimpleCollection;
import glib.data.good.oneDirList.GLinkedList;

public class GTester {
	
	public static void main(String[] args) {
		GBinaryTree<Integer, String> tree = new GBinaryTree<Integer, String>();
		
		tree.add(1, "a");
		tree.add(2, "b");
//		tree.add(3, "c");
//		tree.add(4, "d");
//		tree.add(5, "e");
		
		System.out.println("strom:\n" + tree);
	}
	
	public static void startTests(){

		GFront<Integer> front = new GFront<>();
		GBuffer<Integer> buffer = new GBuffer<>();
//		GLinkedList<Integer> linkedList = new GLinkedList(0, linkedList);
//		System.out.println(testujGSimpleCollection(buffer));
		
//		System.out.println(testujGMap(linkedList));
	}
	
	private static <T> String testujGMap (GMap<T, Integer> item){
		String result = testujGCollection(item);
		
		
		return result;
	}
	
	
	private static String testujGTree(GTree<Integer> item){
		String result = testujGCollection(item);
		
		
		return result;
	}
	
	private static String testujGSimpleCollection(GSimpleCollection<Integer> item){
		String result = testujGCollection(item);
		
		item.clear();
		item.add(1);
		item.add(2);
		item.add(3);
		item.add(4);
		
		if(item.getSize() != 4)
			result += "testovanie 5 neúspšené, do itemu sa pridali 4 prvky no vraj ich tam je " + item.getSize() + " \n";
		else
			result += "test 5 úspešne splnený\n";
		
		int res = item.get();
		if(res != 1 && res != 2 && res != 3 && res != 4)
			result += "testovanie 6 neúspšené, vrátil sa prvok " + res + " ktorý nebol pridaný\n";
		else
			result += "test 6 úspešne splnený\n";
		
		res = item.get();
		if(res != 1 && res != 2 && res != 3 && res != 4)
			result += "testovanie 7 neúspšené, vrátil sa prvok " + res + " ktorý nebol pridaný\n";
		else
			result += "test 7 úspešne splnený\n";
		
		res = item.get();
		if(res != 1 && res != 2 && res != 3 && res != 4)
			result += "testovanie 8 neúspšené, vrátil sa prvok " + res + " ktorý nebol pridaný\n";
		else
			result += "test 8 úspešne splnený\n";
		
		res = item.get();
		if(res != 1 && res != 2 && res != 3 && res != 4)
			result += "testovanie 9 neúspšené, vrátil sa prvok " + res + " ktorý nebol pridaný\n";
		else
			result += "test 9 úspešne splnený\n";
		
		
		item.clear();
		item.add(5);
		if(item.get() != 5)
			result += "testovanie 10 neúspšené, vrátil sa prvok " + res + " ktorý nebol pridaný\n";
		else
			result += "test 10 úspešne splnený\n";
			
		
		
		return result;
	}
	
	private static String testujGCollection(GCollection item){
		String result = "testovanie item: " + item + " \n";
		
		if(item.isEmpty() && item.getSize() > 0)
			result += "testovanie 1 neúspšené, item je prázdny ale poèet prvkov je vaèší ako 0 \n";
		else
			result += "test 1 úspešne splnený\n";
		if(item.getSize() == 0 && !item.isEmpty())
			result += "testovanie 2 neúspšené, item má poèet prvkov 0 ale nieje prázdny \n";
		else
			result += "test 2 úspešne splnený\n";
		
		item.clear();
		
		if(!item.isEmpty())
			result += "testovanie 3 neúspšené, po zmazaní item nieje prázdny \n";
		else
			result += "test 3 úspešne splnený\n";
		
		if(item.getSize() > 0)
			result += "testovanie 4 neúspšené, po zmazaní je poèet prvkov v iteme vaèší ako 0\n";
		else
			result += "test 4 úspešne splnený\n";
		
		
		return result;
	}
}
