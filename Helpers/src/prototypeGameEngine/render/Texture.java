package prototypeGameEngine.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.TextureLoader;

public class Texture {
	private static HashMap<String, Integer> loadedTextures = new HashMap<String, Integer>();
	private static boolean mipMapping = true;
	private String fileName;
	private int ID;
	
	public Texture(String filename){
		if(loadedTextures.containsKey(filename)){
			ID = loadedTextures.get(fileName);
		}
		else{
			int newId = textureLoader(fileName);
			if(newId!=-1){
				loadedTextures.put(fileName, newId);
				ID = newId;
			}
		}
	}
	
	private int textureLoader(String fileName){
		try {
			int id = -1;
			String[] format = fileName.split("\\.");
			id = TextureLoader.getTexture(format[1], new FileInputStream(new File("res/textures/"+fileName))).getTextureID();
			if(mipMapping){
				GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
			}
			return id;
		} catch (IOException e) {
			System.out.println(e);
		}
		return -1;
	}

	public int getID() {
		return ID;
	};
}
