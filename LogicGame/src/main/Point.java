package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Point {
	public static int size = 20;
	private int x;
	private int y;
	private List<Point> vertices = new ArrayList<Point>();
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.fillArc(x, y, size, size, 0, 360);
//		for(Point p:vertices){
//			g2.setColor(Color.red);
//			g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//			g2.drawLine(p.getX()+size/2, p.getY()+size/2, x+size/2, y+size/2);
//		}
	}

	public void addPoint(Point p){
		this.vertices.add(p);
	}
	
	public boolean clickIn(float x, float y){
		float dist;
		dist=(x-getX())*(x-getX())+(y-getY())*(y-getY());
		dist=(float)Math.sqrt(dist);
		if(dist<Point.size){
			return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public List<Point> getVertices() {
		return vertices;
	}
}
