package game;

import game.object.GameObject;
import game.physics.Enviroment;
import game.physics.colliders.BasicCollider;
import game.physics.colliders.SphereCollider;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class GameObjectPhysics extends GameObject{
	private BasicCollider collider = new SphereCollider(this,1);
	private float odrazivost = 0.8f;
	private float weight = 1;
	private GVector3f direction = new GVector3f();
	
	//CONSTRUCTORS
	
	public GameObjectPhysics(Model model) {
		super(model);
		weight = (float)Math.random()*2;
		setScale(new GVector3f((float)Math.random()+0.5f));
	}
	
	//OVERRIDES
	
	public void update() {
		move(direction);
		direction = direction.add(Enviroment.GRAVITY.mul(weight));
		collider.checkBorders(true);
		
	}

	//OTHERS
	
	//GETTERS
	
	public BasicCollider getCollider() {
		return collider;
	}

	//SETTERS
	
	public void setCollider(BasicCollider collider) {
		this.collider = collider;
	}

	public void setDirection(GVector3f direction) {
		this.direction = direction;
	}

	public GVector3f getDirection() {
		return direction;
	}

	public float getOdrazivost() {
		return odrazivost;
	}
	
}
