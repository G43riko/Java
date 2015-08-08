package glib.data;

import glib.data.good.GTree;
import glib.data.good.oneDirList.GLinkedList;
import glib.data.oneDirList.ListO;


public class Tester {

	public static void main(String[] args) {
		//testujList();
		//testujGTree();
		testujGLinkedList();
		
	}

	private static void testujGTree(){
		GTree<String> tree = new GTree<String>(4,"Gabriel");
		
		tree.add(5, "Kika");
		tree.add(3, "fero");
		tree.add(7, "juro");
		tree.add(2, "maro�");
		System.out.println(1E5);
	}
	
	private static void testujGLinkedList(){
		GLinkedList<String> list = new GLinkedList<String>(4,"Gabriel");
		
		list.add(5, "Kika");
		list.add(3, "fero");
		list.add(7, "juro");
		list.add(2, "maro�");
		
		
		GLinkedList<String> list2 = new GLinkedList<String>(1, "sjngh");
		System.out.println(list2);
		list2.addAll(list);
		System.out.println(list2);
		list2.removeAll(list);
		System.out.println(list2);
		list.forEachElement(a -> System.out.println("kv�k: "+a));
		
		int search = 200;
//		System.out.println("na " + search + " mieste je " + list.get(search));
		System.out.println(list);
		list.remove(7);
		
		System.out.println(list);
		list.clear();
		list.add(3, "ferosafewsf");
		
		System.out.println(list);
	}
	
	private static void testujList(){
		ListO list = new ListO(1,2);
		list.add(20, "gabo");
		list.add(10, "rene");
		list.add(5, "ma�o");
		list.add(40, "david");
		list.add(35, "mi�o");
		list.add(50, "simon");
		
		int kde = 40;
		System.out.println("na "+kde+" mieste sa na�iel: "+list.get(kde));
		list.set(kde, "marian");
		System.out.println("na "+kde+" mieste sa na�iel: "+list.get(kde));
		System.out.println(list);
		list.remove(20);
		System.out.println(list);
	}
}
