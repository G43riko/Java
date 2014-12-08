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
	private Color color;
	private List<Point> vertices = new ArrayList<Point>();
	private Color[] colors = new Color[]{new Color(0  ,0  ,0  ,255),
										 new Color(0  ,0  ,255,255),
										 new Color(0  ,255,0  ,255),
										 new Color(0  ,255,255,255),
										 new Color(255,0  ,0  ,255),
										 new Color(255,0  ,255,255),
										 new Color(255,255,0  ,255),
										 new Color(255,255,255,255)};
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		this.color = colors[(int)Math.floor(Math.random()*colors.length)];
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillArc(x, y, size, size, 0, 360);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.setColor(Color.black);
		g2.drawArc(x, y, size, size, 0, 360);
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
