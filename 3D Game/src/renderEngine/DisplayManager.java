package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;
	private static final boolean FULLSCREEN = false;
	
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,0).withForwardCompatible(true);//.withProfileCore(true);
		if(FULLSCREEN){
			try {
				Display.setDisplayMode(Display.getDesktopDisplayMode());
				Display.setFullscreen(true);
				Display.create(new PixelFormat(),attribs);
				Display.setTitle("First 3D Game");
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GL11.glViewport(0, 0, Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight());
		}
		else{
			try {
				Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
				Display.setFullscreen(true);
				Display.create(new PixelFormat(),attribs);
				Display.setTitle("First 3D Game");
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GL11.glViewport(0, 0, WIDTH, HEIGHT);
		}
	};
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
		
	};
	
	public static void closeDisplay(){
		Display.destroy();
	};
}
