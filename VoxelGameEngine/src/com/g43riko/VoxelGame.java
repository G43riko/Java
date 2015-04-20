package com.g43riko;

import com.g43riko.core.CoreGame;
import com.g43riko.object.Shape;
import com.g43riko.rendering.RenderingEngine;

public class VoxelGame extends CoreGame{
	public void init() {
		createWindow("VoxelGame", 800, 600, 60);
		
		setRenderingEngine(new RenderingEngine());
		addToScene(new Shape());
		
	}
	
}
