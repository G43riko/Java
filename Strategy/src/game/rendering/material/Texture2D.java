package game.rendering.material;

import glib.util.vector.GVector3f;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.Texture;

public class Texture2D {
	private class Data{
		private Texture2D t;
		private int count;
		
		public Data(Texture2D t){
			this.t = t;
			count = 1;
		}
	}
	
	private static HashMap<String, Data> loadedTextures = new HashMap<String, Data>();
	public static int maxSize;
	
	public static final int FILTER_LINEAR = GL_LINEAR;
    public static final int FILTER_NEAREST = GL_NEAREST;

    public static final int WRAP_CLAMP = GL_CLAMP;
    public static final int WRAP_CLAMP_TO_EDGE = GL_CLAMP_TO_EDGE;
    public static final int WRAP_REPEAT = GL_REPEAT;
    private GVector3f averageColor;
    private static final int BPP = 4;
	
    private static boolean mipMapping = true;
    
    private int filtering = FILTER_NEAREST;
    private int wrapMode = WRAP_REPEAT;
    
    private int id;
    
    private int width;
	private int height;
	
	private String fileName;
	
	
	public Texture2D(String fileName){
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOld(loadedTextures.get(fileName));
		}
		else{
			addTextureToOpenGL(makeByteBufferFILE(fileName));
			loadedTextures.put(fileName, new Data(this));
		}
		maxSize = glGetInteger(GL_MAX_TEXTURE_SIZE);
	}
	
	public Texture2D(String fileName, URL url){
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOld(loadedTextures.get(fileName));
		}
		else{
			addTextureToOpenGL(makeByteBufferURL(url));
			loadedTextures.put(fileName, new Data(this));
		}
	}
	
	public Texture2D(String fileName, Texture tex, GVector3f averageColor){
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOld(loadedTextures.get(fileName));
		}
		else{
			this.id = tex.getTextureID();
			this.width = tex.getImageWidth();
			this.height = tex.getImageHeight();
			this.averageColor = averageColor;
			bind();
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filtering);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filtering);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapMode);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapMode);
			
			loadedTextures.put(fileName, new Data(this));
		}
	}
	
	public ByteBuffer makeByteBufferURL(URL url){
		try {
			InputStream input = url.openStream();
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
	
	public ByteBuffer makeByteBufferFILE(String fileName){
		try{
			BufferedImage image = ImageIO.read(new File("res/textures/"+fileName));
			width = image.getWidth();
			height = image.getHeight();
			int[] pixels = image.getRGB(0, 0, width, height, null, 0, image.getWidth());
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(BPP * width * height);
			
			boolean hasAlpha = image.getColorModel().hasAlpha();
			double red = 0;
			double green = 0;
			double blue = 0;
			double num = 0;
			
			for(int y=0 ; y<height ; y++){
				for(int x=0 ; x<height ; x++){
					red += new Color(image.getRGB(x, y)).getRed();
					green += new Color(image.getRGB(x, y)).getGreen();
					blue += new Color(image.getRGB(x, y)).getBlue();
					num++;
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
			averageColor = new GVector3f(red/num/255, green/num/255, blue/num/255);
			buffer.flip();
			return buffer;
		}
		catch(Exception e){ System.err.println(e); System.out.println("obr·zok "+fileName+" sa nepodarilo naËÌtaù"); }
		return null;
	}
	
	public void addTextureToOpenGL(ByteBuffer buf){
		id = glGenTextures();
		bind();
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filtering);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filtering);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapMode);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		
		if(mipMapping){
			GL30.glGenerateMipmap(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		}
	}
	
	public void finalize(){
		if(loadedTextures.get(fileName).count == 1)
			loadedTextures.remove(fileName);
		else
			loadedTextures.get(fileName).count--;
	}
	
	private void loadOld(Data d){
		id = d.t.getId();
		width = d.t.getWidth();
		height = d.t.getHeight();
		averageColor = d.t.getAverageColor();
		d.count++;
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D,0);
	}
	
	public void bind() {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public static void setMipMapping(boolean mipMapping) {Texture2D.mipMapping = mipMapping;}
	
	public void setFiltering(int filtering) {
		this.filtering = filtering;
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filtering);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filtering);
		unbind();
	}
	
	public void setWrapMode(int wrapMode) {
		this.wrapMode = wrapMode;
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapMode);
		unbind();
	}

	public GVector3f getAverageColor() {return averageColor; }
	public int getId(){return id; }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
