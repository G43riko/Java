package com.voxel.world;

import org.json.JSONObject;

import glib.util.GLog;
import glib.util.vector.GVector3f;

public class Block extends BasicBlock implements BlockInfo{
	private int sizeX;
	private int sizeY;
	private int sizeZ;	
	private GVector3f color;
	
	public static int MAX_NUM_TYPES = 5;
	
	private boolean isTransparent;
	private boolean isActive;
	
	public Block(int x, int y, int z,int type){
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		sizeX = WIDTH;
		sizeY = HEIGHT;
		sizeZ = DEPTH;
		init();
	}
	
	public void init(){
		neighboards = new Block[]{null, null, null, null, null, null};
		isActive = (type != 0);
		isTransparent = false;
		addWalls();
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public JSONObject toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("sizeX", sizeX);
		obj.put("sizeY", sizeY);
		obj.put("sizeZ", sizeZ);
		
		obj.put("type", type);
		
		obj.put("x", x);
		obj.put("y", y);
		obj.put("z", z);
		
		obj.put("mapR", color.getX());
		obj.put("mapG", color.getY());
		obj.put("mapB", color.getZ());
		obj.put("transparent", transparent);
		return obj;
	}
	
	public void addNeighboard(int dir, Block neighboard){
		neighboards[dir] = neighboard;
	}
}
