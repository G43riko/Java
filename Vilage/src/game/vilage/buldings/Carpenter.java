package game.vilage.buldings;

import game.vilage.Village;
import game.vilage.resources.ResourceBase;
import game.vilage.resources.Suroviny;

import java.util.HashMap;

public class Carpenter extends BasicBuilding{
	public Carpenter(Village village){
		super(village,Buildings.TESAR);
		resources = new ResourceBase(getRequeredResources(),getProducesResources());
	}
	
	protected HashMap<Byte, Integer> getRequeredResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.NASTROJ, 4);
		return res;
	}
	
	protected HashMap<Byte, Integer> getProducesResources(){
		HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
		res.put(Suroviny.NASTROJ, 8);
		return res;
	}
}
