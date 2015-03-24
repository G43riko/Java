package game.entity;

import game.Line;
import game.component.GameComponent;
import game.rendering.RenderingEngine;
import game.rendering.model.Model;
import game.util.Loader;
import game.world.Block;
import game.world.World;
import glib.util.vector.GVector3f;

public class Bullet extends Line{
	private GVector3f direction;
	private int life = -1;
	private boolean dead;
	private World world;
	private GameComponent parent;
	 
	//CONSTRUCTORS
	
	public Bullet(GVector3f a, GVector3f b, World world, GameComponent parent) {
		super(a,b);
		this.world = world;
		this.parent = parent;
		life = 300;
		direction = b.sub(a).Normalized();
	}

	//OVERRIDES
	
	public String toString(){
		return getPosition().toString();
	}
	
	public void update(){
		move(direction);
		if(life > 0){
			life--;
			Block b = world.getBlock(getPosition());
			if(b != null && b.getBlockType() > 0 && b.isClickable()){
				
//				world.remove(b);
				dead = true;
			}
			if(parent.getType() != GameComponent.PLAYER)
				if(world.getCamera().getPosition().dist(getPosition()) < (Block.WIDTH+Block.HEIGHT+Block.DEPTH))
					System.out.println("náraz "+world.getCamera().getPosition().dist(getPosition()));
		}
		if(life == 0)
			dead = true;
	}
	
	//GETTERS
	
	public boolean isDead() {
		return dead;
	}

	public void setColor(GVector3f color) {
		this.color = color;
	}
}
