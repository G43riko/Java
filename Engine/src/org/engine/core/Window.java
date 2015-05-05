package org.engine.core;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import org.engine.gui.Gui;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class Window {
	private final static String TITLE = "Game Engine";
	private static Gui gui;
	
	public static Gui createWindow(CoreEngine game, boolean fullScreen) {
		if(fullScreen){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			game.setWidth((int)screenSize.getWidth());
			game.setHeight((int)screenSize.getHeight());
			game.setExtendedState(Frame.MAXIMIZED_BOTH);
			game.setUndecorated(true);
		}
		
		game.setSize(game.getWidth(), game.getHeight());
		game.setTitle(TITLE);
		game.setResizable(true);
		game.setDefaultCloseOperation(CoreEngine.EXIT_ON_CLOSE);
		
		game.add(gui = new Gui(game));
		game.setVisible(true);
		
		initOpenGL(game);
		
		return gui;
	}
	
	private static void initOpenGL(CoreEngine game){
		try {
			Display.setParent(gui.getCanvas());
	        Display.create();
	        Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			System.out.println("pri vytvárani okna nastala chyba: "+e);
		}
	}

	public static void cleanUp(){
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}
	
	public static Vector2f getCenter(){
		return new Vector2f(Display.getWidth()/2,Display.getHeight()/2);
	}
}
