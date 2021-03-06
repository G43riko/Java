package org.engine.core;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import org.MainStrategy;
import org.engine.gui.Gui;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.strategy.core.CoreStrategy;

public class Window {
	
	//CREATORS
	
	public static Gui createWindow(CoreEngine game){
		if(!MainStrategy.SHOW_GUI){
			Display.setLocation(0, 0);
			Display.setTitle(MainStrategy.TITLE);
			try {
				if(MainStrategy.FULLSCREEN){
					Display.setDisplayMode(Display.getDesktopDisplayMode());
					Display.setFullscreen(true);
				}
				else
					Display.setDisplayMode(new DisplayMode(MainStrategy.WIDTH, MainStrategy.HEIGHT));
				
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {e.printStackTrace();}
			return null;
		}
		else{
			Gui gui;
			initFrame(game);
			game.add(gui = new Gui(game));
			game.setVisible(true);
			try {
				Display.setParent(gui.getCanvas());
		        Display.create();
		        Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {e.printStackTrace(); }
			return gui;
		}
	}

	public static void createWindow(int width, int height, String title){
		Display.setTitle(title);
		Display.setLocation(0, 0);
		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch(LWJGLException e){
			System.out.println(e);
		}
	}
	
	public static void createWindow(String title,boolean fullscreen){
		Display.setTitle(title);
		try{
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setFullscreen(fullscreen);
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch(LWJGLException e){
			System.out.println(e);
		}
	}
	
	//OTHRES
	
	private static void initFrame(CoreEngine game) {
		game.setResizable(true);
		if(MainStrategy.FULLSCREEN){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			MainStrategy.WIDTH = (int)screenSize.getWidth();
			MainStrategy.HEIGHT = (int)screenSize.getHeight();
			game.setExtendedState(Frame.MAXIMIZED_BOTH);
			game.setUndecorated(true);
		}
		game.setTitle(MainStrategy.TITLE);
		game.setSize(MainStrategy.WIDTH, MainStrategy.HEIGHT);
		game.setDefaultCloseOperation(CoreStrategy.EXIT_ON_CLOSE);
	}

	public static void render(){
		Display.update();
	}

	public static void cleanUp(){
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}
	
	//GETTERS
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static int getWidth(){
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight(){
		return Display.getDisplayMode().getHeight();
	}
	
	public static Vector2f getCenter(){
		return new Vector2f(getWidth()/2,getHeight()/2);
	}
}
