package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bomb {
	private int x,y;
	private double add,time=3000;
	private int dist;
	
	public Bomb(float x,float y,int dist){
		this.x = (int)(Math.round(x/Map.block)/**Map.block*/);
		this.y = (int)(Math.round(y/Map.block)/**Map.block*/);
		add = System.currentTimeMillis();
		this.dist = dist;
		//System.out.println("vytvorila sa bomba v "+add);
	}
	
	public boolean draw(Graphics2D g2, Player player){
		g2.setColor(Color.BLACK);
		g2.fillRect((int)(x*Map.block-player.offsetX),(int)(y*Map.block-player.offsetY),Map.block,Map.block);
		
		for(int i=1 ; i<=dist ; i++){
			if(Map.mapa[x][y+i]==0){
				g2.setColor(Color.white);
				g2.fillRect((int)(x*Map.block-player.offsetX),(int)((y+i)*Map.block-player.offsetY),Map.block,Map.block);
			}
			if(Map.mapa[x][y-i]==0){
				g2.setColor(Color.white);
				g2.fillRect((int)(x*Map.block-player.offsetX),(int)((y-i)*Map.block-player.offsetY),Map.block,Map.block);
			}
			if(Map.mapa[x+i][y]==0){
				g2.setColor(Color.white);
				g2.fillRect((int)((x+i)*Map.block-player.offsetX),(int)((y)*Map.block-player.offsetY),Map.block,Map.block);
			}
			if(Map.mapa[x-i][y]==0){
				g2.setColor(Color.white);
				g2.fillRect((int)((x-i)*Map.block-player.offsetX),(int)((y)*Map.block-player.offsetY),Map.block,Map.block);
			}
		}
		
		if(checkExplosion()){
			startExplosion();
			return true;
		}
		return false;
	}
	
	private void startExplosion() {
		//System.out.println("BUM");
		Map.mapa[x][y] = 0;
		for(int i=0 ; i<=dist ; i++){
			Map.mapa[x][y+i] = 0;
			//if(Map.mapa[x].length<y-i)
			Map.mapa[x][y-i] = 0;
			Map.mapa[x+i][y] = 0;
			Map.mapa[x-i][y] = 0;
		}
	}

	public boolean checkExplosion(){
		if(System.currentTimeMillis()-add>time){
			return true;
		}
		return false;
	}
}
