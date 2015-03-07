package game.core;

import java.util.ArrayList;
import java.util.List;

import game.Light;
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
import glib.util.vector.GVector3f;

import javax.swing.JFrame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
	private Light sun;
	private boolean running;
	private boolean[] clicks = new boolean[2];
	
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
//			double time = System.currentTimeMillis();
			input();
			update();
			render();
			Display.update();
			Display.sync(MainStrategy.FPS);
//			System.out.println(1000/(System.currentTimeMillis()-time));
		}
	}
	
	public void stop(){
		running = false;
	}

	private void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){ 
			//normal
			//inverse
			//greyScale
			//averageColor
			//normals
			for(int i=0 ; i<5 ; i++){
				if(Keyboard.isKeyDown(i+2)){
					renderingEngine.setView(i);
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				world.saveToFile("mainWorld.gw");
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_N)){
				world = new World();
				player.setWorld(world);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			renderingEngine.setBlur(true);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
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
		
//		if(world != null)
//			world.render(renderingEngine);
		for(GameObject g: scene){
			g.input();
			g.update();
			g.render(renderingEngine);
		}
		
		if(renderingEngine.getSelectBlock().getBlock() != null){
			RenderingEngine.entityShader.bind();
			RenderingEngine.entityShader.updateUniform("select", true);
			renderingEngine.getSelectBlock().getBlock().setScale(renderingEngine.getSelectBlock().getBlock().getScale().add(0.01f));
			renderingEngine.getSelectBlock().getBlock().render(renderingEngine);
			renderingEngine.getSelectBlock().getBlock().setScale(renderingEngine.getSelectBlock().getBlock().getScale().sub(0.01f));
			RenderingEngine.entityShader.updateUniform("select", false);
			
			if(Mouse.isButtonDown(1) && !clicks[1]){
				if(world != null && renderingEngine!= null && renderingEngine.getSelectBlock() !=null)
				world.remove(renderingEngine.getSelectBlock().getBlock());
				clicks[1] = true;
			}
			if(!Mouse.isButtonDown(1))
				clicks[1] = false;
			
			if(Mouse.isButtonDown(0) && !clicks[0]){
				if(world != null && renderingEngine!= null && renderingEngine.getSelectBlock() !=null)
					world.add(renderingEngine.getSelectBlock().getBlock(), renderingEngine.getSelectBlock().getSide(), player.getSelectBlock());
				clicks[0] = true;
			}
			if(!Mouse.isButtonDown(0))
				clicks[0] = false;
			
			renderingEngine.getSelectBlock().reset();
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
		addToScene(world);
	}

	public void setSun(Light sun) {
		this.sun = sun;
		Light r = new Light(new GVector3f(16,30,4),new GVector3f(1,0,0), new GVector3f(1,0.04f,0.008f));
		Light g = new Light(new GVector3f(22,32,28),new GVector3f(0,1,0), new GVector3f(1,0.04f,0.008f));
		Light b = new Light(new GVector3f(6,34,28),new GVector3f(0,0,1), new GVector3f(1,0.04f,0.008f));
//		renderingEngine.setSun(sun);
		List<Light> l = new ArrayList<Light>();
		l.add(sun);
		l.add(r);
		l.add(g);
		l.add(b);
		renderingEngine.setLights(l);
	}
}
