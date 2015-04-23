package org.engine.component;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MovableFPS extends BasicMovable{
	private boolean flyMode = true;
	
	//CONSTRUCTORS
	
	public MovableFPS(Camera camera){
		super(camera);
		
		keys.put("up", Keyboard.KEY_SPACE);
		keys.put("down", Keyboard.KEY_LSHIFT);
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
			camera.updateForward();
			move = rotate = false;
		}
	}

	//CHECKERS

	private void checkMoveKeys(){
		/*	MOVING KEYS
		 * 	W, A, S, D
		 */
		
		if(Keyboard.isKeyDown(keys.get("forward"))){
			camera.move(camera.getForwardVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(keys.get("back"))){
			camera.move(camera.getBackVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(keys.get("left"))){
			camera.move(camera.getLeftVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(keys.get("right"))){
			camera.move(camera.getRightVector().mul(moveSpeed));
		}
	}
	
	private void checkRotateAndFlyKeys(){
		/*	ROTATION
		 * 	Q and E
		 */
		
		if(Keyboard.isKeyDown(keys.get("turnRight"))){
			camera.rotate(new GVector3f(0, rotSpeed, 0));
			rotate = true;
		}
		
		if(Keyboard.isKeyDown(keys.get("turnLeft"))){
			camera.rotate(new GVector3f(0,-rotSpeed, 0));
			rotate = true;
		}
		
		/*	FLYING KEYS
		 * 	SHIFT, SPACER
		 */
		
		if(flyMode){
			if(Keyboard.isKeyDown(keys.get("up"))){
				camera.move(camera.getUpVector().mul(moveSpeed));
				move = true;
			}
			
			if(Keyboard.isKeyDown(keys.get("down"))){
				camera.move(camera.getDownVector().mul(moveSpeed));
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
			camera.getRotation().setX(camera.getRotation().getX() - (deltaPos.getY() * rotSpeed/2));
		if(rotY)
			camera.getRotation().setY(camera.getRotation().getY() + (deltaPos.getX() * rotSpeed/2));
		
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
