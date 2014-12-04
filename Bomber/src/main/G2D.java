package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;


public class G2D {
	
	private Player player;
	private Map mapa;
	private List<Bomb> bombs;
	
	G2D(){};
	
	public void drawMenu(Graphics2D g2){
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
	public void drawGame(Graphics2D g2){
		g2.setColor(Color.GREEN);
		g2.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		mapa.draw(g2,player);
		for(int i=0 ; i<bombs.size() ; i++){
			if(bombs.get(i).draw(g2, player)){
				bombs.remove(i);
				i--;
			};
		}
		player.move();
		player.draw(g2);
	};
	
	public void drawPause(Graphics2D g2){
		
	};
	
	public void addPlayer(Player kto){
		this.player = kto;
	}
	public void addMap(Map map){
		this.mapa = map;
	}

	public void addBombs(List<Bomb> bombs) {
		this.bombs = bombs;
	}
}
