package game.physics;

import glib.util.vector.GVector3f;

public class Collisions {
	public static boolean SphereSphereCollision(GVector3f posA, float radiusA, GVector3f posB, float radiusB){
		return posA.dist(posB) < radiusA + radiusB;
	}
}
