package org.tester;

import java.util.stream.Collectors;

import org.engine.app.Controlable;
import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.light.DirectionalLight;
import org.engine.component.light.PointLight;
import org.engine.component.movement.TOP;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.core.Scene;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.lwjgl.opengl.Display;
import org.tester.bomber.level.Block;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GameBomber implements GameAble{
	Controlable parent;
	private Scene scene = new Scene(this);
	
	public GameBomber(Controlable parent) {
		this.parent = parent;
		
		
		
		GameObject plane = new GameObject(this, 
				  new Material("materials/texture.png"),
				  CoreEngine.getLoader().loadToVAO(Plane.getVertices(100, 100), 
						  						   Plane.getTextures(100, 100), 
						  						   Plane.getNormals(), 
						  						   Plane.getIndices()));
		plane.setUseFakeLight(true);
		addToScene(plane);

//		DirectionalLight dir = new DirectionalLight(this, new GVector3f(0.5, 1, 0.5), new GVector3f(0.8 ,0, 0.8));
//		parent.getRenderingEngine().setSun(dir);
//		addToScene(dir);
		
		addToScene(new Block(this, 1, new GVector3f()));
//		addToScene(new Block(this, 0, new GVector3f(0,0,1)));
//		addToScene(new Block(this, 2, new GVector3f(1,0,0)));
		
		addToScene(new TOP(this));
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
