package com.voxel.core;

import glib.util.GLog;

import org.lwjgl.opengl.Display;

import com.voxel.gui.Gui;
import com.voxel.main.MainVoxel2;
import com.voxel.render.RenderingEngine;

public class CoreEngine {
	private double frameTime;
	private boolean isRunning;
	private RenderingEngine renderingEngine;
	private Game game;
	private Gui gui;
	
	public CoreEngine(float frameRate,Game game){
		isRunning=false;
		this.game =  game;
		this.frameTime = 1/frameRate;
	}
	
	public void createWindow(){
		//Window.createWindow(MainVoxel2.WIDTH, MainVoxel2.HEIGHT, MainVoxel2.TITLE);
		if(MainVoxel2.SHOW_GUI)
			gui = Window.createWindow(game);
		else
			gui = Window.createWindow(null);
		this.renderingEngine = new RenderingEngine();
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

				game.input();
				game.update((float) frameTime);
				
				if(frameCounter >= 1.0){
					GLog.write("FPS: "+frames,"mainLoop");
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render){
				game.render(renderingEngine);
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
