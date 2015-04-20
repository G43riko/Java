package org.engine.light;

import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;

public class DirectionalLight extends BasicLight{
	
	public DirectionalLight(GVector3f position){
		super(position, GameComponent.DIRECTIONAL_LIGHT);
	}
}
