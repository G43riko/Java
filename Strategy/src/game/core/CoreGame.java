package game.core;

import java.util.ArrayList;
import java.util.List;

import game.component.Camera;
import game.component.GameComponent;
import game.component.SkyBox;
import game.entity.Bullet;
import game.entity.player.Player;
import game.gui.Gui;
import game.light.PointLight;
import game.main.MainStrategy;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.util.Loader;
import game.util.MousePicker;
import game.world.World;
import glib.util.GLog;
import glib.util.vector.GVector3f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class CoreGame extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	private SkyBox skyBox;
	private Player player;
	private World world;
	private PointLight sun;

	private boolean[] clicks = new boolean[2];
	
	//CONSTRUCTORS
	
	public CoreGame(){
		Texture2D.setMipMapping(MainStrategy.MIP_MAPPING);
	}
	
	//OTHERS
	
	public void start(){
		running = true;
		
		if(renderingEngine == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		
		while(running && !Display.isCloseRequested()){
//			double time = System.currentTimeMillis();
			input();
			mainLoop();
			Display.update();
			Display.sync(MainStrategy.FPS);
//			System.out.println(1000/(System.currentTimeMillis()-time));
		}
	}
	
	private void mainLoop() {
		
		renderingEngine.prepare();
		
		ArrayList<GameComponent> toRemove = new ArrayList<GameComponent>();
		int i=0;
		for(GameComponent g: scene){
			i++;
			g.input();
			g.update();
			if(g instanceof Bullet){
				if(((Bullet)g).isDead()){
					toRemove.add(g);
				}
			}
			g.render(renderingEngine);
		}
		scene.removeAll(toRemove);
		
		if(renderingEngine.getSelectBlock().getBlock() != null){
			RenderingEngine.entityShader.bind();
			RenderingEngine.entityShader.updateUniform("select", true);
			renderingEngine.getSelectBlock().getBlock().setScale(renderingEngine.getSelectBlock().getBlock().getScale().add(0.01f));
			renderingEngine.getSelectBlock().getBlock().render(renderingEngine);
			renderingEngine.getSelectBlock().getBlock().setScale(renderingEngine.getSelectBlock().getBlock().getScale().sub(0.01f));
			RenderingEngine.entityShader.updateUniform("select", false);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LMENU)){
				if(Mouse.isButtonDown(1) && !clicks[1]){
					if(world != null && renderingEngine!= null && renderingEngine.getSelectBlock() !=null)
						world.remove(renderingEngine.getSelectBlock().getBlock());
					if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						clicks[1] = true;
				}
				if(!Mouse.isButtonDown(1))
					clicks[1] = false;
				
				if(Mouse.isButtonDown(0) && !clicks[0]){
					if(world != null && renderingEngine!= null && renderingEngine.getSelectBlock() !=null){
						world.add(renderingEngine.getSelectBlock().getBlock(), renderingEngine.getSelectBlock().getSide(), player.getSelectBlock());
					}
					clicks[0] = true;
				}
				
				if(!Mouse.isButtonDown(0))
					clicks[0] = false;
			}
			renderingEngine.getSelectBlock().reset();
		}
		if(Mouse.isButtonDown(0) && !Keyboard.isKeyDown(Keyboard.KEY_LMENU)){
			mousePicker.update();
			addToScene(new Bullet(player.getPosition(), player.getPosition().add(mousePicker.getCurrentRay().mul(10)), world, player));
		}
	}

	protected void input() {
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
			renderingEngine.setLight(true);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			renderingEngine.setLight(false);
		}
	}
	
	//GETTERS

	public SkyBox getSkyBox() {
		return skyBox;
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	//SETTERS
	
	public void setSkyBox(SkyBox skyBox) {
		this.skyBox = skyBox;
		addToScene(skyBox);
	}

	public void setPlayer(Player player) {
		this.player = player;
		addToScene(player);
	}

	public void setWorld(World world) {
		this.world = world;
		world.setRunning(true);
		addToScene(world);
		
	}

	public void setSun(PointLight sun) {
		this.sun = sun;
		PointLight r = new PointLight(new GVector3f(16,30,4 ),new GVector3f(1,0,0), new GVector3f(1, 0.04f, 0.008f));
		PointLight g = new PointLight(new GVector3f(22,32,28),new GVector3f(0,1,0), new GVector3f(1, 0.04f, 0.008f));
		PointLight b = new PointLight(new GVector3f(6 ,34,28),new GVector3f(0,0,1), new GVector3f(1, 0.02f, 0.008f));
//		renderingEngine.setSun(sun);
		List<PointLight> l = new ArrayList<PointLight>();
		l.add(sun);
//		l.add(r);
//		l.add(g);
//		l.add(b);
		renderingEngine.setLights(l);
	}
}
