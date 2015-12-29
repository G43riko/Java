package org.engine.component;

import java.io.EOFException;

import org.engine.app.GameAble;
import org.engine.core.Interactable;
import org.engine.rendering.RenderingEngine;
import org.engine.utils.Maths;
import org.lwjgl.util.vector.Matrix4f;

import glib.util.IDGenerator;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public abstract class GameComponent implements Interactable{
	private int			id	= IDGenerator.getId();
	private GVector3f	position;
	private GVector3f	rotation;
	private GVector3f	scale;
	private boolean		dead;
	private boolean		active;
	private boolean		change;
	private GameAble	parent;
	
	//CONSTRUCTORS
	
	public GameComponent(GameAble parent) {
		this(parent, new GVector3f(), new GVector3f(), new GVector3f(1));
	}
	
	public GameComponent(GameAble parent, GVector3f position) {
		this(parent, position, new GVector3f(), new GVector3f(1));
	}
	
	public GameComponent(GameAble parent, GVector3f position, GVector3f rotation, GVector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.parent = parent;
		active = true;
		dead = false;
	}
	
	//OTHERS
	
	public void move(GVector3f vec){
		if(vec.isNull())
			return;
		change = true;
		position = position.add(vec);
	}
	
	public void rotate(GVector3f vec){
		if(vec.isNull())
			return;
		change = true;
		rotation = rotation.add(vec);
		
	}
		
	//GETTERS
	public String 	 getData(){return position + " " + rotation + " " + scale;}
	public GVector3f getPosition() {return position;}
	public GVector3f getRotation() {return rotation;}
	public GameAble  getParent(){return parent;}
	public GVector3f getScale() {return scale;}
	public int 		 getId(){return id;}
	public boolean   isDead() {return dead;}
	public boolean   isActive() {return active;}
	public boolean 	 isChange() {return change;}
	
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
	public void setActive(boolean active) {this.active = active;}
	public void setChange(boolean change) {this.change = change;}
	public void setScale(GVector3f scale) {this.scale = scale;}
	public void setDead(boolean dead) {this.dead = dead;}

	
}


