import java.awt.Color;
import java.awt.Graphics;

public class UnitA extends Unit {
	
	
	
	public UnitA(double x,double y,String owner){
		this.x=x;
		this.y=y;
		this.status="ready";
		this.owner=owner;
		this.attack=10;
		this.armor=this.maxArmor=0;
		this.speed=(Math.random()*4)+2;
		this.healt=this.maxHealt=100;
		this.angle=Math.random()*Math.PI*2;
		this.dx=Math.sin(this.angle);
		this.dy=Math.cos(this.angle);
		this.color= Color.RED;
	};
	
	public void move(){
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
		
		if(this.x<0||this.x+UnitA.radius*2>Main.WIDTH){
			this.dx*=-1;
			this.x+=this.dx*this.speed*2;
			this.angle=Math.atan2(this.dy, this.dx);
		}
		
		if(this.y<0||this.y+UnitA.radius*2>Main.HEIGHT){
			this.dy*=-1;
			this.y+=this.dy*this.speed*2;
			this.angle=Math.atan2(this.dy, this.dx);
		}
	};
	
	public String getStatus() {
		return status;
	};
	
	public void setStatus(String status) {
		this.status = status;
	};
	
	public String getOwner() {
		return owner;
	};
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillArc((int)this.x, (int)this.y, Unit.radius*2, Unit.radius*2, 0,360);
	};
	
	public static UnitA createUnitA(String meno){
		double x=Math.random()*(Main.WIDTH-UnitA.radius*2);
		double y=Math.random()*(Main.HEIGHT-UnitA.radius*2);
		return new UnitA(x,y,meno);
	}
	
}
