package textures;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import main.Game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import utils.FileLoader;

public class ModelTexture {
	private int textureID;
	private float shineDamper = 1;
	private float reflectivity = 0;
	
	public ModelTexture(String fileName){
		this.textureID = textureLoader(fileName);
	}
	
	private int textureLoader(String fileName){
		try {
			int id = -1;
			String[] format = fileName.split("\\.");
			Texture tex = TextureLoader.getTexture(format[1], new FileInputStream(new File("res/textures/"+fileName)));
			id = tex.getTextureID();
			if(Game.mipMapping){
				GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
			}
			return id;
		} catch (IOException e) {
			System.out.println(e);
		}
		return -1;
	};
	
	public int getID(){
		return textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
}
