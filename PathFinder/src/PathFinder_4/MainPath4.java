package PathFinder_4;

import glib.cycle.GCanvasCicle;
import glib.util.vector.GVector2f;

import java.awt.Color;
import java.awt.Graphics2D;

public class MainPath4 extends GCanvasCicle{
	private Map map;
	private static GVector2f size = new GVector2f(1600, 900);
	public MainPath4(){
		super(size.getXi(), size.getYi(), 30);
		map = new Map(size.getXi(), size.getYi(), 160, 90);
	}
	
	@Override
	public void render(Graphics2D g2) {
		map.render(g2);
	}
	
	public static void main(String[] args) {
		MainPath4 path = new MainPath4();
		path.start();
	}
}
