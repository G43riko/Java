package com.voxel.rendering.material;

import java.util.HashMap;

import com.voxel.rendering.MappedValues;

public class Material extends MappedValues{

	private HashMap<String, Texture> textureHashMap;

	public Material(){
		super();
		textureHashMap = new HashMap<String,Texture>();
	}
	
	public Material(String name,Texture texture){
		textureHashMap = new HashMap<String,Texture>();
		textureHashMap.put(name, texture);
	}
	
	public void addTexture(String name,Texture texture){
		textureHashMap.put(name, texture);
	}
	
	public Texture getTexture(String name){
		Texture result = textureHashMap.get(name);
		if(result != null)
			return result;
		return new Texture("unknown.jpg");
	}
	
	public void addNormal(String name,Texture texture){
		textureHashMap.put(name, texture);
	}
	
	public Texture getNormal(String name){
		Texture result = textureHashMap.get(name);
		if(result != null)
			return result;
		return new Texture("unknown.jpg");
	}

}
