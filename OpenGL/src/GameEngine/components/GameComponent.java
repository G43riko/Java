package GameEngine.components;

import GameEngine.core.GameObject;
import GameEngine.core.Transform;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Shader;

public abstract class GameComponent {
	
	private GameObject parent;
	
	public void setGameObject(){};
	
	public void input(float delta){};
	
	public void update(float delta){};

	public void render(Shader shader, RenderingEngine renderingEngine){};
	
	public Transform getTransform(){
		return parent.getTransform();
	}
	
	public void setParent(GameObject parent){
		this.parent = parent;
	}
	
	public void addToRenderingEngine(RenderingEngine rengeringEngine){};
}
