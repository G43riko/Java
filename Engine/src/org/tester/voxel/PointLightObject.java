package org.tester.voxel;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.engine.component.light.PointLight;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.utils.OBJLoader;

public class PointLightObject extends GameObject{
	private PointLight pointLight;
	
	public PointLightObject(PointLight pointLight) {
		super(new Material(new Texture2D(pointLight.getColor(),new GVector2f(64,64))), OBJLoader.loadObjModel("sphere"));
		this.pointLight = pointLight;
		
		setScale(new GVector3f(0.1f));
		setPosition(pointLight.getPosition());
		setReceiveLight(false);
	}

	public PointLight getPointLight() {
		return pointLight;
	}
	
	@Override
	public void setPosition(GVector3f position) {
		super.setPosition(position);
		pointLight.setPosition(position);
	}
	
	@Override
	public void move(GVector3f vec) {
		super.move(vec);
		pointLight.move(vec);
	}
}
