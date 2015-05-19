package org.tester.voxel.world;

import glib.util.vector.GVector3f;

import org.engine.rendering.RenderingEngine;

public class Block extends BasicBlock{
	public final static int WIDTH = 1;
	public final static int DEPTH = 1;
	public final static int HEIGHT = 1;
	
	private Block[] neightboars = new Block[6];
	
	private boolean[] sides = new boolean[]{true,true,true,true,true,true};
	
	private GVector3f direction = new GVector3f();
	
	public Block(int type, GVector3f position) {
		setPosition(position);
		setScale(new GVector3f(WIDTH, DEPTH, HEIGHT));
		setType(type);
	}
	
	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderBlock(this);
	}
	
	//GETTERS
	
	public boolean getSide(int i) {
		return sides[i];
	}
	
	//SETTERS
	
	public void setNeighBoard(int num, Block block){
		neightboars[num] = block;
		sides[num] = false;
	}
}
