package org.engine.physics;

import glib.math.GColision;

import org.engine.physics.colliders.BoxCollider;

public class Collisions {
	public static boolean collision(BoxCollider a, BoxCollider b){
		return GColision.boxBoxCollision(a.getParent().getPosition(), a.getSize(), b.getParent().getPosition(), b.getSize());
	}
}
