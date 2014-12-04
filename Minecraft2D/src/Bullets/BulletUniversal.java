package Bullets;

import java.awt.Color;
import java.awt.Graphics2D;

import Components.Block;
import Main.Main;


public abstract class BulletUniversal {
	public double x,y,dx,dy,speed,size,sizePerLife,gravitySize,attack;
	public int life;
	public Color fillColor,drawColor=null;
	public boolean gravity=false;
	public boolean isDwindle=false;
	public boolean isExplosive=true;
	
	
	public void setDvindle(boolean isDwindle){
		this.isDwindle=isDwindle;
		if(this.isDwindle){
			this.sizePerLife=(this.size-1)/this.life;
		}
	}
	
	public void setDrawColor(Color drawColor){
		this.drawColor=drawColor;
	}
	
	public void setExplosivity(boolean isExplosive){
		this.isExplosive=isExplosive;
	}
	
	public void setGravity(Boolean co,int sila, double various){
		this.gravity=co;
		if(various!=1){
			various=Math.random()*various-various/2;
		}
		this.gravitySize=sila*various;
	}
	
	public boolean checkLifes(){
		if(life <= 0){
			return true;
		}
		return false;
	}
	
	public boolean checkBorders(boolean withSize){
		if(withSize){
			if(this.x+size<0||this.y+this.size<0||this.x-this.size>Block.size*Main.WIDTH||this.y-this.size>Block.size*Main.HEIGHT){
				return true;
			}
		}
		else{
			if(this.x<0||this.y<0||this.x>Block.size*Main.WIDTH||this.y>Block.size*Main.HEIGHT){
				return true;
			}
		}
		return false;
	}

	public abstract boolean move();

	public abstract boolean checkCollion();

	public abstract void draw(Graphics2D g2);
	
}
