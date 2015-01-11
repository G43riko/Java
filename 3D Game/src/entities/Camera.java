package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.Input;


public class Camera {
	private Vector3f position = new Vector3f(0,3,0);
	private float pitch;
	private float yaw;
	private float roll;
	private static final float SPEED = 0.3f;
	private boolean mouseLocked = false;
	private static final float SENSITIVITY = 0.2f;
	private Vector2f centerPosition = new Vector2f(Display.getWidth()/2,Display.getHeight()/2);
	
	
	public Camera(){
	}

	public void  move(){
		
		if(Input.getKey(Input.KEY_ESCAPE)){
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.GetMouse(0)){
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		
		if(mouseLocked){
			Vector2f deltaPos = new Vector2f(Input.GetMousePosition().getX()-centerPosition.getX(),Input.GetMousePosition().getY()-centerPosition.getY());
			
			boolean rotY =deltaPos.getX() !=0;
			boolean rotX =deltaPos.getY() !=0;
	
			if(rotY){
				yaw += (deltaPos.getX() * SENSITIVITY);
			}
			if(rotX){
				pitch += (-deltaPos.getY() * SENSITIVITY);
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.x += Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * SPEED;
			position.z += -Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.z += Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * SPEED;
			position.x += Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.z -= Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * SPEED;
			position.x -= Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.x -= Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * SPEED;
			position.z -= -Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * SPEED;
		}
		if(Input.getKey(Input.KEY_SPACE)){
			position.y += SPEED;
		}
		if(Input.getKey(Input.KEY_LSHIFT)){
			position.y -= SPEED;
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
}
