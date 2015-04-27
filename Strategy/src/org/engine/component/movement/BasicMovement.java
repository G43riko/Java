package org.engine.component.movement;

import java.util.HashMap;

import glib.util.vector.GVector2f;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class BasicMovement extends GameComponent{
	
	protected HashMap<String, Integer> keys = new HashMap<String, Integer>(); 

	protected int unlockMouseKey = Keyboard.KEY_K;
	protected int lockMouseKey = Keyboard.KEY_L;
	
	protected boolean move;
	protected boolean rotate;

	protected GVector2f centerPosition = new GVector2f(Display.getWidth()/2,Display.getHeight()/2);

	protected boolean mouseLocked = false;
	
	protected Camera camera;

	protected float moveSpeed = 0.3f;
	protected float rotSpeed = 0.6f;
	
	public BasicMovement(Camera camera) {
		super(GameComponent.MOVABLE);
		this.camera = camera;
		
		setDefaultKeys();
	}
	
	private void setDefaultKeys(){
		keys.put("forward", Keyboard.KEY_W);
		keys.put("back", Keyboard.KEY_S);
		keys.put("left", Keyboard.KEY_A);
		keys.put("right", Keyboard.KEY_D);
		
		keys.put("unlock", Keyboard.KEY_K);
		keys.put("lock", Keyboard.KEY_L);
		
		keys.put("turnRight", Keyboard.KEY_E);
		keys.put("turnLeft", Keyboard.KEY_Q);
	}
	
	protected void checkMouseLock(){
		if(Keyboard.isKeyDown(lockMouseKey)){
			lockMouse();
		}
		
		if(Keyboard.isKeyDown(unlockMouseKey)){
			unlockMouse();
		}
	}
	
	private void lockMouse(){
		Mouse.setCursorPosition(centerPosition.getXi(),centerPosition.getYi());
		Mouse.setGrabbed(true);
		mouseLocked = true;
	}
	
	private void unlockMouse(){
		Mouse.setGrabbed(false);
		mouseLocked = false;
	}
}
