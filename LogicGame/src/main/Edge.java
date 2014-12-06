package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	public int stroke = 5;
	private Point a,b;
	private int x1,x2;
	private int y1,y2;
	
	public Edge(Point a, Point b){
		this.a = a;
		this.b = b;
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.drawLine(a.getX()+Point.size/2, a.getY()+Point.size/2, b.getX()+Point.size/2, b.getY()+Point.size/2);
	}
}
