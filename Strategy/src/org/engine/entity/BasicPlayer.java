package org.engine.entity;

import glib.util.vector.GVector3f;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.object.GameObject;
import org.strategy.rendering.RenderingEngineStrategy;

public class BasicPlayer extends GameComponent{

	private GVector3f forward;
	private GameObject object;
	
	//CONSTRUCTORS
	
	public BasicPlayer(GameObject o) {
		super(GameComponent.PLAYER);
		object = o;
	}

	//OTHERS
	
	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double y = Math.sin(Math.toRadians(360-getRotation().getY()))*Math.cos(Math.toRadians(getRotation().getX()));
		double z = Math.sin(Math.toRadians(getRotation().getX()));
		forward = new GVector3f((float)y,(float)z,(float)-x);
	}
	
	//OVERRIDES
	
	public void setPosition(GVector3f position) {
		super.setPosition(position);
		object.setPosition(position);
	}
	public void setRotation(GVector3f rotation) {
		super.setRotation(rotation);
		object.setRotation(rotation);
	}
	public void move(GVector3f vec) {
		super.move(vec);
		object.move(vec);
	}
	
	public void rotate(GVector3f vec) {
		super.rotate(vec);
		object.rotate(vec);
	}
	
	
	public void render(RenderingEngineStrategy renderingEngine) {
		object.render(renderingEngine);
	}

	//GETTERS
	
	public GVector3f getForward() {
		return forward;
	}
	public GVector3f getRight(){
		return Camera.UP.cross(forward).Normalized();
	}
	
}
