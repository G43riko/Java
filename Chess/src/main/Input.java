package main;

import maps.Block;

import org.lwjgl.input.Keyboard;

import entities.Camera;
import entities.Selector;

public class Input {
	private static int rotationSpeed = 2;
	
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
			camera.setRy(camera.getRy()-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			camera.setRy(camera.getRy()+rotationSpeed);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ADD))
			camera.setY(camera.getY()-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT))
			camera.setY(camera.getY()+rotationSpeed);
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			int speed = 4;
			float angle = camera.getRy()%360;
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
				selector.goForward(angle);
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				selector.goBack(angle);
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				selector.goRight(angle);
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				selector.goLeft(angle);
			
		}
		else{
			int speed = 2;
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
				selector.goUp();
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN ))
				selector.goDown();
			
		}
	}
}
