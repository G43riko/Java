package fractals;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.util.vector.GVector2f;

public class Tree {
	private GVector2f position;
	private int angle;
	private int alfa;
	private Tree parent;
	private float length;
	private float shortening = 0.8f;
	private Color color = Color.RED;
	
	private Tree lChild;
	private Tree rChild;
	
	public Tree(GVector2f position, int angle, Tree parent, float length, int alfa) {
		this.position = position;
		this.parent = parent;
		this.length = length;
		this.angle = angle;
		this.alfa = alfa;
	}

	public void addChildrens() {
		if(rChild == null && lChild == null){
			int newAngle = angle+alfa/2;
			float newLenght = length * shortening;
			GVector2f newVec = new GVector2f(Math.cos(Math.toRadians(newAngle)), -Math.sin(Math.toRadians(newAngle))).mul(newLenght);
			rChild = new Tree(position.add(newVec), newAngle, this, newLenght, alfa);
			
			newAngle = angle-alfa/2;
			newVec = new GVector2f(Math.cos(Math.toRadians(newAngle)), -Math.sin(Math.toRadians(newAngle))).mul(newLenght);
			lChild = new Tree(position.add(newVec), newAngle, this, newLenght, alfa);
		}
		else{
			lChild.addChildrens();
			rChild.addChildrens();
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		
		if(parent == null)
			g2.drawLine(position.getXi(), position.getYi(), position.getXi(), position.getYi() + (int)length);
		else 
			g2.drawLine(position.getXi(), position.getYi(), parent.position.getXi(), parent.position.getYi());
		
		if(rChild != null && lChild != null){
			rChild.draw(g2);
			lChild.draw(g2);
		}
			
		
	}

}
