package org.strategy.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.engine.component.GameComponent;
import org.json.JSONObject;
import org.strategy.component.CameraStrategy;
import org.strategy.particles.Explosion;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.noise.PerlinNoise;
import glib.util.vector.GVector3f;

public class World extends GameComponent{
	public static int NUM_X = 3;
	public static int NUM_Z = 3;
	public static float[][] map;
	private CameraStrategy camera;
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>(); 
	private boolean running;
	private HashMap<String, Chunk3D> chunks = new HashMap<String, Chunk3D>();
	
	public static int NUMBER_OF_RENDERED_BLOCK;  
	
	//CONSTRUCTORS
	
	public World() {
		super(GameComponent.WORLD);
		
		map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk3D.NUM_X * NUM_X, Chunk3D.NUM_Z * NUM_Z), 6, 0.7f, true);
		
		
		
		create();
		setNeighboards();
	}
	
	public World(String fileName) {
		super(10);
		
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
		
		create(o);
		setNeighboards();
		
		System.out.println("Svet "+fileName+" bol úspešne naèítaný");
	}

	//CREATORS
	
	private void createSandBox() {
		Chunk3D c = new Chunk3D(new GVector3f(),"sandBox");
		c.setWorld(this);
		chunks.put("0-0", c);
		
	}
	
	private void create(JSONObject o) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				JSONObject data = o.getJSONObject("chunk"+i+j);
				Chunk3D c = new Chunk3D(data);
				c.setWorld(this);
				chunks.put(i+"-"+j, c);
			}
		}
	}
	
	private void create() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				GVector3f pos = new GVector3f(2).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(new GVector3f(i,0,j).mul(new GVector3f(Chunk3D.NUM_X,0,Chunk3D.NUM_Z))));
				Chunk3D c = new Chunk3D(pos);
				c.setWorld(this);
				chunks.put(i+"-"+j, c);
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
//		return i>=0 && j>=0 && i<NUM_X && j < NUM_Z;
		return chunks.containsKey(i+"-"+j);
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
			System.out.println("svet sa úspešne uložil ako "+fileName);
			
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale súbor nebolo možné najs");
			e1.printStackTrace();
		}
		file.close();
	}
	
	public static String loadFromFile(String fileName){
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/components/maps/"+fileName));
			System.out.println("súbor "+fileName+" bol úspešne otvorený");
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

	//OVERRIDES
	
	public void render(RenderingEngineStrategy renderingEngine) {
		NUMBER_OF_RENDERED_BLOCK = 0;
//		double time = System.currentTimeMillis();
		
		for(Entry<String, Chunk3D> e : chunks.entrySet()) {
			Chunk3D c = e.getValue();
			if(c.getPosition().add(new GVector3f(Chunk3D.NUM_X*Block.WIDTH ,0,Chunk3D.NUM_Z*Block.DEPTH)).dist(renderingEngine.getMainCamera().getPosition()) < 120)
				c.render(renderingEngine);
		}
		
		explosions.forEach(e -> e.render(renderingEngine));
		
//		System.out.println(System.currentTimeMillis()-time);
//		System.out.println(NUMBER_OF_RENDERED_BLOCK);
//		System.out.println(explosions.size());
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
				o.put("chunk"+i+k, chunks.get(i+"-"+k).toJSON());
			}
		}
		return o;
	}
	
	public void update(){
		chunks.forEach((a,b) -> b.update());
		
		for(int i=0 ; i<explosions.size() ; i++){
			Explosion e = explosions.get(i);
			e.update();
			if(e.getBlocks().size()==0)
				explosions.remove(e);
		}
		explosions.parallelStream().forEach((e) -> (e.update()));
		explosions.removeAll(explosions.parallelStream().filter(a->a.getBlocks().size()==0).collect(Collectors.toList()));
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
		
		if(exist(chunkX, chunkZ) && chunks.get(chunkX+"-"+chunkZ).exist(blockX, blockY, blockZ, false))
			return chunks.get(chunkX+"-"+chunkZ).getBlock(blockX, blockY, blockZ);
		
		return null;
	}

	public Chunk3D getChunkFromBlock(Block b){
		int chunkX = (int)((b.getPosition().getX()) / Chunk3D.NUM_X*Block.WIDTH/2);
		int chunkZ = (int)((b.getPosition().getZ()) / Chunk3D.NUM_Z*Block.DEPTH/2);
		return chunks.get(chunkX+"-"+chunkZ);
	}
	
	public GVector3f getPosFromBlock(Block b){
		return new GVector3f((b.getPosition().getX()/2) % Chunk3D.NUM_X*Block.WIDTH,
							 (b.getPosition().getY()/2) % Chunk3D.NUM_Y*Block.HEIGHT,
							 (b.getPosition().getZ()/2) % Chunk3D.NUM_Z*Block.DEPTH);
	}
	
	//SETTERS

	public void setCamera(CameraStrategy camera) {
		this.camera = camera;
	}
	
	private void setNeighboards() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				Chunk3D c = chunks.get(i+"-"+j);
				if(i>0)
					c.setNeighboard(3, chunks.get((i-1)+"-"+j));
				if(j>0)
					c.setNeighboard(2, chunks.get(i+"-"+(j-1)));
				if(i+1<NUM_X)
					c.setNeighboard(1, chunks.get((i-1)+"-"+j));
				if(j+1<NUM_Z)
					c.setNeighboard(0, chunks.get(i+"-"+(j+1)));
			}
		}
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Z ; j++){
				chunks.get(i+"-"+j).setNeighboards();
				chunks.get(i+"-"+j).setSides();
			}
		}
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	public CameraStrategy getCamera() {
		return camera;
	}
}
