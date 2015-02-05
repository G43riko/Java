package game.core;


import java.util.ArrayList;

import game.gui.Gui;
import game.main.Loader;
import game.main.MainStrategy;
import game.object.Camera;
import game.object.GameObject;
import game.object.SkyBox;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import glib.util.GLog;

import javax.swing.JFrame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public abstract class CoreGame extends JFrame{
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> scene;
	private RenderingEngine renderingEngine;
	private Gui gui;
	private Loader loader;
	private SkyBox skyBox;
	private boolean running;
	
	public CoreGame(){
		Texture2D.setMipMapping(MainStrategy.MIP_MAPPING);
		scene = new ArrayList<GameObject>();
		running = false;
		
	}
	
	public void createWindow(CoreGame game){
		gui = Window.createWindow(game);
		
	};
	
	public abstract void init();
	
	public void start(){
		running = true;
		while(running && !Display.isCloseRequested()){
			input();
			update();
			renderingEngine.clearScreen();
			render();
			
			Display.update();
			Display.sync(MainStrategy.FPS);
		}
	}
	
	public void stop(){
		running = false;
	}

	private void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_1)){
			//normal
			renderingEngine.setView(0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_2)){
			//inverse
			renderingEngine.setView(1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_3)){
			//greyScale
			renderingEngine.setView(2);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_4)){
			//averageColor
			renderingEngine.setView(3);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			//averageColor
			renderingEngine.setBlur(true);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			//averageColor
			renderingEngine.setBlur(false);
		}
	}

	private void update(){
		skyBox.update();
	}
	
	public void render(){
		if(renderingEngine == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		if(skyBox != null)
			skyBox.render(renderingEngine);
		for(GameObject g: scene){
			g.input();
			g.update();
			g.render(renderingEngine);
		}
	};

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	protected void setRenderingEngine(RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}
	
	protected void setMainCamera(Camera camera) {
		addToScene(camera);
		renderingEngine.setMainCamera(camera);
		
		RenderingEngine.entityShader.bind();
		RenderingEngine.entityShader.updateUniform("projectionMatrix", camera.getProjectionMatrix());
		RenderingEngine.entityShader.unbind();
	}
	
	public void cleanUp(){
		Window.cleanUp();
	}
	
	public void addToScene(GameObject g){
		scene.add(g);
	}
	
	public Gui getGui(){
		return gui;
	}

	public Loader getLoader() {
		return loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public SkyBox getSkyBox() {
		return skyBox;
	}

	public void setSkyBox(SkyBox skyBox) {
		this.skyBox = skyBox;
	}
}
