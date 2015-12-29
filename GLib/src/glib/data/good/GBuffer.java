package glib.data.good;

import java.util.LinkedList;

import glib.data.good.interfaces.GSimpleCollection;

public class GBuffer<T> implements GSimpleCollection<T>{
	private LinkedList<T> list = new LinkedList<T>();
	
	public void add(T item){
		list.add(item);
	}
	
	public T get(){
		return list.remove(list.size() - 1);
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public int getSize() {
		return list.size();
	}
}
