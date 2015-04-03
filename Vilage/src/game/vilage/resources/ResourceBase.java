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
	
	public void addResource(byte resource, int value){
		if(owned.containsKey(resource))
			owned.put(resource, owned.get(resource) + value);
		else
			owned.put(resource,value);
	}
	
	public boolean canWork(){
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.get(e.getKey()) < e.getValue())
				return false;
		
		return true;
	}

	public void build(){
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.containsKey(e.getKey()))
			owned.put(e.getKey(), owned.get(e.getKey()) - e.getValue());
		
		for(Entry<Byte, Integer> e : produce.entrySet())
			if(produce.containsKey(e.getKey()))
				addResource(e.getKey(), e.getValue());
	}

	//GETTERS
	
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
