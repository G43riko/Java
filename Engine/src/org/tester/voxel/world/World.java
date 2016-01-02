package org.tester.voxel.world;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;
import glib.util.vector.GVector3f;

public class World extends GameComponent{
	public final static int NUM_X = 5;
	public final static int NUM_Z = 5;
	
	private Chunk[][] chunks = new Chunk[NUM_X][NUM_Z];
	
	//CONSTRUCTORS
	
	public World(GameAble parent){
		super(parent);
		createChunks();
		setNeighBoardsAndSides();
	}

	//OTHERS
	
	private void setNeighBoardsAndSides() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j] = new Chunk(getParent(), new GVector3f(i * Chunk.WIDHT, 0, j * Chunk.DEPTH));
			}
		}
		
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				if(i > 0)
					chunks[i][j].setNeighBoard(3, chunks[i - 1][j]);
				if(j > 0)
					chunks[i][j].setNeighBoard(2, chunks[i][j - 1]);
				
				if(i + 1 < NUM_X)
					chunks[i][j].setNeighBoard(1, chunks[i + 1][j]);
				if(j + 1 < NUM_Z)
					chunks[i][j].setNeighBoard(0, chunks[i][j + 1]);
			}
		}
		
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].setNeighBoardsAndSides();
			}
		}
	}

	private void createChunks() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j] = new Chunk(getParent(), new GVector3f(i * Chunk.WIDHT, 0, j * Chunk.DEPTH));
			}
		}
	}
	
	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		//if(renderingEngine.getMainCamera().isVisible(getPosition(), getSize()))
			for(int i=0 ; i<NUM_X ; i++)
				for(int j=0 ; j<NUM_Z ; j++)
					//if(renderingEngine.getMainCamera().isVisible(chunks[i][j].getPosition(), Chunk.getSize()))
						chunks[i][j].render(renderingEngine);
	}
	
	@Override
	public void update(float delta) {
//		for(int i=0 ; i<NUM_X ; i++){
//			for(int j=0 ; j<NUM_Z ; j++){
//				chunks[i][j].update();
//			}
//		}
	}

	//GETTERS
	
	public GVector3f getSize(){
		return new GVector3f(getWidth(), getHeight(), getDepth());
	}
	
	public float getWidth(){
		return NUM_X * Chunk.NUM_X * Block.WIDTH;
	}
	
	public float getHeight(){
		return Chunk.NUM_Y * Block.HEIGHT;
	}
	
	public float getDepth(){
		return NUM_Z * Chunk.NUM_Z * Block.DEPTH;
	}
}
