package org.voxel.main;

import org.engine.component.Camera;
import org.engine.world.Plane;
import org.strategy.rendering.RenderingEngineStrategy;
import org.voxel.core.CoreVoxel;

public class MainVoxel extends CoreVoxel{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		setRenderingEngine(new RenderingEngineStrategy());
		setCamera(new Camera());
		setMousePicker(getCamera());
		
		
		addToScene(new Plane());
	}

}
