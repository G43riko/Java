package Sphere;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.cycle.GCanvasCicle;

public class MainSphere extends GCanvasCicle{

	public MainSphere() {
		super(800, 600, 60);
	}

	public static void main(String[] args) {
		MainSphere sphere = new MainSphere();
		sphere.start();
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(50, 50, 250, 250);
	}
	
	@Override
	public void update() {
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

}
