package utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.Display;

public class FileLoader {
	
	
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
			System.out.println("shader "+ file+ " nejde naËÌtaù");
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
