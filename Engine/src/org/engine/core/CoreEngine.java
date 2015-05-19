package org.engine.core;

import glib.util.vector.GVector3f;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.Input;
import org.engine.component.Screen;
import org.engine.gui.Gui;
import org.engine.rendering.RenderingEngine;
import org.engine.utils.Loader;
import org.engine.utils.Log;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.tester.voxel.PointLightObject;

public abstract class CoreEngine extends JFrame{
	private static final long serialVersionUID = 1L;
	private Scene scene = new Scene();

	private RenderingEngine renderingEngine;
	private Camera camera;
	private Gui gui;
	
	private Screen screen;
	
	private boolean usePostFX;
	
	private boolean running = false;
	
	private int fps = 60;
	private int actFps = 60;
	private int width = 800;
	private int height = 600;
	
	public ArrayList<String> changes = new ArrayList<String>();
	
	//CONSTRUCTORS
	
	//DEFAULT MAIN METHODS
	
	private void defaultRender() {
		renderingEngine.prepare();
		
		if(usePostFX)
			screen.startRenderToScreen();
		
		render();
		
		if(usePostFX){
			screen.stopRenderToScreen();
			renderingEngine.renderScreen(screen);
		}
		
		Log.render(actFps,renderingEngine.getRenderedPoligons(), renderingEngine.getRenderedPoints());
	}

	private void defaultUpdate() {
		Input.update();
		
		scene.removeAll(scene.getScene().stream()
										.peek(a->a.update())
										.filter(a->a.isDead())
										.collect(Collectors.toList()));
		
		if(Display.wasResized())
			onResize();
		
		update();
	}
	
	private void defaultInput() {
		scene.getScene().stream().forEach(a -> a.input());
		
		if(Input.getKeyDown(Keyboard.KEY_G)){
			gui.tooglePanels();
		}
		if(Input.getKeyDown(Keyboard.KEY_T)){
			Log.toogle();
		}
		
		for(int i=0 ; i< changes.size() ; i++){
			String s = changes.get(i);
			gui.getCoreEngine().getRenderingEngine().toogleVariable(s);
			changes.remove(s);
			i--;
		}
		
		input();
	}

	//MAIN METHODS
	
	public abstract void init();
	
	protected abstract void render();
	protected abstract void update();
	protected abstract void input();

	//OTHERS

	public void createWindow(CoreEngine game){
		gui = Window.createWindow(game, true);
		gui.repaint();
		mainInit();
	}
	
	private void mainInit(){
		setRenderingEngine(new RenderingEngine());
		setCamera(new Camera(new GVector3f(0,1,5)));
		
		screen = new Screen();
	}
	
	private void onResize(){
		camera.createProjectionMatrix();
		renderingEngine.updateCamera();
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public void addToScene(GameComponent gameComponent){
		scene.add(gameComponent);
	}
	
	public void addToSceneLight(PointLightObject light){
		scene.add(light);
		renderingEngine.setPointLight(light);
		
	}
	
	public void start(){
		running = true;
		
		int ticks=0;
		long time = System.currentTimeMillis();
		while(running && !Display.isCloseRequested()){
			defaultInput();
			defaultUpdate();
			defaultRender();
			
			if(System.currentTimeMillis() - time < 1000)
				ticks++;
			else{
				actFps = ticks;
				ticks = 0;
				time = System.currentTimeMillis();
			}
				
			Display.update();
			Display.sync(fps);
		}
		stop();
	}
	
	public void stop(){
		running = false;
	}
	
	public void cleanUp(){
		stop();
		
		renderingEngine.cleanUp();
		screen.cleanUp();
		
		Loader.cleanUp();
		Window.cleanUp();

		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public void usePostFX(boolean value){
		usePostFX = value;
	}
	
	//GETTERS

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	public Camera getCamera() {
		return camera;
	}

	//SETTERS
	
	
	public void setRenderingEngine(RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public void setCamera(Camera camera) {
		addToScene(camera);
		this.camera = camera;
		renderingEngine.setCamera(camera);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	protected List<GameComponent> getScene(){
		return scene.getScene();
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
}
