package com.voxel.world;

import glib.util.noise.SimplexNoise;

import com.voxel.core.GameObject;

public class Chunk extends GameObject{
	public static final int NUM_X = 16;
	public static final int NUM_Y = 16;
	public static final int NUM_Z = 16;
	
	private Block[][][] blocks;
	
	public Chunk(){
		init();
	}
	
	public void init(){
		blocks = new Block[NUM_X][NUM_Y][NUM_Z];
		create();
		setBorders();
	}
	
	public void setBorders(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					Block b = blocks[i][j][k];
					if(isActive(i,j,k+1))
						b.setBackB(false);
					if(isActive(i,j,k-1))
						b.setForwardB(false);
					if(isActive(i,j+1,k))
						b.setTopB(false);
					if(isActive(i,j-1,k))
						b.setBottomB(false);
					if(isActive(i+1,j,k))
						b.setRightB(false);
					if(isActive(i-1,j,k))
						b.setLeftB(false);
					
				}
			}
		}
	}
	
	public boolean isActive(int x, int y, int z){
		if(exist(x,y,z) && blocks[x][y][z].getType() != 0)
			return true;
		return false;
	}
	
	public boolean exist(int x, int y, int z){
		if(x>=0 && y>=0 && z>=0 && x<NUM_X && y<NUM_Y && z<NUM_Z)
			return true;
		return false;
	}
	
	public void create(){
		float[][][] mapa = SimplexNoise.generateOctavedSimplexNoise(NUM_X, NUM_Y, NUM_Z,  6, 0.8f, 0.008f);
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					float val = mapa[i][j][k];
					val = (float)Math.max(0,Math.min(1, val));
					val *= (Block.MAX_NUM_TYPES-1);
					Block b = new Block(i,j,k,(int)val);
					addChild(b);
					System.out.println(b.getType());
					blocks[i][j][k]=b;
				}
			}
		}
	}
}

