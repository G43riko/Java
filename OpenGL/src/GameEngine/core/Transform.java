package GameEngine.core;

import GameEngine.rendering.Camera;

public class Transform {
//	private static Camera camera;
	
	private Vector3f pos;
	private Vector3f rot;
	private Vector3f scale;
	
	public Transform(){
		pos = new Vector3f(0,0,0);
		rot = new Vector3f(0,0,0);
		scale = new Vector3f(1,1,1);
	}

	public Matrix4f getTransformation(){
		Matrix4f translationMatrix = new Matrix4f().InitTranslation(pos.GetX(), pos.GetY(), pos.GetZ());
		Matrix4f rotationMatrix = new Matrix4f().InitRotation(rot.GetX(), rot.GetY(), rot.GetZ());
		Matrix4f scaleMatrix = new Matrix4f().InitScale(scale.GetX(), scale.GetY(), scale.GetZ());
		
		return translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix));
	}
	
	public Matrix4f getProjectedTransformation(Camera camera){
		return camera.getViewProjection().Mul(getTransformation());
	}
	
	
	public Vector3f getPosition() {
		return pos;
	}

	public void setPosition(Vector3f pos) {
		this.pos = pos;
	}
	
	public void setPosition(float x, float y, float z) {
		this.pos = new Vector3f(x, y, z);
	}
	
	public Vector3f getRotation() {
		return rot;
	}

	public void setRotation(Vector3f rotation) {
		this.rot = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rot = new Vector3f(x, y, z);
	}
	
	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}
}
