package org.engine.core;

import java.util.ArrayList;
import java.util.List;

import org.engine.component.GameComponent;
import org.engine.gui.Hud;
import org.engine.object.GameObject;
import org.engine.particles.ParticleEmmiter;
import org.engine.water.Water;

public class Scene {
	private ArrayList<GameComponent> scene = new ArrayList<GameComponent>();

	public void add(GameComponent g) {
		scene.add(g);
	}
	
	public void remove(GameComponent g){
		scene.remove(g);
	}
	
	public void removeAll(List<GameComponent> g){
		scene.removeAll(g);
	}
	
	public int getSize(){
		return scene.size();
	}
	
	public ArrayList<GameComponent> getScene(){
		return new ArrayList<GameComponent>(scene);
	}
	
	public ArrayList<GameObject> getObjects(){
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		scene.stream().filter(a -> a instanceof GameObject).forEach(a -> result.add((GameObject)a));
		return result;
	}
	
	public ArrayList<Water> getWaters(){
		ArrayList<Water> result = new ArrayList<Water>();
		scene.stream().filter(a -> a instanceof Water).forEach(a -> result.add((Water)a));
		return result;
	}
	
	public ArrayList<ParticleEmmiter> getParticles(){
		ArrayList<ParticleEmmiter> result = new ArrayList<ParticleEmmiter>();
		scene.stream().filter(a -> a instanceof ParticleEmmiter).forEach(a -> result.add((ParticleEmmiter)a));
		return result;
	}
	
	public ArrayList<Hud> getHuds(){
		ArrayList<Hud> result = new ArrayList<Hud>();
		scene.stream().filter(a -> a instanceof Hud).forEach(a -> result.add((Hud)a));
		return result;
	}
	
	public ArrayList<GameComponent> getOthers(){
		ArrayList<GameComponent> result = new ArrayList<GameComponent>();
		scene.stream().filter(a -> !(a instanceof GameObject) &&
								   !(a instanceof Water) &&
								   !(a instanceof ParticleEmmiter) &&
								   !(a instanceof Hud)).forEach(a -> result.add((GameComponent)a));
		return result;
	}
	
//	private ArrayList<GameObject> objects;
//	private ArrayList<Hud> huds;
//	private ArrayList<ParticleEmmiter> particles;
//	private ArrayList<GameComponent> others;
//	private ArrayList<Water> waters;
//	
//	//CONSTRUCTORS
//	
//	public Scene(){
//		objects = new ArrayList<GameObject>();
//		huds = new ArrayList<Hud>();
//		particles = new ArrayList<ParticleEmmiter>();
//		others = new ArrayList<GameComponent>();
//		waters = new ArrayList<Water>();
//	}
//	
//	//ADDERS
//	
//	public void add(GameComponent c){
//		if(c instanceof GameObject)
//			objects.add((GameObject)c);
//		else if(c instanceof ParticleEmmiter)
//			particles.add((ParticleEmmiter)c);
//		else if(c instanceof Hud)
//			huds.add((Hud)c);
//		else if(c instanceof Water)
//			waters.add((Water)c);
//		else
//			others.add(c);
//	}
//
//	//REMOVERS
//	
//	public void remove(GameComponent e){
//		if(objects.contains(e))
//			objects.remove(e);
//		else if(particles.contains(e))
//			particles.remove(e);
//		else if(huds.contains(e))
//			huds.remove(e);
//		else if(waters.contains(e))
//			waters.remove(e);
//		else if(others.contains(e))
//			others.remove(e);
//	}
//	
//	public void removeAll(ArrayList<GameComponent> e){
//		huds.removeAll(e);
//		particles.removeAll(e);
//		objects.removeAll(e);
//		others.removeAll(e);
//		waters.removeAll(e);
//	}
//	
//	//GETTERS
//	
//	public int getSize(){
//		int result = objects.size();
//		
//		result += huds.size();
//		result += particles.size();
//		result += others.size();
//		result += waters.size();
//		
//		return result;
//	}
//
//	
//	public ArrayList<GameObject> getObjects() {
//		return objects;
//	}
//
//	public ArrayList<Hud> getHuds() {
//		return huds;
//	}
//
//	public ArrayList<ParticleEmmiter> getParticles() {
//		return particles;
//	}
//
//	public ArrayList<GameComponent> getOthers() {
//		return others;
//	}
//
//	public ArrayList<Water> getWaters() {
//		return waters;
//	}
//	
//	public ArrayList<GameComponent> getScene() {
//		ArrayList<GameComponent> res = new ArrayList<GameComponent>(others);
//		res.addAll(objects);
//		res.addAll(huds);
//		res.addAll(particles);
//		res.addAll(waters);
//		return res;
//	}
}
