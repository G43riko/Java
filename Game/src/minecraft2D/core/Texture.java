package minecraft2D.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.PNGDecoder;

public class Texture {
	public static int maxSize = glGetInteger(GL_MAX_TEXTURE_SIZE);
	
	public static final int LINEAR = GL_LINEAR;
    public static final int NEAREST = GL_NEAREST;

    public static final int CLAMP = GL_CLAMP;
    public static final int CLAMP_TO_EDGE = GL_CLAMP_TO_EDGE;
    public static final int REPEAT = GL_REPEAT;
    
    private static final int BPP = 4;
	
    private int id;
    
    private int width;
	private int height;
    
//	private float u1;
//	private float v1;
//	private float u2;
//	private float v2;	
	
	public ByteBuffer makeBuffer(String url){
		try {
			InputStream input = new URL(url).openStream();
			PNGDecoder dec = new PNGDecoder(input);
			
			width = dec.getWidth();
			height = dec.getHeight();
			
			ByteBuffer buf = BufferUtils.createByteBuffer(BPP * width * height);
			
			dec.decode(buf, width * BPP, PNGDecoder.RGBA);
			
			buf.flip();
			
			return buf;
		} catch (IOException e) {e.printStackTrace(); }
		
		return null;
	}
	
	
	
	public void init(){
		
			ByteBuffer buf = makeBuffer("http://people.mozilla.org/~faaborg/files/shiretoko/firefoxIcon/firefox-128.png");
			
			glEnable(GL_TEXTURE_2D);
			
			id = glGenTextures();
			bind();
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

			//Setup filtering, i.e. how OpenGL will interpolate the pixels when scaling up or down
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			//Setup wrap mode, i.e. how OpenGL will handle pixels outside of the expected range
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf); 
			
	}
	
	private void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public static void debugTexture(Texture tex, float x, float y, float width, float height) {
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    glEnable(GL_TEXTURE_2D);
	    
	    tex.bind();
	    
	    float u1 = 0f;
	    float v1 = 0f;
	    float u2 = 1f;
	    float v2 = 1f;

	    //immediate mode is deprecated -- we are only using it for quick debugging
	    glColor4f(1f, 1f, 1f, 1f);
	    glBegin(GL_QUADS);
	        glTexCoord2f(u1, v1);
	        glVertex2f(x, y);
	        glTexCoord2f(u1, v2);
	        glVertex2f(x, y + height);
	        glTexCoord2f(u2, v2);
	        glVertex2f(x + width, y + height);
	        glTexCoord2f(u2, v1);
	        glVertex2f(x + width, y);
	    glEnd();
	}
}
