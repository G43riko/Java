package GameEngine.rendering;

import java.util.HashMap;

import GameEngine.core.ResourceLoader;
import GameEngine.core.Vector3f;

public class Material {
	
	private HashMap<String, Texture> textureHashMap;
	private HashMap<String, Vector3f> colorHashMap;
	private HashMap<String, Float> floatHashMap;

	public Material(){
		textureHashMap = new HashMap<String,Texture>();
		colorHashMap = new HashMap<String,Vector3f>();
		floatHashMap = new HashMap<String,Float>();
	}
	
	public void addTexture(String name,Texture texture){
		textureHashMap.put(name, texture);
	}
	public Texture getTexture(String name){
		Texture result = textureHashMap.get(name);
		if(result != null)
			return result;
		return new Texture("dirt.jpg");
	}
	
	public void addColor(String name,Vector3f color){
		colorHashMap.put(name, color);
	}
	
	public Vector3f getColor(String name){
		Vector3f result = colorHashMap.get(name);
		if(result != null)
			return result;
		return new Vector3f(0,0,0);
	}
	
	public void addFloat(String name,float value){
		floatHashMap.put(name, value);
	}
	
	public float getFloat(String name){
		Float result = floatHashMap.get(name);
		if(result != null)
			return result;
		return 0;
	}
}
