package org.engine.component;

import glib.math.GMath;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.engine.entity.BasicPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MovableTPS extends BasicMovable{
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	private float offsetY = 10;
	private final static float maximalPitch = 70;
	private final static float minimalPitch = 0;
	private final static float minimalZoomDistance = 10;
	private final static float maximalZoomDistance = 100;
	
//	private float actMoveX = 0;
//	private float actMoveY = 0;
//	private float actMoveZ = 0;
//	private float maxMove = 1;
	
	private BasicPlayer player;

	public MovableTPS(Camera camera, BasicPlayer player) {
		super(camera);
		this.player = player;
		player.updateForward();
	}
	
	public void input(){
		checkMoveKeys();
		
		checkRotateKeys();
		
		calculateVariables();
		calculateCameraPosition(calculateDistances());
	
		checkMouseLock();
		
		if(move || rotate){
			player.updateForward();
			move = rotate = false;
		}
	}
	
	//CHECKERS
	
	private void checkMoveKeys(){
		if(Keyboard.isKeyDown(keys.get("forward"))){
			player.move(player.getForward().mul(-moveSpeed));
		}
		if(Keyboard.isKeyDown(keys.get("back"))){
			player.move(player.getForward().mul(moveSpeed));
		}
		if(Keyboard.isKeyDown(keys.get("left"))){
			player.move(player.getRight().mul(-moveSpeed));
		}
		if(Keyboard.isKeyDown(keys.get("right"))){
			player.move(player.getRight().mul(moveSpeed));
		}
	}
	
	private void checkRotateKeys(){
		if(Keyboard.isKeyDown(keys.get("turnRight"))){
			rotate = true;
			player.rotate(new GVector3f(0, -rotSpeed, 0));
		}
		
		if(Keyboard.isKeyDown(keys.get("turnLeft"))){
			rotate = true;
			player.rotate(new GVector3f(0,rotSpeed, 0));
		}
	}
	
	//CALCULATORS
	
	private void calculateCameraPosition(GVector2f distance){
		float theta = player.getRotation().getY() + angleAroundPlayer;
		float offsetX = (float)(distance.getX() * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float)(distance.getX() * Math.cos(Math.toRadians(theta)));
		float x = player.getPosition().getX() - offsetX;
		float y = player.getPosition().getY() + distance.getY() + offsetY;
		float z = player.getPosition().getZ() - offsetZ;
		camera.setPosition(new GVector3f(x, y, z));
		camera.getRotation().setY(-player.getRotation().getY() - angleAroundPlayer-180);
	}
	
	private GVector2f calculateDistances(){
		double x = distanceFromPlayer * Math.cos(Math.toRadians(camera.getPitch()));
		double y =  distanceFromPlayer * Math.sin(Math.toRadians(camera.getPitch()));
		return new GVector2f(x, y);
	}
	
	private void calculateVariables(){
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		if(distanceFromPlayer > maximalZoomDistance ||distanceFromPlayer < minimalZoomDistance)
			distanceFromPlayer = GMath.between(distanceFromPlayer, minimalZoomDistance, maximalZoomDistance);
		
		if(Mouse.isButtonDown(1) || mouseLocked){
			float  pitchChange = Mouse.getDY() * 0.1f;
			camera.getRotation().addToX(-pitchChange);


			float  angleChange = Mouse.getDX() * 0.1f;
			angleAroundPlayer -= angleChange;
			
			if(camera.getPitch() > maximalPitch || camera.getPitch() < minimalPitch)
				camera.getRotation().setX(GMath.between(camera.getPitch(), minimalPitch, maximalPitch));
		}
		
	}
}
