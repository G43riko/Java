package org.tester;

import java.util.stream.Collectors;

import org.engine.app.Controlable;
import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.light.DirectionalLight;
import org.engine.component.light.PointLight;
import org.engine.component.movement.FPS;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.core.Scene;
import org.engine.gui.Hud;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.lwjgl.opengl.Display;
import org.tester.voxel.PointLightObject;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GameTest implements GameAble{
	private Controlable parent;
	private Scene scene = new Scene(this);
	
	public GameTest(Controlable parent){
		this.parent = parent;

		addToScene(new Hud(this, new Texture2D("materials/texture.png"), new GVector2f(0.5f,0.5f), new GVector2f(0.25f, 0.25f)));
		parent.getRenderingEngine().setSun(new DirectionalLight(this, new GVector3f(0.5f, 1, 0.5f), new GVector3f(0.8f)));
		addToScene(new FPS(this, false));
		addToScene(new GameObject(this, new Material(new Texture2D("materials/texture.png")), new Terrain(1,1).getModel()));
		
//		GameObject g;
//		for(int i=0 ; i< 500 ; i++){
//			g = new GameObject(this, new Material("materials/crossbow.jpg"), OBJLoader.loadObjModel("crossbow"));
//			g.setScale(new GVector3f(0.05f));
//			int num = 100;
//			float x = (float)Math.random()*num*2-num;
//			float y = (float)Math.random()*num*2;
//			float z = (float)Math.random()*num*2-num;
//			g.setPosition(new GVector3f(x,y,z));
//			g.setRotation(new GVector3f((float)Math.random()*360,(float)Math.random()*360,(float)Math.random()*360));
//			addToScene(g);
//		}
		
//		GameObject g = new GameObject(this, 
//									  new Material(new Texture2D("materials/crossbow.jpg"),
//				 								   new Texture2D("materials/crossbow_normal.jpg"),
//				 								   new Texture2D("materials/crossbow_specular.jpg")), 
//									  OBJLoader.loadObjModel("crossbow")) {
//			@Override
//			public void update(float delta) {
//				rotate(new GVector3f(0, 1, 0));
//			}
//		};
//		g.setScale(new GVector3f(0.1f));
//		g.setPosition(new GVector3f(0, 0, -15));
//		
//		addToScene(g);

		addToSceneLight(new PointLightObject(this, new PointLight(this, new GVector3f(0,1,1), new GVector3f(1,0,1))));
		
		GameObject plane = new GameObject(this, 
										  new Material("materials/texture.png"),
										  CoreEngine.getLoader().loadToVAO(Plane.getVertices(100, 100), 
												  						   Plane.getTextures(100, 100), 
												  						   Plane.getNormals(), 
												  						   Plane.getIndices()));
		plane.setUseFakeLight(true);
		addToScene(plane);
		
		
//		
	}

	public GVector2f getCanvasSize() {return new GVector2f(Display.getWidth(), Display.getHeight());}
	public Camera getCamera() {return parent.getRenderingEngine().getMainCamera();}
	
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
	
	public void render(RenderingEngine renderingEngine) {
		scene.render(renderingEngine);
	};
	

	public void addToScene(GameComponent component){
		scene.add(component);
	}
	
	public void addToSceneLight(PointLightObject light){
		scene.add(light);
		parent.getRenderingEngine().setPointLight(light);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}


}
