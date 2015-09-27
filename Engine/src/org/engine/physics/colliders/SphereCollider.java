package org.engine.physics.colliders;

import org.engine.component.object.GameObjectPhysics;
import org.engine.physics.BasicCollider;

public class SphereCollider extends BasicCollider{
	private float radius;
	private float height;
	
	public SphereCollider(GameObjectPhysics parent) {
		super(parent);
	}

}
