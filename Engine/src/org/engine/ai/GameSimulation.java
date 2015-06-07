package org.engine.ai;

import glib.math.GColision;
import glib.util.vector.GVector3f;

import java.util.ArrayList;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.Input;
import org.engine.core.CoreEngine;
import org.engine.rendering.Bullet;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.lwjgl.input.Keyboard;

public class GameSimulation extends GameComponent{
	private Terrain terrain;
	private Camera camera;
	private ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
	private CoreEngine game;
	private PlayerHoldedObject weapon;
	
	public GameSimulation(Camera camera, CoreEngine game){
		this(new Terrain(), camera, game);
	}
	
	public GameSimulation(Terrain terrain, Camera camera, CoreEngine game) {
		this.terrain = terrain;
		this.camera = camera;
		this.game = game;
		weapon = new PlayerHoldedObject(camera);
		game.addToScene(weapon);
	}
	
	@Override
	public void update() {
//		enemies.stream().forEach(a -> {
//			a.update();
//			putEnemyOnFloor(a);
//		});
	}
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		enemies.stream().forEach(p -> p.render(renderingEngine));
	}
	
	public void addEnemy(){
		addEnemy(new BasicEnemy());
	}
	
	public void addEnemy(int num){
		for(int i=0 ; i<num ; i++)
			addEnemy(new BasicEnemy());
	}
	
	@Override
	public void input() {
		if(Input.getMouseDown(0)){
			game.addToScene(new Bullet(new Material("particles/particle.png"), weapon.getPosition().add(new GVector3f(0, 0.55f, 0)), camera.getMousePicker().getCurrentRay(), camera));
		}
		
	}
	
	public void addEnemy(BasicEnemy enemy){
		enemies.add(enemy);
		
		enemy.setTarget(camera);
		enemy.setMoveOnFloor(true);
		
		putEnemyOnFloor(enemy);
	}
	
	private void putEnemyOnFloor(BasicEnemy enemy){
		enemy.getPosition().setY(terrain.getHeight(enemy.getPosition()) + enemy.getHeight() / 2);
	}
	
	public static double pointToLineDistance(double Ax, double Ay, double Bx,double By , double x, double y) {
		double normalLength = Math.sqrt((Bx-Ax)*(Bx-Ax)+(By-Ay)*(By-Ay));
	    return Math.abs((x-Ax)*(By-Ay)-(y-Ay)*(Bx-Ax))/normalLength;
	}
}
