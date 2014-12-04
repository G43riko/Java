import java.awt.Color;
import java.awt.Graphics;


public class ParticleRect {
	private double width,height,dy,dx,x,y;
	public Color color;
	
	ParticleRect(double x,double y,double width,double height,Color color, double dx, double dy){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		int angle=(int)Math.floor(Math.random()*Math.PI*2);
		this.dx=Math.sin(angle)*((Math.random()*5)-2)+dx;//dx+Math.floor(Math.random()*5)-2;
		this.dy=Math.cos(angle)*((Math.random()*5)-2)+dy;//dy+Math.floor(Math.random()*5)-2;
		this.color=color;
	};
	
	public void draw(Graphics g){
		if(Main.gameIs==1){
			this.color=new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha()-2);
			if(this.width>1){
				this.width-=Math.random()/10;
			}
		}
		g.setColor(this.color);
		g.fillRect((int)this.x, (int)this.y, (int)this.width, (int)this.height);
	};
	
	public void move(){
		this.x+=this.dx;
		this.y+=this.dy;
	};
}
