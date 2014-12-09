package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Utils {
	public static void vypis(String co){
		System.out.println(co);
	}
	
	public static int textureLoaderID(String filename){
		try {
			int id = -1;
			Texture tex = TextureLoader.getTexture("jpg", new FileInputStream(new File("res/tex/"+filename)));
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
	}
	
	public static Texture textureLoader(String filename){
		try {
			int id = 0;
			return  TextureLoader.getTexture("jpg", new FileInputStream(new File("res/tex/"+filename)));
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
