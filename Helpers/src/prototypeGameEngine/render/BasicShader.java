package prototypeGameEngine.render;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
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
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class BasicShader {
	private static HashMap<String,Integer> loadedShader = new HashMap<String,Integer>();
	private String fileName;
	
	public BasicShader(String fileName){
		this.fileName = fileName;
		if(loadedShader.containsKey(fileName)){
			loadedShader.put(fileName+"_n", loadedShader.get(fileName+"_n")+1);
		}
		else{
			int shaderProgram = glCreateProgram();
			int vertexShader = addShader(GL_VERTEX_SHADER, "shader.vert");
			int fragmentShader = addShader(GL_FRAGMENT_SHADER, "shader.frag");
			
			glAttachShader(shaderProgram,vertexShader );
			glAttachShader(shaderProgram,fragmentShader );
			glLinkProgram(shaderProgram);
			glValidateProgram(shaderProgram);
			
			loadedShader.put(fileName, shaderProgram);
			loadedShader.put(fileName+"_v", vertexShader);
			loadedShader.put(fileName+"_f", fragmentShader);
			loadedShader.put(fileName+"_n",1);
		}
	}
	
	public void finalize(){
		if(loadedShader.get(fileName+"_n")>1){
			loadedShader.put(fileName+"_n", loadedShader.get(fileName+"_n")-1);
		}
		else{
			glDeleteProgram(loadedShader.get(fileName));
			glDeleteShader(loadedShader.get(fileName+"_v"));
			glDeleteShader(loadedShader.get(fileName+"_f"));
			
			loadedShader.remove(fileName);
			loadedShader.remove(fileName+"_v");
			loadedShader.remove(fileName+"_f");
			loadedShader.remove(fileName+"_n");
		}
	}
	
	public void start(){
		GL20.glUseProgram(loadedShader.get(fileName));
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	private int addShader(int type, String file){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			//add include option
			BufferedReader reader = new BufferedReader(new FileReader("res/shaders/"+file));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line);
			}
			reader.close();
		}catch(IOException e){
			System.out.println(file+" nejde naËÌtaù");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader "+file+" nebol skompilovany");
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}

	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(loadedShader.get(fileName), uniformName);
	}
	
	protected void loadFloat(int location, float value){
		GL20.glUniform1f(location,value);
	}
	
	protected void loadInt(int location, int value){
		GL20.glUniform1i(location,value);
	}
	
	protected void loadVector(int location, Vector3f vector){
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadBoolean(int location, boolean value){
		float toLoad = 0;
		if(value){
			toLoad = 1;
		}	
		GL20.glUniform1f(location, toLoad);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix){
		//dorobiù flipped buffer
//		matrix.store(matrixBuffer);
//		matrixBuffer.flip();
//		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
}
