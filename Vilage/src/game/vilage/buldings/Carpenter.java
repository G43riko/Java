package game.vilage.buldings;

import game.vilage.resources.ResourceBase;
import game.vilage.resources.Suroviny;

import java.util.HashMap;

public class Carpenter extends BasicBuilding{
	public Carpenter(){
		resources = new ResourceBase(getRequeredResources(),getProducesResources());
	}
	
	private HashMap<Byte, Integer> getRequeredResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.NASTROJ, 4);
		return res;
	}
	
	private HashMap<Byte, Integer> getProducesResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.NASTROJ, 8);
		return res;
	}
}
