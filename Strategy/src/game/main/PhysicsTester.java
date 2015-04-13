package game.main;

import java.util.ArrayList;

import game.component.Camera;
import game.component.Movable;
import game.component.SkyBox;
import game.core.CoreEngine;
import game.light.PointLight;
import game.object.GameObject;
import game.object.world.Plane;
import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.util.Loader;
import game.util.OBJLoader;
import game.world.Terrain;
import glib.shapes.threeDimensional.Box;
import glib.util.vector.GVector3f;

public class PhysicsTester extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setRenderingEngine(new RenderingEngine());
		setCamera(new Camera());
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
