package org.engine.physics;

import org.engine.object.GameObjectPhysics;

public abstract class BasicCollider {
	private GameObjectPhysics parent;

	public BasicCollider(GameObjectPhysics parent) {
		this.parent = parent;
	}

	public GameObjectPhysics getParent() {
		return parent;
	}
}
