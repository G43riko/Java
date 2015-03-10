package game.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.json.JSONObject;

import game.components.Explosion;
import game.object.Camera;
import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.noise.PerlinNoise;
import glib.util.vector.GVector3f;

public class World extends GameObject{
	public static int NUM_X = 3;
	public static int NUM_Z = 3;
	public static float[][] map;
	private Camera camera;
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>(); 
	private boolean running;
	private Chunk3D[][] chunks;
	
	//CONSTRUCTORS
	
	public World() {
		super(new GVector3f(), 10);
		
		map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk3D.NUM_X * NUM_X, Chunk3D.NUM_Z * NUM_Z), 6, 0.7f, true);
		chunks = new Chunk3D[NUM_X][NUM_Z];
		
		create();
		setNeighboards();
	}
	
	public World(String fileName) {
		super(new GVector3f(), 10);
		
		if(fileName.equals("sandBox")){
			NUM_X = 1;
			NUM_Z = 1;
			Chunk3D.NUM_X = 32;
			Chunk3D.NUM_Y = 32;
			Chunk3D.NUM_Z = 32;
			
			createSandBox();
			setNeighboards();
			return;
		}
		
		JSONObject o = new JSONObject(loadFromFile(fileName));
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
		
		System.out.println("Svet "+fileName+" bol ˙speöne naËÌtan˝");
	}

	//CREATORS
	
	private void createSandBox() {
		chunks = new Chunk3D[NUM_X][NUM_Z];
		chunks[0][0] = new Chunk3D(new GVector3f(),"sandBox");
		chunks[0][0].setWorld(this);
		
	}
	
	private void create(JSONObject o) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				JSONObject c = o.getJSONObject("chunk"+i+j);
				chunks[i][j] = new Chunk3D(c);
				chunks[i][j].setWorld(this);
			}
		}
	}
	
	private void create() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				GVector3f pos = new GVector3f(2).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(new GVector3f(i,0,j).mul(new GVector3f(Chunk3D.NUM_X,0,Chunk3D.NUM_Z))));
				chunks[i][j] = new Chunk3D(pos);
				chunks[i][j].setWorld(this);
			}
		}
	}
	
	//OTHERS
	
	public void blockInpact(Block block, Block imp) {
		int HEIGHT_LIMIT = 10;
		if(block.getRelativePos().getY()<-HEIGHT_LIMIT){
			remove(block);
		}
		else{
			Chunk3D chunk = getChunkFromBlock(block); 
			chunk.remove(getPosFromBlock(block));
			
			Block b = new Block(new GVector3f(),block.getBlockType());
			b.setWorld(this);
			chunk.add(getPosFromBlock(imp).add(new GVector3f(0,1,0)),b);
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
		for(Explosion e:explosions){
			e.render(renderingEngine);
		}
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
	
	public void update(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].update();
			}
		}
		ArrayList<Explosion> forRemove = new ArrayList<Explosion>();
		for(Explosion e:explosions){
			e.update();
			if(e.getBlocks().size()==0){
				forRemove.add(e);
			}
		}
		explosions.removeAll(forRemove);
	}
	
	public void remove(Block b) {
		if(b.getPosition().getY() == 0)
			return;
		Block e = (new Block(b.getPosition().add(b.getRelativePos()), b.getBlockType()));
		explosions.add(new Explosion(e,5));
		
		getChunkFromBlock(b).remove(getPosFromBlock(b));
	}

	public void saveToFile(String fileName){
		String data = toJSON().toString();
		File fileToSave = new File("res/components/maps/"+fileName);
		PrintStream file = null;
		try {
			file = new PrintStream(fileToSave);
			file.println(data);
			System.out.println("svet sa ˙speöne uloûil ako "+fileName);
			
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale s˙bor nebolo moûnÈ najsù");
			e1.printStackTrace();
		}
		file.close();
	}
	
	public static String loadFromFile(String fileName){
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/components/maps/"+fileName));
			System.out.println("s˙bor "+fileName+" bol ˙speöne otvoren˝");
			line = reader.readLine();
			reader.close();
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
		Block b = new Block(new GVector3f(),selectBlock);
		b.setWorld(this);
		getChunkFromBlock(block).add(sur,b);
	}

	public void moveDown(Block block) {
		Chunk3D actChunk = getChunkFromBlock(block);
		GVector3f actPos = getPosFromBlock(block);
		actChunk.remove(actPos);
		actChunk.add(actPos, block);
	}

	//GETTERS
	
	public boolean isRunning() {
		return running;
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
	
	//SETTERS

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	private void setNeighboards() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				Chunk3D c = chunks[i][j];
				if(i>0)
					c.setNeighboard(3, chunks[i-1][j]);
				if(j>0)
					c.setNeighboard(2, chunks[i][j-1]);
				if(i+1<NUM_X)
					c.setNeighboard(1, chunks[i+1][j]);
				if(j+1<NUM_Z)
					c.setNeighboard(0, chunks[i][j+1]);
	
				c.setNeighboards();
			}
		}
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks[i][j].setSides();
			}
		}
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
}
