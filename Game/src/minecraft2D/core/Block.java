package minecraft2D.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

import glib.util.vector.GVector2f;

public class Block {
	public final static int WIDTH = 100;
	public final static int HEIGHT = 100;
	
	private GVector2f position;
	private int width;
	private int height;
	private Texture texture;
	
	public Block(float x,float y, Texture texture){
		this(x,y,texture,WIDTH,HEIGHT);
	}
	
	public Block(float x,float y, Texture texture, int width, int height ){
		this.texture = texture;
		position = new GVector2f(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void render(Shader shader){
		texture.bind();
		glUseProgram(shader.getId());
		glBegin(GL_TRIANGLES);
		{
			
			glTexCoord2f(0,0);	glVertex2f(position.getX(),	position.getY());
			glTexCoord2f(1,0);	glVertex2f(position.getX() + width, position.getY());
			glTexCoord2f(0,1);	glVertex2f(position.getX(),	position.getY() + height );
			
			glTexCoord2f(1,1);	glVertex2f(position.getX() + width,	position.getY() + height);
			glTexCoord2f(1,0);	glVertex2f(position.getX() + width, position.getY());
			glTexCoord2f(0,1);	glVertex2f(position.getX(),	position.getY() + height );
			
		}
		glEnd();
	}
}
