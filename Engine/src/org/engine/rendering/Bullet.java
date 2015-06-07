package org.engine.rendering;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;

import org.engine.component.Camera;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.utils.Loader;

public class Bullet extends GameObject{
	private GVector3f direction;
	private static float speed = 10f;
	private static float widht = 0.05f;
	private Camera camera;
	
	public Bullet(Material material,GVector3f position, GVector3f direction, Camera camera) {
		super(material, Loader.loadToVAO(Plane.getVertices(getRandWidth(), getRandHeight()), Plane.getTextures(1,1), Plane.getNormals(), Plane.getIndices()));
		this.direction = direction.Normalized();
		this.camera = camera;
		setPosition(position.add(direction.mul(speed+1)));
	}
	
	@Override
	public GMatrix4f getTransformationMatrix() {
		GVector3f toCamera = camera.getPosition().sub(getPosition()).Normalized();
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
	public void update() {
		move(direction.Normalized().mul(speed));
	}
}
