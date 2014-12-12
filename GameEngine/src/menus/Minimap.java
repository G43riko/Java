package menus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;



public class Minimap extends JPanel {
	private Canvas canvas;
	
	public Minimap(int width){
		setPreferredSize(new Dimension(width,width));
		setBackground(Color.CYAN);
		canvas = new Canvas();
		add(canvas);
	}
	
	public void update(){
		BufferStrategy buffer = canvas.getBufferStrategy();
		if(buffer==null){
			canvas.createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		draw(g2);
		
		g.dispose();
		buffer.show();
	}

	private void draw(Graphics2D g2) {
		
	}
}
