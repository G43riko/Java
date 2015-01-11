package GameEngine.core;

import GameEngine.rendering.RenderingEngine;

 
public abstract class Game {
	private GameObject root; 
	
	public void init(){
	};
	
	public void input(float delta){
		getRootObject().inputAll(delta);
	};
	
	public void update(float delta){
		getRootObject().updateAll(delta);
	};
	
	public void addObject(GameObject object){
		getRootObject().addChild(object);
	}
	
	public void render(RenderingEngine renderingEngine){
		renderingEngine.render(root);
	}
	
	
	private GameObject getRootObject(){
		if(root == null){
			root = new GameObject();
		}
		return root;
	}

	public void setEngine(CoreEngine engine) {
		getRootObject().setEngine(engine);
		
	}
}

