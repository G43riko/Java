package com.voxel.component;

import com.voxel.core.CoreEngine;
import com.voxel.core.GameObject;
import com.voxel.render.RenderingEngine;

public abstract class GameComponent {
	private GameObject parent;
	
	public void render() {}

	public void update(float delta) {}

	public void input() {}

	public void setParent(GameObject gameObject) {}

	public void addToRenderingEngine(RenderingEngine rengeringEngine){}

	public void addToEngine(CoreEngine engine) {};
}
