package org.engine.physics.colliders;

import org.engine.component.object.GameObjectPhysics;
import org.engine.physics.BasicCollider;

import glib.util.vector.GVector3f;

public class BoxCollider extends BasicCollider{
//	private float width;
//	private float height;
//	private float depth;
	private GVector3f size;
	
	public BoxCollider(GameObjectPhysics parent) {
		super(parent);
	}

	public GVector3f getSize(){
		return size;
	}
	
}
