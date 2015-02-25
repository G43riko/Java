package game.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.json.JSONObject;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.noise.PerlinNoise;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class World extends GameObject{
	public static int NUM_X = 2;
	public static int NUM_Z = 2;
	public static float[][] map;
	
	private Chunk3D[][] chunks;
	
	public World() {
		super(new GVector3f(), 10);
		
		map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk3D.NUM_X * NUM_X, Chunk3D.NUM_Z * NUM_Z), 6, 0.7f, true);
		chunks = new Chunk3D[NUM_X][NUM_Z];
		
		create();
		setNeighboards();
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].setSides();
			}
		}
	}
	
	public World(JSONObject o) {
		super(new GVector3f(), 10);
		NUM_X = o.getInt("worldX");
		NUM_Z = o.getInt("worldZ");
		Chunk3D.NUM_X = o.getInt("chunkX");
		Chunk3D.NUM_Y = o.getInt("chunkY");
		Chunk3D.NUM_Z = o.getInt("chunkZ");
		Block.WIDTH = o.getInt("blockX");
		Block.HEIGHT = o.getInt("blockY");
		Block.DEPTH = o.getInt("blockZ");
		
		chunks = new Chunk3D[NUM_X][NUM_Z];
		
		create(o);
		setNeighboards();
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].setSides();
			}
		}
	}

	private void create(JSONObject o) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				JSONObject c = o.getJSONObject("chunk"+i+j);
				chunks[i][j] = new Chunk3D(c);
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
	
	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		o.put("worldX", NUM_X);
		o.put("worldZ", NUM_Z);
		o.put("chunkX", Chunk3D.NUM_X);
		o.put("chunkY", Chunk3D.NUM_Y);
		o.put("chunkZ", Chunk3D.NUM_Z);
		o.put("blockX", Block.WIDTH);
		o.put("blockY", Block.HEIGHT);
		o.put("blockZ", Block.DEPTH);
		for(int i=0 ; i<NUM_X ; i++){
			for(int k=0 ; k<NUM_Z ; k++){
				o.put("chunk"+i+k, chunks[i][k].toJSON());
			}
		}
		return o;
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

	public Chunk3D getChunkFromBlock(Block b){
		int chunkX = (int)((b.getPosition().getX()) / Chunk3D.NUM_X*Block.WIDTH/2);
		int chunkZ = (int)((b.getPosition().getZ()) / Chunk3D.NUM_Z*Block.DEPTH/2);
		return chunks[chunkX][chunkZ];
	}
	
	public GVector3f getPosFromBlock(Block b){
		return new GVector3f((b.getPosition().getX()/2) % Chunk3D.NUM_X*Block.WIDTH,
							 (b.getPosition().getY()/2) % Chunk3D.NUM_Y*Block.HEIGHT,
							 (b.getPosition().getZ()/2) % Chunk3D.NUM_Z*Block.DEPTH);
	}
	
	public void remove(Block b) {
		if(b.getPosition().getY() == 0)
			return;
		getChunkFromBlock(b).remove(getPosFromBlock(b));
	}


	public void saveToFile(String data){
		File fileToSave = new File("ulozenaHra.txt");
		PrintStream file = null;;
		try {
			file = new PrintStream(fileToSave);
			file.println(data);
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale súbor nebolo možné najs");
			e1.printStackTrace();
		}
		file.close();
	}
	
	public static String loadFromFile(){
		BufferedReader reader=null;

		String line = null;
		try {
			reader = new BufferedReader(new FileReader("ulozenaHra.txt"));
			line = reader.readLine();
			reader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void add(Block block, int side, int selectBlock) {
		GVector3f sur = getPosFromBlock(block);
		switch(side){
			case 0:
				sur = sur.add(new GVector3f(0,1,0));
				break;
			case 1:
				sur = sur.add(new GVector3f(-1,0,0));
				break;
			case 2:
				sur = sur.add(new GVector3f(0,-1,0));
				break;
			case 3:
				sur = sur.add(new GVector3f(1,0,0));
				break;
			case 4:
				sur = sur.add(new GVector3f(0,0,1));
				break;
			case 5:
				sur = sur.add(new GVector3f(0,0,-1));
				break;
		}
		getChunkFromBlock(block).add(sur,new Block(new GVector3f(),selectBlock));
	}
}
