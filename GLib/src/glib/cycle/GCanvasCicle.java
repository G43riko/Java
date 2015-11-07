package glib.cycle;

import glib.util.GColor;
import glib.util.GLog2;
import glib.util.Utils;
import glib.util.vector.GVector2f;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class GCanvasCicle{
	private JFrame 		frame		= new JFrame();
	private Canvas 		canvas 		= new Canvas();
	private int 		fps;
	private int 		ticks;
	private int 		frames;
	private boolean 	isRunning 	= false;
	private float 		frameTime 	= 1000 / (float)fps;
	private GVector2f 	size;		
	private GColor 		bgcolor 	= new GColor(255, 255, 255);
	
	//CONSTRUCTORS
	
	public GCanvasCicle(int width, int height, int fps){
		size = new GVector2f(width, height);
		this.fps = fps;
		
		initFrame();
	}
	
	private void initFrame(){
		canvas.setSize(size.getXi(), size.getYi());
		
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
				GLog2.write("frames: " + frames + " ticks: " + ticks);
				cicleTime = System.currentTimeMillis();
				frames = 0;
				ticks = 0;
			}
			Utils.sleep(1);
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
	
	private void clearScreen(Graphics2D g2) {
		g2.setColor(bgcolor);
		g2.fillRect(0, 0, size.getXi(), size.getYi());
	}
	
	//ABSTRACT
	
	public abstract void input();
	public abstract void update();
	public abstract void render(Graphics2D g2);
	
	
	//GETTERS
	
	public int getWidth() {return size.getXi();}
	public int getHeight() {return size.getYi();}
	public GVector2f getSize() {return size;}
	
}
