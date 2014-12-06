package main;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Level {

	private List<Point> vertices = new ArrayList<Point>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Level(){
		for(int i =0 ; i<20 ; i++){
			int x = (int)(Math.random()*Main.WIDTH);
			int y = (int)(Math.random()*Main.HEIGHT);
			vertices.add(new Point(x,y));
		}
		int num = vertices.size();
		for(int i=0 ; i<num ; i++){
			int b, a = (int)(Math.random()*num);
			while((b = (int)(Math.random()*num)) == a);
			connect(vertices.get(b), vertices.get(a));
		}
		
		
//		vertices.add(new Point(140,20));
//		vertices.add(new Point(240,140));
//		vertices.add(new Point(40,140));
//		vertices.add(new Point(140,90));
//		connect(vertices.get(0), vertices.get(1));
//		connect(vertices.get(2), vertices.get(1));
//		connect(vertices.get(0), vertices.get(2));
//		connect(vertices.get(0), vertices.get(3));
//		connect(vertices.get(1), vertices.get(3));
//		connect(vertices.get(2), vertices.get(3));
//		System.out.println(vertices.size()+" a "+edges.size());
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
		if(Main.canAdd){
			for(Edge e:edges){
				if(e.clickIn(x, y)){
					return addPoint(e,x,y);
				}
			}
		}
		return null;
	}
	
	public Point addPoint(Edge e, int x, int y){
		Point novy = new Point(x-Point.size/2,y-Point.size/2);
		Edge nova = new Edge(e.getA(),novy);
		e.setA(novy);
		edges.add(nova);
		vertices.add(novy);
		return novy;
	}

	public void checkEdgeColisions() {
		for(Edge e:edges){
			for(Edge h:edges){
				if(!e.equals(h)&&
					e.getA()!=h.getA()&&
					e.getA()!=h.getB()&&
					e.getB()!=h.getA()&&
					e.getB()!=h.getB()){
					Line2D line1 = new Line2D.Float(e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY());
					Line2D line2 = new Line2D.Float(h.getA().getX(), h.getA().getY(), h.getB().getX(), h.getB().getY());
					if(line2.intersectsLine(line1)){
						return;
					}
				}
			}
		}
		System.out.println("vyhral si!");
	}

}
