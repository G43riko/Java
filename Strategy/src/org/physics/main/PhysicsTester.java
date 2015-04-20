package org.physics.main;

import org.engine.component.Movable;
import org.engine.core.CoreEngine;
import org.engine.gui.Hud;
import org.engine.light.PointLight;
import org.engine.rendeing.material.Texture2D;
import org.engine.util.Loader;
import org.engine.world.Plane;
import org.strategy.component.CameraStrategy;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class PhysicsTester extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setRenderingEngine(new RenderingEngineStrategy());
		setCamera(new CameraStrategy());
		setLoader(new Loader());
		setMousePicker(getCamera());
		
//		addToScene(new SkyBox(getCamera()));
		
		addToScene(new Movable(getCamera()));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader(),255).getModel()));
		
		getRenderingEngine().addLight(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		
//		addToScene(new ParticleEmmiter(new GVector3f(0,1,5)));
		
//		addToScene(new GameObject(getLoader().loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()), new Material("texture.png")));
		
//		addToScene(new GameObject(OBJLoader.loadObjModel("sphere", getLoader())));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader()).getModel()));
		
		addToScene(new Plane());
		
		addToScene(new Hud(new Texture2D("normal.jpg"), new GVector2f(0.5f,0.5f), new GVector2f(0.25f, 0.25f)));
		
	}
	
}
