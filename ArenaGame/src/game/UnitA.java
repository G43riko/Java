package game;

import java.awt.Color;

public class UnitA extends Unit{

	public UnitA(Block[][] blocks, Window window){

		this.radius = window.enemySize.getValue();
		
		this.pos = new Vector2f((float)Math.random()*1240+20,(float)Math.random()*680+20);
		Vector2f act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		while(blocks[(int)act.getX()][(int)act.getY()].getType()!=0){
			this.pos = new Vector2f((float)Math.random()*1240+20,(float)Math.random()*680+20);
			act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		}
		
		
		this.dir = new Vector2f((float)Math.random()*20-10,(float)Math.random()*20-10).getNormalize();
		
		this.speed = window.enemySpeed.getValue();
		
		this.color = Color.GREEN;
	}
}
