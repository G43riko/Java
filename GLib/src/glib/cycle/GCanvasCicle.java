package glib.cycle;

import glib.util.GColor;
import glib.util.GLog;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GCanvasCicle extends JFrame{
	private Canvas canvas;
	private int fps = 60;
	private boolean isRunning = false;
	private float frameTime = 1000/(float)fps;
	private int ticks, frames;
	private int width, height;
	private int color = 0;
	private GColor bgcolor = new GColor(255,255,255);
	
	public GCanvasCicle(){
		this(800,600);
	}
	
	public GCanvasCicle(int width, int height){
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init(){
		canvas = new Canvas();
		canvas.setSize(new Dimension(width,height));
		add(canvas);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void start(){
		isRunning = true;
		mainLoop();
	}
	
	private void mainLoop(){
		double startTime;
		double cicleTime = System.currentTimeMillis();
		while(isRunning){
			startTime = System.currentTimeMillis();
			if(System.currentTimeMillis() - cicleTime > 1000){
				GLog.write("frames: "+frames+" ticks: "+ticks,"mainLoop");
				cicleTime = System.currentTimeMillis();
				frames = 0;
				ticks = 0;
				
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {e.printStackTrace(); }
			while(System.currentTimeMillis() - startTime < frameTime){
				ticks++;
				input();
				update();
			}
			frames++;
			updateCanvas();
		}
	}
	
	private void updateCanvas(){
		BufferStrategy buffer = canvas.getBufferStrategy();
		if(buffer==null){
			canvas.createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		clearScreen(g2);
		render(g2);
		g.dispose();
		buffer.show();
	}
	
	public void input(){
		
	}
	
	public void update(){
		
	}
	
	public void render(Graphics2D g2){
	}
	
	private void clearScreen(Graphics2D g2) {
		g2.setColor(bgcolor);
		g2.fillRect(0, 0, width, height);
	}

	public void setFps(int fps){
		this.fps = fps;
		frameTime = 1/fps;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
