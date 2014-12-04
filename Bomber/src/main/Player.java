package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	private String name;
	private int dosah,bombs,demage,speed;
	private float x,y;
	public float offsetX,offsetY;
	public boolean[] move = new boolean[4];
	
	public Player(String meno){
		this.name=meno;
		this.speed=5;
		this.bombs = 1;
		this.dosah = 5;
		this.x = 0;
		this.y = 0;
		this.offsetX = 0-Main.WIDTH/2;
		this.offsetY = 0-Main.HEIGHT/2;

	}
	
	public void draw(Graphics2D g2){
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect((int)(this.x-offsetX),(int)(this.y-offsetY),Map.block,Map.block);
		
		
	}
	
	public void move(){
		
		if(this.move[0]){
			this.y -= speed;
		}
		if(this.move[1]){
			this.x -= speed;
		}
		if(this.move[2]){
			this.y += speed;
		}
		if(this.move[3]){
			this.x += speed;
		}
		
		//nastaví posun
    	this.offsetX=(this.x-Main.WIDTH/2);
		this.offsetY=(this.y-Main.HEIGHT/2);

		//skontroluje posun
		if(this.offsetX<0){
            this.offsetX=0;
        }
        if(this.offsetX>(Map.numX*Map.block)-Main.WIDTH){
            this.offsetX=(Map.numX*Map.block)-Main.WIDTH;
        }
        if(this.offsetY<0){
            this.offsetY=0;
        }		        
        if(this.offsetY>(Map.numY*Map.block)-Main.HEIGHT){
            this.offsetY=(Map.numY*Map.block)-Main.HEIGHT; 
        }
        
        if(x<0){
        	x=0;
        }
        if(y<0){
        	y=0;
        }
        if(x+Map.block>Map.numX*Map.block){
        	x = Map.numX*Map.block-Map.block;
        }
        if(y+Map.block>Map.numY*Map.block){
        	y = Map.numY*Map.block-Map.block;
        }
	}

	public void addBomb() {
		// TODO Auto-generated method stub
		
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getDosah() {
		return dosah;
	}

}
