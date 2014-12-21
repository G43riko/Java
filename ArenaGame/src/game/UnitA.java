package game;

import java.awt.Color;

public class UnitA extends Unit{

	public UnitA(Block[][] blocks){

		this.radius = (float)Math.random()*8+5;
		
		this.pos = new Vector2f((float)Math.random()*1240+20,(float)Math.random()*680+20);
		Vector2f act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		while(blocks[(int)act.getX()][(int)act.getY()].getType()!=0){
			this.pos = new Vector2f((float)Math.random()*1240+20,(float)Math.random()*680+20);
			act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		}
		
		
		this.dir = new Vector2f((float)Math.random()*20-10,(float)Math.random()*20-10).getNormalize();
		
		this.speed = (float)Math.random()*3+2;
		
		this.color = Color.GREEN;
	}
	
	public void setDirToTarger(Vector2f t){
		this.dir = new Vector2f(t.getX()-pos.getX(),t.getY()-pos.getY()).getNormalize();
	}
}
