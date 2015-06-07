package glib.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Scene<T> {
private ArrayList<T> scene = new ArrayList<T>();
	
	//ADDERS
	
	public void add(T gameComponent){
		scene.add(gameComponent);
	}
	
	public void addAll(ArrayList<T> gameComponents){
		scene.addAll(gameComponents);
	}
	
	//REMOVERS
	
	public void remove(T gameComponent){
		scene.remove(gameComponent);
	}
	
	public void removeAll(List<T> gameComponents){
		scene.removeAll(gameComponents);
	}
	
	public void removeAll(Predicate<? super T> condition){
		scene.removeAll(scene.stream().filter(condition).collect(Collectors.toList()));
	}
	
	//GETTERS
	
	public int getSize(){
		return scene.size();
	}
	
	public ArrayList<T> getScene(){
		return new ArrayList<T>(scene);
	}
}
