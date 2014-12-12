package entities;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import utils.FileLoader;

public abstract class TexturedEntity extends BasicEntity{
	private float tw,th,td;
	private int width;
	private int height;
	private int depth;
	private boolean textureRepeat;
	private int texture;
	private Color color;
	
	public TexturedEntity(float x, float y, float z, float rx, float ry, float rz,int width, int height, int depth, float scale) {
		super(x, y, z, rx, ry, rz, scale);
		th=tw=td=1;
		color = new Color(0,1,1);
		this.texture = 0;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public void setTexture(String filename){
		if(filename == null){
			return;
		}
		texture = FileLoader.textureLoader(filename);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}
	
	public void setTexture(int texture){
		this.texture = texture;
	}
	
	public void setRepeat(boolean repeat){
		this.textureRepeat = repeat;
		if(repeat){
			tw = width;
			th = height;
			td = depth;
		}
		else{
			th=tw=td=1;
		}
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void draw(){
		glPushMatrix();
		{
			
			if(this.texture<=0||!glIsEnabled(GL11.GL_TEXTURE_2D))
				glColor3f(color.getRed(), color.getGreen(), color.getBlue());
			else
				glColor3f(1,1,1);
			
			glTranslatef(this.x,this.y,this.z);
			glRotatef(this.rx,1,0,0);
			glRotatef(this.ry,0,1,0);
			glRotatef(this.rz,0,0,1);
			
			if(this.texture>=0&&glIsEnabled(GL11.GL_TEXTURE_2D))
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
			
			//glUseProgram(shader);
			
			glBegin(GL_QUADS);
			{
				//FrontFace
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(-width,-height,depth);
				if(this.texture>=0)glTexCoord2f(0,th); glVertex3f(-width,height,depth);
				if(this.texture>=0)glTexCoord2f(tw,th); glVertex3f(width,height,depth);
				if(this.texture>=0)glTexCoord2f(tw,0); glVertex3f(width,-height,depth);

				//BackFace
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(-width,-height,-depth);
				if(this.texture>=0)glTexCoord2f(0,th); glVertex3f(-width,height,-depth);
				if(this.texture>=0)glTexCoord2f(tw,th); glVertex3f(width,height,-depth);
				if(this.texture>=0)glTexCoord2f(tw,0); glVertex3f(width,-height,-depth);

				//BottomFace
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(-width,-height,-depth);
				if(this.texture>=0)glTexCoord2f(0,td); glVertex3f(-width,-height,depth);
				if(this.texture>=0)glTexCoord2f(th,td); glVertex3f(-width,height,depth);
				if(this.texture>=0)glTexCoord2f(th,0); glVertex3f(-width,height,-depth);

				//TopFace
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(1*width,-1*height,-1*depth);
				if(this.texture>=0)glTexCoord2f(0,td); glVertex3f(1*width,-1*height,1*depth);
				if(this.texture>=0)glTexCoord2f(th,td); glVertex3f(1*width,1*height,1*depth);
				if(this.texture>=0)glTexCoord2f(th,0); glVertex3f(1*width,1*height,-1*depth);

				//LeftFace
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(-1*width,-1*height,-1*depth);
				if(this.texture>=0)glTexCoord2f(0,tw); glVertex3f(1*width,-1*height,-1*depth);
				if(this.texture>=0)glTexCoord2f(td,tw); glVertex3f(1*width,-1*height,1*depth);
				if(this.texture>=0)glTexCoord2f(td,0); glVertex3f(-1*width,-1*height,1*depth);

				//Right Face
				if(this.texture>=0)glTexCoord2f(0,0); glVertex3f(-width,height,-depth);
				if(this.texture>=0)glTexCoord2f(0,tw); glVertex3f(width,height,-depth);
				if(this.texture>=0)glTexCoord2f(td,tw); glVertex3f(width,height,depth);
				if(this.texture>=0)glTexCoord2f(td,0); glVertex3f(-width,height,depth);
			}
			glEnd();
		}
		glPopMatrix();
	}
}
