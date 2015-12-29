package glib.data.good.oneDirList;

import java.util.ArrayList;

import glib.data.good.interfaces.GMap;

public final class GLinkedList<T> implements GMap<Integer, T>{
	private Node<T> first;
	
	//CONSTRUCTORS
	
	public GLinkedList(int key, T value){
		first = new Node<T>(key, value, null);
	}
	
	//ADDERS
	
	public T add(Integer key, T value){
		if(first == null){
			first = new Node<T>(key, value, null);
			return null;
		}
			
		
		if(first.getKey() > key)
			first = new Node<T>(key, value, first);
		else 
			first.add(key, value);
		

		return null;
	}
	
	//GETTERS
	
	@Override
	public T get(Integer key){
		if(first == null)
			return null;
		
		return first.get(key);
	}
	
	//REMOVERS
	
	@Override
	public T remove(Integer key){
		if(first == null)
		
		if(first.getKey() == key){
			first = first.getNext();
		}
		else
			first.remove(key);
		

		return null;
	}
	
	//OTHERS
	
	@Override
	public int getSize(){
		if(first == null)
			return 0;
		
		return first.getSize();
	}
	
	@Override
	public String toString() {
		return first.toString();
	}

	@Override
	public boolean containsKey(Integer key) {
		if(first == null)
			return false;
		
		if(first.getKey() == key)
			return true;
		else
			return first.containsKey(key);
	}

	public boolean constainsValue(T value) {
		if(first == null)
			return false;
		
		if(first.getValue() == value)
			return true;
		else
			return first.constainsValue(value);
	}

	@Override
	public void clear() {
		if(first == null)
			return;
		
		first.clear();
		first = null;
	}


	@Override
	public ArrayList<T> getAllValues() {
		if(first == null)
			return new ArrayList<T>();
		return first.getAllValues();
	}

	@Override
	public void addAll(GMap<Integer, T> list) {
		((GLinkedList<T>)list).first
							  .getAllNodes()
							  .stream()
							  .forEach(a -> add(a.getKey(), a.getValue()));
	}

	@Override
	public void removeAll(GMap<Integer, T> list) {
		((GLinkedList<T>)list).first
							  .getAllNodes()
							  .stream()
							  .forEach(a -> remove(a.getKey()));
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
