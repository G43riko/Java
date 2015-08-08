package fractals;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.util.vector.GVector2f;

public class Triangel {
	private Color color = Color.RED;
	
	private GVector2f pointA;
	private GVector2f pointB;
	private GVector2f pointC;
	
	private Triangel subTriangelA;
	private Triangel subTriangelB;
	private Triangel subTriangelC;
	
	public Triangel(GVector2f pointA, GVector2f pointB, GVector2f pointC, int limit) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		
		int[] xPoints = new int[]{pointA.getXi(), pointB.getXi(), pointC.getXi()};
		int[] yPoints = new int[]{pointA.getYi(), pointB.getYi(), pointC.getYi()};
		
		g2.fillPolygon(xPoints, yPoints, 3);
		
		if(subTriangelA != null)
			subTriangelA.draw(g2);
		
		if(subTriangelB != null)
			subTriangelB.draw(g2);
		
		if(subTriangelC != null)
			subTriangelC.draw(g2);
	}

	public void addChildrens() {
		// TODO Auto-generated method stub
		
	}
}
