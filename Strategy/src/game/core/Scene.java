package game.core;

import game.component.GameComponent;
import game.entity.Bullet;
import game.entity.enemy.BasicEnemy;

import java.util.ArrayList;

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
	
	public void add(BasicEnemy e){
		enemies.add(e);
	}
	
	public void add(Bullet b){
		bullets.add(b);
	}
	
	public void add(GameComponent g){
		others.add(g);
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
