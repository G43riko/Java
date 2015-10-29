package org.engine.component.light;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public abstract class BasicLight  extends GameComponent{
	protected GVector3f color;
	private float intensity;
	
	//CONSTRUCORS
	
	public BasicLight(GameAble parent, GVector3f color){
		this(parent, new GVector3f(), color, 1);
	}
	
	public BasicLight(GameAble parent, GVector3f position, GVector3f color, int intensity){
		super(parent, position);
		this.color = color;
		this.intensity = intensity;
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
