package glib.data.good;

import java.util.ArrayList;

public class GBuffer<T> {
	private ArrayList<T> list = new ArrayList<T>();
	
	public void add(T item){
		list.add(item);
	}
	
	public T get(){
		return list.remove(list.size() - 1);
	}
	
	public int size(){
		return list.size();
	}
}
