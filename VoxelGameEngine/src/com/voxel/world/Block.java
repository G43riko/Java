package com.voxel.world;

import glib.util.GLog;
import glib.util.vector.GVector3f;

public class Block extends BasicBlock{
	public static int WIDTH = 1;
	public static int HEIGHT = 1;
	public static int DEPTH = 1;
	
	private int sizeX;
	private int sizeY;
	private int sizeZ;	
	
	public static int VOID = 0;
	public static int GRASS = 1;
	public static int DIRT = 2;
	public static int ROCK = 3;
	public static int WATER = 4;
	
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
	
	public void addNeighboard(int dir, Block neighboard){
		neighboards[dir] = neighboard;
	}
}
