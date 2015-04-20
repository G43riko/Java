package org.engine.physics.collider;

import org.engine.object.GameObjectPhysics;
import org.engine.physics.Enviroment;

import glib.math.GColision;
import glib.util.vector.GVector3f;

public class SphereCollider extends BasicCollider{
	private float radius;
	
	//CONSTRUCTORS
	
	public SphereCollider(GameObjectPhysics object){
		this(object,object.getScale().average());
	}
	
	public SphereCollider(GameObjectPhysics object, float radius){
		super(object);
		this.radius = radius;
	}
	
	//OTHERS
	
	public boolean collision(GameObjectPhysics object){
		if(object.getCollider() instanceof SphereCollider){
//			return Collisions.SphereSphereCollision(object.getPosition(), ((SphereCollider)object.getCollider()).getRadius(), this.object.getPosition(), radius);
			return GColision.sphereSphereCollision(object.getPosition(), ((SphereCollider)object.getCollider()).getRadius(), this.object.getPosition(), radius);
		}
		return false;
		
	}

	public boolean checkBorders(boolean changeDirection) {
		GVector3f pos = object.getPosition();
		if(pos.getX() - radius < Enviroment.MIN_X){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(-object.getOdrazivost(),1,1)));
			}
			object.getPosition().setX(Enviroment.MIN_X + radius);
			return true;
		}
		
		if( pos.getX() +radius > Enviroment.MAX_X){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(-object.getOdrazivost(),1,1)));
			}
			object.getPosition().setX(Enviroment.MAX_X - radius);
			return true;
		}
		
		
		
		if(pos.getY() - radius < Enviroment.MIN_Y){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(1,-object.getOdrazivost(),1)));
			}
			object.getPosition().setY(Enviroment.MIN_Y + radius);
			return true;
		}
		
		if( pos.getY() +radius > Enviroment.MAX_Y){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(1,-object.getOdrazivost(),1)));
			}
			object.getPosition().setY(Enviroment.MAX_Y - radius);
			return true;
		}
		
		
		if(pos.getZ() - radius < Enviroment.MIN_Z){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(1,1,-object.getOdrazivost())));
			}
			object.getPosition().setZ(Enviroment.MIN_Z + radius);
			return true;
		}
		
		if( pos.getZ() +radius > Enviroment.MAX_Z){
			if(changeDirection){
				object.setDirection(object.getDirection().mul(new GVector3f(1,1,-object.getOdrazivost())));
			}
			object.getPosition().setZ(Enviroment.MAX_Z - radius);
			return true;
		}
		
		return false;
	}

	//GETTERS
	
	public float getRadius() {
		return radius;
	}

	public float getDownDist() {
		return radius;
	}

	
	//SETTERS
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
}
