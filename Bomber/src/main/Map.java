package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	public static int block = 40;
	public static int numX = 30;
	public static int numY = 30;
	public static int[][] mapa = new int[numY][numX];
	
	public Map(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				mapa[i][j]=(int)(Math.random()*5);
			}
		}
	}
	public void draw(Graphics2D g2, Player player){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				switch(mapa[i][j]){
					case 1:
						g2.setColor(Color.BLUE);
						g2.fillRect((int)(i*Map.block-player.offsetX),(int)(j*Map.block-player.offsetY),Map.block,Map.block);
						break;
				}
			}
		}
	}
}
