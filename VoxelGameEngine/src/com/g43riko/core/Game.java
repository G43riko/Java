package com.g43riko.core;

import com.g43riko.voxel.RenderEngine;


public abstract class Game {
	private RenderEngine renderer;
	private GameObject root; 
	
	public void init(){
	};
	
	public void input(float delta){
		getRootObject().inputAll(delta);
	};
	
	public void update(float delta){
		getRootObject().updateAll(delta);
	};
	
	public void addChild(GameObject child){
		getRootObject().addChild(child);
	}
	
	public void render(){
		getRenderEngine().render();
	};
	
	public RenderEngine getRenderEngine(){
		if(renderer == null){
			renderer = new RenderEngine();
		}
		return renderer;
	}
	
	private GameObject getRootObject(){
		if(root == null){
			root = new GameObject();
		}
		return root;
	}
}
