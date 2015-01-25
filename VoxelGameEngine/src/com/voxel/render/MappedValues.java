package com.voxel.render;

import glib.util.vector.GVector3f;

import java.util.HashMap;

public class MappedValues {
	private HashMap<String, GVector3f> vector3fHashMap;
	private HashMap<String, Float> floatHashMap;
	
	public MappedValues(){
		vector3fHashMap = new HashMap<String,GVector3f>();
		floatHashMap = new HashMap<String,Float>();
	}
	
	public void addGVector3f(String name,GVector3f color){
		vector3fHashMap.put(name, color);
	}
	
	public void addFloat(String name,float value){
		floatHashMap.put(name, value);
	}
	
	public GVector3f getGVector3f(String name){
		GVector3f result = vector3fHashMap.get(name);
		if(result != null)
			return result;
		return new GVector3f(0,0,0);
	}
	
	public float getFloat(String name){
		Float result = floatHashMap.get(name);
		if(result != null)
			return result;
		return 0;
	}
}
