package Shapes;
import java.awt.Color;
import java.awt.Graphics;

import Goniometry.*;


public class Rectangle2f {
	private float w,h;
	private Vector2f pos;
	private Color edge=Color.BLACK,ground=Color.WHITE;
	private Point2d[] points;
	private Line2d[] lines;
	
	public Rectangle2f(float x, float y, float w, float h){
		this(new Vector2f(x,y),w,h);
		
	};
	public Rectangle2f(Vector2f pos, float w, float h){
		this(pos,w,h,new Color(155,155,155));
	}
	
	public Rectangle2f(Vector2f pos, float w, float h,Color color){
		this.pos = pos;
		this.w=w;
		this.h=h;
		this.ground = color;
	}

	public void draw(Graphics g){
		g.setColor(this.edge);
		g.drawRect((int)this.pos.getX(), (int)this.pos.getY(), (int)this.w, (int)this.h);
		
		g.setColor(this.ground);
		g.fillRect((int)this.pos.getX(), (int)this.pos.getY(), (int)this.w, (int)this.h);
	};
	
	public float getContent(){
		return w*h;
	};
	
	public float getCircuit(){
		return 2*w+2*h;
	};
	
	public void setEdge(Color farba){
		this.edge=farba;
	};
	
	public void setGround(Color farba){
		this.ground=farba;
	};
	
	public void setPos(Vector2f pos){
		this.pos = pos;
	};

	public float getWidth() {
		return w;
	};

	public void setWidth(float w) {
		this.w = w;
	};

	public float getHeight() {
		return h;
	};

	public void setHeight(float h) {
		this.h = h;
	};
}
