package glib.data.good;

import glib.data.good.interfaces.GMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class GMultiHashMap<S, T> implements GMap<S, T>{
	private HashMap<S, ArrayList<T>> map;
	
	//CONSTRUCTORS
	
	public GMultiHashMap(){
		map = new HashMap<S, ArrayList<T>>();
	}
	
	public GMultiHashMap(GMultiHashMap<S, T> mapa){
		map = mapa.getMap();
	}
	
	public GMultiHashMap(S key, ArrayList<T> value){
		map = new HashMap<S, ArrayList<T>>();
		addAll(key, value);
	}

	public GMultiHashMap(S key, T value){
		map = new HashMap<S, ArrayList<T>>();
		add(key, value);
	}
	
	///ADDERS
	
	public T add(S key, T value){
		if(!map.containsKey(key))
			map.put(key, new ArrayList<T>());
		
		map.get(key).add(value);
		
		return null;
	}

	public void addAll(S key, ArrayList<T> value){
		if(!map.containsKey(key))
			map.put(key, value);
		else
			map.get(key).addAll(value);
	}
	
	//GETTERS

	@Override
	public T get(S key) {
		return null;
	}

	public ArrayList<T> getAll(S key){
		return map.get(key);
	}

	public ArrayList<T> getAllValues(){
		ArrayList<T> result = new ArrayList<T>();
		
		for(Entry<S, ArrayList<T>> e : map.entrySet())
			result.addAll(e.getValue());
		return result;
	}
	
	private HashMap<S, ArrayList<T>> getMap(){
		return new HashMap<S, ArrayList<T>>(map);
	}

	@Override
	public void addAll(GMap<S, T> tree) {
		for(Entry<S, ArrayList<T>> e : map.entrySet()){
			if(!map.containsKey(e.getKey()))
				map.put(e.getKey(), new ArrayList<T>());
			map.get(e.getKey()).addAll(e.getValue());
		}
	}

	//OTHERS
	
	public void forEachElement(Consumer<? super T> condition){
		map.forEach((key, value)->value.stream().forEach(condition));
	}
	
	public boolean constainsValue(T value){
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			if(e.getValue().contains(value))
				return true;
		
		return false;
	}
	
	public boolean containsKey(S key){
		return map.containsKey(key);
	}
	
	public int getSize(){
		int result = 0;
		
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			result += e.getValue().size();
		
		return result;
	}
	
	public void clear(){
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			e.getValue().clear();
		
		map.clear();
	}

	@Override
	public boolean isEmpty() {
		for(Entry<S, ArrayList<T>> e : map.entrySet())
			if(!e.getValue().isEmpty())
				return false;
		
		return true;
	}

	//REMOVERS
	
	public T remove(S key){
		map.remove(key);
		return null;
	}

	public void removeAll(@SuppressWarnings("unchecked") S... keys){
		for(S key: keys)
			if(map.containsKey(key))
				map.remove(key);
		
	}

	@Override
	public void removeAll(GMap<S, T> tree) {
		for(Entry<S, ArrayList<T>> e : map.entrySet())
			if(map.containsKey(e.getKey()))
				map.get(e.getKey()).removeAll(e.getValue());
		
	}
}
