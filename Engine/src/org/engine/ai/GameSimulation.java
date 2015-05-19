package org.engine.ai;

import java.util.ArrayList;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;

public class GameSimulation extends GameComponent{
	private Terrain terrain;
	private Camera camera;
	private ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
	
	public GameSimulation(Camera camera){
		this(new Terrain(), camera);
	}
	
	public GameSimulation(Terrain terrain, Camera camera) {
		this.terrain = terrain;
		this.camera = camera;
	}
	
	@Override
	public void update() {
		enemies.stream().forEach(p -> {
			p.update();
			putEnemyOnFloor(p);
		});
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
