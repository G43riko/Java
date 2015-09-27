package org.engine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.gui.Hud;
import org.engine.rendering.RenderingEngine;

public class Scene implements Interactable{
	private ArrayList<GameComponent> scene = new ArrayList<GameComponent>();
	private ArrayList<GameComponent> huds = new ArrayList<GameComponent>();
	private GameAble parent;
	//CONTRUCTORS
	
	public Scene(GameAble parent){
		this.parent = parent;
	}
	
	//ADDERS
	
	public void add(GameComponent gameComponent){
		if(gameComponent instanceof Hud)
			huds.add(gameComponent);
		else
			scene.add(gameComponent);
	}
	
	public void addAll(ArrayList<GameComponent> gameComponents){
		scene.addAll(gameComponents);
	}
	
	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		scene.stream().forEach(a -> a.render(renderingEngine));
		huds.stream().forEach(a -> a.render(renderingEngine));
	}
	
	@Override
	public void update(float delta) {
		scene.stream().forEach(a -> a.update(delta));
		huds.stream().forEach(a -> a.update(delta));
	}
	
	@Override
	public void input() {
		scene.stream().forEach(a -> a.input());
		huds.stream().forEach(a -> a.input());
	}
	
	//REMOVERS
	
	public void remove(GameComponent gameComponent){
		scene.remove(gameComponent);
	}
	
	public void removeAll(List<GameComponent> gameComponents){
		scene.removeAll(gameComponents);
	}
	
	public void removeAll(Predicate<? super GameComponent> condition){
		scene.removeAll(scene.stream().filter(condition).collect(Collectors.toList()));
	}
	
	//GETTERS
	
	public int getSize(){
		return scene.size();
	}
	
	public ArrayList<GameComponent> getScene(){
		return new ArrayList<GameComponent>(scene);
	}
}
