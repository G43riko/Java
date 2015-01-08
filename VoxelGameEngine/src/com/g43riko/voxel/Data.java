package com.g43riko.voxel;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

public class Data {
	private static HashMap<String,Vector3f> vectors3f = new HashMap<String,Vector3f>();
	private static HashMap<String,Float> floats = new HashMap<String,Float>();
	private static HashMap<String,Integer> ints = new HashMap<String,Integer>();
	
	public static void add(String key,Vector3f value){
		vectors3f.put(key, value);
	}
	
	public static void add(String key,float value){
		floats.put(key, value);
	}
	
	public static void add(String key,int value){
		ints.put(key, value);
	}
	
	public static Vector3f getVec3f(String key){
		return vectors3f.get(key);
	}
	
	public static float getInt(String key){
		return floats.get(key);
	}
	
	public static int getFloat(String key){
		return ints.get(key);
	}
}
