package prototypeGameEngine.core;

import Goniometry.Matrix4f;
import Goniometry.Quaternion;
import Goniometry.Vector3f;

public class Transform {
	private Transform parent;
	private Matrix4f parentMatrix;
	
	private Vector3f pos;
	private Quaternion rot;
	private Vector3f scale;
	
	private Vector3f oldPos;
	private Quaternion oldRot;
	private Vector3f oldScale;
	
	public Transform(){
		pos = new Vector3f(0,0,0);
		rot = new Quaternion(0,0,0,1);
		scale = new Vector3f(1,1,1);
		parentMatrix = new Matrix4f().InitIdentity();
	}

	public Matrix4f getTransformation(){
		Matrix4f translationMatrix = new Matrix4f().InitTranslation(pos.getX(), pos.getY(), pos.getZ());
		Matrix4f rotationMatrix = rot.ToRotationMatrix();
		Matrix4f scaleMatrix = new Matrix4f().InitScale(scale.getX(), scale.getY(), scale.getZ());
		
		if(parent != null&&parent.hasChange()){
			parentMatrix = parent.getTransformation();
		}
		if(oldPos!=null){
			oldPos.set(pos);
			oldRot.set(rot);
			oldScale.set(scale);
		}
		
		return parentMatrix.Mul(translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix)));
	}
	
	public void setParent(Transform parent){
		this.parent = parent;
	}
	
	public boolean hasChange(){
		if(parent != null && parent.hasChange()){
			return true;
		}
		if(oldPos == null){
			oldPos = new Vector3f(0,0,0).set(pos);
			oldRot = new Quaternion(0,0,0,0).set(rot);
			oldScale = new Vector3f(0,0,0).set(scale);
			return true;
		}
		if(!pos.equals(oldPos)||!rot.equals(oldRot)||!scale.equals(oldScale))
			return true;
		return false;
	}
	
	public void Rotate(Vector3f axis, float angle){
		rot = new Quaternion(axis, angle).mul(rot).normalize();
	}
	
//	public Matrix4f getProjectedTransformation(Camera camera){
//		return camera.getViewProjection().Mul(getTransformation());
//	}
		
	public Vector3f getPosition() {
		return pos;
	}

	public void setPosition(Vector3f pos) {
		this.pos = pos;
	}
	
	public Quaternion getRotation() {
		return rot;
	}

	public void setRotation(Quaternion rotation) {
		this.rot = rotation;
	}
	
	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
}
