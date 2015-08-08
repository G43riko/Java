package org.tester.voxel.world;

import glib.util.vector.GVector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;
import org.lwjgl.opengl.Display;

public class Chunk extends GameComponent{
	public final static int NUM_X = 4;
	public final static int NUM_Y = 32;
	public final static int NUM_Z = 4;
	public final static int WIDHT = NUM_X * Block.WIDTH;
	public final static int DEPTH = NUM_Z * Block.DEPTH;
	
	private List<Block> blocksToRender = new ArrayList<Block>(); 
	
	private Chunk[] neighboards = new Chunk[4];
	
	private HashMap<String, Block> blocks = new HashMap<String, Block>();
	
	//CONSTRUCTORS
	
	public Chunk(GVector3f position) {
		setPosition(position);
		create();
//		setNeighBoardsAndSides();
	}
	
	//OTHERS
	
	private void create() {
		for(int i=0 ; i<NUM_X ; i++){
			for(int k=0 ; k<NUM_Z ; k++){
				float height = NUM_Y / 2;
				for(int j=0 ; j<NUM_Y ; j++){
					int type = Blocks.getRandomType();
//					type = i%2 * k%2;
					if(type == 0)
						setBlock(i, j, k, null);
					else if(height>j)
						setBlock(i, j, k, new Block(type, getPosition().add(new GVector3f(i, j, k)).mul(2).add(1)));
				}
			}
		}
	}

	public void setNeighBoardsAndSides(){
		forEachBlock((sur, block) -> {
			if(block == null)
				return;
			
			String[] surs = sur.split("-");
			int x = Integer.parseInt(surs[0]);
			int y = Integer.parseInt(surs[1]);
			int z = Integer.parseInt(surs[2]);
			
			if(x + 1 < NUM_X)
				if(exist(x + 1, y, z))
					getBlock(x + 1, y, z).setNeighBoard(3, block);
			
			if(x > 0)
				if(exist(x - 1, y, z))
					getBlock(x - 1, y, z).setNeighBoard(1, block);
			
			if(y + 1 < NUM_Y)
				if(exist(x, y + 1, z))
					getBlock(x, y + 1, z).setNeighBoard(2, block);
			
			if(y > 0)
				if(exist(x, y - 1, z))
					getBlock(x, y - 1, z).setNeighBoard(0, block);
			
			if(z + 1 < NUM_Z)
				if(exist(x, y, z + 1))
					getBlock(x, y, z + 1).setNeighBoard(5, block);
			
			if(z > 0)
				if(exist(x, y, z - 1))
					getBlock(x, y, z - 1).setNeighBoard(4, block);
			
			
			if(x + 1 == NUM_X && neighboards[1] != null)
				if(neighboards[1].exist(0, y, z))
					neighboards[1].getBlock(0, y, z).setNeighBoard(3, block);
			
			if(x == 0 && neighboards[3] != null)
				if(neighboards[3].exist(NUM_X - 1, y, z))
					neighboards[3].getBlock(NUM_X - 1, y, z).setNeighBoard(1, block);
			
			if(z + 1 == NUM_Z && neighboards[0] != null)
				if(neighboards[0].exist(x, y, 0))
					neighboards[0].getBlock(x, y, 0).setNeighBoard(5, block);
			
			if(z == 0 && neighboards[2] != null)
				if(neighboards[2].exist(x, y, NUM_Z - 1))
					neighboards[2].getBlock(x , y, NUM_Z - 1).setNeighBoard(4, block);
					
				
		});
	}
	
	public boolean exist(int x, int y, int z){
		return getBlock(x, y, z) != null;
	}
	
	private void forEachBlock(BiConsumer<? super String, ? super Block> action){
		blocks.forEach(action);
	}
	
	
	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		forEachBlock((a, block) ->{
			if(block != null && block.isActive())
				if(renderingEngine.getMainCamera().isVisible(block.getPosition(), new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH)))
				block.render(renderingEngine);
		});
		
//		blocks.entrySet()
//			  .stream()
//			  .filter(e -> e.getValue()!= null && e.getValue().isActive())
//			  .forEach(e -> e.getValue().render(renderingEngine));
	}
	
	@Override
	public void update() {
	}
	
	//SETTERS
	
	private void setBlock(int x, int y, int z, Block block){
		blocks.put(x + "-" + y + "-" + z, block);
	}

	public void setNeighBoard(int num, Chunk chunk){
		neighboards[num] = chunk;
	}
	
	//GETTERS
	
	public Chunk getNeigBoard(int num){
		return neighboards[num];
	}
	
	public Block getBlock(int x, int y, int z){
		return blocks.get(x + "-" + y + "-" + z);
	}

	public static GVector3f getSize() {
		return new GVector3f(WIDHT, NUM_Y * Block.HEIGHT, DEPTH);
	}

}
