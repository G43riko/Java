package org.engine.utils;

import java.awt.Font;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Log {
	private static HashMap<String, Log> logs = new HashMap<String, Log>();
	
	private static TrueTypeFont font;
	private static boolean antiAlias = true;
	private static boolean show = true;
	
	GVector2f position;
	GVector3f color; 
	int textSize;
	String text; 

	static{
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, antiAlias);
	}
	
	private Log(String text, GVector2f position, int textSize, GVector3f color){
		this.position = position;
		this.textSize = textSize;
		this.color = color;
		this.text = text;
	}
	
	public static void addLog(String text, GVector2f position, int textSize, GVector3f color){
		logs.put(text, new Log(text, position, textSize, color));
	}
	
	
	public static void renderAll(){
		init();
		logs.entrySet().stream().map(a -> a.getValue()).forEachOrdered(Log::render);
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
		show = !show;
	}
	
	private static void render(Log log){
		render(log.text, log.position, log.textSize, log.color);
	}
	
	public static void render(String text, GVector2f position, int textSize, GVector3f color){
		TextureImpl.bindNone();
		init();
		GL20.glUseProgram(0);
		new TrueTypeFont(new Font("Times New Roman", 
								  Font.BOLD, 
								  textSize), antiAlias).drawString(position.getX(), 
										  						   position.getY(), 
										  						   text,
										  						   new Color(color.getX() * 255, 
										  								     color.getY() * 255, 
										  								     color.getZ() * 255));
		GL11.glDisable(GL11.GL_BLEND);
	};
	
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
