package prototypeGameEngine.core;

import org.lwjgl.opengl.Display;


public class CoreEngine {
	private double frameTime;
	private boolean isRunning;
	private Game game;
	
	public CoreEngine( double framerate,Game game){
		isRunning=false;
		this.game =  game;
		this.frameTime = 1/framerate;
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
