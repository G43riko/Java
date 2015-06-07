package org.engine.ai;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;

import org.engine.component.Camera;
import org.engine.component.Input;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.rendering.model.Model;
import org.engine.utils.OBJLoader;

public class PlayerHoldedObject extends GameObject{
	private Camera camera;
	private GVector3f off;
	private GVector3f offFront;
	private GVector3f offNormal;
	private boolean front = true;
	private int changeInFrames = 0;
	private int changeTime = 5;
	
	public PlayerHoldedObject(Camera camera) {
		this(new Material(new Texture2D("materials/gun_diffuse.jpg")),
			 OBJLoader.loadObjModel("gun"),
			 camera,
			 new GVector3f(0.5, -1, -2), 
			 new GVector3f(-0.01f, -0.65, -2),
			 0.001f,
			 5);
	}
	
//	public PlayerHoldedObject(Camera camera) {
//		this(new Material(new Texture2D("materials/akTxtBarva.jpg")),
//			 OBJLoader.loadObjModel("ak47"),
//			 camera,
//			 new GVector3f(1.5, -3, -1.5), 
//			 new GVector3f(0, -2.15, -1.5),
//			 0.5f,
//			 5);
//	}
	
	public PlayerHoldedObject(Material material, Model model, Camera camera, GVector3f offNormal, GVector3f offFront, float scale, int changeTime) {
		super(material, model);
		this.camera = camera;
		this.offFront = offFront;
		this.offNormal = offNormal ;
		this.changeTime = changeTime;
		
		setScale(new GVector3f(scale));
		
		off = offNormal;
	}
	
	@Override
	public void input() {
		if(Input.getMouseDown(1) && changeInFrames < 0)
			switchFront();
		
		if(changeInFrames >= 0){
			if(front)
				off = GVector3f.interpolateLinear((float)1/changeTime*changeInFrames, offNormal, offFront);
			else
				off = GVector3f.interpolateLinear((float)1/changeTime*changeInFrames, offFront, offNormal);
			changeInFrames--;
		}
	}
	
	public void switchFront(){
		front = front == false;
		changeInFrames = changeTime;
		
		off = offNormal;	
		if(front)
			off = offFront;
	}
	
	@Override
	public GMatrix4f getTransformationMatrix() {
		GVector3f offset = new GVector3f(camera.getMousePicker().getCurrentRay().cross(camera.getLeftVector()).mul(off.getY()));
		offset = offset.add(camera.getRightVector().mul(off.getX()));
		
		setPosition(camera.getPosition().add(camera.getMousePicker().getCurrentRay().mul(-off.getZ())).add(offset));
		
		GVector3f toCamera = camera.getPosition().add(offset).sub(getPosition()).mul(off.getY()).Normalized();
		GQuaternion res = new GQuaternion(new GMatrix4f().initRotation(toCamera, camera.getMousePicker().getCurrentRay().cross(camera.getLeftVector())));
		return res.toRotationMatrix().mul(super.getTransformationMatrix());
	}

}
