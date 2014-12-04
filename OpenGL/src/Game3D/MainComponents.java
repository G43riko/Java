package Game3D;

import Game3D.Window;
import GameEngine.Game;
import GameEngine.Input;
import GameEngine.RenderUtil;
import GameEngine.Time;

public class MainComponents {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Game";
	public static final double FRAME_CAP = 5000.0;
	
	private boolean isRunning;
	private Game game;
	
	public MainComponents(){
		RenderUtil.initGraphics();
		isRunning=false;
		//game=new Game(new Options());
	}
	
	private void run(){
		isRunning = true;
		int frames=0;
		long frameCounter=0;
		
		final double frameTime=1.0/FRAME_CAP;
		double lastTime = (long)Time.GetTime();
		double unprocessedTime = 0;
		while(this.isRunning){
			boolean render=false;
			double startTime=Time.GetTime();
			double passetTime=startTime-lastTime;
			lastTime=startTime;
			
			unprocessedTime+=passetTime/(double)Time.SECOND;
			while(unprocessedTime>frameTime){
				render=true;

				unprocessedTime-=frameTime;
				
				if(Window.isCloseRequested()){
					this.stop();
					//System.exit(0);
				}
				Input.update();
				
				game.input();
				game.update();
				
				Time.setDelta(frameTime);
				
				if(frameCounter>=Time.SECOND){
					System.out.println(frames);
					frames=0;
					frameCounter=0;
				}
			}
			if(render){
				this.render();
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
	
	private void start(){
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
	
	private void cleanUp(){
		Window.dispose();
	}
	
	public void render(){
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH,HEIGHT,TITLE);
		MainComponents game=new MainComponents();
		game.start();
	}

}
