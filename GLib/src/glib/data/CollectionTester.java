package glib.data;

import java.util.*;

public class CollectionTester{
	private static Map<Integer, Integer> hashMap 		= new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> treeMap 		= new TreeMap<Integer, Integer>();
	private static Map<Integer, Integer> linkedHashMap 	= new LinkedHashMap<Integer, Integer>();
	private static Map<Integer, Integer> hashTable 		= new Hashtable<Integer, Integer>();
	
	private static List<Integer>		 arrayList		= new ArrayList<Integer>();
	private static List<Integer>		 linkedList		= new LinkedList<Integer>();
	private static List<Integer>		 vector			= new Vector<Integer>();
	
	private static Set<Integer>		  	 hashSet		= new HashSet<Integer>();
	private static Set<Integer>		  	 treeSet		= new TreeSet<Integer>();
	
	
	public static void main(String[] args) {
		int num = 10000;
		
		testCollection(arrayList,  Integer.class, num);
		testCollection(linkedList, Integer.class, num);
		testCollection(vector, 	   Integer.class, num);
		
		testCollection(hashSet, Integer.class, num);
		testCollection(treeSet, Integer.class, num);
		
		testMap(hashMap, 	   Integer.class, Integer.class, num);
		testMap(treeMap, 	   Integer.class, Integer.class, num);
		testMap(linkedHashMap, Integer.class, Integer.class, num);
		testMap(hashTable,	   Integer.class, Integer.class, num);
		
	}
	
	public static<T> void  testCollection(Collection<T> collection, Class<T> clazz, int num){
		collection.clear();
		
		System.out.println("testuje sa: " + collection.getClass().getSimpleName());
		
		if(!collection.isEmpty() || collection.size() > 0)
			System.out.println("zle to rata: isEmpty:" + collection.isEmpty() + ", size: " + collection.size());
		
		
		long time = System.currentTimeMillis();
		for(int i=0 ; i<num ; i++)
			collection.add(clazz.cast((int)(Math.random() * Integer.MAX_VALUE)));
		time = System.currentTimeMillis() - time;
		System.out.println(num + " prvkov to pridalo za " + time + " ms");
		
		time = System.currentTimeMillis();
		boolean res = collection.contains(-1);
		time = System.currentTimeMillis() - time;
		System.out.println("neexistujúci prvok to našlo za " + time + " ms s výsledkom: " + res);
		
		time = System.currentTimeMillis();
		collection.clear();
		time = System.currentTimeMillis() - time;
		System.out.println("vymazaloto za za " + time + " ms");
	}
	
	public static<S, T> void  testMap(Map<S, T> map, Class<S> clazzS, Class<T> clazzT, int num){
		map.clear();
		
		System.out.println("testuje sa: " + map.getClass().getSimpleName());
		
		if(!map.isEmpty() || map.size() > 0)
			System.out.println("zle to rata: isEmpty:" + map.isEmpty() + ", size: " + map.size());
		
		
		long time = System.currentTimeMillis();
		for(int i=0 ; i<num ; i++)
			map.put(clazzS.cast((int)(Math.random() * Integer.MAX_VALUE)), clazzT.cast((int)(Math.random() * Integer.MAX_VALUE)));
		time = System.currentTimeMillis() - time;
		System.out.println(num + " prvkov to pridalo za " + time + " ms");
		
		time = System.currentTimeMillis();
		boolean res = map.containsKey(-1);
		time = System.currentTimeMillis() - time;
		System.out.println("neexistujúci klúč to našlo za " + time + " ms s výsledkom: " + res);
		
		time = System.currentTimeMillis();
		res = map.containsValue(-1);
		time = System.currentTimeMillis() - time;
		System.out.println("neexistujúci prvok to našlo za " + time + " ms s výsledkom: " + res);
		
		time = System.currentTimeMillis();
		map.clear();
		time = System.currentTimeMillis() - time;
		System.out.println("vymazaloto za za " + time + " ms");
	}
	
	Collection<?> collection = new Collection<Integer>(){
		@Override
		public boolean addAll(Collection<? extends Integer> c) {return false;}
		public boolean containsAll(Collection<?> c) {return false;}
		public boolean removeAll(Collection<?> c) {return false;}
		public boolean retainAll(Collection<?> c) {return false;}
		public boolean contains(Object o) {return false;}
		public boolean remove(Object o) {return false;}
		public boolean add(Integer e) {return false;}
		public boolean isEmpty() {return false;}
		public Iterator<Integer> iterator() {return null;}
		public <T> T[] toArray(T[] a) {return null;}
		public Object[] toArray() {return null;}
		public int size() {return 0;}
		public void clear() {}
		
	};

	
	
}
