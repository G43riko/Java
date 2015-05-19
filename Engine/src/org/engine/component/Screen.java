package org.engine.component;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;

import org.engine.rendering.RenderingEngine;
import org.engine.rendering.ToFrameBufferRendering;
import org.engine.rendering.material.Texture2D;
import org.engine.rendering.model.Model;
import org.engine.utils.Loader;
import org.engine.utils.Maths;
import org.lwjgl.opengl.Display;

public class Screen {
	private final static Model MODEL = Loader.loadToVAO(new float[]{-1,1,-1,-1,1,1,1,-1});
	private Texture2D texture;
	
	private GVector2f position;
	private GVector2f scale;
	
	private ToFrameBufferRendering frameRenderer;
	
	//CONSTRUCTORS
	
	public Screen() {
		this(new GVector2f(Display.getWidth(), Display.getHeight()));
	}
	
	public Screen(GVector2f resolution) {
		frameRenderer = new ToFrameBufferRendering(resolution);
		texture = frameRenderer.getTexture();
		position = new GVector2f();
		scale = new GVector2f(1,1);
	}
	
	//OTHERS
	
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderScreen(this);
	}

	public void cleanUp(){
		frameRenderer.cleanUp();
	}
	
	//GETTERS

	public GMatrix4f getTransformationMatrix() {
		return Maths.MatrixToGMatrix(Maths.createTransformationMatrix(position, scale));
	}

	public Model getModel(){
		return MODEL;
		
	}
	
	public Texture2D getTexture() {
		return texture;
	}

	//SETTERS
	
	public void setTexture(Texture2D texture) {
		this.texture = texture;
	}

	public void startRenderToScreen() {
		frameRenderer.startRenderToFrameBuffer();
	}

	public void stopRenderToScreen() {
		frameRenderer.stopRenderToFrameBuffer();
	}
}
