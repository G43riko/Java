package org.engine.component;


import java.beans.ConstructorProperties;

import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public abstract class GameComponent {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	
	private boolean dead;
	private boolean active;
	
	//CONSTRUCTORS
	
	public GameComponent() {
		this(new GVector3f(), new GVector3f(), new GVector3f(1));
	}
	
	@ConstructorProperties({"position"})
	public GameComponent(GVector3f position) {
		this(position, new GVector3f(), new GVector3f(1));
	}
	
	@ConstructorProperties({"position", "rotation", "scale"})
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

	//SETTERS
	
	public void setPosition(GVector3f position) {this.position = position;}
	public void setRotation(GVector3f rotation) {this.rotation = rotation;}
	public void setScale(GVector3f scale) {this.scale = scale;}
	public void setActive(boolean active) {this.active = active;}
	public void setDead(boolean dead) {this.dead = dead;}
}


