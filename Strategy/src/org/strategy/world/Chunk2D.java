package org.strategy.world;

import org.engine.component.GameComponent;
import org.strategy.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public class Chunk2D extends GameComponent{
	public static final int NUM_X = 16;
	public static final int NUM_Z = 16;

	private Chunk2D[] neighboards = new Chunk2D[4];
	public Block[][] blocks = new Block[NUM_X][NUM_Z];
	
	//CONSTRUCTORS
	
	public Chunk2D(GVector3f position) {
		super(position, GameComponent.CHUNK);
		create();
	}
	
	//OTHERS
	
	private void create(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				float height = World.map[i+getPosition().getXi()/2][j+getPosition().getZi()/2];
				int type = (int)(Block.blockDatas.size()*Math.random())+1;
//				type = (int)(Block.blockDatas.size()*height)+1;
//				System.out.println(height);
				blocks[i][j] = new Block(getPosition().add(new GVector3f(i,(int)(height*10-5),j).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(2))),type);
			}
		}
	}
	
	public boolean exist(int x, int z){
		return x>=0 && z>=0 && x<NUM_X && z<NUM_Z;
	}
	
	//OVERRIDES
	
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				blocks[i][j].render(renderingEngine);
			}
		}
	}

	//GETTERS
	
	public Block getBlock(int i, int j){
		return blocks[i][j];
	}
	
	//SETTERS

	public void setSides(){
//		for(int i=0 ; i<NUM_X ; i++){
//			for(int j=0 ; j<NUM_Z ; j++){
//				Block b = blocks[i][j];
//					b.sides[0] = true;
//					b.sides[2] = false;
//					b.sides[3] = (exist(i+1, j) && b.getPosition().getY() > blocks[i+1][j].getPosition().getY());
//					b.sides[1] = (exist(i-1, j) && b.getPosition().getY() > blocks[i-1][j].getPosition().getY());
//					b.sides[4] = (exist(i, j+1) && b.getPosition().getY() > blocks[i][j+1].getPosition().getY());
//					b.sides[5] = (exist(i, j-1) && b.getPosition().getY() > blocks[i][j-1].getPosition().getY());
//					
//					if(i==0 && neighboards[3] != null && b.getPosition().getY() > neighboards[3].getBlock(NUM_X-1, j).getPosition().getY())
//						b.sides[1] = true;
//					if(i+1==NUM_X && neighboards[1] != null && b.getPosition().getY() > neighboards[1].getBlock(0, j).getPosition().getY())
//						b.sides[3] = true;
//					
//					if(j==0 && neighboards[2] != null && b.getPosition().getY() > neighboards[2].getBlock(i, NUM_Z-1).getPosition().getY())
//						b.sides[5] = true;
//					if(j+1==NUM_Z && neighboards[0] != null && b.getPosition().getY() > neighboards[0].getBlock(i, 0).getPosition().getY())
//						b.sides[4] = true;
//			}
//		}
	}
	
	public void setNeighboard(int i, Chunk2D n){
		neighboards[i] = n;
	}
}
