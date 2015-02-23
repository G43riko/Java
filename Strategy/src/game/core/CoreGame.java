package game.core;

import java.util.ArrayList;

import game.components.Player;
import game.gui.Gui;
import game.main.Loader;
import game.main.MainStrategy;
import game.object.Camera;
import game.object.GameObject;
import game.object.SkyBox;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.world.World;
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
	private Player player;
	private World world;
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
	
	public void update(){
		
	};
	
	public void start(){
		running = true;
		while(running && !Display.isCloseRequested()){
			input();
			update();
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
	
	public void render(){
		if(renderingEngine == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		
		renderingEngine.prepare();
		
		if(skyBox != null)
			skyBox.render(renderingEngine);
		
		if(world != null)
			world.render(renderingEngine);
		
		for(GameObject g: scene){
			g.input();
			g.update();
			g.render(renderingEngine);
		}
		
		if(renderingEngine.getSelect() != null){
			RenderingEngine.entityShader.bind();
			RenderingEngine.entityShader.updateUniform("select", true);
			renderingEngine.getSelect().setScale(renderingEngine.getSelect().getScale().add(0.01f));
			renderingEngine.getSelect().render(renderingEngine);
			renderingEngine.getSelect().setScale(renderingEngine.getSelect().getScale().sub(0.01f));
			RenderingEngine.entityShader.updateUniform("select", false);
			renderingEngine.setSelect(null);
		}
	};

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	public void cleanUp(){
		running = false;
		renderingEngine.cleanUp();
		Window.cleanUp();
	}

	public void addToScene(GameObject g){
		scene.add(g);
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
		addToScene(skyBox);
	}

	public void setPlayer(Player player) {
		this.player = player;
		addToScene(player);
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
