package com.voxel.component;

import com.voxel.core.CoreEngine;
import com.voxel.core.GameObject;
import com.voxel.core.Transform;
import com.voxel.rendering.RenderingEngine;
import com.voxel.rendering.shader.Shader;

public abstract class GameComponent {
	private GameObject parent;
	
	public void render(Shader shader, RenderingEngine renderingEngine) {}

	public void update(float delta) {}

	public void input() {}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public void addToRenderingEngine(RenderingEngine rengeringEngine){}

	public void addToEngine(CoreEngine engine) {};
	
	public Transform getTransform(){
		return parent.getTransform();
	}
}
