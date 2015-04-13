package org.engine.physics.colliders;

import org.physics.object.GameObjectPhysics;

public abstract class BasicCollider {
	protected GameObjectPhysics object;
	
	public BasicCollider(GameObjectPhysics object) {
		this.object = object;
	}

	public abstract float getDownDist();
	
	public abstract boolean checkBorders(boolean changeDirection);
}
