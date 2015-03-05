package com.g43riko.object;

import com.g43riko.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public abstract class GameObject {
	private GVector3f pos;
	private GVector3f rot;
	private GVector3f scale;
	
	public GameObject(){
		pos = new GVector3f();
		rot = new GVector3f();
		scale = new GVector3f(1);
	}
	
	
	public void input(){
		
	}
	
	public void render(RenderingEngine renderingEngine){
		
	}

	public void update(){
		
	}
}
