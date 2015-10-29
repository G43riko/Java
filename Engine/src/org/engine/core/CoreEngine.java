package org.engine.core;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.engine.Config;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.movement.BasicMovement;
import org.engine.component.movement.FPS;
import org.engine.component.movement.TPS;
import org.engine.gui.Gui;
import org.engine.rendering.RenderingEngine;
import org.engine.utils.Log;
import org.engine.utils.resource.Loader;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public abstract class CoreEngine extends JFrame{
	private static final long serialVersionUID = 1L;

	private static Loader loader = new Loader();

	private Gui gui;
	private RenderingEngine renderingEngine;
	private Screen screen;
	
//
//	private GameComponent center;

//	private void setCenter(GameComponent center) {
//		this.center = center;
//	}
	
//	public void setMovementType(BasicMovement b){
//		if(b instanceof FPS){
//			setCenter(renderingEngine.getMainCamera());
//		}
//		else if(b instanceof TPS){
//			setCenter(((TPS)b).getPlayer());
//		}
//	}
	
	private boolean running = false;
	
	private int actFps = 60;
	private int width = Config.WINDOW_SIZE.getXi();
	private int height = Config.WINDOW_SIZE.getYi();
	
	public ArrayList<String> changes = new ArrayList<String>();
	
	//CONSTRUCTORS
	
	public CoreEngine(){
		gui = Window.createWindow(this, Config.WINDOW_FULLSCREEN);
		gui.repaint();
		
		renderingEngine = new RenderingEngine(new Camera(new GVector3f(0,1,5)));
		
		screen = new Screen();
	}
	
	//DEFAULT MAIN METHODS
	
	private void defaultRender() {
		renderingEngine.prepare();
		
		if(Config.ENGINE_POST_FX)
			screen.startRenderToScreen();
		
		render();
		
		if(Config.ENGINE_POST_FX){
			screen.stopRenderToScreen();
			renderingEngine.renderScreen(screen);
		}
		
		Log.render(actFps,renderingEngine.getRenderedPoligons(), renderingEngine.getRenderedPoints());
//		Log.render("rendered points: " + renderingEngine.getRenderedPoints(), new GVector2f(0, 48), 48, new GVector3f(1,0,1));
	}

	private void defaultUpdate() {
		Input.update();
		
		
		
		if(Display.wasResized())
			onResize();
		
		update();
	}
	
	private void defaultInput() {
		
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
	
	protected abstract void onResize();
	
	protected abstract void onExit();
	
	
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
			Display.sync(Config.ENGINE_FPS);
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
		
		loader.cleanUp();
		Window.cleanUp();

		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	
	//GETTERS

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}


	//SETTERS

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public static Loader getLoader() {
		return loader ;
	}
	
}
