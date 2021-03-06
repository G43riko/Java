package org.strategy.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glib.util.GLog;

import org.MainStrategy;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.core.CoreEngine;
import org.engine.light.PointLight;
import org.engine.rendeing.material.Texture2D;
import org.engine.world.SkyBox;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.strategy.component.CameraStrategy;
import org.strategy.entity.Bullet;
import org.strategy.entity.player.Player;
import org.strategy.rendering.RenderingEngineStrategy;
import org.strategy.world.World;

public abstract class CoreStrategy extends CoreEngine{
	private static final long serialVersionUID = 1L;
	
	private SkyBox skyBox;
	private Player player;
	private World world;
	private PointLight sun;
//	private CameraStrategy camera;

	public ArrayList<String> changes = new ArrayList<String>();
	
	
	private boolean[] clicks = new boolean[2];
	
	//CONSTRUCTORS
	
	public CoreStrategy(){
		Texture2D.setMipMapping(MainStrategy.MIP_MAPPING);
	}
	
	//OTHERS
	
	public void start(){
		setRunning(true);
		
		if(getRenderingEngine() == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		
		while(isRunning() && !Display.isCloseRequested()){
//			double time = System.currentTimeMillis();
			input();
			mainLoop();
			Display.update();
			Display.sync(MainStrategy.FPS);
//			System.out.println(1000/(System.currentTimeMillis()-time));
		}
	}
	
	private void doForAll(GameComponent g){
		g.input();
		g.update();
		g.render(getRenderingEngine());
	}
	
	private void mainLoop() {
		
		getRenderingEngine().prepare();
		
		getSceneObject().getScene().stream().forEach(e -> doForAll(e));
		getSceneObject().getScene().removeAll(getSceneObject().getScene().stream()
																		 .filter(a -> a instanceof Bullet && ((Bullet)a).isDead())
																		 .collect(Collectors.toList()));

		
		if(getRenderingEngine().getSelectBlock().getBlock() != null){
			RenderingEngineStrategy.getShader("entityShader").bind();
			RenderingEngineStrategy.getShader("entityShader").updateUniform("select", true);
			getRenderingEngine().getSelectBlock().getBlock().setScale(getRenderingEngine().getSelectBlock().getBlock().getScale().add(0.01f));
			getRenderingEngine().getSelectBlock().getBlock().render(getRenderingEngine());
			getRenderingEngine().getSelectBlock().getBlock().setScale(getRenderingEngine().getSelectBlock().getBlock().getScale().sub(0.01f));
			RenderingEngineStrategy.getShader("entityShader").updateUniform("select", false);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LMENU)){
				if(Mouse.isButtonDown(1) && !clicks[1]){
					if(world != null && getRenderingEngine()!= null && getRenderingEngine().getSelectBlock() !=null)
						world.remove(getRenderingEngine().getSelectBlock().getBlock());
					if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						clicks[1] = true;
				}
				if(!Mouse.isButtonDown(1))
					clicks[1] = false;
				
				if(Mouse.isButtonDown(0) && !clicks[0]){
					if(world != null && getRenderingEngine()!= null && getRenderingEngine().getSelectBlock() !=null){
						world.add(getRenderingEngine().getSelectBlock().getBlock(), getRenderingEngine().getSelectBlock().getSide(), player.getSelectBlock());
					}
					clicks[0] = true;
				}
				
				if(!Mouse.isButtonDown(0))
					clicks[0] = false;
			}
			getRenderingEngine().getSelectBlock().reset();
		}
		if(Mouse.isButtonDown(0) && !Keyboard.isKeyDown(Keyboard.KEY_LMENU)){
			getCamera().getMousePicker().update();
			addToScene(new Bullet(getPlayer().getPosition(), getPlayer().getPosition().add(getCamera().getMousePicker().getCurrentRay().mul(10)), getWorld(), getPlayer(), getLoader()));
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
					getRenderingEngine().setView(i);
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
			getRenderingEngine().setVariable("light",true);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			getRenderingEngine().setVariable("light",false);
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

//	@Override
//	public Camera getCamera() {
//		return getCamera();
//	}
	
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
//		PointLight r = new PointLight(new GVector3f(16,30,4 ),new GVector3f(1,0,0), new GVector3f(1, 0.04f, 0.008f));
//		PointLight g = new PointLight(new GVector3f(22,32,28),new GVector3f(0,1,0), new GVector3f(1, 0.04f, 0.008f));
//		PointLight b = new PointLight(new GVector3f(6 ,34,28),new GVector3f(0,0,1), new GVector3f(1, 0.02f, 0.008f));
//		getRenderingEngine().setSun(sun);
		List<PointLight> l = new ArrayList<PointLight>();
		l.add(sun);
//		l.add(r);
//		l.add(g);
//		l.add(b);
		getRenderingEngine().setLights(l);
	}
}
