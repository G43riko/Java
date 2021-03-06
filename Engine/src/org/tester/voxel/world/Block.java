package org.tester.voxel.world;

import org.engine.app.GameAble;
import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public class Block extends BasicBlock{
	public final static int WIDTH = 1;
	public final static int DEPTH = 1;
	public final static int HEIGHT = 1;
	
	private Block[] neightboars = new Block[6];
	
	
	private GVector3f direction = new GVector3f();
	
	private GVector3f[][] points = new GVector3f[][]{ new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT,-Block.DEPTH)}};
	
	public Block(GameAble parent, int type, GVector3f position) {
		super(parent);
		setPosition(position);
		setScale(new GVector3f(WIDTH, DEPTH, HEIGHT));
		setType(type);
	}
	
	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		if(isVisible())
			renderingEngine.renderBlock(this);
	}
	
	//GETTERS
	
	public boolean getSide(int i) {
		return neightboars[i] == null || !neightboars[i].isVisible() ;
	}
	
	public GVector3f getPoint(int i, int j){
		return points[i][j];
	}
	
	//SETTERS
	
	public void setNeighBoard(int num, Block block){
		neightboars[num] = block;
	}
	
	public boolean isSelectable(){
		return true;
	}

	public boolean isVisible() {
		return getType() > 0;
	}
}
