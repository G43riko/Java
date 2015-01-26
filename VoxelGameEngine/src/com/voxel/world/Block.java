package com.voxel.world;

import com.voxel.core.util.GVector3f;

public class Block extends BasicBlock{
	public static int WIDTH = 1;
	public static int HEIGHT = 1;
	public static  int DEPTH = 1;
	
	public static int VOID = 0;
	public static int GRASS = 1;
	public static int DIRT = 2;
	public static int ROCK = 3;
	public static int WATER = 4;
	
	public static int MAX_NUM_TYPES = 5;
	
	private boolean isActive;
	private int x, y, z;
	
	public Block(int x, int y, int z,int type){
		this.x = x;
		this.y = y;
		this.z = z;
		this.getTransform().setPosition(new GVector3f(x*2,y*2,z*2));
		this.type = type;
		isActive = true;
		if(type==0)
			isActive = false;
		addWalls();
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
