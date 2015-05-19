package org.engine.ai;

import glib.shapes.threeDimensional.Box;
import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.utils.Loader;

public class BasicEnemy extends GameObject{
	
	private Follower follower = new Follower(this, null);
	
	
	public BasicEnemy() {
		this(new GVector3f());
	}
	
	public BasicEnemy(GVector3f position) {
		super(new Material("materials/texture.png"), Loader.loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()));
		setPosition(position);
	}
	
	@Override
	public void update() {
		follower.update();
	}
	
	public void setTarget(GameComponent target){
		follower.setTarget(target);
	}
	
	public void setMoveOnFloor(boolean value){
		follower.setOnGroung(value);
	}
//	@Override
//	public GMatrix4f getTransformationMatrix() {
//			if(follower.getTarget() == null)
//				return super.getTransformationMatrix();
//		
//			GVector3f toCamera = follower.getTarget().getPosition().sub(getPosition()).Normalized();
//			
//			System.out.println(toCamera);
//			toCamera = GVector3f.interpolateLinear(0.01f, getRotation(), toCamera);
//			System.out.println(toCamera);
//			
//			GQuaternion res = new GQuaternion(new GMatrix4f().initRotation(toCamera, new GVector3f(0,1,0)));
//			return res.toRotationMatrix().mul(super.getTransformationMatrix());
//	}

	public float getHeight() {
		return getScale().getY() * 2;
	}

}
