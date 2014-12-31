package GameEngine.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import GameEngine.Game;
import GameEngine.rendering.RenderUtil;
import GameEngine.rendering.Window;


public class MainComponents {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "GAME_ENGINE";
	public static final double FRAME_CAP = 5000.0;
	
	public static Options window;
	
	private boolean isRunning;
	private Game game;
	
	public MainComponents(){
		//System.out.println(RenderUtil.getOpenGLVersion());
		//System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		RenderUtil.initGraphics();
		
		this.isRunning=false;
		 
		game=new Game();
		
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
		//this.cleanUp();
	}
	
	private void run(){
		this.isRunning=true;
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
					//this.stop();
					System.exit(0);
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
	
	public void render(){
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	private void cleanUp(){
		Window.dispose();
	}
	
	public static void main(String[] args){
//		JFrame frame = new JFrame("test");
//		Canvas canvas = new Canvas();
//		frame.add(canvas, BorderLayout.CENTER);
//		frame.setPreferredSize(new Dimension(1024, 786));
//        frame.setMinimumSize(new Dimension(800, 600));
//        frame.pack();
//        frame.setVisible(true);
//        Window.createWindow(canvas);
		Window.createWindow(WIDTH,HEIGHT,TITLE);
		MainComponents.window = new Options();
		MainComponents game=new MainComponents();
		MainComponents.window.addGame(game.game);
		game.game.addWindow(MainComponents.window);
		game.start();
	}
}
