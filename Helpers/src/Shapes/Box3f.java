package Shapes;

import java.awt.Color;

import Goniometry.Vector3f;

public class Box3f {
	private Vector3f pos;
	
	private float w;
	private float h;
	private float d;
	
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	
	private float textureWidth = 1;
	private float textureHeight = 1;
	private float textureDepth = 1;
	
	private boolean textureRepeat = true;

	public Box3f(float x, float y,float z){
		this(new Vector3f(x,y,z));
	}
	
	public Box3f(Vector3f pos){
		this(pos,1,1,1);
	}
	
	public Box3f(Vector3f pos,float w,float h,float d){
		this(pos,w,h,d,0,0,0);
	}
	
	public Box3f(Vector3f pos,float w,float h,float d, float rx, float ry, float rz){
		this.pos = pos;
		
		this.w = w;
		this.h = h;
		this.d = d;
		
		this.rotateX = rx;
		this.rotateY = ry;
		this.rotateZ = rz;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public float getWidth() {
		return w;
	}

	public void setWidth(float w) {
		this.w = w;
	}

	public float getHeight() {
		return h;
	}

	public void setHeight(float h) {
		this.h = h;
	}

	public float getDepth() {
		return d;
	}

	public void setDepth(float d) {
		this.d = d;
	}
}
