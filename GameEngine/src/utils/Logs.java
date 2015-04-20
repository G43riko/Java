package utils;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Logs {
	TrueTypeFont font;
	public Logs(){
		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		
	}
	
	public void update(){
		
//		GL13.glActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glShadeModel(GL11.GL_SMOOTH);
//		
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_LIGHTING);  
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		
//		GL11.glEnable(GL11.GL_BLEND);
//		
//		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
//        GL11.glClearDepth(1);    
//
////        GL11.glViewport(0,0,800,600);
////		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
//		GL11.glOrtho(0, 800, 600, 0, 1, -1);
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//		
//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Color.white.bind();
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
		
//		glEnable(GL11.GL_CULL_FACE);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_BLEND);
		//font2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);
	}
	
	public static void initGL(int width, int height) {
//		try {
//			Display.setDisplayMode(new DisplayMode(width,height));
//			Display.create();
//			Display.setVSyncEnabled(true);
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

}
