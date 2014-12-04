package PathFinder_2;
import java.awt.Color;
import java.awt.Graphics;


public class G2D {
	G2D(){};
	
	public void drawMenu(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
	public void drawGame(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		Mapa.draw(g);
	};
}
