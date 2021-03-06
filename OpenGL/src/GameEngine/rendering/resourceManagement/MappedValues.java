package GameEngine.rendering.resourceManagement;

import java.util.HashMap;

import GameEngine.core.util.Vector3f;

public abstract class MappedValues {
	private HashMap<String, Vector3f> vector3fHashMap;
	private HashMap<String, Float> floatHashMap;
	
	public MappedValues(){
		vector3fHashMap = new HashMap<String,Vector3f>();
		floatHashMap = new HashMap<String,Float>();
	}
	
	public void addVector3f(String name,Vector3f color){
		vector3fHashMap.put(name, color);
	}
	
	public void addFloat(String name,float value){
		floatHashMap.put(name, value);
	}
	
	public Vector3f getVector3f(String name){
		Vector3f result = vector3fHashMap.get(name);
		if(result != null)
			return result;
		return new Vector3f(0,0,0);
	}
	
	public float getFloat(String name){
		Float result = floatHashMap.get(name);
		if(result != null)
			return result;
		return 0;
	}
}
