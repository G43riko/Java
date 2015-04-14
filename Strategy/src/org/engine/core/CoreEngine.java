package org.engine.core;

import java.util.ArrayList;

import game.gui.Gui;
import game.gui.windows.MainWindow;
import glib.util.GLog;

import javax.swing.JFrame;

import org.MainStrategy;
import org.engine.component.GameComponent;
import org.engine.component.Input;
import org.engine.util.Loader;
import org.engine.util.MousePicker;
import org.engine.util.OBJLoader;
import org.lwjgl.opengl.Display;
import org.physics.object.GameObjectPhysics;
import org.strategy.component.CameraStrategy;
import org.strategy.rendering.RenderingEngineStrategy;

public abstract class CoreEngine extends JFrame{
	protected static final long serialVersionUID = 1L;

	private Gui gui;
	protected ArrayList<GameComponent> scene;
	private RenderingEngineStrategy renderingEngine;
	private Loader loader;
	private CameraStrategy camera;
	private boolean running;
	
	//CONSTRUCTORS
	
	public CoreEngine(){
		scene = new ArrayList<GameComponent>();
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
			o.setDirection(camera.getForward().mul(-1));
			addToScene(o);
		}

		if(Input.getKeyDown(Input.KEY_5)){
			JFrame p = new MainWindow();
			
			p.setLocation(Display.getX() + Display.getWidth() + 15, Display.getY());
//			p.add(new PhysicsWindow());
			p.setVisible(true);
		}
		
		Input.update();
		for(GameComponent g: scene){
			g.input();
		}
	};
	
	protected void update(){
		for(GameComponent g: scene){
			g.update();
		}
	};
	
	protected void render(){
		for(GameComponent g: scene){
			g.render(renderingEngine);
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

	public ArrayList<GameComponent> getScene() {
		return new ArrayList<GameComponent>(scene);
	}

	public boolean isRunning() {
		return running;
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

	

//	public MousePicker getMousePicker() {
//		return mousePicker;
//	}
}
