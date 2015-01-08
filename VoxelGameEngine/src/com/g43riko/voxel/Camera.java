package com.g43riko.voxel;

import static org.lwjgl.opengl.GL11.GL_DEPTH;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;

public class Camera{
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
	
	public void input(){
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
	
	public void useView(){
		glRotatef(this.rx,1,0,0);
		glRotatef(this.ry,0,1,0);
		glRotatef(this.rz,0,0,1);
		glTranslatef(this.x,this.y,this.z);
	}

//	public float getX() {
//		return x;
//	}
//
//	public void setX(float x) {
//		this.x = x;
//	}
//
//	public float getY() {
//		return y;
//	}
//
//	public void setY(float y) {
//		this.y = y;
//	}
//
//	public float getZ() {
//		return z;
//	}
//
//	public void setZ(float z) {
//		this.z = z;
//	}
//
//	public float getRx() {
//		return rx;
//	}
//
//	public void setRx(float rx) {
//		this.rx = rx;
//	}
//
//	public float getRy() {
//		return ry;
//	}
//
//	public void setRy(float ry) {
//		this.ry = ry;
//	}
//
//	public float getRz() {
//		return rz;
//	}
//
//	public void setRz(float rz) {
//		this.rz = rz;
//	}
	
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


