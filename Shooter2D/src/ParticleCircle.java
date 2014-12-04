import java.awt.Color;
import java.awt.Graphics;


public class ParticleCircle {
	private double dy,dx,x,y;
	public double radius;
	public Color color;
	
	ParticleCircle(double x,double y,double radius,Color color, double dx, double dy){
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.dx=dx;
		if(dx==0){
			this.dx=dx+(Math.random()*5)-3;
		}
		this.dy=dy;
		if(dy==0){
			this.dy=dy+(Math.random()*5)-3;
		}
		this.color=color;
	};
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fillOval((int)this.x,(int)this.y,(int)this.radius,(int)this.radius);
		if(Main.gameIs==1){
			this.radius--;
		}
	};
	
	public void move(){
		this.x+=this.dx;
		this.y+=this.dy;
	};
}
