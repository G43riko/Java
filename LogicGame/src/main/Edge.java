package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	public static int stroke = 5;
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
	
	public Point getA(){
		return a;
	}
	
	public Point getB(){
		return b;
	}

	public boolean clickIn(int x, int y) {
		if(x<Math.max(a.getX()+Point.size/2, b.getX()+Point.size/2)&&
		   x>Math.min(a.getX()+Point.size/2, b.getX()+Point.size/2)&&
		   y<Math.max(a.getY()+Point.size/2, b.getY()+Point.size/2)&&
		   y>Math.min(a.getY()+Point.size/2, b.getY()+Point.size/2)){
			
			if(pointToLineDistance(a, b, x, y)<Edge.stroke){
				return true;
			}
		}
		return false;
	}
	
	public double pointToLineDistance(Point A, Point B, int x, int y) {
		double normalLength = Math.sqrt((B.getX()-A.getX())*(B.getX()-A.getX())+(B.getY()-A.getY())*(B.getY()-A.getY()));
	    return Math.abs((x-A.getX()-Point.size/2)*(B.getY()-A.getY())-(y-A.getY()-Point.size/2)*(B.getX()-A.getX()))/normalLength;
	}
	
	public void setA(Point a) {
		this.a = a;
	}

	public void setB(Point b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Edge [a=" + a + ", b=" + b + "]";
	}
}
