package main;

import maps.Map;

public class Level {
	private Map map;
	
	public Level(int x, int y, int z){
		this.map = new Map(x,z,y);
		map.createDefaultMap();
		
	}
	
	public void draw(){
		map.draw();
	}
	
	public Map getMap(){
		return map;
	}
}
