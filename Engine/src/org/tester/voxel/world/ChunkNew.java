package org.tester.voxel.world;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public class ChunkNew extends GameComponent{
	public final static int NUM_X = 8;
	public final static int NUM_Y = 8;
	public final static int NUM_Z = 8;
	
	private HashMap<String, Block> map = new HashMap<String, Block>();
	
	public ChunkNew(GameAble parent){
		super(parent);
		
	}
	
	private void create(){
		for(int i=0 ; i<NUM_X ; i++)
			for(int j=0 ; j<NUM_Y ; j++)
				for(int k=0 ; k<NUM_Z ; k++)
					set(i, j, k, new Block(getParent(), Blocks.getRandomType(), new GVector3f(i, j, k).mul(2)));
	}
	
	private Block get(int x, int y, int z){
		return map.get(x + "_" + y + "_" + z);
	}
	
	private void set(int x, int y, int z, Block block){
		map.put(x + "_" + y + "_" + z, block);
	}
	
	private void forEachBlock(Consumer<? super Block> action){
		map.forEach((a, b) -> action.accept(b));
	}
	
	private void forEachBlock(Consumer<? super Block> action, Predicate<? super Block> contition){
		map.entrySet()
		   .stream()
		   .filter(a -> contition.test(a.getValue()))
		   .forEach(a -> action.accept(a.getValue()));
	}
}
