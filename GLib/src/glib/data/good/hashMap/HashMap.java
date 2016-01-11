package glib.data.good.hashMap;

import glib.data.good.interfaces.GMap;

public class HashMap<S, T> implements GMap<S, T>{
	private final static int DEFAULT_SIZE = 10;
	private Node<S, T>[] array;
	private int nodes = 0;
	private int size;
	private int free;
	
	public HashMap(){
		
		initMap(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	private void initMap(int arraySize){
		array = (Node<S, T>[])new Node[arraySize];
		size = arraySize;
		free = arraySize;
	}
	
//	private void rehash(){
//		size *= 2;
//	}
	
	public T put(S key, T value){
		int index = key.hashCode() % size;
		Node<S, T> novy = new Node<S, T>(key, value);
		
		if(array[index] == null){
			array[index] = novy;
			free--;
			nodes++;
		}
		else{
			Node<S, T> act;
			for(act = array[index] ; act.getNext() != null ; act = act.getNext())
				if(act.getKey().equals(key)){
					act.setValue(value);
					return value;
				}
			nodes++;
			act.setNext(novy);		
		}
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i=0 ; i<size ; i++)
			result.append("array[" + i +"]:" + array[i] + "\n");
		
		return result.toString();
	}
	
	public T get(S key){
		int index = key.hashCode() % size;
		Node<S, T> act;
		for(act = array[index] ; act != null ; act = act.getNext())
			if(act.getKey() == (key))
				return act.getValue();
	
		return null;
	}
	
	public int getSize(){
		return nodes;
	}
	
	public static void main(String[] args) {
		HashMap<Integer, String > mapa = new HashMap<Integer, String>();
		mapa.put(1, "a");
		mapa.put(2,"b");
		mapa.put(3,"c");
		mapa.put(4,"d");
		mapa.put(5,"e");
		
		System.out.println(mapa);
	}

	@Override
	public boolean isEmpty() {
		return size == free;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(S key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean constainsValue(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(S key) {
		// TODO Auto-generated method stub
		return false;
	}
}
