package game.world;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.noise.PerlinNoise;
import glib.util.vector.GVector3f;

public class World extends GameObject{
	public final static int NUM_X = 3;
	public final static int NUM_Z = 3;
	public static float[][] map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk.NUM_X * NUM_X, Chunk.NUM_Z * NUM_Z), 6, 0.7f, true);
	
	private Chunk[][] chunks = new Chunk[NUM_X][NUM_Z];
	
	public World() {
		super(new GVector3f(), 10);
		create();
	}

	private void create() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j] = new Chunk(getPosition().mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(new GVector3f(i,0,j).mul(new GVector3f(Chunk.NUM_X,0,Chunk.NUM_Z)))));
			}
		}
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].render(renderingEngine);
			}
		}
	}
	
}
