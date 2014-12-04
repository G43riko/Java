package Bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Components.Block;
import Main.Main;
import Particles.Explosion;


public class BulletC extends BulletUniversal {
	
	public BulletC(double x,double y,double dx, double dy,Color color,int life,double size,double speed){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.speed=speed;
		this.fillColor=color;
		this.life=life;
		this.size=size;
		this.attack=Math.random()/5;
	}
	
	
	public void draw(Graphics2D g2){
		g2.setColor(this.fillColor);
		g2.fillArc((int)(this.x-Main.players.offsetX), (int)(this.y-Main.players.offsetY), (int)this.size, (int)this.size, 0, 360);
		if(this.drawColor!=null){
			g2.setColor(this.drawColor);
			g2.drawArc((int)this.x, (int)this.y, (int)this.size, (int)this.size, 0, 360);
		}
	}
	
	public boolean move() {
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
		if(this.gravity){
			this.dy+=Main.gravity/gravitySize;
		}
		if(this.isDwindle){
			this.size-=sizePerLife;
		}
		this.life--;
		if(this.checkLifes()||this.checkBorders(true)){
			return true;
		}
		return false;
	}
	
	public boolean checkCollion(){
		boolean collision=false;
		int x=(int)Math.floor(this.x/Block.size);
		int y=(int)Math.floor(this.y/Block.size);
		if(x<0||y<0||x>=Main.mapa.Mapa.length||y>=Main.mapa.Mapa[x].length){
			return false;
		}
		if(Main.mapa.Mapa[x][y].type!=0){
			collision=true;
		}
		if(collision){
			if(this.isExplosive){
				new Explosion(this.x,this.y,70,30);
			}
			Main.mapa.Mapa[x][y].addDemage(this.attack+Main.players.attack);
			return true;
		}
		return false;
	}
	
	public static ArrayList<BulletUniversal> createBullet(double tx, double ty){
		ArrayList<BulletUniversal> bullets = new ArrayList<BulletUniversal>();
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		
		for(int i=0 ; i<60 ; i++){
			double helper=angle+Math.random()*Math.PI/20-Math.PI/40;
			double dx=Math.cos(helper);
			double dy=Math.sin(helper);
			double size = Math.random()*Block.size/4;
			double speed=15+(Math.random()*4-2);
			BulletC co=new BulletC(x,y, dx, dy, Color.DARK_GRAY, 200, size,speed);
			co.setDrawColor(Color.BLACK);
			co.setGravity(true, 100,100);
			co.setExplosivity(false);
			bullets.add(co);
		}
		return bullets;
	}
}
