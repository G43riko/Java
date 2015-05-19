package org.tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.engine.ai.BasicEnemy;
import org.engine.ai.GameSimulation;
import org.engine.component.light.DirectionalLight;
import org.engine.component.light.PointLight;
import org.engine.component.movement.FPS;
import org.engine.component.movement.auto.AToB;
import org.engine.component.movement.auto.AroundAOverB;
import org.engine.core.CoreEngine;
import org.engine.gui.Hud;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.utils.Loader;
import org.engine.utils.OBJLoader;
import org.tester.voxel.PointLightObject;
import org.tester.voxel.world.Block;
import org.tester.voxel.world.Chunk;
import org.tester.voxel.world.World;

public class DefaultTester extends CoreEngine{
	private static final long serialVersionUID = 1L;

	
	
	@Override
	public void init() {
//		GameObject g = new GameObject(new Material("materials/texture.png"), OBJLoader.loadObjModel("axe")){
		
		usePostFX(true);
		
//		GameObject g = new GameObject(new Material(new Texture2D("materials/crossbow.jpg"),
////												   new Texture2D("materials/crossbow_normal.jpg"),
//												   new Texture2D("materials/crossbow_specular.jpg")), OBJLoader.loadObjModel("crossbow")){
//			@Override
//			public void update() {
//				rotate(new GVector3f(0,1,0));
//			}
//		};
//		g.setScale(new GVector3f(0.1f));
//		g.setPosition(new GVector3f(0,0,-15));
//		addToScene(g);
		
//		GameObject g = new GameObject(new Material(new Texture2D("materials/skull_diffuse.jpg")),OBJLoader.loadObjModel("Skull"));
//		addToScene(g);
		
//		addToScene(new World());
//		addToScene(new Chunk(new GVector3f(0,0,0)));
//		addToScene(new Block(0));
		

//		BasicEnemy e = new BasicEnemy();
//		e.setTarget(getCamera());
//		e.setMoveOnFloor(true);
//		addToScene(e);
		
		GameSimulation simulation = new GameSimulation(getCamera());
		addToScene(simulation);
		simulation.addEnemy();
		
//		addToScene(ObjectLoader.loadModel("warrior_axe"));
		PointLightObject o = new PointLightObject(new PointLight(new GVector3f(0, 4, 0),new GVector3f(1,0,1), new GVector3f(1, 0.04f, 0.008f)));
		addToScene(new AToB(new GVector3f(1,10,1), new GVector3f(1,1,1), o, 0.1f));
//		addToScene(new AroundAOverB(new GVector3f(0,10,0), new GVector3f(20,10,0), o, 0.1f));
		addToSceneLight(o);
		
		
		getRenderingEngine().setSun(new DirectionalLight(new GVector3f(0.5f, 1, 0.5f), new GVector3f(0.8f)));
		
//		for(int i=0 ; i< 1000 ; i++){
//			g = new GameObject(new Material("materials/crossbow.jpg"), OBJLoader.loadObjModel("crossbow"));
//			g.setScale(new GVector3f(0.05f));
//			int num = 100;
//			float x = (float)Math.random()*num*2-num;
//			float y = (float)Math.random()*num*2;
//			float z = (float)Math.random()*num*2-num;
//			g.setPosition(new GVector3f(x,y,z));
//			g.setRotation(new GVector3f((float)Math.random()*360,(float)Math.random()*360,(float)Math.random()*360));
//			addToScene(g);
//		}
		
		
		
		addToScene(new FPS(getCamera()));
		
		GameObject plane = new GameObject(new Material("materials/texture.png"),Loader.loadToVAO(Plane.getVertices(100, 100), 
																								 Plane.getTextures(100, 100), 
																								 Plane.getNormals(), 
																								 Plane.getIndices()));
		plane.setUseFakeLight(true);
		addToScene(plane);
		
		addToScene(new Hud(new Texture2D("materials/texture.png"), new GVector2f(0.5f,0.5f), new GVector2f(0.25f, 0.25f)));
		
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
