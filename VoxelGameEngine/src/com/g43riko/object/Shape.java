package com.g43riko.object;

import static org.lwjgl.opengl.GL11.*;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import com.g43riko.Util.Loader;
import com.g43riko.rendering.RawModel;
import com.g43riko.rendering.RenderingEngine;

public class Shape extends GameObject{
	private RawModel rawModel;
	public Shape(){
		float w = .5f;
		float h = .5f;
		float d = 1f;
		
		float[] vertices = new float[]{-w,h,-d,	
									   -w,-h,-d,	
									   	w,-h,-d,	
									   	w,h,-d};
		float[] texture ={0,1,
				 		  0,1,
				 		  0,1,
				 		  0,1};
		
		int[] indices ={3,1,0,	
						2,1,3};
		
		float[] normals = {0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f};
		
		rawModel = new Loader().loadToVAO(vertices, texture, normals, indices);
	}
	
	public void render(RenderingEngine renderingEngine){
		renderingEngine.renderShape(this);
	}

	public RawModel getModel() {
		return rawModel;
	}
	
	/*
	public void render(RenderingEngine renderingEngine){
//		renderingEngine.renderShape(this);
		
		RenderingEngine.DEFAULT_SHADER.bind();
		glPushMatrix();
		{
			glTranslatef(0,0,-10);
			glRotatef(0,1,0,0);
			glRotatef(0,0,1,0);
			glRotatef(0,0,0,1);
			
			glBegin(GL_TRIANGLES);
			{
				glColor3f(1,0,0);	glVertex3f(-0.5f , -0.5f,0);
				glColor3f(0,1,0);	glVertex3f( 0.5f , -0.5f,0);
				glColor3f(0,0,1);	glVertex3f( 0    ,  0.5f,0 );
			}
			glEnd();
			
		}
		glPopMatrix();
	}
	*/
}
