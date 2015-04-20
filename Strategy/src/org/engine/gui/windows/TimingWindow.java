package org.engine.gui.windows;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

public class TimingWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	private final static int PANEL_WIDTH = 200;
	private final static int UPTADE_TIME = 1000;
	private int update = -1;
	private Canvas canvas;
	
	
	public TimingWindow(){
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(300,300));
		setLayout(new BorderLayout());
		add(createRightPanel(), BorderLayout.EAST);
		add(canvas, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800,600));
		
//		startDrawing();
	}
	
	private JPanel createRightPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, (int)panel.getSize().getHeight()));
		return panel;
	}
	
	private void startDrawing(){
		update = 1;
		Thread process = new Thread(new Runnable(){
			public void run() {
				while(update >= 0){
					System.out.println(canvas);
					BufferStrategy buffer = canvas.getBufferStrategy();
					if(buffer==null){
						canvas.createBufferStrategy(3);
						return;
					}
					Graphics g = buffer.getDrawGraphics();
					Graphics2D g2 = (Graphics2D) g;
					
					draw(g2);
					System.out.println("teraz2");
					g.dispose();
					buffer.show();
					try {
						Thread.sleep(UPTADE_TIME);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		System.out.println("teraz");
		process.start();
	}
	
	private void draw(Graphics2D g2){
		g2.setColor(Color.RED);
		g2.drawString("muhahaaa", 20, 20);
	}
}
