package com.voxel.core;

import javax.swing.JFrame;

import com.voxel.rendering.RenderingEngine;

public class Game extends JFrame{
	private GameObject root;
	private static final long serialVersionUID = 1L;
	
	public void init(){}
	
	public void input(){
		getRootObject().inputAll();
	}

	public void update(float delta){
		getRootObject().updateAll(delta);
	}
	
	public void addObject(GameObject object){
		getRootObject().addChild(object);
	}
	
	public void render(RenderingEngine renderingEngine){
		double time = System.currentTimeMillis();
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
