package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import Goniometry.*;


public class Rectangle2d {
	private double x,y,w,h;
	private Color edge=Color.BLACK,ground=Color.WHITE;
	private Point2d[] points;
	private Line2d[] lines;
	
	public Rectangle2d(double x, double y, double w, double h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	};
	
	public void setEdge(Color farba){
		this.edge=farba;
	};
	
	public void setGround(Color farba){
		this.ground=farba;
	};
	
	public void setPos(double x, double y){
		this.x=x;
		this.y=y;
	};

	public double getWidth() {
		return w;
	};

	public void setWidth(double w) {
		this.w = w;
	};

	public double getHeight() {
		return h;
	};

	public void setHeight(double h) {
		this.h = h;
	};

	public double getX() {
		return x;
	};

	public double getY() {
		return y;
	};
	
	public void draw(Graphics g){
		g.setColor(this.edge);
		g.drawRect((int)this.x, (int)this.y, (int)this.w, (int)this.h);
		
		g.setColor(this.ground);
		g.fillRect((int)this.x, (int)this.y, (int)this.w, (int)this.h);
	};
	
}
