package entities;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import javax.swing.text.Position;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import terrains.Block;
import main.Main;

public class Camera extends BasicEntity {
	public static final float ROTATION_SPEED = 2;
	public static final float MOVE_SPEED = 4;
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	//private float x, y, z;
	//private float dx, dy, dz;
	
	public Camera() {
		super(0, 0, -20, 45, 0, 0, 1);
		init3DProjection();
	}
	
	public static void init3DProjection(){
		//GL11.glEnable(GL11.GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_BLEND);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(FOV,(float)Display.getWidth()/(float)Display.getHeight(),NEAR_PLANE,FAR_PLANE);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public static void init2DProjection(){
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
              
		glClearDepth(1);                                       
        
        
        
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
        GL11.glViewport(0,0,Display.getWidth(),Display.getHeight());
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
	}
	
	public void useView(){
		System.out.println("c = "+x+" "+y+" "+z+" "+getRx()+" "+getRy()+" "+getRz());
		glRotatef(this.rx,1,0,0);
		glRotatef(this.ry,0,1,0);
		glRotatef(this.rz,0,0,1);
		glTranslatef(this.x,this.y,this.z);
	}
}
