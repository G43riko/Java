package com.voxel.component.viewAndMovement;

import com.voxel.component.GameComponent;
import com.voxel.core.CoreEngine;

import glib.util.vector.GMatrix4f;

public class Camera extends GameComponent{
	private GMatrix4f projection;
	
	public Camera(float fov, float aspect, float zNear, float zFar){
		this.projection = new GMatrix4f().initPerspective(fov,aspect,zNear,zFar);
	}
	
	public GMatrix4f getViewProjection(){
		GMatrix4f cameraRotation = getTransform().getRotation().ToRotationMatrix();
		GMatrix4f cameraTranslation =  new GMatrix4f().InitTranslation(-getTransform().getPosition().getX(), 
																	   -getTransform().getPosition().getY(), 
																	   -getTransform().getPosition().getZ());
		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	public void addToEngine(CoreEngine engine){
		engine.getRenderingEngine().addCamera(this);
	};
}
