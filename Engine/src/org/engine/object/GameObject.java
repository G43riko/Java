package org.engine.object;

import java.beans.ConstructorProperties;

import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;

public class GameObject extends GameComponent{
	private Material material;
	private Model model;
	private boolean useFakeLight;
	
	//CONSTRUCTORS
	
	public GameObject(Material material, Model model) {
		this.material = material;
		this.model = model;
	}

	//OVERRIDES
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderObject(this);
	}
	
	//GETTERS
	
	public Material getMaterial() {
		return material;
	}

	public Model getModel() {
		return model;
	}

	public boolean isUseFakeLight() {
		return useFakeLight;
	}
	
	//SETTERS

	public void setUseFakeLight(boolean useFakeLight) {
		this.useFakeLight = useFakeLight;
	}
}
