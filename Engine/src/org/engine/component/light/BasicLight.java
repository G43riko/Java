package org.engine.component.light;

import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;

public abstract class BasicLight  extends GameComponent{
	protected GVector3f color;
	private float intensity;
	
	//CONSTRUCORS
	
	public BasicLight(GVector3f position){
		super(position);
		intensity = 1;
	}
	
	//GETTERS
	
	public GVector3f getColor() {
		return color;
	}

	public float getIntensity() {
		return intensity;
	}
}
