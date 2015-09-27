package org.engine.app;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.core.Interactable;
import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector2f;


public interface GameAble extends Interactable{
//	public RenderingEngine getRenderingEngine();
	public Camera getCamera();
	public GVector2f getCanvasSize();
	public void addToScene(GameComponent component);
}
