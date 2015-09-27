package org.engine;

import java.util.HashMap;

import org.engine.utils.persistance.DataParser;
import org.engine.utils.persistance.XMLParser;
import org.tester.CoreGame;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;


public class DefaultApp extends CoreGame {
	private static final long serialVersionUID = 1L;
	private static XMLParser xmlData = new XMLParser("bomber.xml");
	private static DataParser viewData = new DataParser("bomberView.txt");
	
	public DefaultApp(){
	}
	
	public static HashMap<String, String> getData(String type) {return xmlData.getData(type);}
	
	public static int getInt(String value) {return viewData.getInt(value);}
	public static float getFloat(String value) {return viewData.getFloat(value);}
	public static String getString(String value) {return viewData.getString(value);}
	public static boolean getBoolean(String value) {return viewData.getBoolean(value);}
	public static GVector2f getVector2f(String value) {return viewData.getVector2f(value);}
	public static GVector3f getVector3f(String value) {return viewData.getVector3f(value);}
	
//		GameObject g = new GameObject(new Material("materials/texture.png"), OBJLoader.loadObjModel("axe")){
//		usePostFX(true);
		
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
		
//		GameObject g = new GameObject(new Material(new Texture2D("materials/texture.png")),OBJLoader.loadObjModel("ak47"));
//		g.setScale(new GVector3f(0.1f));
//		addToScene(g);
		
//		addToScene(new World());
//		addToScene(new Chunk(new GVector3f(0,0,0)));
//		addToScene(new Block(0));
		

//		BasicEnemy e = new BasicEnemy();
//		e.setTarget(getCamera());
//		e.setMoveOnFloor(true);
//		addToScene(e);
		
		
//		GameSimulation simulation = new GameSimulation(getCamera(), this);
//		addToScene(simulation);
//		simulation.addEnemy();
		
//		addToScene(ObjectLoader.loadModel("warrior_axe"));
//		addToScene(new AroundAOverB(new GVector3f(0,10,0), new GVector3f(20,10,0), o, 0.1f));
		
		
		
		
//		PointLightObject o = new PointLightObject(new PointLight(new GVector3f(0, 4, 0),new GVector3f(1,0,1), new GVector3f(1, 0.04f, 0.008f)));
//		addToScene(new AToB(new GVector3f(1,10,1), new GVector3f(1,1,1), o, 0.1f));
//		addToSceneLight(o);
		
		
		
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
}
