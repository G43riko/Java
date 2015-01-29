package utils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.awt.Font;
import java.io.InputStream;
 


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
 
public class LogTester {
	private float rx = 0;
	private float ry = 0;
	private float rz = 0;
	private float x = 0;
	private float y = 0;
	private float z = 1;
 
	/** The fonts to draw to the screen */
	private TrueTypeFont font;
	private TrueTypeFont font2;
 
	/** Boolean flag on whether AntiAliasing is enabled or not */
	private boolean antiAlias = true;
 
	/**
	 * Start the test 
	 */
	public void start() {
		initGL(800,600);
		init();
 
		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
 
			Display.update();
			Display.sync(100);
 
			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		
	}
	
	public static void init2D(){
		GL11.glMatrixMode(GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL_MODELVIEW);
	}
 
	public static void init3D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(70,Display.getWidth()/Display.getHeight(),0.3f,1000);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public static void drawBlock(){
        glTexCoord2f(0,0); glVertex3f(-1,-1,1);
        glTexCoord2f(0,1); glVertex3f(-1,1,1);
        glTexCoord2f(1,1); glVertex3f(1,1,1);
        glTexCoord2f(1,0); glVertex3f(1,-1,1);

        //BackFace
        glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
        glTexCoord2f(0,1); glVertex3f(-1,1,-1);
        glTexCoord2f(1,1); glVertex3f(1,1,-1);
        glTexCoord2f(1,0); glVertex3f(1,-1,-1);

        //BottomFace
        glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
        glTexCoord2f(0,1); glVertex3f(-1,-1,1);
        glTexCoord2f(1,1); glVertex3f(-1,1,1);
        glTexCoord2f(1,0); glVertex3f(-1,1,-1);

        //TopFace
        glTexCoord2f(0,0); glVertex3f(1,-1,-1);
        glTexCoord2f(0,1); glVertex3f(1,-1,1);
        glTexCoord2f(1,1); glVertex3f(1,1,1);
        glTexCoord2f(1,0); glVertex3f(1,1,-1);

        //LeftFace
        glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
        glTexCoord2f(0,1); glVertex3f(1,-1,-1);
        glTexCoord2f(1,1); glVertex3f(1,-1,1);
        glTexCoord2f(1,0); glVertex3f(-1,-1,1);

        //Right Face
        glTexCoord2f(0,0); glVertex3f(-1,1,-1);
        glTexCoord2f(0,1); glVertex3f(1,1,-1);
        glTexCoord2f(1,1); glVertex3f(1,1,1);
        glTexCoord2f(1,0); glVertex3f(-1,1,1);
	}
	
	public void init() {
		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
	}
 
	public void render() {
		
		init2D();
		Color.white.bind();
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
		//font2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);
		init3D();
		drawBlock();
		
	}
	
	public void resetView(){
		glRotatef(0,1,0,0);
		glRotatef(0,0,1,0);
		glRotatef(0,0,0,1);
		glTranslatef(0,0,0);
	}
	
	public void useView(){
		glRotatef(this.rx,1,0,0);
		glRotatef(this.ry,0,1,0);
		glRotatef(this.rz,0,0,1);
		glTranslatef(this.x,this.y,this.z);
	}
 
	public static void main(String[] argv) {
		LogTester fontExample = new LogTester();
		fontExample.start();
	}
}
