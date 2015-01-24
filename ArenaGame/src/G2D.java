import java.awt.Color;
import java.awt.Graphics;


public class G2D {
	private float[][] mapa;
	G2D(){
//		mapa = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(1600, 900), 8, 0.7f, true);
		mapa = SimplexNoise.generateOctavedSimplexNoise(1600, 900, 6, 0.8f, 0.008f);
	};
	
	public void drawMenu(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
	public void drawGame(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		if(Main.gameIs==1){
			for(int i=0 ; i<mapa.length ; i++){
				for(int j=0 ; j<mapa[i].length ; j++){
					float color = mapa[i][j]*255;
					color = Math.max(0,Math.min(255, color));
					if(color < 51)
						g.setColor(new Color(51,0,0,255));
					else if(color < 102)
						g.setColor(new Color(102,0,0,255));
					else if(color < 153)
						g.setColor(new Color(153,0,0,255));
					else if(color < 204)
						g.setColor(new Color(204,0,0,255));
					else
						g.setColor(new Color(255,0,0,255));
					
					g.setColor(new Color((int)color,(int)color,(int)color,255));
					g.fillRect(i, j, 1, 1);		
				}
			}
		}
	};
	
	public void drawPause(Graphics g){
		
	}
}
