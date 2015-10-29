package org.engine.component.movement;

import org.engine.app.GameAble;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class FPS extends BasicMovement{
	private boolean flyMode;
	
	//CONSTRUCTORS
	
	public FPS(GameAble parent, boolean flyMode){
		super(parent, new GVector3f(0.3, 0.6, 0.1));
		this.flyMode = flyMode;
		keys.put(UP, Keyboard.KEY_SPACE);
		keys.put(DOWN, Keyboard.KEY_LSHIFT);
	}

	//OVERRIDES
	
	public void input(){	
		checkMoveKeys();
	
		checkRotateAndFlyKeys();
		
		checkMouseLock();
		
		if(mouseLocked){
			rotate = mouseMove() || rotate;
		}
		
		if(move || rotate){
			getParent().getCamera().updateForward();
			move = rotate = false;
		}
	}

	//CHECKERS

	private void checkMoveKeys(){
		/*	MOVING KEYS
		 * 	W, A, S, D
		 */
		
		if(Keyboard.isKeyDown(keys.get(FORWARD))){
			getParent().getCamera().move(getParent().getCamera().getForwardVector().mul(getMoveSpeed()));
		}
		
		if(Keyboard.isKeyDown(keys.get(BACK))){
			getParent().getCamera().move(getParent().getCamera().getBackVector().mul(getMoveSpeed()));
		}
		
		if(Keyboard.isKeyDown(keys.get(LEFT))){
			getParent().getCamera().move(getParent().getCamera().getLeftVector().mul(getMoveSpeed()));
		}
		
		if(Keyboard.isKeyDown(keys.get(RIGHT))){
			getParent().getCamera().move(getParent().getCamera().getRightVector().mul(getMoveSpeed()));
		}
	}
	
	private void checkRotateAndFlyKeys(){
		/*	ROTATION
		 * 	Q and E
		 */
		
		if(Keyboard.isKeyDown(keys.get(TURN_RIGHT))){
			getParent().getCamera().rotate(new GVector3f(0, getRotSpeed(), 0));
			rotate = true;
		}
		
		if(Keyboard.isKeyDown(keys.get(TURN_LEFT))){
			getParent().getCamera().rotate(new GVector3f(0,-getRotSpeed(), 0));
			rotate = true;
		}
		
		/*	FLYING KEYS
		 * 	SHIFT, SPACER
		 */
		
		if(flyMode){
			if(Keyboard.isKeyDown(keys.get("up"))){
				getParent().getCamera().move(getParent().getCamera().getUpVector().mul(getMoveSpeed()));
				move = true;
			}
			
			if(Keyboard.isKeyDown(keys.get("down"))){
				getParent().getCamera().move(getParent().getCamera().getDownVector().mul(getMoveSpeed()));
				move = true;
			}
		}
	}

	//OTHERS
	
	private boolean mouseMove() {
		GVector2f deltaPos = new GVector2f();
		deltaPos = new GVector2f(Mouse.getX(),Mouse.getY()).sub(centerPosition);
		
		boolean rotY = deltaPos.getX() !=0;
		boolean rotX = deltaPos.getY() !=0;

		if(rotX)
			getParent().getCamera().getRotation().setX(getParent().getCamera().getRotation().getX() - (deltaPos.getY() * getRotSpeed()/2));
		if(rotY)
			getParent().getCamera().getRotation().setY(getParent().getCamera().getRotation().getY() + (deltaPos.getX() * getRotSpeed()/2));
		
		if(rotY || rotX){
			Mouse.setCursorPosition((int)centerPosition.getX(), (int)centerPosition.getY());
			return true;
		}
		return false;
	}
	
	//SETTERS
	
	public void setFlyMode(boolean flyMode) {
		this.flyMode = flyMode;
	}
}
