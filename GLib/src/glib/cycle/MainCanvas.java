package glib.cycle;

import java.awt.Graphics2D;

import glib.util.GColor;
import glib.util.noise.PerlinNoise;
import glib.util.noise.SimplexNoise;


public class MainCanvas extends GCanvasCicle{
	private static final long serialVersionUID = 1L;
	
	private float[][] mapa;
//	private GColor[][] map;

	public static void main(String[] args){
		MainCanvas game = new MainCanvas();
		try {
			game.finalize();
		} catch (Throwable e) {e.printStackTrace(); }
	}
	
	public MainCanvas(){
		mapa = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(getWidth(), getHeight()), 8, 0.7f, true);
		mapa = SimplexNoise.generateOctavedSimplexNoise(getWidth(), getHeight(), 6, 0.8f, 0.008f);
		start();
	}
	
	public void render(Graphics2D g2){
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				float color = mapa[i][j]*255;
				color = (float)Math.max(0,Math.min(255, color));
				g2.setColor(new GColor(color,0,color/2));
				g2.fillRect(i, j, 1, 1);
			}
		}
//		g2.setColor(GColor.red);
//		g2.setPaint(new TexturePaint(null, new Rectangle2D.Float()));
//		g2.fillPolygon(new int[]{10,20,10}, new int[]{10,10,20}, 3);
	}

}
