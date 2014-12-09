package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public Window(int width, int height, String title){
		Display.setTitle(title);
		Display.setLocation(0, 0);
		try{
			if(Game.fullscreen){
				Display.setDisplayMode(Display.getDesktopDisplayMode());
			}
			else{
				Display.setDisplayMode(new DisplayMode(width, height));
			}
			Display.setFullscreen(Game.fullscreen);
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch(LWJGLException e){
			System.out.println(e);
		}
	}

	public Window(int width, int height, String title, boolean can) {
		JFrame frame = new JFrame(title);
		Canvas canvas = new Canvas();
		frame.add(canvas, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
		try {
			Display.setParent(canvas);
	        Display.setVSyncEnabled(true);
	        Display.create();
	        Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		Display.sync(Main.fps);
		Display.update();
	}
	
	public void cleanUp()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
}
