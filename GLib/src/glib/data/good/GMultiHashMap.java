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
	 * Parameter je už existujúca GMultiMapa
	 * @param mapa
	 */
	public GMultiHashMap(GMultiHashMap<S, T> mapa){
		map = mapa.getMap();
	}
	
	/**
	 * Parameter je klúè a ArrayList na pridanie
	 * @param key
	 * @param value
	 */
	public GMultiHashMap(S key, ArrayList<T> value){
		map = new HashMap<S, ArrayList<T>>();
		addAll(key, value);
	}

	/**
	 * Parameter je klúè a hodnota
	 * @param key
	 * @param value
	 */
	public GMultiHashMap(S key, T value){
		map = new HashMap<S, ArrayList<T>>();
		add(key, value);
	}
	
	///ADDERS
	
	/**
	 * pridá hodnotu do GMultiHashMapy podla klúèa
	 * @param key
	 * @param value
	 */
	public void add(S key, T value){
		if(!map.containsKey(key))
			map.put(key, new ArrayList<T>());
		
		map.get(key).add(value);
	}

	/**
	 * pridá ArrayList do GMultiHashMapy podla klúèa
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
	 * Vráti ArrayList podla klúèa
	 * @param key
	 * @return
	 */
	public ArrayList<T> get(S key){
		return map.get(key);
	}

	/**
	 * Vráti všeky hodnoty v jednom ArrayListe
	 * @return
	 */
	public ArrayList<T> getAllValues(){
		ArrayList<T> result = new ArrayList<T>();
		
		for(Entry<S, ArrayList<T>> e : map.entrySet())
			result.addAll(e.getValue());
		
		return result;
	}
	
	/**
	 * Vráti kópiu GMultiHashMapy
	 * @return
	 */
	private HashMap<S, ArrayList<T>> getMap(){
		return new HashMap<S, ArrayList<T>>(map);
	}
	
	//OTHERS
	
	/**
	 * Vykoná operáciu pre kažnú hodnotu
	 * @param condition
	 */
	public void forEachElement(Consumer<? super T> condition){
		map.forEach((key, value)->value.stream().forEach(condition));
	}
	
	/**
	 * Vráti true ak GMultiHashMapa obsahuje hodnotu zadanú ako parameter. Ináè vráti false
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
	 * Vráti true ak GMultiHashMapa obsahuje klúè zadaný ako parameter. Ináè vráti false
	 * @param key
	 * @return
	 */
	public boolean constainsKey(S key){
		return map.containsKey(key);
	}
	
	/**
	 * Vráti poèet hodnôt v GMultiHashMape
	 * @return
	 */
	public int getSize(){
		int result = 0;
		
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			result += e.getValue().size();
		
		return result;
	}
	
	/**
	 * Vymaže celú GMultiHashMapu
	 */
	public void clear(){
		for(Entry<S, ArrayList<T>> e: map.entrySet())
			e.getValue().clear();
		
		map.clear();
	}
	
	//REMOVERS
	
	/**
	 * Vymaže všetky uložené pod klúèom zadaným v parametri
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
