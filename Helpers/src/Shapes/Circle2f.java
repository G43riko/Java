package Shapes;
import java.awt.Color;
import java.awt.Graphics;

import Goniometry.Vector2f;


public class Circle2f {
	private float r;
	private Vector2f pos;
	private Color edge=Color.BLACK,ground=Color.WHITE;
	
	public Circle2f(float x, float y, float radius){
		this(new Vector2f(x,y),radius);
	};
	
	public Circle2f(Vector2f pos, float radius){
		this(pos,radius,new Color(155,155,155));
	};
	public Circle2f(Vector2f pos, float radius,Color color){
		this.pos = pos;
		this.r = radius;
		this.ground = color;
	}
	
	public void setEdge(Color farba){
		this.edge=farba;
	};
	
	public void setGround(Color farba){
		this.ground=farba;
	};
	
	public void setPos(Vector2f pos){
		this.pos = pos;
	};

	public double getRadius() {
		return r;
	};

	public void setRadius(float r) {
		this.r = r;
	};
	
	public void draw(Graphics g){
		g.setColor(this.edge);
		g.drawArc((int)this.pos.getX(), (int)this.pos.getY(), (int)this.r, (int)this.r, 0, 360);
		
		g.setColor(this.ground);
		g.fillArc((int)this.pos.getX(), (int)this.pos.getY(), (int)this.r, (int)this.r, 0, 360);
	};
	
	public float getContent(){
		return (float)Math.PI*r*r;
	}
	public float getCircuit(){
		return (float)Math.PI*r*2;
	}
	
	
}
