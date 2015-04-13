package org.engine.object;

import org.engine.component.GameComponent;
import org.engine.rendeing.RenderingEngine;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.model.Model;

public class GameObject extends GameComponent{
	protected Model model;
	protected Material material;

	public GameObject(Model model){
		this(model,new Material("texture.png"));
	}
	
	public GameObject(Model model, Material material) {
		super(GameComponent.GAME_OBJECT);
		this.model = model;
		this.material = material;
	}
	
	//OVERRIDES
	
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderObject(this);
	}
	
	//GETTERS
	
	public Model getModel() {
		return model;
	}
	
	public Material getMaterial() {
		return material;
	}
}
