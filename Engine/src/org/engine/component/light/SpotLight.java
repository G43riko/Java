package org.engine.component.light;

import org.engine.app.GameAble;

import glib.util.vector.GVector3f;

public class SpotLight extends PointLight{
	private float cutoff;
	
	//CONSTRUCTORS
	
	public SpotLight(GameAble parent, GVector3f position){
		this(parent, position, new GVector3f(1), PointLight.DEFAULT_RANGE, 0, 1);
	}
	
//	public SpotLight(GameAble parent, GVector3f position,  GVector3f color ){
//		this(parent, position, color, new GVector3f(1,0,0), 0);
//	}
//	
//	public SpotLight(GameAble parent, GVector3f position,  GVector3f color, GVector3f attenuation){
//		this(parent, position, color, attenuation, 0);
//	}
//	
	public SpotLight(GameAble parent, GVector3f position,  GVector3f color, float range, float cutoff, int intensity){
		super(parent, position, color, range, intensity);
		this.cutoff = cutoff;
	}

	//GETTERS
	
	public float getCutoff() {
		setChange(true);
		return cutoff;
	}
}
