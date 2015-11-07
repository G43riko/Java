package glib.cycle;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.util.vector.GVector2f;


public class MainCanvas extends GCanvasCicle{
	
//	private float[][] mapa;
//	private GColor[][] map;

	public static void main(String[] args){
		MainCanvas game = new MainCanvas();
		try {
			game.finalize();
		} catch (Throwable e) {e.printStackTrace(); }
	}
	
	public MainCanvas(){
		super(800, 600, 60);
		start();
	}
	
	public void render(Graphics2D g2){
		drawTriangel(g2, new GVector2f(50,50), new GVector2f(150,50), new GVector2f(50,150));
	}
	
	public void drawLine(Graphics2D g2, GVector2f a, GVector2f b){
		g2.setColor(Color.red);
		g2.drawLine(a.getXi(), a.getYi(), b.getXi(), b.getYi());
	}
	
	public void drawPoint(Graphics2D g2, GVector2f a){
		g2.setColor(Color.GREEN);
		g2.drawArc(a.getXi()-5, a.getYi()-5, 10, 10, 0, 360);
	}
	
	public void drawTriangel(Graphics2D g2, GVector2f a, GVector2f b, GVector2f c){
		g2.setColor(Color.red);
		g2.drawLine(a.getXi(), a.getYi(), b.getXi(), b.getYi());
		g2.drawLine(b.getXi(), b.getYi(), c.getXi(), c.getYi());
		g2.drawLine(c.getXi(), c.getYi(), a.getXi(), a.getYi());
	}
	
	public void drawQuad(Graphics2D g2, GVector2f a, GVector2f b, GVector2f c, GVector2f d){
		g2.setColor(Color.red);
		g2.drawLine(a.getXi(), a.getYi(), b.getXi(), b.getYi());
		g2.drawLine(b.getXi(), b.getYi(), c.getXi(), c.getYi());
		g2.drawLine(c.getXi(), c.getYi(), d.getXi(), d.getYi());
		g2.drawLine(d.getXi(), d.getYi(), a.getXi(), a.getYi());
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	

}
