package game.world;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.noise.PerlinNoise;
import glib.util.vector.GVector3f;

public class World extends GameObject{
	public final static int NUM_X = 2;
	public final static int NUM_Z = 2;
	public static float[][] map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk3D.NUM_X * NUM_X, Chunk3D.NUM_Z * NUM_Z), 6, 0.7f, true);
	
	private Chunk3D[][] chunks = new Chunk3D[NUM_X][NUM_Z];
	
	public World() {
		super(new GVector3f(), 10);
		create();
		setNeighboards();
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].setSides();
			}
		}
	}

	private void create() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				GVector3f pos = new GVector3f(2).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(new GVector3f(i,0,j).mul(new GVector3f(Chunk3D.NUM_X,0,Chunk3D.NUM_Z))));
				chunks[i][j] = new Chunk3D(pos);
			}
		}
	}
	
	private void setNeighboards() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				if(i>0)
					chunks[i][j].setNeighboard(3, chunks[i-1][j]);
				if(j>0)
					chunks[i][j].setNeighboard(2, chunks[i][j-1]);
				if(i+1<NUM_X)
					chunks[i][j].setNeighboard(1, chunks[i+1][j]);
				if(j+1<NUM_Z)
					chunks[i][j].setNeighboard(0, chunks[i][j+1]);
			}
		}
	}
	
	private boolean exist(int i, int j){
		return i>=0 && j>=0 && i<NUM_X && j < NUM_Z;
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].render(renderingEngine);
			}
		}
	}
	
	public GVector3f getMaxSize(){
		return new GVector3f(NUM_X, 1, NUM_Z).mul(2).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH)).mul(new GVector3f(Chunk3D.NUM_X, Chunk3D.NUM_Y, Chunk3D.NUM_Z));
	}
	
	public Block getBlock(GVector3f from){
		from = from.add(new GVector3f(Block.WIDTH,Block.HEIGHT, Block.DEPTH)).div(new GVector3f(Block.WIDTH*2,Block.HEIGHT*2, Block.DEPTH*2));
		int chunkX = from.getXi()/Chunk3D.NUM_X*Block.WIDTH;
		int chunkZ = from.getZi()/Chunk3D.NUM_Z*Block.DEPTH;
		
		int blockX = from.getXi()% Chunk3D.NUM_X * Block.WIDTH;
		int blockY = from.getYi()/Block.HEIGHT;
		int blockZ = from.getZi()% Chunk3D.NUM_Z * Block.DEPTH;
		
		if(exist(chunkX, chunkZ) && chunks[chunkX][chunkZ].exist(blockX, blockY, blockZ, false))
			return chunks[chunkX][chunkZ].getBlock(blockX, blockY, blockZ);
		
		return null;
	}
}
