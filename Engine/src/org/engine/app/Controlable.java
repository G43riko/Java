package org.engine.app;

import org.engine.component.movement.BasicMovement;
import org.engine.core.Window;
import org.engine.rendering.RenderingEngine;

public interface Controlable {
	public void exit();
	public void newGame();
	public void initGame();
	public RenderingEngine getRenderingEngine();
	public Window getWindow();
	public GameAble getGame();
//	public void setMovementType(BasicMovement b);
}
