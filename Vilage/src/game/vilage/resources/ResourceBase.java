package game.vilage.resources;

import java.util.HashMap;
import java.util.Map.Entry;

public class ResourceBase {
	private HashMap<Byte, Integer> required;
	private HashMap<Byte, Integer> owned;
	private HashMap<Byte, Integer> produce;
	
	public ResourceBase(HashMap<Byte, Integer> required, HashMap<Byte, Integer> produced){
		this.required = required;
	}
	
	public void addResource(byte resource, int value){
		if(owned.containsKey(resource))
			owned.put(resource, owned.get(resource) + value);
		else
			owned.put(resource,value);
	}
	
	public boolean canWork(){
		return canWork(1);
	}
	
	public boolean canWork(int num){
		for(Entry<Byte, Integer> e : required.entrySet())
			if(owned.get(e.getKey()) < e.getValue() * num)
				return false;
		
		return true;
	}
	
	public void build(int num){
		for(Entry<Byte, Integer> e : required.entrySet())
			owned.put(e.getKey(), owned.get(e.getKey()) - e.getValue() * num);
		
		for(Entry<Byte, Integer> e : produce.entrySet())
			owned.put(e.getKey(), owned.get(e.getKey()) + e.getValue() * num);
	}
}
