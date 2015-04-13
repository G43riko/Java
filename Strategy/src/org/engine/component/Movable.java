package org.engine.component;

import game.component.CameraStrategy;
import game.entity.player.Player;
import game.main.StrategyGame;
import glib.util.vector.GVector3f;

import org.lwjgl.input.Keyboard;

public class Movable extends GameComponent{
	private boolean flyMode = true;
	
	private int unlockMouseKey = Keyboard.KEY_K;
	private int lockMouseKey = Keyboard.KEY_L;
	
	private int forwardKey = Keyboard.KEY_W;
	private int backKey = Keyboard.KEY_S;
	private int leftKey = Keyboard.KEY_A;
	private int rightKey = Keyboard.KEY_D;
	
	private int turnRightKey = Keyboard.KEY_E;
	private int turnLeftKey = Keyboard.KEY_Q;
	
	private int jumpKey = Keyboard.KEY_SPACE;
	
	private int upKey = Keyboard.KEY_SPACE;
	private int downKey = Keyboard.KEY_LSHIFT;
	
	private CameraStrategy camera;
	
	private boolean move;
	private boolean rotate;
	
	private float moveSpeed = 1;
	private float rotSpeed = 1;
	
	//CONSTRUCTORS
	
	public Movable(CameraStrategy camera){
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
				camera.goUp();
				move = true;
			}
			
			if(Keyboard.isKeyDown(downKey)){
				camera.goDown();
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
			camera.lockMouse();
		}
		
		if(Keyboard.isKeyDown(unlockMouseKey)){
			camera.unlockMouse();
		}
		
		if(camera.isMouseLocked()){
			rotate = camera.mouseMove() || rotate;
		}
		
		if(move || rotate){
			camera.updateForward();
			move = rotate = false;
		}
	}
}
