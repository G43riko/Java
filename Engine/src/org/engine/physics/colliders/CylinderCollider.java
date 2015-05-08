package org.engine.physics.colliders;

import org.engine.object.GameObjectPhysics;
import org.engine.physics.BasicCollider;

public class CylinderCollider extends BasicCollider{
	private float radius;
	private float height;
	
	public CylinderCollider(GameObjectPhysics parent) {
		super(parent);
	}

}
