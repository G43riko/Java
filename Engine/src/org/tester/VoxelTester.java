package org.tester;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector3f;

import org.engine.component.light.DirectionalLight;
import org.engine.component.movement.FPS;
import org.engine.core.CoreEngine;
import org.engine.object.GameObject;
import org.engine.object.GameObjectPhysics;
import org.engine.rendering.material.Material;
import org.engine.utils.Loader;
import org.engine.utils.OBJLoader;
import org.tester.voxel.world.ChunkNew;
import org.tester.voxel.world.Terrain;
import org.tester.voxel.world.World;

public class VoxelTester extends CoreEngine{
	@Override
	public void init() {
		getRenderingEngine().setSun(new DirectionalLight(new GVector3f(0.5f, 1, 0.5f), new GVector3f(0.8f)));
		
		GameObject plane = new GameObject(new Material("materials/texture.png"),Loader.loadToVAO(Plane.getVertices(100, 100), 
				 Plane.getTextures(100, 100), 
				 Plane.getNormals(), 
				 Plane.getIndices()));
		plane.setUseFakeLight(true);
		//addToScene(plane);
		
		addToScene(new FPS(getCamera()));
		
		//addToScene(new World());
		//addToScene(new Terrain(80, 80, 50));
		addToScene(new ChunkNew());
		
		
		addToScene(new GameObject(new Material("materials/texture.png"), OBJLoader.loadObjModel("sphere")));
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
