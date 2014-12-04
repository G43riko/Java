package Bullets;

import java.awt.Color;
import java.awt.Graphics2D;

import Components.Block;
import Main.Main;
import Particles.Explosion;
import Particles.ParticleCirc;


public class BulletB extends BulletUniversal{
	private double lastParticleDrop;
	
	public BulletB(double x,double y,double dx, double dy,Color color,int life,double size,double speed){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.speed=speed;
		this.fillColor=color;
		this.life=life;
		this.size=size;
		this.lastParticleDrop=System.currentTimeMillis();
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
		double div=Math.random()*1000;
		if(System.currentTimeMillis()-this.lastParticleDrop>div){
			this.dropPartice();
		}
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
	
	private void dropPartice(){
		Color color=new Color(this.fillColor.getRed(),this.fillColor.getGreen(),this.fillColor.getBlue(),150);
		double speed=Math.random()/4+.25;
		ParticleCirc casticka = new ParticleCirc(x, y, this.dx/10, speed, color, 100, size/2,8);
		casticka.setDvindle(true);
		Main.particlesCirc.add(casticka);
		this.lastParticleDrop=System.currentTimeMillis();
	};
	
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
				new Explosion(this.x,this.y,70,Block.size*3);
			}
			//Minecraft2D.mapa.autoSetLightnessOnce(false);
			return true;
		}
		else return false;
	}
	
	public static BulletB createBullet(double tx, double ty){
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		double dx=Math.cos(angle);
		double dy=Math.sin(angle);
		BulletB co=new BulletB(x,y, dx, dy, Color.ORANGE, 200, Block.size,8);
		return co;
	}
}
