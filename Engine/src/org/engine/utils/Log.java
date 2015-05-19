package org.engine.utils;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

public class Log {
	private static TrueTypeFont font;
	private static boolean antiAlias = true;
	private static boolean show = true;
	static{
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, antiAlias);
		
	}
	
	private static void init(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
//		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
//        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
//        GL11.glViewport(0,0,800,600);
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public static void toogle(){
		show = show == false;
	}
	
	public static void render(int fps, int poligons, int points){
		if(!show)
			return;
		
		TextureImpl.bindNone();
		init();
		GL20.glUseProgram(0);
		
		Color.white.bind();
		font.drawString(0, 0, "FPS: "+fps);
		font.drawString(0, 24, "rendered poligons: "+poligons);
		font.drawString(0, 48, "rendered points: "+points);
	}
}
