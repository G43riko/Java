package org.engine.component;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Movable extends GameComponent{
	private boolean flyMode = true;
	private boolean mouseLocked = false;
	
	private boolean move;
	private boolean rotate;
	
	private int unlockMouseKey = Keyboard.KEY_K;
	private int lockMouseKey = Keyboard.KEY_L;
	
	private int forwardKey = Keyboard.KEY_W;
	private int backKey = Keyboard.KEY_S;
	private int leftKey = Keyboard.KEY_A;
	private int rightKey = Keyboard.KEY_D;
	
	private int turnRightKey = Keyboard.KEY_E;
	private int turnLeftKey = Keyboard.KEY_Q;
	
	private int upKey = Keyboard.KEY_SPACE;
	private int downKey = Keyboard.KEY_LSHIFT;
	
	private Camera camera;
	
	private float moveSpeed = 0.3f;
	private float rotSpeed = 0.6f;
	
	
	private GVector2f centerPosition = new GVector2f(Display.getWidth()/2,Display.getHeight()/2);
	
	//CONSTRUCTORS
	
	public Movable(Camera camera){
		super(GameComponent.MOVABLE);
		this.camera = camera;
	}

	//OVERRIDES
	
	public void input(){
		
		/*	MOVING KEYS
		 * 	W, A, S, D
		 */
		
		if(Keyboard.isKeyDown(forwardKey)){
			camera.move(camera.getForwardVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(backKey)){
			camera.move(camera.getBackVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(leftKey)){
			camera.move(camera.getLeftVector().mul(moveSpeed));
		}
		
		if(Keyboard.isKeyDown(rightKey)){
			camera.move(camera.getRightVector().mul(moveSpeed));
		}
		if(flyMode){
			if(Keyboard.isKeyDown(upKey)){
//				camera.goUp();
				camera.move(camera.getUpVector().mul(moveSpeed));
				move = true;
			}
			
			if(Keyboard.isKeyDown(downKey)){
				camera.move(camera.getDownVector().mul(moveSpeed));
//				camera.goDown();
				move = true;
			}
		}
		
		/*	ROTATION
		 * 	Q and E
		 */
		
		if(Keyboard.isKeyDown(turnRightKey)){
			camera.rotate(new GVector3f(0, rotSpeed, 0));
			rotate = true;
		}
		
		if(Keyboard.isKeyDown(turnLeftKey)){
			camera.rotate(new GVector3f(0,-rotSpeed, 0));
			rotate = true;
		}
		
		
		/*	MOUSE LOCK & UNLOCK
		 * 	M, N
		 */
		
		if(Keyboard.isKeyDown(lockMouseKey)){
			lockMouse();
		}
		
		if(Keyboard.isKeyDown(unlockMouseKey)){
			unlockMouse();
		}
		
		if(mouseLocked){
			rotate = mouseMove() || rotate;
		}
		
		if(move || rotate){
			camera.updateForward();
			move = rotate = false;
		}
	}

	//OTHERS

	public boolean mouseMove() {
		GVector2f deltaPos = new GVector2f();
		deltaPos = new GVector2f(Mouse.getX(),Mouse.getY()).sub(centerPosition);
		
		boolean rotY = deltaPos.getX() !=0;
		boolean rotX = deltaPos.getY() !=0;

		if(rotX){
			camera.getRotation().setX(camera.getRotation().getX() - (deltaPos.getY() * rotSpeed/2));
		}
		if(rotY){
			camera.getRotation().setY(camera.getRotation().getY() + (deltaPos.getX() * rotSpeed/2));
		}
		
		if(rotY || rotX){
			Mouse.setCursorPosition((int)centerPosition.getX(), (int)centerPosition.getY());
			return true;
		}
		return false;
	}

	
	public void lockMouse(){
		Mouse.setCursorPosition(centerPosition.getXi(),centerPosition.getYi());
//		Mouse.setGrabbed(true);
		mouseLocked = true;
	}
	
	public void unlockMouse(){
		Mouse.setGrabbed(false);
		mouseLocked = false;
	}
	
	
	//SETTERS
	
	public void setFlyMode(boolean flyMode) {
		this.flyMode = flyMode;
	}
}
