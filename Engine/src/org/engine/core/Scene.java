package org.engine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.engine.component.GameComponent;

public class Scene {
	private ArrayList<GameComponent> scene = new ArrayList<GameComponent>();
	
	//ADDERS
	
	public void add(GameComponent gameComponent){
		scene.add(gameComponent);
	}
	
	public void addAll(ArrayList<GameComponent> gameComponents){
		scene.addAll(gameComponents);
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
