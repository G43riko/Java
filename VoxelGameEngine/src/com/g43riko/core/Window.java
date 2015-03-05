package com.g43riko.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import com.g43riko.MainVoxel;

public class Window {
	private static int fps;
	public static void createWindow(String title, int width, int height, int fps){
		Window.fps = fps;
		Display.setLocation(0, 0);
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {e.printStackTrace();}
	}
	
	public static void render(){
		Display.update();
		Display.sync(fps);
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
	
	public static void cleanUp(){
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}
	
	public static Vector2f getCenter(){
		return new Vector2f(getWidth()/2,getHeight()/2);
	}
}
