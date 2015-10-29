package org.engine.rendering.material;

import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.engine.Config;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import glib.util.GColor;
import glib.util.GDebug;
import glib.util.ResourceLoader;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Texture2D {
	private static HashMap<String, Texture2D> loadedTextures = new HashMap<String, Texture2D>();
	private final static int BPP = 4;
	
	private final static boolean DEFAULT_MIPMAPPING = true;
	
	private GVector3f averageColor = new GVector3f(0.5f);
	private GVector2f resolution;
	private String fileName;
	private int id;
	
	//CONSTRUCTORS
	
	
	public Texture2D(String fileName) {
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOldTexture(loadedTextures.get(fileName));
			return;
		}
		addTextureToOpenGL(makeByteBufferFromFile(fileName));
		loadedTextures.put(fileName, this);
	}
	
	public Texture2D(String fileName, boolean useSlickUtil) {
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOldTexture(loadedTextures.get(fileName));
			return;
		}
		addTextureToOpenGL(fileName);
		
		loadedTextures.put(fileName, this);
	}

	public Texture2D(String fileName, int id, GVector2f resolution) {
		this.fileName = fileName;
		this.resolution = resolution;
		if(loadedTextures.containsKey(fileName)){
			loadOldTexture(loadedTextures.get(fileName));
			return;
		}
		this.id = id;
	}

	//OTHERS
	

	public Texture2D(GVector3f color, GVector2f resolution) {
		this.fileName = color.toString();
		this.resolution = resolution;
		this.averageColor = color;
		if(loadedTextures.containsKey(fileName)){
			loadOldTexture(loadedTextures.get(fileName));
			return;
		}
		addTextureToOpenGL(makeByteBufferFromFile(color));
		
		loadedTextures.put(fileName, this);
	}

	private void addTextureToOpenGL(String fileName) {
		try {
			Texture texture = TextureLoader.getTexture(fileName.split("\\.")[1], ResourceLoader.load("textures/"+fileName));
			id = texture.getTextureID();
			resolution = new GVector2f(texture.getImageWidth(), texture.getImageHeight());
		} catch (IOException e) {
			GDebug.logError("bastala chyba pri naËÌtavanÌ obr·zku", "Texture2D", e);
		}
	}

	
	public void addTextureToOpenGL(ByteBuffer buf){
		id = glGenTextures();
		bind();
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, Config.TEXTURE_DEFAULT_FILTER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, Config.TEXTURE_DEFAULT_FILTER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, Config.TEXTURE_DEFAULT_WRAP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, Config.TEXTURE_DEFAULT_WRAP);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, resolution.getXi(), resolution.getYi(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		
		glTexParameterf(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 16.0f);
		
		if(DEFAULT_MIPMAPPING){
			GL30.glGenerateMipmap(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		}
	}
	
	public ByteBuffer makeByteBufferFromFile(GVector3f color){
		ByteBuffer buffer = BufferUtils.createByteBuffer(BPP * resolution.getXi() * resolution.getYi());
		int pixel = new GColor(color.mul(255)).getRGB();
		
		for(int y=0 ; y<resolution.getYi() ; y++){
			for(int x=0 ; x<resolution.getYi() ; x++){
				buffer.put((byte)((pixel >> 16)&0xFF));
				buffer.put((byte)((pixel >> 8)&0xFF));
				buffer.put((byte)((pixel)&0xFF));
				buffer.put((byte)(0xFF));
			}
		}
		buffer.flip();
		return buffer;
	}
	
	public ByteBuffer makeByteBufferFromFile(String fileName){
		ByteBuffer buffer = null;
		try{
			BufferedImage image = ImageIO.read(ResourceLoader.load("textures/"+fileName));
			resolution = new GVector2f(image.getWidth(), image.getHeight());
			 buffer = BufferUtils.createByteBuffer(BPP * resolution.getXi() * resolution.getYi());
			int[] pixels = image.getRGB(0, 0, resolution.getXi(), resolution.getYi(), null, 0, image.getWidth());
			
			boolean hasAlpha = image.getColorModel().hasAlpha();
			float num = 0;
			
			for(int y=0 ; y<resolution.getYi() ; y++){
				for(int x=0 ; x<resolution.getYi() ; x++){
					Color actualColor = new Color(image.getRGB(x, y));
					
					averageColor = averageColor.add(new GVector3f(actualColor.getRed(), 
												actualColor.getGreen(), 
												actualColor.getBlue()));
					
					num++;
					
					int pixel = pixels[y * resolution.getXi() + x];
					buffer.put((byte)((pixel >> 16)&0xFF));
					buffer.put((byte)((pixel >> 8)&0xFF));
					buffer.put((byte)((pixel)&0xFF));
					
					if(hasAlpha)
						buffer.put((byte)((pixel >> 24)&0xFF));
					else
						buffer.put((byte)(0xFF));
				}
			}
			averageColor = averageColor.div(num/255);
			buffer.flip();
		}
		catch(Exception e){
			GDebug.logError("obr·zok " + fileName + " sa nepodarilo naËÌtaù", "Texture2D", e);
		}
		return buffer;
	}
	
	private void loadOldTexture(Texture2D t){
		id = t.getId();
		resolution = t.getSize();
		averageColor = t.getAverageColor();
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	//GETTERS
	
	public String getFileName() {return fileName;}
	public GVector3f getAverageColor() {return averageColor;}
	public GVector2f getSize() {return resolution;}
	public int getId() {return id;}
	
}
