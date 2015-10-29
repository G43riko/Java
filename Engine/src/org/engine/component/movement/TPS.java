package org.engine.component.movement;

import glib.math.GMath;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.engine.app.GameAble;
import org.engine.component.BasicPlayer;
import org.engine.component.Camera;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class TPS extends BasicMovement{
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

	public TPS(GameAble parent, Camera camera, BasicPlayer player) {
		super(parent, new GVector3f(0.3, 0.6, 0.1));
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
		if(Keyboard.isKeyDown(keys.get(FORWARD))){
			player.move(player.getForward().mul(-getMoveSpeed()));
		}
		if(Keyboard.isKeyDown(keys.get(BACK))){
			player.move(player.getForward().mul(getMoveSpeed()));
		}
		if(Keyboard.isKeyDown(keys.get(LEFT))){
			player.move(player.getRight().mul(-getMoveSpeed()));
		}
		if(Keyboard.isKeyDown(keys.get(RIGHT))){
			player.move(player.getRight().mul(getMoveSpeed()));
		}
	}
	
	private void checkRotateKeys(){
		if(Keyboard.isKeyDown(keys.get(TURN_RIGHT))){
			rotate = true;
			player.rotate(new GVector3f(0, -getRotSpeed(), 0));
		}
		
		if(Keyboard.isKeyDown(keys.get(TURN_LEFT))){
			rotate = true;
			player.rotate(new GVector3f(0, getRotSpeed(), 0));
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
		getParent().getCamera().setPosition(new GVector3f(x, y, z));
		getParent().getCamera().getRotation().setY(-player.getRotation().getY() - angleAroundPlayer-180);
	}
	
	private GVector2f calculateDistances(){
		double x = distanceFromPlayer * Math.cos(Math.toRadians(getParent().getCamera().getPitch()));
		double y =  distanceFromPlayer * Math.sin(Math.toRadians(getParent().getCamera().getPitch()));
		return new GVector2f(x, y);
	}
	
	private void calculateVariables(){
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		if(distanceFromPlayer > maximalZoomDistance ||distanceFromPlayer < minimalZoomDistance)
			distanceFromPlayer = GMath.between(distanceFromPlayer, minimalZoomDistance, maximalZoomDistance);
		
		if(Mouse.isButtonDown(1) || mouseLocked){
			//VERTICAL
			float  pitchChange = Mouse.getDY() * 0.1f;
			getParent().getCamera().getRotation().addToX(-pitchChange);

			
			//HORIZONTAL
			float  angleChange = Mouse.getDX() * 0.1f;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				player.getRotation().addToY(-angleChange);
				angleAroundPlayer = 0;
				rotate = true;
			}
			else
				angleAroundPlayer -= angleChange;
			
			
			if(getParent().getCamera().getPitch() > maximalPitch || getParent().getCamera().getPitch() < minimalPitch)
				getParent().getCamera().getRotation().setX(GMath.between(getParent().getCamera().getPitch(), minimalPitch, maximalPitch));
		}
		
	}

	
	//GETTERS
	
	public BasicPlayer getPlayer() {
		return player;
	}
}
