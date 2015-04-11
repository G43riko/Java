package game.physics.colliders;

import game.GameObjectPhysics;

public abstract class BasicCollider {
	protected GameObjectPhysics object;
	
	public BasicCollider(GameObjectPhysics object) {
		this.object = object;
	}

	public abstract float getDownDist();
	
	public abstract boolean checkBorders(boolean changeDirection);
}
