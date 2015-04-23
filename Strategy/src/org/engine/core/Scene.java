package org.engine.core;

import java.util.ArrayList;

import org.engine.component.GameComponent;
import org.engine.gui.Hud;
import org.engine.object.GameObject;
import org.engine.particles.ParticleEmmiter;
import org.engine.water.Water;

public class Scene {
	private ArrayList<GameObject> objects;
	private ArrayList<Hud> huds;
	private ArrayList<ParticleEmmiter> particles;
	private ArrayList<GameComponent> others;
	private ArrayList<Water> waters;
	
	//CONSTRUCTORS
	
	public Scene(){
		objects = new ArrayList<GameObject>();
		huds = new ArrayList<Hud>();
		particles = new ArrayList<ParticleEmmiter>();
		others = new ArrayList<GameComponent>();
		waters = new ArrayList<Water>();
	}
	
	//ADDERS
	
	public void add(GameComponent c){
		if(c instanceof GameObject)
			objects.add((GameObject)c);
		else if(c instanceof ParticleEmmiter)
			particles.add((ParticleEmmiter)c);
		else if(c instanceof Hud)
			huds.add((Hud)c);
		else if(c instanceof Water)
			waters.add((Water)c);
		else
			others.add(c);
	}

	//REMOVERS
	
	public void remove(GameComponent e){
		if(objects.contains(e))
			objects.remove(e);
		else if(particles.contains(e))
			particles.remove(e);
		else if(huds.contains(e))
			huds.remove(e);
		else if(waters.contains(e))
			waters.remove(e);
		else if(others.contains(e))
			others.remove(e);
	}
	
	public void removeAll(ArrayList<GameComponent> e){
		huds.removeAll(e);
		particles.removeAll(e);
		objects.removeAll(e);
		others.removeAll(e);
		waters.removeAll(e);
	}
	
	//GETTERS
	
	public int getSize(){
		int result = objects.size();
		
		result += huds.size();
		result += particles.size();
		result += others.size();
		result += waters.size();
		
		return result;
	}

	
	public ArrayList<GameObject> getObjects() {
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

	public ArrayList<Water> getWaters() {
		return waters;
	}
	
	public ArrayList<GameComponent> getScene() {
		ArrayList<GameComponent> res = new ArrayList<GameComponent>(others);
		res.addAll(objects);
		res.addAll(huds);
		res.addAll(particles);
		res.addAll(waters);
		return res;
	}

	
	
}
