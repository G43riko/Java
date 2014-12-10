package utils;

import java.awt.Font;

import main.Main;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Logs {
	TrueTypeFont font;
	private boolean antiAlias = true;
	
	public Logs(){
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, antiAlias);
        
        
	}
	
	public void update(){
		Color.white.bind();
		
	    font.drawString(50, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY",Color.yellow);
		
	}

}
