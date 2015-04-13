package game.core;

import game.entity.Bullet;
import game.entity.enemy.BasicEnemy;

import java.util.ArrayList;

import org.engine.component.GameComponent;

public class Scene {
	private ArrayList<BasicEnemy> enemies;
	private ArrayList<Bullet> bullets;
	private ArrayList<GameComponent> others;
	
	//CONSTRUCTORS
	
	public Scene(){
		enemies = new ArrayList<BasicEnemy>();
		bullets = new ArrayList<Bullet>();
		others = new ArrayList<GameComponent>();
	}
	
	//ADDERS
	
	public void add(GameComponent c){
		if(c instanceof BasicEnemy)
			enemies.add((BasicEnemy)c);
		else if(c instanceof Bullet)
			bullets.add((Bullet)c);
		else 
			others.add(c);
	}
	

	
	//OTHERS
	
	public boolean constains(BasicEnemy e){
		return enemies.contains(e);
	}
	
	public boolean constains(Bullet b){
		return bullets.contains(b);
	}
	
	public boolean constains(GameComponent g){
		return others.contains(g);
	}
	
	//REMOVERS
	
	public void remove(BasicEnemy e){
		enemies.remove(e);
	}
	
	public void remove(Bullet b){
		bullets.remove(b);
	}
	
	public void remove(GameComponent g){
		others.remove(g);
	}
	
	//GETTERS
	
	public int getSize(){
		
		int result = enemies.size();
		result += bullets.size();
		result += others.size();
		
		return result;
	}
	
	
}
