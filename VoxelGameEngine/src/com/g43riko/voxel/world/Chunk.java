package com.g43riko.voxel.world;

import java.util.Arrays;

public class Chunk {
	public static final int NUM_X = 64;
	public static final int NUM_Y = 16;
	public static final int NUM_Z = 64;
	
	private Block[][][] blocks;
	
	public Chunk(){
		blocks = new Block[NUM_X][NUM_Y][NUM_Z];
		
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					blocks[i][j][k] = new Block(i,j,k,(int)(Math.random()*10));
				}
			}
		}
		for(int i=0 ;i<5 ; i++){
			setActive();
		}
	}

	public void setActive(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					if(exist(i,j,k-1)&&exist(i,j,k+1)&&exist(i,j-1,k)&&exist(i,j+1,k)&&exist(i-1,j,k)&&exist(i+1,j,k)){
						blocks[i][j][k].setActive(true);
					}
					if(j==0||i==0||k==0||i==NUM_X-1||j==NUM_Z-1||k==NUM_Z-1){
						blocks[i][j][k].setActive(true);
					}
				}
			}
		}
	}
	
	public void draw() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					Block b = blocks[i][j][k];
					if(!b.isActive()||b.getType()==0){
						continue;
					}
					if(b.getFaces()==null){
						setFace(b, i,j,k);
					}
					blocks[i][j][k].draw();
				}
			}
		}
	}
	
	private void setFace(Block b, int i, int j, int k) {
		b.setFaces(new boolean[6]);
		Arrays.fill(b.getFaces(), true);
		
		if(isActive(i,j,k-1)){
			b.getFaces()[0] = false;
		}
		if(isActive(i,j,k+1)){
			b.getFaces()[1] = false;
		}
		if(isActive(i,j+1,k)){
			b.getFaces()[2] = false;
		}
		if(isActive(i,j-1,k)){
			b.getFaces()[3] = false;
		}
		if(isActive(i+1,j,k)){
			b.getFaces()[4] = false;
		}
		if(isActive(i-1,j,k)){
			b.getFaces()[5] = false;
		}
		
	}

	private boolean exist(int x, int y, int z){
		if(x>=0 && y>=0 && z>=0 && x<NUM_X && y<NUM_Y && z<NUM_Z && blocks[x][y][z].getType()!=0){
			return true;
		}
		return false;
	}
	private boolean isActive(int x, int y, int z){
		if(exist(x,y,z)){
			return true;
		}
		if(x>0 && y>0 && z>0 && x<NUM_X-1 && y<NUM_Y-1 && z<NUM_Z-1){
			if(blocks[x+1][y][z].getType()!=0||blocks[x-1][y][z].getType()!=0||
			   blocks[x][y+1][z].getType()!=0||blocks[x][y-1][z].getType()!=0||
			   blocks[x][y][z+1].getType()!=0||blocks[x][y][z-1].getType()!=0){
				return true;
			}
		}
		return false;
	}
	
	
}
