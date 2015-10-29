package org.engine.component;

import glib.util.vector.GVector3f;

import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.component.object.GameObject;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;

public class BasicPlayer extends GameObject{

	private GVector3f forward;
	
	public GVector3f lookingAt;
	
	//CONSTRUCTORS
	
	public BasicPlayer(GameAble parent, Model model, Material material) {
		super(parent, material, model);
	}
	
	public BasicPlayer(GameAble parent, GameObject o) {
		super(parent, o.getMaterial(), o.getModel());
	}

	//OTHERS
	
	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double y = Math.sin(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double z = Math.sin(Math.toRadians(getRotation().getX()));
		forward = new GVector3f((float)y,(float)z,(float)-x);
	}
	
	//OVERRIDES
	
	public void render(RenderingEngine renderingEngine) {
		
		renderingEngine.renderObject(this);
	}

	//GETTERS
	
	public GVector3f getForward() {
		return forward;
	}
	
	public GVector3f getRight(){
		return Camera.UP.cross(forward).Normalized();
	}
	
}
