package game.main;

import game.component.Camera;
import game.component.Movable;
import game.component.SkyBox;
import game.core.CoreEngine;
import game.object.GameObject;
import game.object.world.Plane;
import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.util.Loader;
import game.world.Terrain;
import glib.shapes.threeDimensional.Box;

public class PhysicsTester extends CoreEngine{
	private static final long serialVersionUID = 1L;

	public void init() {
		setRenderingEngine(new RenderingEngine());
		setCamera(new Camera());
		setLoader(new Loader());
		setMousePicker(getCamera());
	
		addToScene(new SkyBox(getCamera()));
		
		addToScene(new Movable(getCamera()));
		
		addToScene(new GameObject(new Terrain(0,0,getLoader(),"heightMap").getModel(),new Material("texture.png")));
		
//		addToScene(new GameObject(getLoader().loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()), new Material("texture.png")));
		
//		addToScene(new Plane());
	}
	
}
