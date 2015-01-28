package com.voxel.core;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import com.voxel.gui.Gui;
import com.voxel.main.MainVoxel2;

public class Window {
	
	public static Gui createWindow(Game game){
		Gui gui = new Gui(game);
		if(game == null){
			Display.setLocation(0, 0);
			Display.setTitle(MainVoxel2.TITLE);
			try {
				if(MainVoxel2.FULLSCREEN){
					Display.setDisplayMode(Display.getDesktopDisplayMode());
					Display.setFullscreen(true);
				}
				else{
					Display.setDisplayMode(new DisplayMode(MainVoxel2.WIDTH, MainVoxel2.HEIGHT));
				}
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {e.printStackTrace();}
			return null;
		}
		else{
			initFrame(game);
			game.add(gui);
			try {
				Display.setParent(gui.getCanvas());
		        Display.create();
		        Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {e.printStackTrace(); }
//			gui.ini
			return gui;
		}
	}
	
	
	
	private static void initFrame(Game game) {
		game.setResizable(true);
		if(MainVoxel2.FULLSCREEN){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			MainVoxel2.WIDTH = (int)screenSize.getWidth();
			MainVoxel2.HEIGHT = (int)screenSize.getHeight();
			game.setExtendedState(Frame.MAXIMIZED_BOTH);
			game.setUndecorated(true);
		}
		game.setVisible(true);
		game.setTitle(MainVoxel2.TITLE);
		game.setSize(MainVoxel2.WIDTH, MainVoxel2.HEIGHT);
		game.setDefaultCloseOperation(Game.EXIT_ON_CLOSE);
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
	
	public static void createWindow(int width, int height, String title,boolean fullscreen){
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
	
	public static void render(){
		Display.update();
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static int getWidth(){
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight(){
		return Display.getDisplayMode().getHeight();
	}
	
	public static void dispose()
	{
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}
	
	public static Vector2f getCenter(){
		return new Vector2f(getWidth()/2,getHeight()/2);
	}
}
