package org.engine.entity;

import glib.util.vector.GVector3f;

import org.engine.component.Camera;
import org.engine.object.GameObject;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.model.Model;
import org.strategy.rendering.RenderingEngineStrategy;

public class BasicPlayer extends GameObject{

	private GVector3f forward;
	
	public GVector3f lookingAt;
	
	//CONSTRUCTORS
	
	public BasicPlayer(Model model, Material material) {
		super(model, material);
	}
	
	public BasicPlayer(GameObject o) {
		super(o.getModel(), o.getMaterial());
	}

	//OTHERS
	
	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double y = Math.sin(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double z = Math.sin(Math.toRadians(getRotation().getX()));
		forward = new GVector3f((float)y,(float)z,(float)-x);
	}
	
	//OVERRIDES
	
	public void render(RenderingEngineStrategy renderingEngine) {
		
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
