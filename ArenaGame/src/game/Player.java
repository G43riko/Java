package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	public float x=0,y=0;
	public int dir = 3;
	public Color color = Color.CYAN;
	public int speed = 5;
	
	public void draw(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect((int)x, (int)y, (int)(Block.size.getX()), (int)(Block.size.getY()));
		
		g2.setColor(color);
		g2.fillRect((int)(x+Block.size.getX()/2-2.5), (int)(y+Block.size.getY()/2-10), (int)(5), (int)(20));
		
		g2.setColor(Color.black);
		g2.drawRect((int)(x+Block.size.getX()/2-2.5), (int)(y+Block.size.getY()/2-10), (int)(5), (int)(20));
	}
	
	public void move(){
		if(Keyboard.keys[0]){
			this.y-=speed;
		}
		if(Keyboard.keys[1]){
			this.x-=speed;
		}
		if(Keyboard.keys[2]){
			this.y+=speed;
		}
		if(Keyboard.keys[3]){
			this.x+=speed;
		}
	}
}
