package prototypeGameEngine.core;

import prototypeGameEngine.RenderEngine;

public abstract class Game {
private RenderEngine renderer = null;
	
	public void init(){};
	
	public void input(float delta){};
	
	public void update(float delta){};
	
	public void render(){
		getRenderEngine().render();
	};
	
	public RenderEngine getRenderEngine(){
		if(renderer == null){
			renderer = new RenderEngine();
		}
		return renderer;
	}
}
