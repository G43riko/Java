package com.voxel.component.light;

import glib.util.vector.GVector3f;

import com.voxel.component.GameComponent;
import com.voxel.core.CoreEngine;
import com.voxel.rendering.shader.Shader;

public class BaseLight extends GameComponent{
	private GVector3f color;
	private float intensity;
	private Shader shader;
	
	public BaseLight(GVector3f color, float intensity){
		this.color= color;
		this.intensity = intensity;
	}

	public GVector3f getColor() {
		return color;
	}
	
	public Shader getShader() {
		return shader;
	}
	
	public void addToEngine(CoreEngine engine){
		engine.getRenderingEngine().addLight(this);
	};
	
	public void setShader(Shader shader){
		this.shader = shader;
	}

	public void setColor(GVector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
}
