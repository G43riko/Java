package Utils;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.Main;


public class Helpers {
	
	public static double choose(double... argc){
		double rand=argc[(int)Math.floor(Math.random()*argc.length)];
		return rand;
	};
	
	public static double average(double[] argc){
		double sum=0;
		for(int i=0 ; i<argc.length ; i++){
			sum+=argc[i];
		}
		return sum/argc.length;
	};
	
	public static double getDist(double ax, double ay, double bx, double by){
		double dist;
		dist=(ax-bx)*(ax-bx)+(ay-by)*(ay-by);
		dist=Math.sqrt(dist);
		return dist;
	};

	public static boolean collisionRectRect(int ax,int ay, int awidth, int aheight, int bx,int by, int bwidth, int bheight){
		  if((bx+bwidth>ax)&&
		    (by+bheight>ay)&&
		    (bx<ax+awidth)&&
		    (by<ay+aheight)){
		    return true;
		  }  
		return false;
	};

	public static boolean collisionCircleCircle(double ax, double ay, int asize, double bx, double by, int bsize){
		double distX=ax-bx;
		double distY=ay-by;
		int rad_a=asize;
		int rad_b=bsize;
		double dist = Math.sqrt((distX * distX) + (distY * distY));
		if(dist <=(rad_a+rad_b)){
			return false;
		}
		return false;
	};
	
	public static boolean collisionPointRec(double ax, double ay, int awidth, int aheight, double bx,double by){
		if(bx>ax&&bx<ax+awidth&&by>ay&&by<ay+aheight){
			return true;
		}
		return false;
	};
	
	public static boolean collisionPointCircle(double ax, double ay, double bx, double by, double bradius){
		if(getDist(ax,ay,bx,by)<bradius){
			return true;
		}
		return false;
	}
	public static void drawHelpingTexts(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Ticks: "+Main.FPS, 0, 10);
		g2.drawString("FPS: "+Main.ticks, 0, 20);
		g2.drawString("isJumping: "+Main.players.isJumping, 0, 30);
		g2.drawString("isMoving: "+Main.players.isMoving, 0, 40);
		g2.drawString("Block on screen: "+Main.xOnScreen*Main.yOnScreen, 0,50);
		//g2.drawString("Score: "+Integer.toString(Main.score), 0, 10);
		//g2.drawString("Healt: "+Integer.toString(Main.player.healt), 0, 20);
		//g2.drawString("Number of enemies: "+Main.EnemyNum, 0, 30);
		//g2.drawString("Max bullets: "+Main.BulletNum, 0, 40);
		//g2.drawString("Number of bullets: "+Main.bullets.getNum(), 0, 50);
	};
}

