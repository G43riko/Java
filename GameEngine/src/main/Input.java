package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import entities.Camera;
import entities.Selector;

public class Input {
	
	public static void update(Camera camera,Selector selector){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			camera.move(-(float)Math.sin(Math.toRadians((camera.getRy()))), 0, (float)Math.cos(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			camera.move(+(float)Math.sin(Math.toRadians((camera.getRy()))), 0, -(float)Math.cos(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			camera.move(-(float)Math.cos(Math.toRadians((camera.getRy()))), 0, -(float)Math.sin(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			camera.move((float)Math.cos(Math.toRadians((camera.getRy()))), 0, (float)Math.sin(Math.toRadians((camera.getRy()))));
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			camera.rotate(0, -Camera.ROTATION_SPEED, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			camera.rotate(0, Camera.ROTATION_SPEED, 0);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ADD))
			camera.move(0, -Camera.MOVE_SPEED, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT))
			camera.move(0, Camera.MOVE_SPEED, 0);
		
		//System.out.println(Mouse.isButtonDown(1));
		
//		if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
//			int speed = 4;
//			float angle = camera.getRy()%360;
//			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
//				selector.goForward(angle);
//			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
//				selector.goBack(angle);
//			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
//				selector.goRight(angle);
//			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
//				selector.goLeft(angle);
//		}
//		else{
//			int speed = 2;
//			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
//				selector.goUp();
//			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN ))
//				selector.goDown();
//			
//		}
	}
}
