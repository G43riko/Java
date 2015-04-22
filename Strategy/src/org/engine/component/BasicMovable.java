package org.engine.component;

import org.lwjgl.input.Keyboard;
import org.strategy.component.CameraStrategy;

public class BasicMovable extends GameComponent{

	protected int forwardKey = Keyboard.KEY_W;
	protected int backKey = Keyboard.KEY_S;
	protected int leftKey = Keyboard.KEY_A;
	protected int rightKey = Keyboard.KEY_D;

	protected Camera camera;
	
	public BasicMovable() {
		super(GameComponent.MOVABLE);
	}

}
