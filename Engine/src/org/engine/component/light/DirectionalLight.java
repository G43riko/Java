package org.engine.component.light;

import glib.util.vector.GVector3f;

public class DirectionalLight extends BasicLight{
	
	public DirectionalLight(GVector3f direction, GVector3f color){
		super(color);
		setRotation(direction);
	}
	
	public void setColor(GVector3f color){
		setChange(true);
		this.color = color;
	}
}
