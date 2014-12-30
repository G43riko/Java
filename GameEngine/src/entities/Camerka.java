package entities;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import terrains.Block;

public class Camerka {
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	public static final float ROTATION_SPEED = 0.6f;
	public static final float MOVE_SPEED = 0.3f;
	
	private Matrix4f projectionMatrix;
	
	//private Vector3f position = new Vector3f(Block.WIDTH*10,15,Block.DEPTH*20);
	private Vector3f position = new Vector3f();
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camerka(StaticShader shader){
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		reset();
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
		float y_scale = ((float)(1f /Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}

	public void update(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			//position.z-=MOVE_SPEED;
			goForward();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			//position.z+=MOVE_SPEED;
			goBack();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			//position.x+=MOVE_SPEED;
			goRight();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			//position.x-=MOVE_SPEED;
			goLeft();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			yaw-=ROTATION_SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			yaw+=ROTATION_SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			goUp();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			goDown();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ADD)){
			pitch-=ROTATION_SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
			pitch+=ROTATION_SPEED;
		}
	}
	
	public void reset(){
		position = new Vector3f(-15,40,-15);
		pitch = 20;
		yaw = 135;
	}
	
	public void goForward(){
		//move(-(float)Math.sin(Math.toRadians((yaw))), 0, (float)Math.cos(Math.toRadians((yaw))));
		position.x += Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		position.z += -Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
	}
	
	public void goBack(){
		position.x -= Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		position.z -= -Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
	}
	
	public void goLeft(){
		position.z -= Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		position.x -= Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
	}
	
	public void goRight(){
		position.z += Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		position.x += Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
	}
	
	public void goUp(){
		move(0,MOVE_SPEED,0);
	}
	
	public void goDown(){
		move(0,-MOVE_SPEED,0);
	}
	
	private void move(float x, float y, float z){
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
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
