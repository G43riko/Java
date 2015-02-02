package minecraft2D.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;



import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.PNGDecoder;

public class Texture {
	public static int maxSize = glGetInteger(GL_MAX_TEXTURE_SIZE);
	
	public static final int FILTER_LINEAR = GL_LINEAR;
    public static final int FILTER_NEAREST = GL_NEAREST;

    public static final int WRAP_CLAMP = GL_CLAMP;
    public static final int WRAP_CLAMP_TO_EDGE = GL_CLAMP_TO_EDGE;
    public static final int WRAP_REPEAT = GL_REPEAT;
    
    private static final int BPP = 4;
	
    private static boolean mipMapping = true;
    
    private int filtering;
    private int wrapMode;
    
    private int id;
    
    private int width;
	private int height;
	
	private String fileName;
	private String url;
	
	public Texture(String fileName){
		this.fileName = fileName;
		filtering = FILTER_NEAREST;
		wrapMode = WRAP_REPEAT;
		addTextureToOpenGL();
	}
	
	public ByteBuffer makeByteBufferURL(){
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
	
	public ByteBuffer makeByteBufferFILE(){
		try{
			BufferedImage image = ImageIO.read(new File("res/textures/"+fileName));
			width = image.getWidth();
			height = image.getHeight();
			int[] pixels = image.getRGB(0, 0, width, height, null, 0, image.getWidth());
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(BPP * width * height);
			
			boolean hasAlpha = image.getColorModel().hasAlpha();
			
			for(int y=0 ; y<height ; y++){
				for(int x=0 ; x<height ; x++){
					int pixel = pixels[y * width + x];
					buffer.put((byte)((pixel >> 16)&0xFF));
					buffer.put((byte)((pixel >> 8)&0xFF));
					buffer.put((byte)((pixel)&0xFF));
					
					if(hasAlpha)
						buffer.put((byte)((pixel >> 24)&0xFF));
					else
						buffer.put((byte)(0xFF));
				}
			}
			
			buffer.flip();
			return buffer;
		}
		catch(Exception e){ System.exit(1); }
		return null;
	}
	
	public void addTextureToOpenGL(){
			ByteBuffer buf = makeByteBufferFILE();
			
			id = glGenTextures();
			
			bind();
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

			//Setup filtering, i.e. how OpenGL will interpolate the pixels when scaling up or down
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filtering);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filtering);

			//Setup wrap mode, i.e. how OpenGL will handle pixels outside of the expected range
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapMode);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapMode);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
			
			if(mipMapping){
				GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
				glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
				glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
			}
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D,0);
	}
	
	public void bind() {
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
	

	public static void setMipMapping(boolean mipMapping) {Texture.mipMapping = mipMapping;}
	public void setFiltering(int filtering) {this.filtering = filtering;}
	public void setWrapMode(int wrapMode) {this.wrapMode = wrapMode;}

}
