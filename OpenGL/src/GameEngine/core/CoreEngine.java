package GameEngine.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import GameEngine.TestGame;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Window;


public class CoreEngine {
//	public static final int WIDTH = 800;
//	public static final int HEIGHT = 800;
//	public static final String TITLE = "GAME_ENGINE";
//	public static final double FRAME_CAP = 5000.0;
//	
//	public static Options window;
	private int width;
	private int height;
	private double frameTime;
	private RenderingEngine renderingEngine;
	private boolean isRunning;
	private Game game;
	
	public CoreEngine(int width, int height, double framerate,Game game){

		game=new TestGame();
		
		isRunning=false;
		this.game =  game;
		this.width = width;
		this.height = height;
		this.frameTime = 1/framerate;
	}
//	
//	private void initRenderSystem(){
//		System.out.println(RenderUtil.getOpenGLVersion());
//		RenderUtil.initGraphics();
//	}
	
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
		//this.cleanUp();
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
					//this.stop();
					System.exit(0);
				}
				Input.update();
				
				game.input((float)frameTime);
				renderingEngine.input((float)frameTime);
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
//				this.render();
				renderingEngine.render(game.getRootObject());
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
	
//	public void render(){
//		RenderUtil.clearScreen();
//		game.render();
//		Window.render();
//	}
	
	private void cleanUp(){
		Window.dispose();
	}
	
//	public static void main(String[] args){
////		JFrame frame = new JFrame("test");
////		Canvas canvas = new Canvas();
////		frame.add(canvas, BorderLayout.CENTER);
////		frame.setPreferredSize(new Dimension(1024, 786));
////        frame.setMinimumSize(new Dimension(800, 600));
////        frame.pack();
////        frame.setVisible(true);
////        Window.createWindow(canvas);
//		Window.createWindow(WIDTH,HEIGHT,TITLE);
//		//MainComponents.window = new Options();
//		MainComponents game=new MainComponents();
//		//MainComponents.window.addGame(game.game);
//		game.game.addWindow(MainComponents.window);
//		game.start();
//	}
}
