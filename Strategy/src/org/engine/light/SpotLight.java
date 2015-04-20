package org.engine.light;

import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public class SpotLight extends PointLight{
	private float cutoff;
	
	//CONSTRUCTORS
	
	public SpotLight(GVector3f position){
		this(position, new GVector3f(1), new GVector3f(1,0,0), 0);
	}
	
	public SpotLight(GVector3f position,  GVector3f color ){
		this(position, color, new GVector3f(1,0,0), 0);
	}
	
	public SpotLight(GVector3f position,  GVector3f color, GVector3f attenuation){
		this(position, color, attenuation, 0);
	}
	
	public SpotLight(GVector3f position,  GVector3f color, GVector3f attenuation, float cutoff){
		super(position, color, attenuation, GameComponent.SPOTLIGHT);
		this.cutoff = cutoff;
	}

	//GETTERS
	
	public float getCutoff() {
		return cutoff;
	}
}
