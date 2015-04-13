package org.physics.main;

import java.util.ArrayList;

import org.engine.component.Movable;
import org.engine.component.SkyBox;
import org.engine.core.CoreEngine;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.rendeing.material.Material;
import org.engine.util.Loader;
import org.engine.util.OBJLoader;
import org.engine.world.Plane;
import org.engine.world.Terrain;
import org.strategy.component.CameraStrategy;
import org.strategy.rendering.RenderingEngine;

import glib.shapes.threeDimensional.Box;
import glib.util.vector.GVector3f;

public class PhysicsTester extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setRenderingEngine(new RenderingEngine());
		setCamera(new CameraStrategy());
		setLoader(new Loader());
		setMousePicker(getCamera());
		
	
//		addToScene(new SkyBox(getCamera()));
		
		addToScene(new Movable(getCamera()));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader(),255).getModel()));
		
		getRenderingEngine().addLight(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		
		
//		addToScene(new GameObject(getLoader().loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()), new Material("texture.png")));
		
		
		
//		addToScene(new GameObject(OBJLoader.loadObjModel("sphere", getLoader())));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader()).getModel()));
		
		addToScene(new Plane());
		
	}
	
}
