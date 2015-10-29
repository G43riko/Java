package org.engine.component.movement;

import org.engine.DefaultApp;
import org.engine.app.GameAble;
import org.engine.core.Input;
import org.lwjgl.input.Mouse;

import glib.util.vector.GVector3f;

public class TOP extends BasicMovement{

	public TOP(GameAble parent) {
		super(parent, new GVector3f(0.3, 0.6, 0.01));
		
		parent.getCamera().setPosition(new GVector3f(0,10,0));
		parent.getCamera().setRotation(new GVector3f(90,0,0));
	}
	
	@Override
	public void input() {
		GVector3f tmpUp = getParent().getCamera().getUpVector().cross(getParent().getCamera().getRightVector());
		
		if(Input.isKeyDown(keys.get(FORWARD))){
			getParent().getCamera().move(tmpUp.mul(getMoveSpeed()));
			move = true;
		}
		if(Input.isKeyDown(keys.get(BACK))){
			getParent().getCamera().move(tmpUp.mul(-getMoveSpeed()));
			move = true;
		}
		
		if(Input.isKeyDown(keys.get(RIGHT))){
			getParent().getCamera().move(getParent().getCamera().getRightVector().mul(getMoveSpeed()));
			move = true;
		}
		
		if(Input.isKeyDown(keys.get(LEFT))){
			getParent().getCamera().move(getParent().getCamera().getRightVector().mul(-getMoveSpeed()));
			move = true;
		}
		
		if(DefaultApp.getBoolean("canRotate") && Input.isKeyDown(keys.get(TURN_LEFT))){
			getParent().getCamera().rotate(new GVector3f(0, -getRotSpeed(), 0));;
			rotate = true;
		}
		
		if(DefaultApp.getBoolean("canRotate") && Input.isKeyDown(keys.get(TURN_RIGHT))){
			getParent().getCamera().rotate(new GVector3f(0, getRotSpeed(), 0));;
			rotate = true;
		}
		getParent().getCamera().move(new GVector3f(0, -Mouse.getDWheel() * getZoomSpeed(), 0));
			
		
		if(move || rotate){
			getParent().getCamera().updateForward();
			move = rotate = false;
		}
		
	}

}
