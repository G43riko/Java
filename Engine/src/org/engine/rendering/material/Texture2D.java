package org.engine.rendering.material;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import java.beans.ConstructorProperties;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class Texture2D {
	private static HashMap<String, Texture2D> loadedTextures = new HashMap<String, Texture2D>();
	
	private final static int DEFAULT_FILTER = GL_NEAREST;
	private final static int DEFAULT_WRAP = GL_REPEAT;
	
	private final static boolean DEFAULT_MIPMAPPING = true;
	
	private GVector3f averageColor = new GVector3f(0.5f);
	private GVector2f size;
	private String fileName;
	private int id;
	//CONSTRUCTORS
	
	@ConstructorProperties({"fileName"})
	public Texture2D(String fileName) {
		this.fileName = fileName;
		if(loadedTextures.containsKey(fileName)){
			loadOldTexture(loadedTextures.get(fileName));
			return;
		}
		
//		addTextureToOpenGL(makeByteBufferFILE(fileName));
		loadedTextures.put(fileName, this);
		
	}

	//OTHERS
	
	public void addTextureToOpenGL(ByteBuffer buf){
		id = glGenTextures();
		bind();
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, DEFAULT_FILTER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, DEFAULT_FILTER);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, DEFAULT_WRAP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, DEFAULT_WRAP);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, size.getXi(), size.getYi(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		
		if(DEFAULT_MIPMAPPING){
			GL30.glGenerateMipmap(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		}
	}
	
	private void loadOldTexture(Texture2D t){
		id = t.getId();
		size = t.getSize();
		averageColor = t.getAverageColor();
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	//GETTERS
	
	public String getFileName() {
		return fileName;
	}

	public GVector3f getAverageColor() {
		return averageColor;
	}

	public GVector2f getSize() {
		return size;
	}

	public int getId() {
		return id;
	}
	
}
