package org.engine.component.light;

import org.engine.app.GameAble;

import glib.util.vector.GVector3f;

public class DirectionalLight extends BasicLight{
	
	public DirectionalLight(GameAble parent, GVector3f direction, GVector3f color){
		super(parent, color);
		setRotation(direction);
	}
	
	public void setColor(GVector3f color){
		setChange(true);
		this.color = color;
	}
}
