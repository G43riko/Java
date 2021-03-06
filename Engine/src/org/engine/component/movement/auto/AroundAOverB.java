package org.engine.component.movement.auto;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;

import glib.util.vector.GVector3f;

public class AroundAOverB extends GameComponent{
	private GVector3f pointA;
	private GVector3f pointB;
	private GameComponent object;
	private float speed;
	
	private float radius;
	private float angle;
	
	public AroundAOverB(GameAble parent, GVector3f pointA, GVector3f pointB, GameComponent object, float speed) {
		super(parent);
		this.pointA = pointA;
		this.pointB = pointB;
		this.object = object;
		this.speed = speed;
		
		radius = pointA.sub(pointB).getLength();
		angle = 0;
	}
	
	@Override
	public void update(float delta) {
		GVector3f pos = new GVector3f(Math.cos(Math.toRadians(angle)),0, Math.sin(Math.toRadians(angle))).mul(radius).add(pointA);
		pos.setY(object.getPosition().getY());
		object.setPosition(pos);
		angle++;
	}
}
