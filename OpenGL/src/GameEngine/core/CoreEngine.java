package GameEngine.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import GameEngine.TestGame;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Window;


public class CoreEngine {
	private int width;
	private int height;
	private double frameTime;
	private RenderingEngine renderingEngine;
	private boolean isRunning;
	private Game game;
	
	public CoreEngine(int width, int height, double framerate,Game game){
		isRunning=false;
		this.game =  game;
		this.width = width;
		this.height = height;
		this.frameTime = 1/framerate;
	}
	
	public void createWindow(String title){
			Window.createWindow(width, height, title);
			this.renderingEngine = new RenderingEngine();
	}
	
	public void start(){
		
		if(this.isRunning){
			return;
		}
		run();
	}
	
	public void stop(){
		if(!this.isRunning){
			return;
		}
		this.isRunning=false;
		this.cleanUp();
	}
	
	private void run(){
		this.isRunning=true;
		int frames=0;
		double frameCounter=0;
		
		game.init();
		
		double lastTime = (long)Time.GetTime();
		double unprocessedTime = 0;
		while(this.isRunning){
			boolean render=false;
			double startTime=Time.GetTime();
			double passetTime=startTime-lastTime;
			lastTime=startTime;
			
			unprocessedTime+=passetTime;
			while(unprocessedTime>frameTime){
				render=true;

				unprocessedTime-=frameTime;
				
				if(Window.isCloseRequested()){
					this.stop();
					System.exit(0);
				}
				Input.update();
				
				game.input((float)frameTime);
				Input.update();
				game.update((float)frameTime);
				
				Time.setDelta(frameTime);
				
				if(frameCounter>=1){
					System.out.println(frames);
					frames=0;
					frameCounter=0;
				}
			}
			if(render){
				game.render(renderingEngine);
				//renderingEngine.render(game.getRootObject());
				Window.render();
			}
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void cleanUp(){
		Window.dispose();
	}
}
