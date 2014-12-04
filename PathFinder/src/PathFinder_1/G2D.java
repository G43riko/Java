package PathFinder_1;
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
		if(!Main.findGoal){
			PathFinding.findPossibles();
			PathFinding.findBestWay(0);
			PathFinding.moveToPossibles();
		}
		else if(!Main.removedPossibles){
			for( int i=0 ; i<Main.map.length ; i++){
				for( int j=0 ; j<Main.map[i].length ; j++){
					if(Main.map[i][j].type==7){
						Main.map[i][j].type=4;
					}
				}
			}
			Main.removedPossibles=true;
			PathFinding.findPath(1);
		}
	};
}
