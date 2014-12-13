package tests.vbo;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public ShaderProgram(String vertexFile,String fragmentFile){
		vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
		
		programID = glCreateProgram();
		
		glAttachShader(programID,vertexShaderID );
		glAttachShader(programID,fragmentShaderID );
		glLinkProgram(programID);
		glValidateProgram(programID);
	}
	
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	protected abstract void bindAttributes();
	
	protected void bindAttribute(int attribute,String variableName){
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	
	public void cleanUp(){
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		glDeleteProgram(programID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
	}
	
	private static int loadShader(String fileName, int type){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("res/shaders/"+fileName));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line);
			}
			reader.close();
		}catch(IOException e){
			System.out.println("shader "+ fileName+ " nejde naËÌtaù");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader "+fileName+" nebol skompilovany");
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}
}
