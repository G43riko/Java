package org.engine.core;

import org.engine.rendering.RenderingEngine;

public interface Interactable {
	public default void render(RenderingEngine renderingEngine){};
	public default void input(){};
	public default void update(float delta){};
	public default void cleanUp(){};
}
