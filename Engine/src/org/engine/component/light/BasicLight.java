package org.engine.component.light;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public abstract class BasicLight  extends GameComponent{
	protected GVector3f color;
	private float intensity;
	
	//CONSTRUCORS
	
	public BasicLight(GameAble parent, GVector3f color){
		this(parent, new GVector3f(), color);
	}
	
	public BasicLight(GameAble parent, GVector3f position, GVector3f color){
		super(parent, position);
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
