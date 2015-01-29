package com.voxel.world;

import org.json.JSONObject;

import glib.util.vector.GVector3f;

public class Block extends BasicBlock implements BlockInfo{
	public static int MAX_NUM_TYPES = BlockInfo.DATA.length()+1;
	
	public Block(int x, int y, int z,BlockType block){
		this.block = block;
		pos = new GVector3f(x,y,z);
		
		size = new GVector3f(WIDTH, HEIGHT, DEPTH);
		
		isActive = (block.getId() != 0);
		
		init();
	}
	
	public void init(){
		neighboards = new Block[]{null, null, null, null, null, null};
		addWalls();
	}
	
	public JSONObject toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("sizeX", size.getX());
		obj.put("sizeY", size.getY());
		obj.put("sizeZ", size.getZ());
		
		obj.put("type", block.getId());
		
		obj.put("x", pos.getX());
		obj.put("y", pos.getY());
		obj.put("z", pos.getZ());
		
		obj.put("mapR", block.getColor().getX());
		obj.put("mapG", block.getColor().getY());
		obj.put("mapB", block.getColor().getZ());
		obj.put("transparent", block.isTransparent());
		return obj;
	}
	
	public void addNeighboard(int dir, Block neighboard){
		neighboards[dir] = neighboard;
	}
	
	public boolean isActive() {return isActive; }
	
	public void setActive(boolean isActive) {this.isActive = isActive; }
}
