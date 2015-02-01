package minecraft2D.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import minecraft2D.main.MainMinecraft2D;

public class GameCore {
	private boolean running = false;
	public GameCore(){
		 
		try {
			Display.setTitle(MainMinecraft2D.TITLE);
			Display.setResizable(true); 
			Display.setDisplayMode(new DisplayMode(MainMinecraft2D.WIDTH, MainMinecraft2D.HEIGHT));
			Display.setVSyncEnabled(MainMinecraft2D.VSYNC);
			Display.setFullscreen(MainMinecraft2D.FULLSCREEN);
			Display.create();
			
		} catch (LWJGLException e) {e.printStackTrace();}
		initDisplay();
	}
	
	protected void start(){
		running = true;
		while(running && !Display.isCloseRequested()){
			grender();
			Display.update();
			Display.sync(60);
		}
	}

	private void grender() {
		glClear(GL_COLOR_BUFFER_BIT);
		render();
	}
	
	protected void render(){
		
	}
	
	private void initDisplay(){
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		
		glDisable(GL_DEPTH_TEST);
			
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClearColor(0f, 0f, 0f, 0f);
	}
}
