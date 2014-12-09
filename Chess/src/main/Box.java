package main;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import java.awt.Color;


public class Box {
	private float x;
	private float y;
	private float z;
	
	private float w;
	private float h;
	private float d;
	
	private float rx;
	private float ry;
	private float rz;
	
	private float tw=1;
	private float th=1;
	private float td=1;
	
	private Color color=null;
	private Texture texture=null;
	private boolean repeat = true;
	public Box(float x, float y,float z,float w,float h,float d){
		this.x=x;
		this.y=y;
		this.z=z;
		
		this.w=w;
		this.h=h;
		this.d=d;
		
		this.rx=0;
		this.ry=0;
		this.rz=0;
	}
	
	public void setTexture(Texture text,boolean repeat){
		this.texture = text;
		this.repeat=repeat;
		if(repeat){
			th=h;
			tw=w;
			td=d;
		}
	}
	
	public void draw(){
		glPushMatrix();
		{
			if(this.color!=null){
				glColor3f(this.color.getRed(),this.color.getGreen(),this.color.getBlue());
			}
			glTranslatef(this.x,this.y,this.z);
			glRotatef(this.rx,1,0,0);
			glRotatef(this.ry,0,1,0);
			glRotatef(this.rz,0,0,1);
			if(this.texture!=null){
				this.texture.bind();
			}
			glBegin(GL_QUADS);
			{
				
				//FrontFace
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(-w,-h,d);
				if(this.texture!=null)glTexCoord2f(0,th); glVertex3f(-w,h,d);
				if(this.texture!=null)glTexCoord2f(tw,th); glVertex3f(w,h,d);
				if(this.texture!=null)glTexCoord2f(tw,0); glVertex3f(w,-h,d);

				//BackFace
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(-w,-h,-d);
				if(this.texture!=null)glTexCoord2f(0,th); glVertex3f(-w,h,-d);
				if(this.texture!=null)glTexCoord2f(tw,th); glVertex3f(w,h,-d);
				if(this.texture!=null)glTexCoord2f(tw,0); glVertex3f(w,-h,-d);

				//BottomFace
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(-w,-h,-d);
				if(this.texture!=null)glTexCoord2f(0,td); glVertex3f(-w,-h,d);
				if(this.texture!=null)glTexCoord2f(th,td); glVertex3f(-w,h,d);
				if(this.texture!=null)glTexCoord2f(th,0); glVertex3f(-w,h,-d);

				//TopFace
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(1*w,-1*h,-1*d);
				if(this.texture!=null)glTexCoord2f(0,td); glVertex3f(1*w,-1*h,1*d);
				if(this.texture!=null)glTexCoord2f(th,td); glVertex3f(1*w,1*h,1*d);
				if(this.texture!=null)glTexCoord2f(th,0); glVertex3f(1*w,1*h,-1*d);

				//LeftFace
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(-1*w,-1*h,-1*d);
				if(this.texture!=null)glTexCoord2f(0,tw); glVertex3f(1*w,-1*h,-1*d);
				if(this.texture!=null)glTexCoord2f(td,tw); glVertex3f(1*w,-1*h,1*d);
				if(this.texture!=null)glTexCoord2f(td,0); glVertex3f(-1*w,-1*h,1*d);

				//Right Face
				if(this.texture!=null)glTexCoord2f(0,0); glVertex3f(-w,h,-d);
				if(this.texture!=null)glTexCoord2f(0,tw); glVertex3f(w,h,-d);
				if(this.texture!=null)glTexCoord2f(td,tw); glVertex3f(w,h,d);
				if(this.texture!=null)glTexCoord2f(td,0); glVertex3f(-w,h,d);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		//this.color=new Color((int)Math.floor(Math.random()*2)*255,(int)Math.floor(Math.random()*2)*255,(int)Math.floor(Math.random()*2)*255,255);
	}
}
