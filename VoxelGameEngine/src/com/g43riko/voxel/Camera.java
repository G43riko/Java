package com.g43riko.voxel;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;

import com.g43riko.components.GameComponent;

public class Camera extends GameComponent{
	private float x;
	private float y;
	private float z;
	
	private float rx;
	private float ry;
	private float rz;
	
	private float fov,aspect,near,far;
	
	public Camera(float fov, float aspect, float near, float far) {
		this.x=0;
		this.y=-10;
		this.z=-10;
		
		this.rx=45;
		this.ry=0;
		this.rz=0;
		
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
			move(-(float)Math.sin(Math.toRadians((ry))), 0, (float)Math.cos(Math.toRadians((ry))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			move(+(float)Math.sin(Math.toRadians((ry))), 0, -(float)Math.cos(Math.toRadians((ry))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			move(-(float)Math.cos(Math.toRadians((ry))), 0, -(float)Math.sin(Math.toRadians((ry))));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			move((float)Math.cos(Math.toRadians((ry))), 0, (float)Math.sin(Math.toRadians((ry))));
		}
		int rotationSpeed = 2;
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			ry=(ry-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			ry=(ry+rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			move(0,1,0);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			move(0,-1,0);
	}
	
	public void update(float delta){
		useView();
	}
	
	public void useView(){
		glRotatef(this.rx,1,0,0);
		glRotatef(this.ry,0,1,0);
		glRotatef(this.rz,0,0,1);
		glTranslatef(this.x,this.y,this.z);
	}
	
	public void move(float x, float y, float z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void rotate(float x, float y, float z){
		this.rx += x;
		this.ry += y;
		this.rz += z;
	}
}


