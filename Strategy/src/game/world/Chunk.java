package game.world;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Chunk extends GameObject{
	public static final int NUM_X = 32;
	public static final int NUM_Z = 32;

	public Block[][] blocks = new Block[NUM_X][NUM_Z];
	
	public Chunk(GVector3f position) {
		super(position, 9);
		create();
		setSides();
	}
	
	private void create(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				blocks[i][j] = new Block(getPosition().add(new GVector3f(i,(int)(World.map[i][j]*10-5),j).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(2))),1);
			}
		}
	}
	
	private void setSides(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				blocks[i][j].sides[0] = true;
				blocks[i][j].sides[2] = false;
				blocks[i][j].sides[3] = (exist(i+1, j) && blocks[i][j].getPosition().getY() > blocks[i+1][j].getPosition().getY());
				blocks[i][j].sides[1] = (exist(i-1, j) && blocks[i][j].getPosition().getY() > blocks[i-1][j].getPosition().getY());
				blocks[i][j].sides[4] = (exist(i, j+1) && blocks[i][j].getPosition().getY() > blocks[i][j+1].getPosition().getY());
				blocks[i][j].sides[5] = (exist(i, j-1) && blocks[i][j].getPosition().getY() > blocks[i][j-1].getPosition().getY());
			}
		}
	}
	
	public boolean exist(int x, int z){
		return x>=0 && z>=0 && x<NUM_X && z<NUM_Z;
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				blocks[i][j].render(renderingEngine);
			}
		}
	}
}
