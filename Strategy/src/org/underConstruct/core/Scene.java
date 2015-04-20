package org.underConstruct.core;

import java.util.ArrayList;

import org.engine.component.GameComponent;
import org.engine.gui.Hud;
import org.engine.object.GameObject;
import org.engine.particles.ParticleEmmiter;
import org.strategy.entity.Bullet;
import org.strategy.entity.enemy.BasicEnemy;

public class Scene {
	private ArrayList<GameObject> objects;
	private ArrayList<Hud> huds;
	private ArrayList<ParticleEmmiter> particles;
	private ArrayList<GameComponent> others;
	
	//CONSTRUCTORS
	
	public Scene(){
		objects = new ArrayList<GameObject>();
		huds = new ArrayList<Hud>();
		particles = new ArrayList<ParticleEmmiter>();
		others = new ArrayList<GameComponent>();
	}
	
	//ADDERS
	
	public void add(GameObject c){
		objects.add(c);
	}
	
	public void add(Hud c){
		huds.add(c);
	}
	
	public void add(ParticleEmmiter c){
		particles.add(c);
	}
	
	public void add(GameComponent c){
		others.add(c);
	}
	
	//REMOVERS
	
	public void remove(GameObject e){
		objects.remove(e);
	}
	
	public void removeAll(ArrayList<GameComponent> e){
		huds.removeAll(e);
		particles.removeAll(e);
		objects.removeAll(e);
		others.removeAll(e);
	}
	
	public void remove(Hud b){
		huds.remove(b);
	}
	
	public void remove(ParticleEmmiter g){
		particles.remove(g);
	}
	
	public void remove(GameComponent g){
		others.remove(g);
	}
	
	//GETTERS
	
	public int getSize(){
		
		int result = objects.size();
		result += huds.size();
		result += particles.size();
		result += others.size();
		
		return result;
	}

	
	public ArrayList<GameObject> getGameObjects() {
		return objects;
	}

	public ArrayList<Hud> getHuds() {
		return huds;
	}

	public ArrayList<ParticleEmmiter> getParticles() {
		return particles;
	}

	public ArrayList<GameComponent> getOthers() {
		return others;
	}
	public ArrayList<GameComponent> getScene() {
		ArrayList<GameComponent> res = new ArrayList<GameComponent>(others);
		res.addAll(objects);
		res.addAll(huds);
		res.addAll(particles);
		return res;
	}
	
	
}
