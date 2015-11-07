package org.tester.voxel.world;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public class Sandbox extends GameComponent{
	public final static GVector3f SIZE = new GVector3f(40, 60, 40);
	private HashMap<String, Block> blocks = new HashMap<String, Block>();
	
	public Sandbox(GameAble parent) {
		super(parent);
		create();
		setNeighBoardsAndSides();
	}
	
	private void create(){
		for(int i=0 ; i<SIZE.getX() ; i++)
			for(int k=0 ; k<SIZE.getZ() ; k++)
				set(i, 0, k, new Block(getParent(), Blocks.getRandomType(), new GVector3f(i, 0, k).mul(2)));
	}
	
	public void render(RenderingEngine renderingEngine) {
		forEachBlock((a, block) ->{
			if(block != null && block.isActive())
				if(renderingEngine.getMainCamera().isVisible(block.getPosition(), new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH)))
				block.render(renderingEngine);
		});
	}
	
	public void setNeighBoardsAndSides(){
		forEachBlock((sur, block) -> {
			if(block == null)
				return;
			
			String[] surs = sur.split("_");
			int x = Integer.parseInt(surs[0]);
			int y = Integer.parseInt(surs[1]);
			int z = Integer.parseInt(surs[2]);
			
			if(x + 1 < SIZE.getXi())
				if(exist(x + 1, y, z))
					get(x + 1, y, z).setNeighBoard(3, block);
			
			if(x > 0)
				if(exist(x - 1, y, z))
					get(x - 1, y, z).setNeighBoard(1, block);
			
			if(y + 1 < SIZE.getYi())
				if(exist(x, y + 1, z))
					get(x, y + 1, z).setNeighBoard(2, block);
			
			if(y > 0)
				if(exist(x, y - 1, z))
					get(x, y - 1, z).setNeighBoard(0, block);
			
			if(z + 1 < SIZE.getZi())
				if(exist(x, y, z + 1))
					get(x, y, z + 1).setNeighBoard(5, block);
			
			if(z > 0)
				if(exist(x, y, z - 1))
					get(x, y, z - 1).setNeighBoard(4, block);
			
		});
	}
	
	private void forEachBlock(BiConsumer<? super String, ? super Block> action){
		blocks.forEach(action);
	}
	
	private Block get(int x, int y, int z){
		return blocks.get(x + "_" + y + "_" + z);
	}
	
	private void set(int x, int y, int z, Block block){
		blocks.put(x + "_" + y + "_" + z, block);
	}
	
	public boolean exist(int x, int y, int z){
		return get(x, y, z) != null;
	}
	
	private void forEachBlock(Consumer<? super Block> action){
		blocks.forEach((a, b) -> action.accept(b));
	}
	
	private void forEachBlock(Consumer<? super Block> action, Predicate<? super Block> contition){
		blocks.entrySet()
		   .stream()
		   .filter(a -> contition.test(a.getValue()))
		   .forEach(a -> action.accept(a.getValue()));
	}

}
