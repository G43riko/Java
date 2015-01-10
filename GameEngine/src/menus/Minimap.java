package menus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import terrains.Block;



public class Minimap extends JPanel {
	private Canvas canvas;
	private Block[][] minimap = null;;
	
	public Minimap(int width){
		setPreferredSize(new Dimension(width,width));
		//setBackground(Color.CYAN);
		canvas = new Canvas();
		canvas.setPreferredSize(this.getPreferredSize());
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
		if(minimap == null){
			return;
		}
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		int numX = canvas.getWidth()/minimap.length;
		int numY = canvas.getHeight()/minimap[0].length;
		for(int i=0 ; i<minimap.length ; i++){
			for(int j=0 ; j<minimap[i].length ; j++){
				g2.setColor(minimap[i][j].getColor());
				g2.fillRect(i*numX, j*numY, numX, numY);
			}
		}
	}

	public void setMinimap(Block[][] terrain) {
		this.minimap = terrain;
	}
}
