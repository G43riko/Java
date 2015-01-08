package com.g43riko.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.util.vector.Vector2f;

public class Window {
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
