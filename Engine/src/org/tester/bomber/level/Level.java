package org.tester.bomber.level;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector2f;

public class Level extends GameComponent{
	private Map map;
	public Level(GameAble parent) {
		super(parent);
		map = new Map(parent, new GVector2f(20, 20));
	}
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		map.render(renderingEngine);
	}

}
