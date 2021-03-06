package GameEngine.rendering;

import java.util.HashMap;

import GameEngine.rendering.resourceManagement.MappedValues;

public class Material extends MappedValues{
	
	private HashMap<String, Texture> textureHashMap;

	public Material(){
		super();
		textureHashMap = new HashMap<String,Texture>();
	}
	
	public void addTexture(String name,Texture texture){
		textureHashMap.put(name, texture);
	}
	
	public Texture getTexture(String name){
		Texture result = textureHashMap.get(name);
		if(result != null)
			return result;
		return new Texture("brick_diffuse.jpg");
	}
	
	public void addNormal(String name,Texture texture){
		textureHashMap.put(name, texture);
	}
	
	public Texture getNormal(String name){
		Texture result = textureHashMap.get(name);
		if(result != null)
			return result;
		return new Texture("brick_normall.jpg");
	}
}
