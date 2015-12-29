package org.engine.ai;

import glib.util.vector.GVector3f;

public class Terrain {
	
	public float getHeight(float x, float z){
		return 0;
	}
	
	public float getHeight(GVector3f position){
		return getHeight(position.getX(), position.getZ());
	}
	
	
}
