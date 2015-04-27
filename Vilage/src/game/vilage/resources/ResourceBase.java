package game.vilage.resources;

import java.util.HashMap;
import java.util.Map.Entry;

public class ResourceBase {
	private HashMap<Byte, Integer> required;
	private HashMap<Byte, Integer> owned;
	private HashMap<Byte, Integer> produce;
	
	//CONSTRUCTORS
	
	/**
	 * @param required
	 * @param produced
	 */
	public ResourceBase(HashMap<Byte, Integer> required, HashMap<Byte, Integer> produced){
		this.required = required;
		this.produce = produced;
		owned = new HashMap<Byte, Integer>();
	}
	
	//OTHERS
	
	/**
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
	 * @return
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
	 * 
	 */
	public void build(){
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.containsKey(e.getKey()))
			owned.put(e.getKey(), owned.get(e.getKey()) - e.getValue());
		
		for(Entry<Byte, Integer> e : produce.entrySet())
			if(produce.containsKey(e.getKey()))
				addResource(e.getKey(), e.getValue());
	}

	//GETTERS
	
	/**
	 * @param type
	 * @return
	 */
	public int getOwned(byte type){
		int have = 0;
		if(owned.containsKey(type))
			have = owned.get(type);
		return have;
	}
	
	/**
	 * @param type
	 * @return
	 */
	public int getRequired(byte type){
		int need = 0;
		if(required.containsKey(type))
			need = required.get(type);
		return need;
	}
	
	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getMissingResources(){
		HashMap<Byte, Integer> missing = new HashMap<Byte, Integer>();
		
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.get(e.getKey()) < e.getValue())
				missing.put(e.getKey(), (e.getValue() - owned.get(e.getKey())));
		
		return missing;
	}
	
	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getRequired() {
		return required;
	}

	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getProduce() {
		return produce;
	}

	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getOwned() {
		return owned;
	}

	/**
	 * @return
	 */
	public HashMap<Byte, Integer> getAll(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>(owned);
		res.putAll(required);
		res.putAll(produce);
		return res;
	}
}
