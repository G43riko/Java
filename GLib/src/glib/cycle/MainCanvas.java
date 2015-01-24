package glib.cycle;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.util.GColor;
import glib.util.noise.PerlinNoise;
import glib.util.noise.SimplexNoise;


public class MainCanvas extends GCanvas{
	private float[][] mapa;
	private GColor[][] map;

	public static void main(String[] args){
		MainCanvas game = new MainCanvas();
	}
	
	public MainCanvas(){
		//mapa = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(getWidth(), getHeight()), 8, 0.7f, true);
		//mapa = SimplexNoise.generateOctavedSimplexNoise(getWidth(), getHeight(), 6, 0.8f, 0.008f);
		mapa = new float[600][600];
		map = new GColor[600][600];
		GColor color = new GColor(Color.MAGENTA);
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				map[i][j] = color.getSimilarInstance(250, false);
			}
		}
		start();
	}
	
	public void render(Graphics2D g2){
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				//float color = mapa[i][j]*255;
				//color = (float)Math.max(0,Math.min(255, color));
				g2.setColor(map[i][j]);
				g2.fillRect(i, j, 1, 1);
			}
		}
	}

}
