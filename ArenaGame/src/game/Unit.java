package game;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Unit {
	protected Vector2f pos;
	protected Vector2f dir;
	protected float speed;
	protected float radius;
	protected Color color;
	
	public void move(Block[][] blocks){
		pos.move(dir.getX()*speed, 0);

		Vector2f act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		if(Map.exist((int)act.getX(), (int)act.getY(), blocks))
			if(blocks[(int)act.getX()][(int)act.getY()].getType()==1){
//				this.dir.mul(new Vector2f(-1,0));
				this.dir.setX(this.dir.getX()*-1);
				pos.move(dir.getX()*speed, 0);
			}
		
		pos.move(0, dir.getY()*speed);
		act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		if(Map.exist((int)act.getX(), (int)act.getY(), blocks))
			if(blocks[(int)act.getX()][(int)act.getY()].getType()==1){
//				this.dir.mul(new Vector2f(0,-1));
				this.dir.setY(this.dir.getY()*-1);
				pos.move(0, dir.getY()*speed);
			}
		
		if(pos.getX()<0+radius||pos.getX()+radius>=Window.WIDTH){
			dir.setX(dir.getX()*-1);
		}
		if(pos.getY()<0+radius||pos.getY()+radius>=Window.HEIGHT){
			dir.setY(dir.getY()*-1);
		}
		
	};
	
	public void draw(Graphics2D g2){
		g2.setColor(this.color);
		g2.fillArc((int)(pos.getX()-radius), (int)(pos.getY()-radius), (int)radius*2, (int)radius*2, 0, 360);
		
		g2.setColor(Color.BLACK);
		g2.drawArc((int)(pos.getX()-radius), (int)(pos.getY()-radius), (int)radius*2, (int)radius*2, 0, 360);
//		g2.setColor(Color.red);
//		g2.fillArc((int)(pos.getX()), (int)(pos.getY()), 2, 2, 0, 360);
	}
}
