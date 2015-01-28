package com.voxel.world;

import glib.util.GLog;
import glib.util.noise.PerlinNoise;
import glib.util.noise.SimplexNoise;
import glib.util.vector.GVector3f;

import com.voxel.core.GameObject;

public class World extends GameObject{
	private final static int NUM_X = 3;
	private final static int NUM_Y = 3;
	public static float[][][] data3d = SimplexNoise.generateOctavedSimplexNoise(Chunk.NUM_X *NUM_X , Chunk.NUM_Y, Chunk.NUM_Z*NUM_Y,  6, 0.8f, 0.008f);
	public static float[][] data2d = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk.NUM_X *NUM_X, Chunk.NUM_Z*NUM_Y), 6, 0.7f, true);
	
	private Chunk[][] map;
	
	public World(){
		super("World");
		init();
	}
	
	public void init(){
		map = new Chunk[NUM_X][NUM_Y];
		create();
		setNeihboards();
		setBorders();
	}

	private void setBorders() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				map[i][j].setBorders();
			}
		}
	}

	private void setNeihboards() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				map[i][j].setNeighboards();
				if(exist(i,j+1))
					map[i][j].addNeighboard(0, map[i][j+1]);
				if(exist(i+1,j))
					map[i][j].addNeighboard(1, map[i+1][j]);
				if(exist(i,j-1))
					map[i][j].addNeighboard(2, map[i][j-1]);
				if(exist(i-1,j))
					map[i][j].addNeighboard(3, map[i-1][j]);
			}
		}
	}

	public boolean exist(int x, int y){
		return (x>=0 && y>=0 && x<NUM_X && y<NUM_Y );
	}
	
	private void create() {
		int sum = 0;
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				Chunk c = new Chunk(i,j);
				float x = Block.WIDTH  * i / 4;
				float z = Block.DEPTH  * j / 4;
				
				c.getTransform().setPosition(new GVector3f(x, 0, z));
				map[i][j] = c;
				sum += c.getNumOfBlock();
				addChild(c);
			}
		}
		GLog.write("vytvorilo sa "+sum+" kociek", "mapa");
	}
}
