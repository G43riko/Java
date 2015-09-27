package glib.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import glib.util.vector.GVector2f;


public class PathFinder{
//	private final static int START = 3;
//	private final static int CIEL = 2;
//	private final static int WALL = 1;
	private final static int NOTHING = 0;
	private final static int PATH = 4;
	
	/**
	 * Funkcia dostane HashMapu s ˙dajmi o mape, ötart, ciel a povolenie Ìsù diagon·lne 
	 * a vr·ti najkratöiu cestu od ötartu k ciuelu
	 * 
	 * @param map
	 * @param start
	 * @param ciel
	 * @param diagonal
	 * @return
	 */
	public static ArrayList<GVector2f> findPath(HashMap<String, Integer> map, String start, String ciel, boolean diagonal){
		diagonal = !diagonal;
		Map<String, Float> dists = new HashMap<String, Float>();
		Set<String> checked = new HashSet<String>();
		Set<String> act = new HashSet<String>();
		ArrayList<GVector2f> res = new ArrayList<GVector2f>();
		dists.put(start, 0f);
		act.add(start);

		float d = 0;
		while(!act.isEmpty()){
			Set<String> newAct = new HashSet<String>();
			for(String a : act){
				GVector2f p = new GVector2f(a);
				checked.add(a);
				dists.put(a, d);
				for(int i=-1 ; i<=1 ; i++){
					for(int j=-1 ; j<=1 ; j++){
						if(i == 0 && j ==0 )
							continue;
						if(i != 0 && j !=0 )
							continue;
						String b = p.add(new GVector2f(i, j)).toString();
						if(!map.containsKey(b) || map.get(b) != NOTHING)
							continue;
						if(!act.contains(b) && !checked.contains(b))
							newAct.add(b);
					}
				}

				
			}
			d++;
			act = new HashSet<String>(newAct);
		}
		
		String current = ciel;
		if(!dists.containsKey(ciel))
			return res;
		
		float shortestD = dists.get(ciel);
		String shortestB = ciel;
		while(current != start){
			for(int i=-1 ; i<=1 ; i++){
				for(int j=-1 ; j<=1 ; j++){
					if(diagonal && i != 0 && j !=0 )
						continue;
					String b = new GVector2f(current).add(new GVector2f(i, j)).toString();
					if(!map.containsKey(b) || map.get(b) != NOTHING)
						continue;
					if(dists.get(b) < shortestD){
						shortestD = dists.get(b);
						shortestB = b;
					}
				}
			}
			current = shortestB;
			if(shortestD == 0)
				break;
			res.add(new GVector2f(current));
			map.put(current, PATH);
		}
		return res;
	}
}
