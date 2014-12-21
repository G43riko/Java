package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	public static Vector2f size;
	private int type;
	private Vector2f pos;
	
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
	}

	public static void setSize(Vector2f size) {
		Block.size = size;
	}
}
