package org.engine.core;

import java.util.ArrayList;




import glib.util.GLog;

import javax.swing.JFrame;

import org.MainStrategy;
import org.engine.component.GameComponent;
import org.engine.component.Input;
import org.engine.gui.Gui;
import org.engine.object.GameObjectPhysics;
import org.engine.rendeing.RenderingEngine;
import org.engine.rendeing.ToFrameBufferRendering;
import org.engine.util.Loader;
import org.engine.util.MousePicker;
import org.engine.util.OBJLoader;
import org.lwjgl.opengl.Display;
import org.strategy.component.CameraStrategy;
import org.strategy.rendering.RenderingEngineStrategy;

public abstract class CoreEngine extends JFrame{
	protected static final long serialVersionUID = 1L;

	private ToFrameBufferRendering frameRender; 
	
	private Gui gui;
//	protected ArrayList<GameComponent> scene;
	private Scene scene;
	private RenderingEngineStrategy renderingEngine;

	private Loader loader;
	private CameraStrategy camera;
	private boolean running;
	
	//CONSTRUCTORS
	
	public CoreEngine(){
//		scene = new ArrayList<GameComponent>();
		scene = new Scene();
		running = false;
	}
	
	//OTHERS
	
	public abstract void init();
	
	public void createWindow(CoreEngine game){
		gui = Window.createWindow(game);
	};
	
	public void stop(){
		running = false;
	}

	public void cleanUp(){
		running = false;
		renderingEngine.cleanUp();
		Window.cleanUp();
	}

	public void addToScene(GameComponent g){
		scene.add(g);
	}

	public void start(){
		running = true;

		
		if(renderingEngine == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		
		while(running && !Display.isCloseRequested()){
			input();
			if(gui!=null)
				gui.update();
			update();
			renderingEngine.prepare();
			render();
			Display.update();
			Display.sync(MainStrategy.FPS);
		}
	}
	
	protected void input(){
		if(Input.getMouseDown(0)){
			GameObjectPhysics o = new GameObjectPhysics(OBJLoader.loadObjModel("sphere", loader));
			o.setPosition(camera.getPosition());
//			o.setDirection(camera.getForward().mul(-1));
			o.setDirection(camera.getMousePicker().getCurrentRay());
			addToScene(o);
		}

		
		
		Input.update();
		for(GameComponent g: scene.getScene()){
			g.input();
		}
	};
	
	protected void update(){
		
		ArrayList<GameComponent> scena = scene.getScene();
		for(int i=0 ; i<scena.size() ; i++){
			GameComponent e = scena.get(i);
			e.update();
			if(e.isDead()){
				scene.remove(e);
				i--;
			}
		}
		
//		for(GameComponent g: scene.getScene()){
//			g.update();
//		}
	};
	
	private void finalRender(){
		renderingEngine.renderObject(scene.getObjects());
		renderingEngine.renderWater(scene.getWaters());
		renderingEngine.renderParticle(scene.getParticles());
		for(GameComponent g: scene.getOthers()){
			g.render(renderingEngine);
		}
	}
	
	protected void render(){
		
		if(frameRender != null)
			frameRender.startRenderToFrameBuffer();
		
		finalRender();
		
		if(frameRender != null){
			frameRender.stopRenderToFrameBuffer();
			finalRender();
			renderingEngine.renderHud(scene.getHuds());
		}
	};
	
	//GETTERS

	public RenderingEngineStrategy getRenderingEngine() {
		return renderingEngine;
	}

	public Gui getGui(){
		return gui;
	}

	public Loader getLoader() {
		return loader;
	}
	
	public CameraStrategy getCamera() {
		return camera;
	}

	public boolean isRunning() {
		return running;
	}

	public ArrayList<GameComponent> getScene() {
		return scene.getScene();
	}
	
	public Scene getSceneObject() {
		return scene;
	}
	
	//SETTERS
	
	public void setCamera(CameraStrategy camera) {
		addToScene(camera);
		this.camera = camera;
		renderingEngine.setMainCamera(camera);
	}
	
	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	protected void setRenderingEngine(RenderingEngineStrategy renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public void setMousePicker(CameraStrategy camera){
		camera.setMousePicker( new MousePicker(camera));
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setFrameRender(ToFrameBufferRendering frameRender) {
		this.frameRender = frameRender;
	}

	public ToFrameBufferRendering getFrameRender() {
		return frameRender;
	}

	

//	public MousePicker getMousePicker() {
//		return mousePicker;
//	}
}
