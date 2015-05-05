package org.tester;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector3f;

import org.engine.component.movement.FPS;
import org.engine.core.CoreEngine;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.utils.Loader;
import org.engine.utils.OBJLoader;

public class DefaultTester extends CoreEngine{
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		GameObject g = new GameObject(new Material("materials/texture.png"), OBJLoader.loadObjModel("stall")){
			@Override
			public void update() {
				rotate(new GVector3f(0,1,0));
			}
		};
		g.setPosition(new GVector3f(0,0,-15));
		addToScene(g);
		
		addToScene(new FPS(getCamera()));
		
		addToScene(new GameObject(new Material("materials/texture.png"),Loader.loadToVAO(Plane.getVertices(100, 100), 
																						 Plane.getTextures(100, 100), 
																						 Plane.getNormals(), 
																						 Plane.getIndices())));
		
		
	}
	
	@Override
	protected void render() {
		getScene().stream().forEach(a -> a.render(getRenderingEngine()));
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	protected void input() {
	}

	

}
