package fractals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import glib.util.vector.GVector2f;

public class Block {
	private GVector2f position;
	private GVector2f size;
	private float angle;
	private Block parent;
	private Block lChild;
	private Block rChild;
	private Color color;
	
	
	public Block(GVector2f position, float angle, Block parent) {
		super();
		this.position = position;
		this.angle = angle;
		this.parent = parent;
		System.out.println(position + " " + angle + " ");
		
		if(parent==null){
			color = Color.red;
			size = new GVector2f(50,50);
		}
		else{
			color = parent.color;
			size = parent.size.mul(0.8f);
		}
	}
	
	public void draw(Graphics2D g2){
		AffineTransform saveAT = g2.getTransform();
		g2.setColor(color);
		g2.translate(position.getXi(), position.getYi());
		g2.rotate(angle);
		g2.fillRect(-size.getXi() / 2, -size.getYi() / 2, size.getXi(), size.getYi());
		g2.setTransform(saveAT);
		
		if(lChild != null)
			lChild.draw(g2);
		
		if(rChild != null)
			rChild.draw(g2);
	}
	public void addChildrens() {
		if(rChild != null && lChild != null){
			rChild.addChildrens();
			lChild.addChildrens();
			return;
		}
		
		float offset = 30;
		float a = angle + (float)Math.toRadians(offset);
		if(parent == null)
			a += (float)Math.PI/2;
		GVector2f dir = new GVector2f(Math.cos(a), -Math.sin(a));
//		System.out.println(a+" "+dir);
		lChild = new Block(position.add(dir.mul(size.getLength()/1.3f)), angle+(float)Math.PI/2, this);
		
		a = angle - (float)Math.toRadians(offset) ;
		if(parent == null)
			a += (float)Math.PI/2;
		dir = new GVector2f(Math.cos(a), -Math.sin(a));
//		System.out.println(a+" "+dir);
		rChild = new Block(position.add(dir.mul(size.getLength()/1.3f )), angle+(float)Math.PI/2, this);
		
	}
}
