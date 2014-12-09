package maps;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex3f;



import static org.lwjgl.opengl.GL20.glUseProgram;

import java.awt.Color;

import main.Utils;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Block {
	public float w,h,d;
	private int x,y,z;
	private int rx,ry,rz;
	private float tw,th,td;
	private boolean repeat;
	private double r,g,b;
	private int type;
	private int textureID = -1;
	private int shader = 0;
	private Color color = null;
	private static int[] texturesID = new int[]{-1,Utils.textureLoader("dirt.jpg")};
	
	public Block(int x, int y, int z,int type){
		this.x = x*(int)Map.width*2;
		this.y = y*(int)Map.height*2;
		this.z = z*(int)Map.depth*2;
		this.type = type;
		th=tw=td=1;
		rx=ry=rz=0;
		
		setRandColor();
		
		this.w = Map.width;
		this.h = Map.height;
		this.d = Map.depth;
		if(type>=0){
			this.textureID = texturesID[type];
		}
	}
	
	public void addShader(int shader){
		this.shader = shader;
		//System.out.println("pridal sa shader "+shader);
	}
	
	public int[] getSur(){
		return new int[]{this.x, this.y, this.z}; 
	}
	
	public void setSize(float w, float h, float d){
		this.w = w;
		this.h = h;
		this.d = d;
	}
	
//	public void setTexture(Texture text,boolean repeat){
//		this.texture = text;
//		this.repeat=repeat;
//		if(repeat){
//			th=h;
//			tw=w;
//			td=d;
//		}
//	}
	
	public void setRandColor(){
		this.r = Math.random();
		this.g = Math.random();
		this.b = Math.random();
	}
	
	public void setColor(Color color){
		this.r = color.getRed()/255;
		this.g = color.getGreen()/255;
		this.b = color.getBlue()/255;
	}
	
	public void draw(){
		if(type==0){
			return;
		}
		
		glPushMatrix();
		{
			
			if(this.type==-1){
				glColor3f((float)r,(float)g,(float)b);
			}
			else{
				glColor3f(1,1,1);
			}
			
			glTranslatef(this.x,this.y,this.z);
			glRotatef(this.rx,1,0,0);
			glRotatef(this.ry,0,1,0);
			glRotatef(this.rz,0,0,1);
			
			if(this.textureID>=0){
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			}
			
			glUseProgram(shader);
			
			glBegin(GL_QUADS);
			{
				//FrontFace
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(-w,-h,d);
				if(this.textureID>=0)glTexCoord2f(0,th); glVertex3f(-w,h,d);
				if(this.textureID>=0)glTexCoord2f(tw,th); glVertex3f(w,h,d);
				if(this.textureID>=0)glTexCoord2f(tw,0); glVertex3f(w,-h,d);

				//BackFace
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(-w,-h,-d);
				if(this.textureID>=0)glTexCoord2f(0,th); glVertex3f(-w,h,-d);
				if(this.textureID>=0)glTexCoord2f(tw,th); glVertex3f(w,h,-d);
				if(this.textureID>=0)glTexCoord2f(tw,0); glVertex3f(w,-h,-d);

				//BottomFace
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(-w,-h,-d);
				if(this.textureID>=0)glTexCoord2f(0,td); glVertex3f(-w,-h,d);
				if(this.textureID>=0)glTexCoord2f(th,td); glVertex3f(-w,h,d);
				if(this.textureID>=0)glTexCoord2f(th,0); glVertex3f(-w,h,-d);

				//TopFace
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(1*w,-1*h,-1*d);
				if(this.textureID>=0)glTexCoord2f(0,td); glVertex3f(1*w,-1*h,1*d);
				if(this.textureID>=0)glTexCoord2f(th,td); glVertex3f(1*w,1*h,1*d);
				if(this.textureID>=0)glTexCoord2f(th,0); glVertex3f(1*w,1*h,-1*d);

				//LeftFace
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(-1*w,-1*h,-1*d);
				if(this.textureID>=0)glTexCoord2f(0,tw); glVertex3f(1*w,-1*h,-1*d);
				if(this.textureID>=0)glTexCoord2f(td,tw); glVertex3f(1*w,-1*h,1*d);
				if(this.textureID>=0)glTexCoord2f(td,0); glVertex3f(-1*w,-1*h,1*d);

				//Right Face
				if(this.textureID>=0)glTexCoord2f(0,0); glVertex3f(-w,h,-d);
				if(this.textureID>=0)glTexCoord2f(0,tw); glVertex3f(w,h,-d);
				if(this.textureID>=0)glTexCoord2f(td,tw); glVertex3f(w,h,d);
				if(this.textureID>=0)glTexCoord2f(td,0); glVertex3f(-w,h,d);
			}
			glEnd();
			
		}
		glPopMatrix();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Color getColor() {
		return color;
	}
}

