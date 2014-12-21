import java.awt.Color;
import java.awt.Graphics;


public class G2D {
	G2D(){};
	
	public void drawMenu(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
	public void drawGame(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		Main.display.units.draw(g);
		if(Main.gameIs==1){
			Main.display.units.move();
		}
	};
	
	public void drawPause(Graphics g){
		
	}
}
