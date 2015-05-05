package game.vilage.resources;

import java.util.HashMap;
import java.util.Map.Entry;

public class ResourceBase {
	private HashMap<Byte, Integer> required;
	private HashMap<Byte, Integer> owned;
	private HashMap<Byte, Integer> produce;
	
	//CONSTRUCTORS
	
	public ResourceBase(HashMap<Byte, Integer> required, HashMap<Byte, Integer> produced){
		this.required = required;
		this.produce = produced;
		owned = new HashMap<Byte, Integer>();
	}
	
	//OTHERS
	/**
	 * pripoèíta suroviny
	 * @param resource
	 * @param value
	 */
	public void addResource(byte resource, int value){
		if(owned.containsKey(resource))
			owned.put(resource, owned.get(resource) + value);
		else
			owned.put(resource,value);
	}
	
	
	/**
	 * skontroluje èi môe budova pracova
	 * @return if building can work
	 */
	public boolean canWork(){
		for(Entry<Byte, Integer> e : required.entrySet()){
			if(!owned.containsKey(e.getKey()))
				return false;
			if(owned.get(e.getKey()) < e.getValue())
				return false;
		}
		
		return true;
	}
	
	/**
	 * odpoèíta suroviny ktoré sa pri vırobe spotrebuju. 
	 * pripoèíta suroviny ktoré sa pri vırobe vytvoria
	 */
	public void build(){
		required.forEach((key,value) ->{
			if(owned.containsKey(key))
				owned.put(key, owned.get(key)-value);
		});
		
		required.forEach((key,value) ->{
			if(produce.containsKey(key))
				addResource(key,value);
		});
	}

	//GETTERS
	
	public int getOwned(byte type){
		if(owned.containsKey(type))
			return owned.get(type);
		
		return 0;
	}
	
	public int getRequired(byte type){
		if(required.containsKey(type))
			return required.get(type);
		return 0;
	}
	
	/**
	 * vypoèíta aké surovinı chıbajú a kolko
	 * @return missing resources
	 */
	public HashMap<Byte, Integer> getMissingResources(){
		HashMap<Byte, Integer> missing = new HashMap<Byte, Integer>();
		
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.get(e.getKey()) < e.getValue())
				missing.put(e.getKey(), (e.getValue() - owned.get(e.getKey())));
		
		return missing;
	}
	
	public HashMap<Byte, Integer> getRequired() {
		return required;
	}

	public HashMap<Byte, Integer> getProduce() {
		return produce;
	}

	public HashMap<Byte, Integer> getOwned() {
		return owned;
	}

	public HashMap<Byte, Integer> getAll(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>(owned);
		res.putAll(required);
		res.putAll(produce);
		return res;
	}
}
