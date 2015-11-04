package glib.data.good;

import java.util.ArrayList;

import glib.data.good.interfaces.GCollection;

public class GBuffer<T> implements GCollection{
	private ArrayList<T> list = new ArrayList<T>();
	
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
