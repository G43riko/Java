package main;
import java.awt.Color;
import java.awt.Graphics2D;


public class G2D {
	
	Level actLevel = new Level();
	
	public void drawMenu(Graphics2D g2){
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
	public void drawGame(Graphics2D g2){
		g2.setColor(Color.GREEN);
		g2.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		actLevel.draw(g2);
	};
	
	public void drawPause(Graphics2D g2){
		
	};
	
	public Level getLevel(){
		return actLevel;
	}

}
