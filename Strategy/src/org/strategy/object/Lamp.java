package org.strategy.object;

import org.engine.light.PointLight;
import org.engine.rendeing.RenderingEngine;
import org.engine.rendeing.material.Material;
import org.engine.util.Loader;
import org.engine.util.OBJLoader;

import glib.util.vector.GVector3f;

public class Lamp extends GameObjectWithLight{
	public Lamp(GVector3f position, RenderingEngine renderingEngine) {
		super(OBJLoader.loadObjModel("lamp",new Loader()),new Material("lamp.png"),new PointLight(position.add(new GVector3f(0,12.5f,0)),new GVector3f(1f, 0, 1f),new GVector3f(0.25f, 0.01f, 0.002f)));
		setPosition(position);
		renderingEngine.addLight(getLights());
//		fakeLight = true;
	}
	
}
