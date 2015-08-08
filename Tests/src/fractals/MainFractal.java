package fractals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import glib.cycle.GCanvasCicle;
import glib.util.vector.GVector2f;

public class MainFractal extends GCanvasCicle{
//	private float angle = 0;
//	private ArrayList<Block> blocks = new ArrayList<Block>();
	static Tree a = new Tree(new GVector2f(800,750), 90, null, 100, 45);
//	static Block b = new Block(new GVector2f(800,450), 0, null);
	static Triangel c = new Triangel(new GVector2f(50,50),new GVector2f(1550,50), new GVector2f(800,850),3);
	
	public MainFractal(int i, int j, int k) {
		super(i,j,k);
	}

	public static void main(String[] args){
		MainFractal game = new MainFractal(1600,900,60);
		
		for(int i=0 ; i<15 ; i++)
//			a.addChildrens();
//			b.addChildrens();
			c.addChildrens();
		
//		game.blocks.add(new Block(new GVector2f(200,200), 0, null));
		game.start();
	}
	
	@Override
	public void render(Graphics2D g2) {
//		blocks.stream().forEach(a -> a.draw(g2));

//		a.draw(g2);
//		b.draw(g2);
		c.draw(g2);
		
//		g2.setColor(Color.RED);
//		g2.translate(200, 200);
//		g2.rotate(Math.toRadians(angle));
//		g2.fillRect(-50, -50, 100, 100);
//		angle++;
	}
}
