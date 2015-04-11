package game.core;

import java.util.ArrayList;

import game.GameObjectPhysics;
import game.component.Camera;
import game.component.GameComponent;
import game.gui.Gui;
import game.main.MainStrategy;
import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.util.Loader;
import game.util.MousePicker;
import game.util.OBJLoader;
import glib.util.GLog;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class CoreEngine extends JFrame{
	protected static final long serialVersionUID = 1L;

	protected Gui gui;
	protected ArrayList<GameComponent> scene;
	protected RenderingEngine renderingEngine;
	protected Loader loader;
	protected Camera camera;
	protected MousePicker mousePicker;
	protected boolean running;
	
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
			update();
			renderingEngine.prepare();
			render();
			Display.update();
			Display.sync(MainStrategy.FPS);
		}
	}
	
	protected void input(){
		if(Mouse.isButtonDown(0)){
			GameObjectPhysics o = new GameObjectPhysics(OBJLoader.loadObjModel("sphere", loader));
			o.setPosition(camera.getPosition());
			o.setDirection(camera.getForward().mul(-1));
			addToScene(o);
		}
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

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	public Gui getGui(){
		return gui;
	}

	public Loader getLoader() {
		return loader;
	}
	
	public Camera getCamera() {
		return camera;
	}


	//SETTERS
	
	public void setCamera(Camera camera) {
		addToScene(camera);
		this.camera = camera;
		renderingEngine.setMainCamera(camera);
		RenderingEngine.entityShader.bind();
		RenderingEngine.entityShader.updateUniform("projectionMatrix", camera.getProjectionMatrix());
		RenderingEngine.entityShader.unbind();
	}
	
	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	protected void setRenderingEngine(RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public void setMousePicker(Camera camera){
		mousePicker = new MousePicker(camera);
	}


}
