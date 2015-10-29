package org.tester;

import java.lang.reflect.GenericArrayType;
import java.util.stream.Collectors;

import org.engine.app.Controlable;
import org.engine.app.GameAble;
import org.engine.component.BasicPlayer;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.light.DirectionalLight;
import org.engine.component.light.PointLight;
import org.engine.component.movement.BasicMovement;
import org.engine.component.movement.TOP;
import org.engine.component.movement.TPS;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.core.Scene;
import org.engine.gui.Hud;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.utils.resource.OBJLoader;
import org.lwjgl.opengl.Display;
import org.tester.bomber.level.Block;
import org.tester.bomber.level.Level;
import org.tester.voxel.PointLightObject;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GameBomber implements GameAble{
	private GameComponent center;
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

		parent.getRenderingEngine().setSun(new DirectionalLight(this, new GVector3f(0.5f, 1, 0.5f), new GVector3f(0.8f)));
		addToScene(new Level(this));
		
//		addToScene(new Block(this, 1, new GVector3f()));
//		addToScene(new Block(this, 0, new GVector3f(0,0,1)));
//		addToScene(new Block(this, 2, new GVector3f(1,0,0)));
		
//		addToScene(new TOP(this));
		crateTPSgame();
		
//		addToSceneLight(new PointLightObject(this, new PointLight(this, new GVector3f(0.5f), new GVector3f(1), 800, 10)));
		
	}

	private void crateTPSgame(){
		BasicPlayer player = new BasicPlayer(this, new GameObject(this, new Material("playerTexture.png"), OBJLoader.loadObjModel("person")));
		addToScene(player);
		BasicMovement b = new TPS(this, getCamera(), player);
//		parent.setMovementType(b);
		addToScene(b);
		
		addCursor("cursor.png",0.9f);
	}
	
	private void addCursor(String name, float size){
		float offsetY = 0.2f;
		GVector2f s =  new GVector2f(0.05 * size, 0.05 * size / (float)Display.getHeight() * (float)Display.getWidth());
		addToScene(new Hud(this, new Texture2D(name), new GVector2f(0,offsetY),s));
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
	
	public void addToSceneLight(PointLightObject light){
		scene.add(light);
		parent.getRenderingEngine().setPointLight(light);
	}

	@Override
	public void exit() {
		parent.exit();
	}

}
