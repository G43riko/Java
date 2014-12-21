package game;

import java.awt.Color;

public class UnitA extends Unit{

	public UnitA(){

		this.radius = (float)Math.random()*8+5;
		
		this.pos = new Vector2f((float)Math.random()*1240+20,(float)Math.random()*680+20);
		this.dir = new Vector2f((float)Math.random()*20-10,(float)Math.random()*20-10).getNormalize();
		
		this.speed = (float)Math.random()*3+2;
		
		this.color = Color.GREEN;
	}
	
	public void setDirToTarger(Vector2f t){
		this.dir = new Vector2f(t.getX()-pos.getX(),t.getY()-pos.getY()).getNormalize();
	}
}
