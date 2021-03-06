package org.tester.voxel.world;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.rendering.material.Material;

public abstract class BasicBlock extends GameComponent{
	public BasicBlock(GameAble parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	private int type;

	//GETTERS
	
	public Material getMaterial(){
		return Blocks.getMaterial(type);
	}
	
	public int getBlockType() {
		return type;
	}
	
	public int getType(){
		return type;
	}

	//SETTERS
	
	public void setType(int type) {
		this.type = type;
	}
}
