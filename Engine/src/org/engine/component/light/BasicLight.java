package org.engine.component.light;

import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;

public abstract class BasicLight  extends GameComponent{
	protected GVector3f color;
	private float intensity;
	
	//CONSTRUCORS
	
	public BasicLight( GVector3f color){
		this(new GVector3f(), color);
	}
	
	public BasicLight(GVector3f position, GVector3f color){
		super(position);
		this.color = color;
		intensity = 1;
	}
	
	//GETTERS
	
	public GVector3f getColor() {
		setChange(true);
		return color;
	}

	public float getIntensity() {
		setChange(true);
		return intensity;
	}
	
}
