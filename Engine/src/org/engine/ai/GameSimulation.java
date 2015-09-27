package org.engine.ai;

import java.util.ArrayList;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.core.Input;
import org.engine.rendering.Bullet;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;

import glib.util.vector.GVector3f;

public class GameSimulation extends GameComponent{
	private Terrain terrain;
	private ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
	private PlayerHoldedObject weapon;
	
	public GameSimulation(GameAble parent){
		this(parent, new Terrain());
	}
	
	public GameSimulation(GameAble parent, Terrain terrain) {
		super(parent);
		this.terrain = terrain;
		weapon = new PlayerHoldedObject(parent, parent.getCamera());
		parent.addToScene(weapon);
	}
	
	@Override
	public void update(float delta) {
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
		addEnemy(new BasicEnemy(getParent()));
	}
	
	public void addEnemy(int num){
		for(int i=0 ; i<num ; i++)
			addEnemy(new BasicEnemy(getParent()));
	}
	
	@Override
	public void input() {
		if(Input.getMouseDown(0)){
			getParent().addToScene(new Bullet(getParent(), 
								   			  new Material("particles/particle.png"), 
								   			  weapon.getPosition().add(new GVector3f(0, 0.55f, 0)), 
								   			  getParent().getCamera().getMousePicker().getCurrentRay()));
		}
		
	}
	
	public void addEnemy(BasicEnemy enemy){
		enemies.add(enemy);
		
		enemy.setTarget(getParent().getCamera());
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
