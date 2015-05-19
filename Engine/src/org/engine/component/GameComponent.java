package org.engine.component;

import org.engine.rendering.RenderingEngine;
import org.engine.utils.Maths;
import org.lwjgl.util.vector.Matrix4f;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public abstract class GameComponent {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	
	private boolean dead;
	private boolean active;
	private boolean change;
	
	//CONSTRUCTORS
	
	public GameComponent() {
		this(new GVector3f(), new GVector3f(), new GVector3f(1));
	}
	
	public GameComponent(GVector3f position) {
		this(position, new GVector3f(), new GVector3f(1));
	}
	
	public GameComponent(GVector3f position, GVector3f rotation, GVector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		active = true;
		dead = false;
	}
	
	//OTHERS
	
	public void render(RenderingEngine renderingEngine){};
	public void input(){};
	public void update(){};
	
	public void move(GVector3f vec){
		change = true;
		position = position.add(vec);
	}
	
	public void rotate(GVector3f vec){
		rotation = rotation.add(vec);
	}
		
	//GETTERS
	
	public GVector3f getPosition() {return position;}
	public GVector3f getRotation() {return rotation;}
	public GVector3f getScale() {return scale;}
	public boolean isDead() {return dead;}
	public boolean isActive() {return active;}
	
	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(position, rotation, scale);
		return Maths.MatrixToGMatrix(trans);
	}

//	public GVector3f getForwardVector(){
//		double x = Math.cos(Math.toRadians(360-rotation.getY())) * Math.cos(Math.toRadians(rotation.getX()));
//		double y = Math.sin(Math.toRadians(360-rotation.getY())) * Math.cos(Math.toRadians(rotation.getX()));
//		double z = Math.sin(Math.toRadians(rotation.getX()));
//		return new GVector3f((float)y,(float)z,(float)-x).Normalized();
//	}
	
	//SETTERS
	
	public void setPosition(GVector3f position) {this.position = position;}
	public void setRotation(GVector3f rotation) {this.rotation = rotation;}
	public void setScale(GVector3f scale) {this.scale = scale;}
	public void setActive(boolean active) {this.active = active;}
	public void setDead(boolean dead) {this.dead = dead;}

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}
}


