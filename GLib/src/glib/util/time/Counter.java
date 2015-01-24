package glib.util.time;

import java.util.HashMap;

public class Counter {
	private static HashMap<String, Integer> data = new HashMap<String,Integer>();
	
	public static void set(String name,int value){
		data.put(name, value);
	}
	
	private static void add(String name, int value){
		if(!data.containsKey(name)){
			reset(name);
			return;
		}
		data.put(name, data.get(name) + value);
	}
	
	public static void increase(String name){
		if(!data.containsKey(name)){
			reset(name);
			return;
		}
		add(name,+1);
	}
	
	public static void decrease(String name){
		if(!data.containsKey(name)){
			reset(name);
			return;
		}
		add(name,-1);
	}
	
	public static void reset(String name){
		data.put(name, 0);
	}
	
	public static void remove(String name){
		data.remove(name);
	}
	
	public static int get(String name){
		return data.get(name);
	}
}
