package org.engine.component;

import java.util.ArrayList;
import java.util.List;

import org.engine.core.MousePicker;
import org.engine.utils.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public class Camera extends GameComponent{
	public final static GVector3f UP = new GVector3f(0,1,0);
	protected final static boolean VERTICAL = false; 
	
	protected float FOV = 70;
	protected float NEAR_PLANE = 0.1f;
	protected float FAR_PLANE = 1000;
	protected float ASPECT_RATIO;
	
	private MousePicker mousePicker;
	
	private GMatrix4f projectionMatrix;
	private GVector3f forward;
	
	//CONSTRUCTORS
	
	public Camera(GVector3f position) {
		super(null, position);
		createProjectionMatrix();
		updateForward();
		mousePicker = new MousePicker(this);
	}
		
	//OTHERS
	
	public void createProjectionMatrix() {
		ASPECT_RATIO  = (float)Display.getWidth()/(float)Display.getHeight();
		float y_scale = (1f / (float)Math.tan(Math.toRadians(FOV / 2f))) * ASPECT_RATIO;
		float x_scale = y_scale/ASPECT_RATIO;
		
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		Matrix4f mat = new Matrix4f();
		mat.m00 = x_scale;
		mat.m11 = y_scale;
		mat.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		mat.m23 = -1;
		mat.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		mat.m33 = 0;
		projectionMatrix = Maths.MatrixToGMatrix(mat);
	}
	
	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double y = Math.sin(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double z = Math.sin(Math.toRadians(getPitch()));
		forward = new GVector3f((float)y,(float)z,(float)x);
	}
	
	public boolean isVisible(GVector3f point){
		float tang = (float)Math.tan(Math.toRadians(FOV) * 0.5f);
		float Hnear = 2 * tang * NEAR_PLANE;
		float Wnear = Hnear * ASPECT_RATIO;

		GVector3f right = getForward().cross(UP).Normalized();
		GVector3f up = getForward().cross(right).Normalized();
		
		GVector3f Cnear = getPosition().add(getForward().mul(NEAR_PLANE));
		
		GVector3f nearTopLeft = Cnear.add(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
		GVector3f nearTopRight = Cnear.add(up.mul(Hnear / 2)).add(right.mul(Wnear / 2));
		GVector3f nearBottomLeft = Cnear.sub(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
		
		return GVector3f.intersectRayWithSquare(getPosition(), point, nearTopLeft, nearTopRight, nearBottomLeft) &&
			   point.sub(getPosition()).dot(getForward()) < 1;
	}
	
	public boolean isVisible(GVector3f point ,GVector3f size){
		
		return	isVisible(point) || isVisible(point.add(size)) ||
				isVisible(point.add(size.mul(new GVector3f(1,0,0)))) ||
				isVisible(point.add(size.mul(new GVector3f(0,1,0)))) ||
				isVisible(point.add(size.mul(new GVector3f(0,0,1)))) ||
				isVisible(point.add(size.mul(new GVector3f(0,1,1)))) ||
				isVisible(point.add(size.mul(new GVector3f(1,0,1)))) ||
				isVisible(point.add(size.mul(new GVector3f(1,1,0))));
		
	}
	
	public List<GVector3f> calcFrustum(){
		float tang = (float)Math.tan(Math.toRadians(FOV) * 0.5f);
		float Hnear = 2 * tang * NEAR_PLANE;
		float Wnear = Hnear * ASPECT_RATIO;
		float Hfar = 2 * tang * FAR_PLANE;
		float Wfar = Hfar * ASPECT_RATIO;

		GVector3f right = getForward().cross(UP).Normalized();
		GVector3f up = getForward().cross(right).Normalized();
		
		GVector3f Cnear = getPosition().add(getForward().mul(NEAR_PLANE));
		GVector3f Cfar = getPosition().add(getForward().mul(FAR_PLANE));
		
		GVector3f nearTopLeft = Cnear.add(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
		GVector3f nearTopRight = Cnear.add(up.mul(Hnear / 2)).add(right.mul(Wnear / 2));
		GVector3f nearBottomLeft = Cnear.sub(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
		GVector3f nearBottomRight = Cnear.sub(up.mul(Hnear / 2)).add(right.mul(Wnear / 2));
		
		GVector3f farTopLeft = Cfar.add(up.mul(Hfar / 2)).sub(right.mul(Wfar / 2));
		GVector3f farTopRight = Cfar.add(up.mul(Hfar / 2)).add(right.mul(Wfar / 2));
		GVector3f farBottomLeft = Cfar.sub(up.mul(Hfar / 2)).sub(right.mul(Wfar / 2));
		GVector3f farBottomRight = Cfar.sub(up.mul(Hfar / 2)).add(right.mul(Wfar / 2));


		List<GVector3f> result = new ArrayList<GVector3f>();
		
		result.add(farTopLeft.sub(nearTopLeft));
		result.add(farTopRight.sub(nearTopRight));
		result.add(farBottomLeft.sub(nearBottomLeft));
		result.add(farBottomRight.sub(nearBottomRight));
		
		return result;
	}
	
	//GETTERS
	
	public GMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public GVector3f getForward() {
		return forward;
	}

	public MousePicker getMousePicker() {
		return mousePicker;
	}
	
	//GETTERS-ANGLES

	public float getPitch(){
		return getRotation().getX();
	}
	
	public float getYaw(){
		return getRotation().getY();
	}
	
	//GETTERS-VECTORS
	
	public GVector3f getForwardVector(){
		if(VERTICAL)
			return forward.mul(-1).Normalized();
		
		return UP.cross(forward).cross(UP).mul(-1).Normalized();
	}
	
	public GVector3f getBackVector(){
		if(VERTICAL)
			return forward.Normalized();
		
		return UP.cross(forward).cross(UP).Normalized();
	}
	
	public GVector3f getRightVector(){
		return UP.cross(forward).Normalized();
	}
	
	public GVector3f getLeftVector(){
		return UP.cross(forward).mul(-1).Normalized();
	}
	
	public GVector3f getUpVector(){
		return UP.Normalized();
	}
	
	public GVector3f getDownVector(){
		return UP.mul(-1).Normalized();
	}
}
