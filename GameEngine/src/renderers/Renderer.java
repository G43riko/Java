package renderers;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.lwjgl.opengl.GL11;

import menus.RMenu;


public class Renderer {
	
	
	public static void clearScreen(RMenu rmenu){
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//glLoadIdentity();
		
		GL11.glClearColor((float)rmenu.BGRed.getValue()/255,(float)rmenu.BGGreen.getValue()/255,(float)rmenu.BGBlue.getValue()/255, 1.0f);
	}
	
	public static void initGraphics(){
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		
		glEnable(GL_DEPTH_TEST);
//		glFrontFace(GL_CW);
//		glCullFace(GL_BACK);
//		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH);
		glEnable(GL_DEPTH_TEST);
//		glEnable(GL_FRAMEBUFFER_SRGB);
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void change(int co){
		if(glIsEnabled(co)){
			glDisable(co);
		}
		else{
			glEnable(co);
		}
	}
	
	
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}
}
