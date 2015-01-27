package com.voxel.world;
import glib.util.GLog;
import glib.util.vector.GVector3f;

import com.voxel.core.GameObject;

public class Chunk extends GameObject{
	public static final int NUM_X = 8;
	public static final int NUM_Y = 16;
	public static final int NUM_Z = 8;
	
	private int x;
	private int y;
	private int numOfBlock = 0;
	
	private Block[][][] blocks;
	private Chunk[] neighboards;
	
	public Chunk(int x, int y){
		init(x,y);
		this.x = x;
		this.y = y;
	}
	
	public void init(int x, int y){
		neighboards = new Chunk[]{null, null, null, null};
		blocks = new Block[NUM_X][NUM_Y][NUM_Z];
		create(x,y);
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
	
	public void setNeighboards(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					Block b = blocks[i][j][k];
					if(exist(i,j+1,k))
						b.addNeighboard(0, blocks[i][j+1][k]);
					if(exist(i+1,j,k))
						b.addNeighboard(1, blocks[i+1][j][k]);
					if(exist(i,j-1,k))
						b.addNeighboard(2, blocks[i][j-1][k]);
					if(exist(i-1,j,k))
						b.addNeighboard(3, blocks[i-1][j][k]);
					if(exist(i,j,k+1))
						b.addNeighboard(4, blocks[i][j][k+1]);
					if(exist(i,j,k-1))
						b.addNeighboard(5, blocks[i][j][k-1]);
				}
			}
		}
	}
	
	public void simplification(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					
				}
			}
		}
	}
	
	public boolean isActive(int x, int y, int z){
		if(exist(x,y,z) && blocks[x][y][z].getType() != 0)
			return true;
		if(x<0 && neighboards[3]!=null){
			return neighboards[3].isActive(NUM_X-1, y,z);
		}
		
		if(z<0 && neighboards[2]!=null)
			return neighboards[2].isActive(x, y, NUM_Z-1);
		
		if(x==NUM_X && neighboards[1]!=null)
			return neighboards[1].isActive(0, y, z);
		
		if(z==NUM_Z && neighboards[0]!=null)
			return neighboards[0].isActive(x, y, 0);
		
		return false;
	}
	
	public boolean exist(int x, int y, int z){
		return (x>=0 && y>=0 && z>=0 && x<NUM_X && y<NUM_Y && z<NUM_Z);
	}
	
	private int getVal(int x, int y, int z){
		float val = World.data[x][y][z];
		val = (float)Math.max(0,Math.min(1, val));
		val *= (Block.MAX_NUM_TYPES-1);
		return (int)val;
	}
	
	public void create(int x, int y){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					int surX = x*NUM_X+i;
					int surY = j;
					int surZ = y*NUM_Z+k;
					
					int val = getVal(surX, surY, surZ);
					
					if(j>NUM_Y/8)
						val = 0;
					if(val!=0)
						numOfBlock++;
					Block b = new Block(x*NUM_X+i,j,y*NUM_Z+k,val);
					
					b.getTransform().setPosition(new GVector3f(surX*2, surY*2, surZ*2));
					
					addChild(b);
					blocks[i][j][k]=b;
				}
			}
		}
	}
	
	public void addNeighboard(int dir, Chunk neighboard){
		neighboards[dir] = neighboard;
	}

	public int getNumOfBlock() {
		return numOfBlock;
	}
}

