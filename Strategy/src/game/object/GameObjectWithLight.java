package game.object;

import java.util.ArrayList;

import game.component.GameComponent;
import game.light.PointLight;
import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.rendering.model.Model;

public class GameObjectWithLight extends GameComponent{
	protected Model model;
	protected Material material;
	protected ArrayList<PointLight> lights = new ArrayList<PointLight>();
	
	protected boolean fakeLight;
	
	public GameObjectWithLight(Model model, Material material, PointLight... lights) {
		super(GameComponent.GAME_OBJECT);
		this.model = model;
		this.material = material;
		
		for(PointLight p: lights)
			this.lights.add(p);
	}
	
	//OVERRIDES
	
	public void render(RenderingEngine renderingEngine){
		renderingEngine.renderObjectWithLight(this);
	}

	//GETTERS
	
	public Model getModel() {
		return model;
	}

	public Material getMaterial() {
		return material;
	}

	public ArrayList<PointLight> getLights() {
		return lights;
	}
	
	public boolean isFakeLight() {
		return fakeLight;
	}

	//SETTERS
	
	public void setFakeLight(boolean fakeLight) {
		this.fakeLight = fakeLight;
	}


}
