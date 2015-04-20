package main;

import java.awt.Canvas;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Window {

	public Window(Canvas canvas) {
		//ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setParent(canvas);
//			Display.setVSyncEnabled(Main.VSYNC);
	        //Display.create(new PixelFormat(), attribs);
	        Display.create();
	        Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		Display.update();
//		Display.sync(Main.FPS);
	}
	
	public void cleanUp(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

}
