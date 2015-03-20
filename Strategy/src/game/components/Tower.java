package game.components;

import java.util.ArrayList;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class Tower extends GameObject{
	private Model model;
	private GameObject target;
	private ArrayList<Line> bullets = new ArrayList<Line>();
	private int shot;
	private int shotEveryNthFrame = 10;
	
	public Tower() {
		super(GameObject.TOWER);
		shot = 0;
	}
	
	public void update(){
		for(Line l : bullets){
			l.update();
		}
		shot++;
		if(target != null && shot == shotEveryNthFrame){
			shot = 0;
			GVector3f toTarget = target.getPosition().sub(getPosition()).div(20).randomize(1);
			bullets.add(new Line(getPosition(),getPosition().add(toTarget)));
		}
	}
	
	public void render(RenderingEngine renderingEngine){
		for(Line l : bullets)
			l.render(renderingEngine);
	}

	public void setTarget(GameObject target) {
		this.target = target;
	}

}
