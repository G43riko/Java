package org.engine.component.movement.auto;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;

import glib.math.GMath;
import glib.util.vector.GVector3f;

public class AToB extends GameComponent{
	private GVector3f pointA;
	private GVector3f pointB;
	private GameComponent object;
	private float speed;
	
	private GVector3f center;
	private float dist;
	private boolean toA = true;
	private boolean linear = true;
	
	public AToB(GameAble parent, GVector3f pointA, GVector3f pointB, GameComponent object, float speed) {
		super(parent);
		this.pointA = pointA;
		this.pointB = pointB;
		this.object = object;
		this.speed = speed;
		
		center = GMath.center(pointA, pointB);		
		dist = pointA.sub(pointB).div(2).getLength();
	}
	
	private void toPoint(GVector3f point){
		GVector3f vecToA = point.sub(object.getPosition());
		
		if(vecToA.getLength() < speed){
			object.setPosition(point);
			toA = toA == false;
		}
		else{
			float colinear = 1;
			if(!linear)
				colinear = GMath.between(1 - center.sub(object.getPosition()).getLength()/dist, 0.4f, 1);
			
			GVector3f move = vecToA.Normalized().mul(speed * colinear);
			object.move(move);
		}
	}
	
	@Override
	public void update(float delta) {
		if(toA)
			toPoint(pointA);
		else
			toPoint(pointB);
	}

	//SETTERS
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setLinear(boolean linear) {
		this.linear = linear;
	}
}
