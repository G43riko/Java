package org.engine.component.movement;

import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.core.Input;
import org.lwjgl.input.Mouse;

import glib.util.vector.GVector3f;

public class TOP extends BasicMovement{

	public TOP(GameAble parent) {
		super(parent);
		
		parent.getCamera().setPosition(new GVector3f(0,10,0));
		parent.getCamera().setRotation(new GVector3f(90,0,0));
	}
	
	@Override
	public void input() {
		GVector3f tmpUp = getParent().getCamera().getUpVector().cross(getParent().getCamera().getRightVector());
		
		if(Input.isKeyDown(keys.get(BasicMovement.FORWARD))){
			getParent().getCamera().move(tmpUp.mul(moveSpeed));
			move = true;
		}
		if(Input.isKeyDown(keys.get(BasicMovement.BACK))){
			getParent().getCamera().move(tmpUp.mul(-moveSpeed));
			move = true;
		}
		
		if(Input.isKeyDown(keys.get(BasicMovement.RIGHT))){
			getParent().getCamera().move(getParent().getCamera().getRightVector().mul(moveSpeed));
			move = true;
		}
		
		if(Input.isKeyDown(keys.get(BasicMovement.LEFT))){
			getParent().getCamera().move(getParent().getCamera().getRightVector().mul(-moveSpeed));
			move = true;
		}
		
		if(Input.isKeyDown(keys.get(BasicMovement.TURN_LEFT))){
			getParent().getCamera().rotate(new GVector3f(0, -rotSpeed, 0));;
			rotate = true;
		}
		
		if(Input.isKeyDown(keys.get(BasicMovement.TURN_RIGHT))){
			getParent().getCamera().rotate(new GVector3f(0, rotSpeed, 0));;
			rotate = true;
		}
		getParent().getCamera().move(new GVector3f(0, -Mouse.getDWheel() * moveSpeed * 0.1, 0));
			
		
		if(move || rotate){
			getParent().getCamera().updateForward();
			move = rotate = false;
		}
		
	}

}
