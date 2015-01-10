package game.towers;

import java.awt.Color;
import java.awt.Graphics2D;

import game.core.Vector2f;
import game.units.Unit;

public class Bullet {
	private Unit target;
	private Vector2f pos;
	private float speed;
	private Color color;
	private int radius = 5;
	
	public Bullet(Unit target,Vector2f pos){
		speed = target.getSpeed()*3;
		this.target = target;
		this.pos = pos;
		color = Color.RED;
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(this.color);
		g2.fillArc((int)(pos.getX()-radius), (int)(pos.getY()-radius), (int)radius*2, (int)radius*2, 0, 360);
	}
	
	public boolean move(){
		Vector2f temp = target.getPos().sub(pos);
		temp.normalize();
		temp.mul(speed);
		this.pos.add(temp);
		if(!Unit.units.contains(target)){
			return true;
		}
		if(pos.dist(target.getPos())<target.getRadius()){
			return true;
		}
		return false;
	}

	public Unit getTarget() {
		return target;
	}
}
