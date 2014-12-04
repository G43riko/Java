package Particles;
import java.awt.Color;
import java.awt.Graphics2D;

import Components.Block;
import Main.Main;


public class ParticleCirc {
	private double x,y,dx,dy,speed,size,sizePerLife;
	private int life,gravitySize;
	private Color fillColor,drawColor=null;
	private boolean gravity=false;
	private boolean isDwindle=false;
	private int myLightId;
	
	public ParticleCirc(double x,double y,double dx, double dy,Color color,int life,double size,double speed){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.speed = speed;
		//this.fillColor=new Color(color.getRed(),color.getGreen(), color.getBlue(),(int)(Math.random()*155)+100);
		this.fillColor = color;
		this.life = life;
		this.size = size;
		this.myLightId = Main.lights.size();
		Main.lights.add(new Light(x,y,(int)size));
	}
	
	public void setDvindle(boolean isDwindle){
		this.isDwindle=isDwindle;
		if(this.isDwindle){
			this.sizePerLife=(this.size-1)/this.life;
		}
	}
	public void setDrawColor(Color drawColor){
		this.drawColor=drawColor;
	}
	public void setGravity(Boolean co,int sila){
		this.gravity=co;
		this.gravitySize=sila;
	}
	public void draw(Graphics2D g2){
		int X = (int)(this.x-Main.players.offsetX);
		int Y = (int)(this.y-Main.players.offsetY);
		g2.setColor(this.fillColor);
		g2.fillArc(X,Y, (int)this.size, (int)this.size, 0, 360);
		if(this.drawColor!=null){
			g2.setColor(this.drawColor);
			g2.drawArc((int)this.x, (int)this.y, (int)this.size, (int)this.size, 0, 360);
		}
	}
	
	public boolean move() {
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
		Main.lights.get(this.myLightId).setPos(this.x, this.y);
		if(this.gravity){
			this.dy+=Main.gravity/gravitySize;
		}
		if(this.isDwindle){
			this.size-=sizePerLife;
		}
		this.life--;
		if(this.checkLifes()||this.checkBorders()){
			return true;
		}
		return false;
		
	}
	
	private boolean checkLifes(){
		if(life <= 0){
			return true;
		}
		return false;
	}
	
	private boolean checkBorders(){
		if(this.x+size<0||this.y+this.size<0||this.x-this.size>Block.size*Main.WIDTH||this.y-this.size>Block.size*Main.HEIGHT){
			return true;
		}
		return false;
	}
}
