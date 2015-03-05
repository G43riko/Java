package com.g43riko.core;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import com.g43riko.object.GameObject;
import com.g43riko.rendering.RenderingEngine;
import com.g43riko.rendering.shader.DefaultShader;

public abstract class CoreGame {
	private boolean isRunning;
	public abstract void init();
	private ArrayList<GameObject> scene;
	
	private RenderingEngine renderingEngine;	
	
	public CoreGame(){
		scene = new ArrayList<GameObject>();
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		for(GameObject g : scene){
			g.render(renderingEngine);
		}
		
		Window.render();
	}
	
	public void input(){
		for(GameObject g : scene){
			g.input();
		}
	}
	
	public void start(){
		isRunning = true;
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		while(isRunning && !Window.isCloseRequested()){
			input();
			update();
			render();
		}
	}
	
	public void update(){
		for(GameObject g : scene){
			g.update();
		}
	}
	
	public void cleanUp(){
		Window.cleanUp();
	}

	public void addToScene(GameObject g){
		scene.add(g);
	}
	
	public void createWindow(String title, int width, int height, int fps){
		Window.createWindow(title, width, height, fps);
	}

	public void setRenderingEngine(RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}
}
