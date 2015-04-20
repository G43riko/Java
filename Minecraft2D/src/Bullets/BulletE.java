package Bullets;

import java.awt.Color;
import java.awt.Graphics2D;

import Components.Block;
import Main.Main;
import Particles.Firework;
import Utils.Helpers;


public class BulletE extends BulletUniversal {
	
	public BulletE(double x,double y,double dx, double dy,Color color,int life,double size,double speed){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.speed=speed;
		this.fillColor=color;
		this.life=life;
		this.size=size;
		this.attack=5;
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
			this.destroyAround(x,y,2);
		}
		if(collision){
			if(this.isExplosive){
				//new Explosion(this.x,this.y,70,Block.size*3);
				new Firework(this.x,this.y,100);
			}
			return true;
		}
		else return false;
	}
	
	private void destroyAround(double sx, double sy, int dist) {
		for(double i=-dist ; i<dist ; i++){
			for(double j=-dist ; j<dist ; j++){
				if(Helpers.getDist(sx+i, sy+j, sx, sy)<=dist-2){
					if(sx+i>=0&&sx+i<Main.mapa.Mapa.length&&sy+j>=0&sy+j<Main.mapa.Mapa[(int)(sx+i)].length){
						Main.mapa.Mapa[(int)(sx+i)][(int)(sy+j)].addDemage(this.attack+Main.players.attack);
					}
				}
			}
		}
	}
	
	public static BulletE createBullet(double tx, double ty){
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		double dx=Math.cos(angle);
		double dy=Math.sin(angle);
		BulletE co=new BulletE(x,y, dx, dy, Color.PINK, 200, Block.size,8);
		co.setGravity(true,80,1);
		return co;
	}
}
