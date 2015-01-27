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
//		double time = System.currentTimeMillis();
		RenderingEngine.numOfTriangels = 0;
		RenderingEngine.numOfRenderedBoxSides = 0;
		renderingEngine.render(root);
//		System.out.println("vyrenderovalo sa "+RenderingEngine.numOfTriangels+" polygonov");
//		System.out.println("vyrenderovalo sa "+RenderingEngine.numOfRenderedBoxSides+" strán kociek");
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
