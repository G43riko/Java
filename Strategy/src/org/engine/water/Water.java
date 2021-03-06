package org.engine.water;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;
import org.engine.rendeing.ToFrameBufferRendering;
import org.engine.rendeing.WaterFrameBuffers;
import org.engine.rendeing.model.Model;
import org.engine.util.Loader;
import org.engine.util.Maths;
import org.strategy.rendering.RenderingEngineStrategy;

public class Water extends GameComponent{
	private static Model model;
//	private WaterFrameBuffers fbos;
	private ToFrameBufferRendering fbos;

	//CONSTRUCTORS
	
	public Water(GVector3f position, Loader loader) {
		super(position, GameComponent.WATER);
//		this.fbos = fbos;
		fbos = new ToFrameBufferRendering();
		setUpVAO(loader);
	}
	
	//OVERRIDES
	
	public void render(RenderingEngineStrategy renderingEngine) {
		renderingEngine.renderWater(this);
	}
	
	//OTHERS
	
	private void setUpVAO(Loader loader) {
        float[] vertices = { -1, -1, -1, 1, 1, -1, 1, -1, -1, 1, 1, 1 };
        model = loader.loadToVAO(vertices);
    }
	
	//GETTERS
	
	public static Model getModel() {
		return model;
	}
	
	public GMatrix4f getTransformationMatrix() {
		return Maths.MatrixToGMatrix(Maths.createTransformationMatrix(getPosition(), new GVector3f(), getScale()));
	}

	public ToFrameBufferRendering getFbos() {
		return fbos;
	}
}
