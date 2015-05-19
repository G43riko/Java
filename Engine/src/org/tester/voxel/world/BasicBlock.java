package org.tester.voxel.world;

import org.engine.component.GameComponent;
import org.engine.rendering.material.Material;

public abstract class BasicBlock extends GameComponent{
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
