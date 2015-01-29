package com.voxel.world;

import com.voxel.rendering.material.Material;

import glib.util.vector.GVector3f;

public class BlockType {
	private int id;
	
	private String name;
	
	private GVector3f color;
	private Material material;
	
	private boolean transparent;
	private boolean repeat;
	
	private float repX;
	private float repY;

	public BlockType(int id, String name, GVector3f color, Material material, boolean transparent) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.material = material;
		this.transparent = transparent;
		
		repeat = true;
		repX = 1;
		repY = 1;
	}
	
	public int getId() {return id;}
	public String getName() {return name;}
	public GVector3f getColor() {return color;}
	public Material getMaterial() {return material;}
	
	public boolean isTransparent() {return transparent;}
	public boolean isRepeat() {return repeat;}
	
	public float getRepX() {return repX;}
	public float getRepY() {return repY;}
	
	public void setId(int id) {	this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setColor(GVector3f color) {this.color = color;}
	public void setMaterial(Material material) {this.material = material;}
	public void setTransparent(boolean transparent) {this.transparent = transparent;}
	public void setRepeat(boolean repeat) {this.repeat = repeat;}
	public void setRepX(float repX) {this.repX = repX;}
	public void setRepY(float repY) {this.repY = repY;}
}
