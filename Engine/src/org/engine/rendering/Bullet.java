package org.engine.rendering;

import org.engine.app.GameAble;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.rendering.material.Material;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;

public class Bullet extends GameObject{
	private static float speed = 10f;
	private static float widht = 0.05f;
	private GVector3f direction;
	
	public Bullet(GameAble parent, Material material,GVector3f position, GVector3f direction) {
		super(parent, material, CoreEngine.getLoader().loadToVAO(Plane.getVertices(getRandWidth(), getRandHeight()), Plane.getTextures(1,1), Plane.getNormals(), Plane.getIndices()));
		this.direction = direction.Normalized();
		setPosition(position.add(direction.mul(speed+1)));
	}
	
	@Override
	public GMatrix4f getTransformationMatrix() {
		GVector3f toCamera = getParent().getCamera().getPosition().sub(getPosition()).Normalized();
		toCamera = toCamera.cross(direction).cross(direction.mul(-1));
		GQuaternion res = new GQuaternion(new GMatrix4f().initRotation(direction, new GVector3f(toCamera)));
		
		return res.toRotationMatrix().mul(super.getTransformationMatrix());
	}
	
	private static float getRandWidth(){
		return (float)Math.random() * widht + widht*2;
	}
	
	private static float getRandHeight(){
		return (float)Math.random() * speed + speed*2;
	}
	
	@Override
	public void update(float delta) {
		move(direction.Normalized().mul(speed));
	}
}
