package org.engine.main;

import org.engine.component.movement.BasicMovement;
import org.engine.component.movement.FPS;
import org.engine.component.movement.TPS;
import org.engine.core.CoreEngine;
import org.engine.entity.BasicPlayer;
import org.engine.gui.Hud;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.particles.ParticleEmmiter;
import org.engine.rendeing.ToFrameBufferRendering;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.material.Texture2D;
import org.engine.util.Loader;
import org.engine.util.OBJLoader;
import org.engine.water.Water;
import org.engine.world.Plane;
import org.engine.world.SkyBox;
import org.lwjgl.opengl.Display;
import org.strategy.component.CameraStrategy;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class MainTester extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setRenderingEngine(new RenderingEngineStrategy());
		setCamera(new CameraStrategy());
		setLoader(new Loader());
		setMousePicker(getCamera());
		setFrameRender(new ToFrameBufferRendering());
		
		crateTPSgame();
//		setMovementType(new FPS(getCamera()));
		
		addToScene(new SkyBox(getCamera()));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader(),255).getModel()));
		
		getRenderingEngine().addLight(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		
//		addToScene(new ParticleEmmiter(new GVector3f(0,2,5)));
		
//		addToScene(new GameObject(getLoader().loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()), new Material("texture.png")));
		
//		addToScene(new GameObject(OBJLoader.loadObjModel("sphere", getLoader())));
		
//		addToScene(new GameObject(new Terrain(0,0,getLoader()).getModel()));
		
		
		
		addToScene(new Hud(getFrameRender().getTexture(), new GVector2f(0.5f,0.5f), new GVector2f(0.25f, 0.25f)));
		
		addToScene(new Water(new GVector3f(1,1,1), getLoader()));
		
		addToScene(new Plane());
		
		
	}
	
	//TEMPERARY

	private void crateTPSgame(){
		BasicPlayer player = new BasicPlayer(new GameObject(OBJLoader.loadObjModel("person", getLoader()), new Material("playerTexture.png")));
		addToScene(player);
		setMovementType(new TPS(getCamera(), player));
		addCursor("cursor.png",0.9f);
		
	}
	
	private void addCursor(String name, float size){
		float offsetY = 0.2f;
		GVector2f s =  new GVector2f(0.05 * size, 0.05 * size / (float)Display.getHeight() * (float)Display.getWidth());
		addToScene(new Hud(new Texture2D(name), new GVector2f(0,offsetY),s));
	}
	
}
