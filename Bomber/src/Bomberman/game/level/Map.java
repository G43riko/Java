package Bomberman.game.level;

import glib.util.vector.GVector2f;

import java.awt.Graphics2D;
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import Bomberman.core.Interactable;

public class Map implements Interactable, Serializable{
	private static final long serialVersionUID = 1L;
	
	private transient HashMap<String, Block> blocks;
	private Level parent;
	private GVector2f numberOfBlocks;
	private StringBuilder encodedblocks;
	
	public Map(Level parent) {
		this.parent = parent;
		createRandomMap(20,20);
		saveBlocks();
	}
	
	private void createRandomMap(int x, int y){
		blocks = new HashMap<String, Block>();
		numberOfBlocks = new GVector2f(x, y);
		
		for(int i=0 ; i<x ; i++){
			for(int j=0 ; j<y ; j++){
				addBlock(i, j, new Block(new GVector2f(i,j),(int)(Math.random() * 3), this));
			}
		}
	}
	
	private void createMap(){
		blocks = new HashMap<String, Block>();
		
		String[] strings = encodedblocks.toString().split(";");
		
		Arrays.stream(strings)
			  .map(a -> a.split(":"))
		      .forEach(block -> blocks.put(block[0], new Block(new GVector2f(block[0]), 
		    		  										   Integer.parseInt(block[1]), 
		    		  										   this, 
		    		  										   Integer.parseInt(block[2]))));
	}
	
	public void hitBlocks(ArrayList<String> affectedBlocks, int demage){
		affectedBlocks.stream()
					  .map(a -> blocks.get(a))
					  .filter(a -> a.hit(demage))
					  .forEach(a -> a.setType(0));
	}
	
	private void saveBlocks(){
		encodedblocks = new StringBuilder();
		blocks.entrySet()
		      .stream()
		      .map(a -> a.getKey()              + ":" + 
		    		  	a.getValue().getType()  + ":" + 
		    		  	a.getValue().getHealt() + ";")
		      .forEach(encodedblocks::append);
	}
	
	@Override
	public void render(Graphics2D g2) {
		if(blocks == null)
			createMap();
		
		blocks.entrySet()
		      .stream()
		      .map(a -> a.getValue())
		      .filter(parent.getParrent()::isVisible)
		      .forEach(a -> a.render(g2));
	}
	
	public ArrayList<String> getBlockAroundPoint(GVector2f point, int range){
		return blocks.entrySet()
		             .stream()
		             .filter(a -> point.dist(new GVector2f(a.getKey())) <= range)
		             .map(a -> a.getKey())
		             .collect(Collectors.toCollection(ArrayList::new));
	}
	
	private void addBlock(int i, int j, Block block){
		blocks.put(i + "_" + j, block);
	}
	
	public Block getRandomEmptyBlock(){
		ArrayList<Block> b = blocks.entrySet()
								   .stream()
								   .map(a -> a.getValue())
								   .filter(a -> a.getType() == Block.NOTHING)
								   .collect(Collectors.toCollection(ArrayList<Block>::new));
		return b.get((int)(Math.random() * b.size()));
	}
	
	public Block getBlock(int i, int j){
		return blocks.get(i + "_" + j);
	}
	public Block getBlock(String block){
		return blocks.get(block);
	}

	public Block getBlockOnPosition(GVector2f sur){
		GVector2f blockSize = new GVector2f(Block.WIDTH, Block.HEIGHT);
		GVector2f modulo = sur.mod(blockSize);
		GVector2f pos = sur.sub(modulo).div(blockSize);
		
		return getBlock(pos.getXi(), pos.getYi());
	}
	
	public GVector2f getOffset() {
		return parent.getOffset();
	}

	public GVector2f getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public Level getParent() {
		return parent;
	}
}
