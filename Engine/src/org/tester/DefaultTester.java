package org.tester;

import glib.shapes.threeDimensional.Plane;
import glib.util.vector.GVector3f;

import org.engine.component.movement.FPS;
import org.engine.core.CoreEngine;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.utils.Loader;
import org.engine.utils.OBJLoader;
import org.tester.Util.ObjectLoader;

public class DefaultTester extends CoreEngine{
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
//		GameObject g = new GameObject(new Material("materials/texture.png"), OBJLoader.loadObjModel("axe")){
		
		GameObject g = new GameObject(new Material(new Texture2D("materials/crossbow.jpg"), 
												   new Texture2D("materials/crossbow_specular.jpg")), OBJLoader.loadObjModel("crossbow")){
			@Override
			public void update() {
				rotate(new GVector3f(0,1,0));
			}
		};
		g.setScale(new GVector3f(0.1f));
		g.setPosition(new GVector3f(0,0,-15));
		addToScene(g);
		
//		addToScene(ObjectLoader.loadModel("warrior_axe"));
		
		
//		for(int i=0 ; i< 1000 ; i++){
//			GameObject g = new GameObject(new Material("materials/crossbow.jpg"), OBJLoader.loadObjModel("crossbow"));
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
