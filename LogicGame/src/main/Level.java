package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Level {

	private List<Point> vertices = new ArrayList<Point>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Level(){
		vertices.add(new Point(140,20));
		vertices.add(new Point(240,140));
		vertices.add(new Point(40,140));
		vertices.add(new Point(140,90));
		connect(vertices.get(0), vertices.get(1));
		connect(vertices.get(2), vertices.get(1));
		connect(vertices.get(0), vertices.get(2));
		connect(vertices.get(0), vertices.get(3));
		connect(vertices.get(1), vertices.get(3));
		connect(vertices.get(2), vertices.get(3));
	}
	
	public void draw(Graphics2D g2) {
		for(Edge e:edges){
			e.draw(g2);
		}
		for(Point p:vertices){
			p.draw(g2);
		}
	}
	
	private void connect(Point a, Point b){
		a.addPoint(b);
		b.addPoint(a);
		edges.add(new Edge(a,b));
	}

	public Point checkClick(int x, int y) {
		for(Point p: vertices){
			if(p.clickIn(x, y)){
				return p;
			}
		}
		return null;
	}

}
