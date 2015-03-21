package game.entity;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.model.Model;
import game.util.Loader;
import game.world.Block;
import game.world.World;
import glib.util.vector.GVector3f;

public class Bullet extends GameObject{
	private GVector3f a;
	private GVector3f b;
	private GVector3f direction;
	private int life = -1;
	private boolean dead;
	private Model model;
	private World world;
	
	private GVector3f color;
	 
	//CONSTRUCTORS
	
	public Bullet(GVector3f a, GVector3f b, World world) {
		super(GameObject.LINE);
		this.a = a;
		this.b = b;
		this.model = createModel();
		this.color = new GVector3f();
		this.world = world;
		life = 300;
		direction = b.sub(a).Normalized();
	}

	//CREATORS
	
	private Model createModel(){
		float[] position = new float[]{a.getXi(), a.getYi(), a.getZi(),
									   b.getXi(), b.getYi(), b.getZi()};

		int[] indices = new int[]{0, 1};
		return new Loader().loadToVAO(position, indices);
	}
	
	//OVERRIDES
	
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderLine(this);
	}
	
	public String toString(){
		return getPosition().toString();
	}
	
	public void update(){
		move(direction);
		if(life > 0){
			life--;
			Block b = world.getBlock(this.b.add(getPosition()));
			if(b != null && b.getBlockType() > 0 && b.isClickable()){
				
				world.remove(b);
				dead = true;
			}
		}
		if(life == 0)
			dead = true;
	}
	
	//GETTERS
	
	public Model getModel() {
		return model;
	}

	public boolean isDead() {
		return dead;
	}

	public GVector3f getColor() {
		return color;
	}

	public void setColor(GVector3f color) {
		this.color = color;
	}
}
