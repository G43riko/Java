package main;

import maps.Block;

import org.lwjgl.input.Keyboard;

import entities.Camera;
import entities.Selector;

public class Input {
	private static int rotationSpeed = 2;
	
	public static void update(Camera camera,Selector selector){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			camera.setZ(camera.getZ()+(float)Math.cos(Math.toRadians((camera.getRy()))));
			camera.setX(camera.getX()-(float)Math.sin(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			camera.setZ(camera.getZ()-(float)Math.cos(Math.toRadians((camera.getRy()))));
			camera.setX(camera.getX()+(float)Math.sin(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			camera.setZ(camera.getZ()-(float)Math.sin(Math.toRadians((camera.getRy()))));
			camera.setX(camera.getX()-(float)Math.cos(Math.toRadians((camera.getRy()))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			camera.setZ(camera.getZ()+(float)Math.sin(Math.toRadians((camera.getRy()))));
			camera.setX(camera.getX()+(float)Math.cos(Math.toRadians((camera.getRy()))));
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			camera.setRy(camera.getRy()-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			camera.setRy(camera.getRy()+rotationSpeed);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_HOME))
			camera.setY(camera.getY()-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_END))
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
				selector.setY(selector.getY()-speed);
			
		}
	}
}
