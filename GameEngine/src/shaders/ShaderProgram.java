package shaders;

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
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile,String fragmentFile){
		fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
		vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
		
		programID = glCreateProgram();
		
		glAttachShader(programID,vertexShaderID );
		glAttachShader(programID,fragmentShaderID );
		bindAttributes();
		glLinkProgram(programID);
		glValidateProgram(programID);
		getAllUniformsLocations();
	}
	
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
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
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	protected abstract void getAllUniformsLocations();
	
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
				source.append(line).append("\n");
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
			System.out.println(GL20.glGetShaderInfoLog(shader, 500));
			System.out.println("shader "+fileName+" nebol skompilovany");
			System.exit(1);
		}
		
		return shader;
	}
}
