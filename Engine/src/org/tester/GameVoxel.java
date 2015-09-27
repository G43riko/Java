package org.tester;

import java.util.stream.Collectors;

import org.engine.app.Controlable;
import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.light.DirectionalLight;
import org.engine.component.movement.FPS;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.core.Scene;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.utils.resource.Loader;
import org.engine.utils.resource.OBJLoader;
import org.lwjgl.opengl.Display;
import org.tester.voxel.world.ChunkNew;
import org.tester.voxel.world.World;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GameVoxel  implements GameAble{
	private Controlable parent;
	private Scene scene = new Scene(this);
	
	public GameVoxel(Controlable parent) {
		this.parent = parent;
		
		
		parent.getRenderingEngine().setSun(new DirectionalLight(this, new GVector3f(0.5f, 1, 0.5f), new GVector3f(0.8f)));
		
		GameObject plane = new GameObject(this, 
										  new Material("materials/texture.png"),
										  CoreEngine.getLoader().loadToVAO(Plane.getVertices(100, 100), 
												  						   Plane.getTextures(100, 100), 
												  						   Plane.getNormals(), 
												  						   Plane.getIndices()));
		plane.setUseFakeLight(true);
		//addToScene(plane);
		
		addToScene(new FPS(this, true));
		
		addToScene(new World(this));
//		addToScene(new Terrain(80, 80, 50));
		addToScene(new ChunkNew(this));
		
		
		addToScene(new GameObject(this, new Material("materials/texture.png"), OBJLoader.loadObjModel("sphere")));
	}

	@Override
	public void render(RenderingEngine renderingEngine) {
		scene.render(renderingEngine);
	}
	
	@Override
	public void update(float delta) {
		scene.removeAll(scene.getScene().stream()
				.peek(a->a.update(delta))
				.filter(a->a.isDead())
				.collect(Collectors.toList()));
	}
	
	@Override
	public void input() {
		scene.getScene().stream().forEach(a -> a.input());
	}
	
	@Override
	public Camera getCamera() {
		return parent.getRenderingEngine().getMainCamera();
	}

	@Override
	public GVector2f getCanvasSize() {
		return new GVector2f(Display.getWidth(), Display.getHeight());
	}

	@Override
	public void addToScene(GameComponent component) {
		scene.add(component);
	}

}
