package game.maps;

import game.core.Vector2f;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	public static Vector2f size;
	private int type;
	private float distToGoal=-1;
	private Vector2f pos;
	private int dir = 0;
	public int dir2 = 0;
	
	public Block(int x, int y,int type){
		pos = new Vector2f(x,y);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void draw(Graphics2D g2) {
		if(type==1){
			g2.setColor(Color.BLACK);
			g2.fillRect((int)(pos.getX()*size.getX()), (int)(pos.getY()*size.getY()), (int)size.getX(), (int)size.getY());
		}
		if(type==0&&distToGoal!=-1){
			g2.setColor(new Color(0,0,255,Math.min(255,Math.max(255-(int)Math.abs(distToGoal)*6,0))));
			g2.fillRect((int)(pos.getX()*size.getX()), (int)(pos.getY()*size.getY()), (int)size.getX(), (int)size.getY());
		}
		g2.setColor(Color.BLACK);
		g2.drawString(String.valueOf(distToGoal), (int)(pos.getX()*size.getX()), (int)(pos.getY()*size.getY())+10);
		g2.drawString(String.valueOf(dir), (int)(pos.getX()*size.getX())+20, (int)(pos.getY()*size.getY())+25);
	}

	public static void setSize(Vector2f size) {
		Block.size = size;
	}

	public float getDistToGoal() {
		return distToGoal;
	}

	public void setDistToGoal(float distToGoal) {
		this.distToGoal = distToGoal;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
}
