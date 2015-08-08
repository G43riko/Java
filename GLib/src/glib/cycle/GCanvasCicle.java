package glib.cycle;

import glib.util.GColor;
import glib.util.vector.GVector2f;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GCanvasCicle{
	private JFrame frame;
	
	private Canvas canvas;
	private int fps = 60;
	private boolean isRunning = false;
	private float frameTime = 1000/(float)fps;
	private int ticks, frames;
	private GVector2f size;
	private int color = 0;
	private GColor bgcolor = new GColor(255,255,255);
	
	public GCanvasCicle(){
		this(800,600,60);
	}
	
	public GCanvasCicle(int width, int height, int fps){
		size = new GVector2f(width, height);
		this.fps = 60;
		
		initFrame();
	}
	
	private void initFrame(){
		canvas = new Canvas();
		canvas.setSize(new Dimension(size.getXi(), size.getYi()));
		
		frame = new JFrame();
		frame.add(canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
				System.out.println("frames: " + frames + " ticks: " + ticks);
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
		if(buffer == null){
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
		g2.fillRect(0, 0, size.getXi(), size.getYi());
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getWidth() {
		return size.getXi();
	}

	public int getHeight() {
		return size.getYi();
	}

	public GVector2f getSize() {
		return size;
	}
}
