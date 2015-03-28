package game.object;

import game.light.PointLight;
import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.util.Loader;
import game.util.OBJLoader;
import glib.util.vector.GVector3f;

public class Lamp extends GameObject{
	public Lamp(GVector3f position, RenderingEngine renderingEngine) {
		super(OBJLoader.loadObjModel("lamp",new Loader()),new Material("lamp.png"),new PointLight(position.add(new GVector3f(0,12.5f,0)),new GVector3f(1f, 0, 1f),new GVector3f(0.25f, 0.01f, 0.002f)));
		setPosition(position);
		renderingEngine.addLight(getLights());
//		fakeLight = true;
	}
	
}
