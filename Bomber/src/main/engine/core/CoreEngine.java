package main.engine.core;

import javax.swing.JFrame;

import main.engine.Bomberman;
import main.both.core.utils.Time;

public class CoreEngine {
	private double frameTime;
	private boolean isRunning;
	private Game game;
	
	public CoreEngine(int framerate, Bomberman bomberman) {
		isRunning=false;
		this.game = bomberman;
		frameTime = 1/(double)framerate;
	}

	public void start() {
		if(!isRunning)
			run();
	}

	private void run() {
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
				
				//pause/play

				game.input((float) frameTime);
				game.update((float) frameTime);

				if(frameCounter >= 1.0){
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render){
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
	}
}
