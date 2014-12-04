package Game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private float x;
	private float y;
	private float z;
	
	private float rx;
	private float ry;
	private float rz;
	
	private float fov,aspect,near,far;
	
	public Camera(float fov, float aspect, float near, float far) {
		this.x=0;
		this.y=0;
		this.z=0;
		
		this.rx=0;
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
		gluPerspective(this.fov,this.aspect,this.near,this.far);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
	}
	
	public void reload(Player p){
		setX((float)p.x);
		setY((float)p.y);
		setZ((float)p.z);
		setRX((float)p.rx);
		setRY((float)p.ry);
		setRZ((float)p.rz);
	}
	
	public void useView(){
		glRotatef(this.rx,1,0,0);
		glRotatef(this.ry,0,1,0);
		glRotatef(this.rz,0,0,1);
		glTranslatef(this.x,this.y,this.z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getRX() {
		return rx;
	}

	public void setRX(float rx) {
		this.rx = rx;
	}

	public float getRY() {
		return ry;
	}

	public void setRY(float ry) {
		this.ry = ry;
	}

	public float getRZ() {
		return rz;
	}

	public void setRZ(float rz) {
		this.rz = rz;
	}
	
}
