package utils;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.awt.Font;
 




import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
 
public class LogTester {
	private TrueTypeFont font;
 
	private boolean antiAlias = true;
 
	private int width = 800;
	private int height = 600;

	//INITIALIZATIONS
	
	private void init2D(){     
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
               
        GL11.glClearDepth(1);                                       
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void init3D(){
		glEnable(GL_DEPTH);
		glEnable(GL_DEPTH_TEST);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
		glMatrixMode(GL_MODELVIEW);
	}
	
	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
 
	public void init() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);   
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  
		
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, antiAlias);
	}
	
	//RENDERERS
	
	private void renderBlock() {
		glLoadIdentity();
		useView();
		
		glPushMatrix();
		{
			glColor3f(1,0,1);
			
			glTranslatef(0,0,5);
			glRotatef(0,1,0,0);
			glRotatef(0,0,1,0);
			glRotatef(0,0,0,1);
			
			float w = 1;
			float h = 1;
			float d = 1;
			glBegin(GL_QUADS);
			{
				
				//FrontFace
				glVertex3f(-w,-h,d);
				glVertex3f(-w,h,d);
				glVertex3f(w,h,d);
				glVertex3f(w,-h,d);

				//BackFace
				glVertex3f(-w,-h,-d);
				glVertex3f(-w,h,-d);
				glVertex3f(w,h,-d);
				glVertex3f(w,-h,-d);

				//BottomFace
				glVertex3f(-w,-h,-d);
				glVertex3f(-w,-h,d);
				glVertex3f(-w,h,d);
				glVertex3f(-w,h,-d);

				//TopFace
				glVertex3f(1*w,-1*h,-1*d);
				glVertex3f(1*w,-1*h,1*d);
				glVertex3f(1*w,1*h,1*d);
				glVertex3f(1*w,1*h,-1*d);

				//LeftFace
				glVertex3f(-1*w,-1*h,-1*d);
				glVertex3f(1*w,-1*h,-1*d);
				glVertex3f(1*w,-1*h,1*d);
				glVertex3f(-1*w,-1*h,1*d);

				//Right Face
				glVertex3f(-w,h,-d);
				glVertex3f(w,h,-d);
				glVertex3f(w,h,d);
				glVertex3f(-w,h,d);
			}
			glEnd();
		}
		glPopMatrix();
		
	}

	public void renderText() {
		Color.white.bind();
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
	}

	//OTHERS

	public void useView(){
		glRotatef(0,1,0,0);
		glRotatef(0,0,1,0);
		glRotatef(0,0,0,1);
		glTranslatef(0,0,0);
	}
	
	public void start() {
		initGL();
		init();
		
		while (true) {
//			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			init2D();
			renderText();
			init3D();
			renderBlock();
			Display.update();
			Display.sync(100);
 
			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] argv) {
		LogTester fontExample = new LogTester();
		fontExample.start();
	}
}