package com.voxel.component.viewAndMovement;

import glib.util.vector.GMatrix4f;

import com.voxel.component.GameComponent;
import com.voxel.core.CoreEngine;

public class Camera extends GameComponent{
	private GMatrix4f projection;
	public final static float MAX_ANGLE = (float)Math.toRadians(100);
	
	public Camera(float fov, float aspect, float zNear, float zFar){
		this.projection = new GMatrix4f().initPerspective(fov,aspect,zNear,zFar);
	}
	
	public GMatrix4f getViewProjection(){
		GMatrix4f cameraRotation = getTransform().getRotation().toRotationMatrix();
		GMatrix4f cameraTranslation =  new GMatrix4f().initTranslation(-getTransform().getPosition().getX(), 
																	   -getTransform().getPosition().getY(), 
																	   -getTransform().getPosition().getZ());
		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	public void addToEngine(CoreEngine engine){
		engine.getRenderingEngine().addCamera(this);
	}
}
