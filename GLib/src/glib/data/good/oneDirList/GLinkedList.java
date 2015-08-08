package glib.data.good.oneDirList;

import java.util.ArrayList;
import java.util.function.Consumer;

import glib.data.good.interfaces.GList;

public final class GLinkedList<T> implements GList<T>{
	private Node<T> first;
	
	public GLinkedList(int key, T value){
		first = new Node<T>(key, value, null);
	}
	
	@Override
	public void add(int key, T value){
		if(first == null){
			first = new Node<T>(key, value, null);
			return;
		}
			
		
		if(first.getKey() > key)
			first = new Node<T>(key, value, first);
		else 
			first.add(key, value);
	}
	
	@Override
	public T get(int key){
		if(first == null)
			return null;
		
		return first.get(key);
	}
	
	@Override
	public void remove(int key){
		if(first == null)
			return;
		
		if(first.getKey() == key)
			first = first.getNext();
		else
			first.remove(key);
	}
	
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
	public boolean containsKey(int key) {
		if(first == null)
			return false;
		
		if(first.getKey() == key)
			return true;
		else
			return first.containsKey(key);
	}

	@Override
	public boolean containsValue(T value) {
		if(first == null)
			return false;
		
		if(first.getValue() == value)
			return true;
		else
			return first.containsValue(value);
	}

	@Override
	public void clear() {
		if(first == null)
			return;
		
		first.clear();
		first = null;
	}

	@Override
	public void forEachElement(Consumer<? super T> action) {
		if(first == null)
			return;
		
		first.forEachElement(action);
		
	}

	@Override
	public ArrayList<T> getAllValues() {
		if(first == null)
			return new ArrayList<T>();
		return first.getAllValues();
	}

	@Override
	public void addAll(GList<T> list) {
		((GLinkedList<T>)list).first
							  .getAllNodes()
							  .stream()
							  .forEach(a -> add(a.getKey(), a.getValue()));
	}

	@Override
	public void removeAll(GList<T> list) {
		((GLinkedList<T>)list).first
							  .getAllNodes()
							  .stream()
							  .forEach(a -> remove(a.getKey()));
	}
	

}
