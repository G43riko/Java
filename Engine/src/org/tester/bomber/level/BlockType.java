package org.tester.bomber.level;

import org.engine.rendering.material.Material;

public class BlockType {
	private Material material;
	private int healt;
	
	public BlockType(Material material, int healt) {
		this.material = material;
		this.healt = healt;
	}
	
	public Material getMaterial() {
		return material;
	}
	public int getHealt() {
		return healt;
	}
	
	
}
