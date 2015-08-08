package glib.data.good;

import glib.data.good.interfaces.GMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class GMultiHashMap<S, T> implements GMap<S, T>{
	private HashMap<S, ArrayList<T>> map;
	
	//CONSTRUCTORS
	
	/**
	 * 
	 * Constructor
	 */
	public GMultiHashMap(){
		map = new HashMap<S, ArrayList<T>>();
	}
	
	/**
	 * Parameter je u� existuj�ca GMultiMapa
	 * @param mapa
	 */
	public GMultiHashMap(GMultiHashMap<S, T> mapa){
		map = mapa.getMap();
	}
	
	/**
	 * Parameter je kl�� a ArrayList na pridanie
	 * @param key
	 * @param value
	 */
	public GMultiHashMap(S key, ArrayList<T> value){
		map = new HashMap<S, ArrayList<T>>();
		addAll(key, value);
	}

	/**
	 * Parameter je kl�� a hodnota
	 * @param key
	 * @param value
	 */
	public GMultiHashMap(S key, T value){
		map = new HashMap<S, ArrayList<T>>();
		add(key, value);
	}
	
	///ADDERS
	
	/**
	 * prid� hodnotu do GMultiHashMapy podla kl��a
	 * @param key
	 * @param value
	 */
	public void add(S key, T value){
		if(!map.containsKey(key))
			map.put(key, new ArrayList<T>());
		
		map.get(key).add(value);
	}

	/**
	 * prid� ArrayList do GMultiHashMapy podla kl��a
	 * @param key
	 * @param value
	 */
	public void addAll(S key, ArrayList<T> value){
		if(!map.containsKey(key))
			map.put(key, value);
		else
			map.get(key).addAll(value);
	}
	
	//GETTERS
	
	/**
	 * Vr�ti ArrayList podla kl��a
	 * @param key
	 * @return
	 */
	public ArrayList<T> get(S key){
		return map.get(key);
	}

	/**
	 * Vr�ti v�eky hodnoty v jednom ArrayListe
	 * @return
	 */
	public ArrayList<T> getAllValues(){
		ArrayList<T> result = new ArrayList<T>();
		
		for(Entry<S, ArrayList<T>> e : map.entrySet())
			result.addAll(e.getValue());
		
		return result;
	}
	
	/**
	 * Vr�ti k�piu GMultiHashMapy
	 * @return
	 */
	private HashMap<S, ArrayList<T>> getMap(){
		return new HashMap<S, ArrayList<T>>(map);
	}
	
	//OTHERS
	
	/**
	 * Vykon� oper�ciu pre ka�n� hodnotu
	 * @param condition
	 */
	public void forEachElement(Consumer<? super T> condition){
		map.forEach((key, value)->value.stream().forEach(condition));
	}
	
	/**
	 * Vr�ti true ak GMultiHashMapa obsahuje hodnotu zadan� ako parameter. In�� vr�ti false
	 * @param value
	 * @return
	 */
	public boolean constainsValue(T value){
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			if(e.getValue().contains(value))
				return true;
		
		return false;
	}
	
	/**
	 * Vr�ti true ak GMultiHashMapa obsahuje kl�� zadan� ako parameter. In�� vr�ti false
	 * @param key
	 * @return
	 */
	public boolean constainsKey(S key){
		return map.containsKey(key);
	}
	
	/**
	 * Vr�ti po�et hodn�t v GMultiHashMape
	 * @return
	 */
	public int getSize(){
		int result = 0;
		
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			result += e.getValue().size();
		
		return result;
	}
	
	/**
	 * Vyma�e cel� GMultiHashMapu
	 */
	public void clear(){
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			e.getValue().clear();
		
		map.clear();
	}
	
	//REMOVERS
	
	/**
	 * Vyma�e v�etky ulo�en� pod kl��om zadan�m v parametri
	 * @param key
	 */
	public void remove(S key){
		map.remove(key);
	}
	
	/**
	 * 
	 * @param keys
	 */
	public void removeAll(@SuppressWarnings("unchecked") S... keys){
		for(S key: keys)
			if(map.containsKey(key))
				map.remove(key);
		
	}
}
