package main;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;	

import main.Box;
import maps.Block;

public class Game {
	public static int width = 1280;
	public static int height = 720;
	public static String title = "nadpis";
	public static boolean fullscreen = false;
	
	private Window window;
	private Level actLevel; 
	private Camera camera;
	private Selector selector;
	private HUD info;
	
	public void init() {
		System.out.println(this.getClass());
		 window = new Window(width,height,"Chess");
		 actLevel = new Level(40,10,40);
		 camera = new Camera(70,Display.getWidth()/Display.getHeight(),0.3f,1000);
		 info = new HUD();
		/*
		 * create level
		 * init level
		 * create camera
		 * init camera
		 * create entities
		 * init entities
		 */
		 selector = new Selector(0,1,0);
		
		 selector.setSize(2.1f,1.1f,2.1f);
	}

	public void start() {
		while(!Display.isCloseRequested()){
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&&fullscreen){
				break;
			}
			Input.update(camera,selector);
			Renderer.clearScreen();
			camera.useView();
			
			actLevel.draw();
			
			selector.draw();
			window.update();
			info.draw();
		}
	}
	
	public void cleanUp(){
		window.cleanUp();
	}

}
