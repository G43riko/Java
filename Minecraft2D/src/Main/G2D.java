package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

import Utils.Helpers;


public class G2D {
	private Rectangle2D.Float plocha;
	private Image image;
	
	G2D(){
		this.plocha=new Rectangle2D.Float(0,0,Main.WIDTH,Main.HEIGHT);
		this.image=new ImageIcon(ClassLoader.getSystemResource("\\Images\\Backgrounds\\Background - Desert.png")).getImage();
	};
	
	public void drawMenu(Graphics g){
		g.setColor(Color.DARK_GRAY);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(this.plocha);
	};
	
	public void drawGame(Graphics2D g2){
		if(Main.BacgroundAsImage){
			g2.drawImage(this.image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
		}
		else{
			g2.setColor(Color.BLACK);
			g2.fill(this.plocha);
		}
		if(Main.Player){
			Main.players.startFallIfCan();
		}
		Main.mapa.draw(g2);
		Main.bullets.move();
		Main.bullets.draw(g2);
		Main.bullets.checkCollision();
		if(Main.particlesCirc!=null){
			for(int i=0 ; i<Main.particlesCirc.size() ; i++){
				Main.particlesCirc.get(i).draw(g2);
				if(Main.particlesCirc.get(i).move()){
					Main.particlesCirc.remove(Main.particlesCirc.get(i));
					i--;
				}
			}
		}
		if(Main.Player&&Main.players!=null){
			Main.players.draw(g2);
			Main.players.move();
		}
		if(Main.Sidebar){
			Main.sidebar.draw(g2);
		}
		if(Main.Warnings){
			for(int i=0 ; i<Main.oznamenia.size() ; i++){
				Main.oznamenia.get(i).draw(g2);
			}
		}
		if(Main.HelpingTexts){
			Helpers.drawHelpingTexts(g2);
		}
	};
	
	public void drawPause(Graphics2D g2){
		
	}
}
