package game.vilage.buldings;

import java.util.HashMap;

import game.vilage.resources.ResourceBase;
import game.vilage.resources.Suroviny;

public class LumberJack extends BasicBuilding{
	public LumberJack(){
		resources = new ResourceBase(getRequeredResources(),getProducesResources());
	}
	
	private HashMap<Byte, Integer> getRequeredResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.NASTROJ, 3);
		return res;
	}
	
	private HashMap<Byte, Integer> getProducesResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.DREVO, 5);
		return res;
	}
}
