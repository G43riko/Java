package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class MultiHashMap<T, S> {
	private HashMap<T, ArrayList<S>> map;
	
	//CONSTRUCTORS
	
	public MultiHashMap(){
		map = new HashMap<T, ArrayList<S>>();
	}
	
	public MultiHashMap(MultiHashMap<T, S> mapa){
		map = mapa.getMap();
	}
	
	public MultiHashMap(T key, ArrayList<S> value){
		map = new HashMap<T, ArrayList<S>>();
		addAll(key, value);
	}
	
	public MultiHashMap(T key, S value){
		map = new HashMap<T, ArrayList<S>>();
		add(key, value);
	}
	
	///ADDERS
	
	public void add(T key, S value){
		if(!map.containsKey(key))
			map.put(key, new ArrayList<S>());
		
		map.get(key).add(value);
	}
	
	public void addAll(T key, ArrayList<S> value){
		if(!map.containsKey(key))
			map.put(key, value);
		else
			map.get(key).addAll(value);
	}
	
	//GETTERS
	
	public ArrayList<S> get(T key){
		return map.get(key);
	}
	
	public ArrayList<S> get(@SuppressWarnings("unchecked") T... keys){
		ArrayList<S> result = new ArrayList<S>();
		for(T key: keys){
			result.addAll(map.get(key));
		}
		return result;
	}
	
	public ArrayList<S> getAll(){
		ArrayList<S> result = new ArrayList<S>();
		
		for(Entry<T, ArrayList<S>> e : map.entrySet())
			result.addAll(e.getValue());
		
		return result;
	}
	
	private HashMap<T, ArrayList<S>> getMap(){
		return new HashMap<T, ArrayList<S>>(map);
	}
	
	//OTHERS
	
	public void forEachElement(Consumer<? super S> condition){
		map.forEach((key, value)->value.stream().forEach(condition));
	}
	
	public boolean constains(S value){
		for(Entry<T, ArrayList<S>> e: map.entrySet())
			if(e.getValue().contains(value))
				return true;
		
		return false;
	}
	
	public int size(){
		int result = 0;
		
		for(Entry<T, ArrayList<S>> e: map.entrySet())
			result += e.getValue().size();
		
		return result;
	}
	
	public void clear(){
		for(Entry<T, ArrayList<S>> e: map.entrySet())
			e.getValue().clear();
		
		map.clear();
	}
	
	//REMOVERS
	
	public void removeAll(T key){
		map.remove(key);
	}
	
	public void removeAll(@SuppressWarnings("unchecked") T... keys){
		for(T key: keys)
			if(map.containsKey(key))
				map.remove(key);
		
	}
	
	public void remove(S value){
		map.forEach((a,b)->{
			if(b.contains(value))
				b.remove(value);
		});
	}
}
