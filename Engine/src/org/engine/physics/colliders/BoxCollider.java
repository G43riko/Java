package org.engine.physics.colliders;

import glib.util.vector.GVector3f;

import org.engine.object.GameObjectPhysics;
import org.engine.physics.BasicCollider;

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
