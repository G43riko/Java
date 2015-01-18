package com.g43riko.voxel;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.g43riko.components.GameComponent;

public class Camera extends GameComponent{
	public static Vector3f pos = new Vector3f();
	public static Vector3f forward = new Vector3f();
	
	private float yaw;
	private float pitch;
	private static float maxAngle = (float)Math.toRadians(40);
	private float fov,aspect,near,far;
	
	private boolean moved = false;
	private boolean rotate = false;
	
	public Camera(float fov, float aspect, float near, float far) {
		pos.x=0;
		pos.y=-10;
		pos.z=-10;
		
		yaw = 0;
		pitch = 45;
		
		this.fov = fov;
		this.aspect = aspect;
		this.near = near;
		this.far = far;
		
		this.initProjection();
	}
	
	private void initProjection(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov,aspect,near,far);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void input(float delta){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			moved = true;
			move(-(float)Math.sin(Math.toRadians((yaw))), 0, (float)Math.cos(Math.toRadians((yaw))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			moved = true;
			move(+(float)Math.sin(Math.toRadians((yaw))), 0, -(float)Math.cos(Math.toRadians((yaw))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			moved = true;
			move(-(float)Math.cos(Math.toRadians((yaw))), 0, -(float)Math.sin(Math.toRadians((yaw))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			moved = true;
			move((float)Math.cos(Math.toRadians((yaw))), 0, (float)Math.sin(Math.toRadians((yaw))));
		}
		int rotationSpeed = 2;
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			rotate = true;
			yaw -=  rotationSpeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			rotate = true;
			yaw +=  rotationSpeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			move(0,1,0);
			moved = true;
		}
			
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			moved = true;
			move(0,-1,0);
		}
		
		if(moved||rotate){
			updateForward();
			moved = false;
			rotate = false;
		}
	}
	
	public void update(float delta){
		useView();
	}
	
	public void useView(){
		glRotatef(pitch,1,0,0);
		glRotatef(yaw,0,1,0);
		glTranslatef(pos.x,pos.y,pos.z);
	}
	
	public void move(float x, float y, float z){
		pos.x += x;
		pos.y += y;
		pos.z += z;
	}
	
	private void updateForward(){
		double x = Math.cos(Math.toRadians(yaw))*Math.cos(Math.toRadians(pitch));
		double y = Math.sin(Math.toRadians(yaw))*Math.cos(Math.toRadians(pitch));
		double z = Math.sin(Math.toRadians(pitch));
		forward  = new Vector3f((float)y,(float)z,(float)x);
	}

	public static float getMaxangle() {
		return maxAngle;
	}
}


