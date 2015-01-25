package com.voxel.core;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;

public class Transform {
	private Transform  parent;
	private GMatrix4f   parentMatrix;
	
	private GVector3f position;
	private GQuaternion rotation;
	private GVector3f scale;
	
	private GVector3f   oldPosition;
	private GQuaternion oldRotation;
	private GVector3f   oldScale;
	
	public Transform(){
		position = new GVector3f(0,0,0);
		rotation = new GQuaternion(0,0,0,1);
		scale = new GVector3f(1,1,1);

		parentMatrix = new GMatrix4f().InitIdentity();
	}
	
	public void update(){
		if(oldPosition != null)
		{
			oldPosition.set(position);
			oldRotation.set(rotation);
			oldScale.set(scale);
		}
		else
		{
			oldPosition = new GVector3f(0,0,0).set(position).add(1.0f);
			oldRotation = new GQuaternion(0,0,0,0).set(rotation).mul(0.5f);
			oldScale = new GVector3f(0,0,0).set(scale).add(1.0f);
		}
	}
	
	public GVector3f getPosition() {
		return position;
	}

	public GQuaternion getRotation() {
		return rotation;
	}
	
	public GVector3f getScale() {
		return scale;
	}
	
	public void rotate(GVector3f axis, float angle){
		rotation = new GQuaternion(axis, angle).mul(rotation).normalize();
	}
	
	public GVector3f getRotationInEulerAngles(){
		GMatrix4f rot = rotation.ToRotationMatrix();
		return new GVector3f((float)Math.atan2(rot.Get(3, 1),rot.Get(3, 2)), 
							 (float)Math.acos(rot.Get(3, 3)),
							 (float)-Math.atan2(rot.Get(1, 3),rot.Get(2, 3)));
	}

	public GMatrix4f getTransformation(){
		GMatrix4f translationMatrix = new GMatrix4f().InitTranslation(position.getX(), position.getY(), position.getZ());
		GMatrix4f rotationMatrix = rotation.ToRotationMatrix();
		GMatrix4f scaleMatrix = new GMatrix4f().InitScale(scale.getX(), scale.getY(), scale.getZ());

		return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
	}

	private GMatrix4f getParentMatrix(){
		if(parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();

		return parentMatrix;
	}
	
	public boolean hasChanged(){
		if(parent != null && parent.hasChanged())
			return true;

		if(!position.equals(oldPosition))
			return true;

		if(!rotation.equals(oldRotation))
			return true;

		if(!scale.equals(oldScale))
			return true;

		return false;
	}

	public void setRotation(GQuaternion rotation){
		this.rotation = rotation;
	}
	
	public void setParent(Transform parent){
		this.parent = parent;
	}

	public void setPosition(GVector3f position) {
		this.position = position;
	}
}
