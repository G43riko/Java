package com.g43riko.voxel.world;

public class World {
	private final static int NUM_X = 1;
	private final static int NUM_Y = 1;
	
	private Chunk[][] chunks;
	
	public World(){
		chunks = new Chunk[NUM_X][NUM_Y];
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				chunks[i][j] = new Chunk(i*Chunk.NUM_X*Block.WIDTH*2,  j*Chunk.NUM_Z*Block.DEPTH*2);
			}
		}
	}
	
	public void draw() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				chunks[i][j].draw();;
			}
		}
	}
}
