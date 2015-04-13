package org.engine.light;

import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public class DirectionalLight extends GameComponent{
	private GVector3f color;
	
	//CONSTRUCTORS
	
	public DirectionalLight(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		super(position, GameComponent.DIRECTIONAL_LIGHT);
	}

	//GETTERS
	
	public GVector3f getColor() {
		return color;
	}
	
}
