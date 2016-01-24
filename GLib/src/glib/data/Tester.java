package glib.data;

import glib.data.good.GTree;
import glib.data.good.oneDirList.old.GLinkedList;
import glib.data.oneDirList.ListO;


public class Tester {

	public static void main(String[] args) {
		
		//testujList();
		//testujGTree();
		testujGLinkedList();
		
	}

	private static void testujGMap(){
		GTree<String> tree = new GTree<String>(4,"Gabriel");
		
		tree.put(5, "Kika");
		tree.put(3, "fero");
		tree.put(7, "juro");
		tree.put(2, "maroš");
		System.out.println(1E5);
	}
	
	private static void testujGLinkedList(){
		GLinkedList<String> list = new GLinkedList<String>(4,"Gabriel");
		
		list.put(5, "Kika");
		list.put(3, "fero");
		list.put(7, "juro");
		list.put(2, "maroš");
		
		
		GLinkedList<String> list2 = new GLinkedList<String>(1, "sjngh");
		System.out.println(list2);
		list2.addAll(list);
		System.out.println(list2);
		list2.removeAll(list);
		System.out.println(list2);
		
		int search = 200;
//		System.out.println("na " + search + " mieste je " + list.get(search));
		System.out.println(list);
		list.remove(7);
		
		System.out.println(list);
		list.clear();
		list.put(3, "ferosafewsf");
		
		System.out.println(list);
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
