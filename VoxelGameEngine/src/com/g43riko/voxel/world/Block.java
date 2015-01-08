package com.g43riko.voxel.world;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Block {
	public static final int WIDTH = 1;
	public static final int HEIGHT = 1;
	public static final int DEPTH = 1;
	
	private int textureID = 0;
	private boolean isActive;
	private Vector3f position,color;
	private int type;
	private boolean[] faces;
	public Block(int x, int y, int z,int type){
		isActive = true;
		if(type==0){
			isActive = false;
		}
		position = new Vector3f(x*WIDTH*2,y*HEIGHT*2,z*DEPTH*2);
		color = new Vector3f((float)Math.random(),(float)Math.random(),(float)Math.random());
		this.type = type;
	};
	
	public void draw(){
		glPushMatrix();
		{
			glColor3f(color.x,color.y,color.z);
			
			glTranslatef(position.x,position.y,position.z);
			glRotatef(0,1,0,0);
			glRotatef(0,0,1,0);
			glRotatef(0,0,0,1);
			
//			glUseProgram(shader);
			int th,tw,td,h,w,d;
			th=td=tw=h=w=d=1;
            glBegin(GL_QUADS);
            {
                //front face
            	if(faces[0]){
            		glNormal3f(0f, 0f, 1f);
	                glTexCoord2f(0f, 1f);glVertex3f( WIDTH, -HEIGHT, -DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(-WIDTH, -HEIGHT, -DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(-WIDTH, HEIGHT, -DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f( WIDTH, HEIGHT, -DEPTH); // 4
            	}
                //right face
            	if(faces[1]){
	                glNormal3f(0f, 0f, -1f);
	                glTexCoord2f(0f, 1f);glVertex3f(-WIDTH, -HEIGHT, DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(WIDTH, -HEIGHT, DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(WIDTH, HEIGHT, DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f(-WIDTH, HEIGHT, DEPTH); // 4
            	}
                //top face
                if(faces[2]){
	                glNormal3f(0f, -1f, 0f);
	                glTexCoord2f(0f, 1f);glVertex3f(WIDTH, HEIGHT, -DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(-WIDTH, HEIGHT, -DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(-WIDTH, HEIGHT, DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f(WIDTH, HEIGHT, DEPTH); // 4
                }
                //bottom face
                if(faces[3]){
	                glNormal3f(0f, 1f, 0f);
	                glTexCoord2f(0f, 1f);glVertex3f(WIDTH, -HEIGHT, DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(-WIDTH, -HEIGHT, DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(-WIDTH, -HEIGHT, -DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f(WIDTH, -HEIGHT, -DEPTH); // 4 
                }
                //left face
                if(faces[4]){
	                glNormal3f(-1f, 0f, 0f);
	                glTexCoord2f(0f, 1f);glVertex3f(WIDTH, -HEIGHT, DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(WIDTH, -HEIGHT, -DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(WIDTH, HEIGHT, -DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f(WIDTH, HEIGHT, DEPTH); // 4
                }
                //right face
                if(faces[5]){
	                glNormal3f(1f, 0f, 0f);
	                glTexCoord2f(0f, 1f);glVertex3f(-WIDTH, -HEIGHT, -DEPTH); //1
	                glTexCoord2f(1f, 1f);glVertex3f(-WIDTH, -HEIGHT, DEPTH); // 2
	                glTexCoord2f(1f, 0f);glVertex3f(-WIDTH, HEIGHT, DEPTH); // 3
	                glTexCoord2f(0f, 0f);glVertex3f(-WIDTH, HEIGHT, -DEPTH); // 4
                }
            }
            glEnd();
			
		}
		glPopMatrix();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean[] getFaces() {
		return faces;
	}

	public void setFaces(boolean[] faces) {
		this.faces = faces;
	}
}
