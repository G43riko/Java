package com.g43riko.core;

import org.lwjgl.opengl.Display;


public class CoreEngine {
	private int width;
	private int height;
	private double frameTime;
	private boolean isRunning;
	private Game game;
	
	public CoreEngine(int width, int height, double framerate,Game game){
		isRunning=false;
		this.game =  game;
		this.width = width;
		this.height = height;
		this.frameTime = 1/framerate;
	}
	
	public void createWindow(String title,boolean fullScreen){
		if(fullScreen)
			Window.createWindow(width, height, title,true);
		else
			Window.createWindow(width, height, title);
	}
	
	public void start(){		
		if(!isRunning)
			run();
	}
	
	private void stop(){
		if(isRunning){
			isRunning = false;
		}
	}
	
	private void run(){
		isRunning = true;
		int frames = 0;
		double frameCounter = 0;

		game.init();

		double lastTime = Time.GetTime();
		double unprocessedTime = 0;
		
		while(isRunning){
			boolean render = false;

			double startTime = Time.GetTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime){
				render = true;
				unprocessedTime -= frameTime;
				
				if(Display.isCloseRequested())
					stop();

				game.input((float) frameTime);
				game.update((float) frameTime);
				
				if(frameCounter >= 1.0){
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render){
				game.render();
				Window.render();
				frames++;
			}
			else{
				try{
					Thread.sleep(1);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		cleanUp();
	}
	
	public void cleanUp(){
		Window.dispose();
	}
}
