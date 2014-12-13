package utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import main.Game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class FileLoader {
	public static int textureLoader(String fileName){
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
	
	public static int shaderLoader(int type, String file){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("res/shaders/"+file));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line);
			}
			reader.close();
		}catch(IOException e){
			System.out.println("shader "+ file+ " nejde na��ta�");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader nebol skompilovany");
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}
}
