package game.components;

import game.main.Loader;
import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class Line extends GameObject{
	private GVector3f a;
	private GVector3f b;
	private GVector3f direction;
	private int life = -1;
	private boolean dead;
	private Model model;
	
	private GVector3f color;
	 
	//CONSTRUCTORS
	
	public Line(GVector3f a, GVector3f b) {
		super(GameObject.LINE);
		this.a = a;
		this.b = b;
		this.model = createModel();
		this.color = new GVector3f(0, 0, 0);
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
