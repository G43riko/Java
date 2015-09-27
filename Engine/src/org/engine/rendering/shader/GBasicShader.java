package org.engine.rendering.shader;

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
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;

import glib.util.Loader;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;
import glib.util.vector.GVector4f;


public abstract class GBasicShader {
	private class Data{
		private int shader;
		private int fragmentShader;
		private int vertexShader;
		private int count;
		
		public Data(int shader, int fragmentShader, int vertexShader){
			this.shader = shader;
			this.vertexShader = vertexShader;
			this.fragmentShader = fragmentShader;
			this.count = 1;
		}
	}
	
	private static HashMap<String,Data> loadedShaders = new HashMap<String,Data>();
	protected HashMap<String,Integer> uniforms = new HashMap<String,Integer>();
	private String fileName;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	//CONSTRUCTORS
	
	public GBasicShader(String fileName){
		this.fileName = fileName;
		
		if(loadedShaders.containsKey(fileName))
			loadedShaders.get(fileName).count++;
		
		else{
			int shaderProgram = glCreateProgram();
			int vertexShader = addShader(GL_VERTEX_SHADER, fileName+".vert");
			int fragmentShader = addShader(GL_FRAGMENT_SHADER, fileName+".frag");
			
			glAttachShader(shaderProgram,vertexShader );
			glAttachShader(shaderProgram,fragmentShader );

			loadedShaders.put(fileName, new Data(shaderProgram, fragmentShader, vertexShader));

		}
		
		bindAttributes();
		glLinkProgram(loadedShaders.get(fileName).shader);
		glValidateProgram(loadedShaders.get(fileName).shader);
		getAllUniformsLocations();
	}
	
	//OTERS
	
	public boolean hasUniform(String name){
		return uniforms.containsKey(name);
	}
	
	public void connectTextures(){};
	protected abstract void bindAttributes();
	
	@Override
	public void finalize(){
		cleanUp();
	}
	
	public void cleanUp(){
		if(loadedShaders.get(fileName).count>1)
			loadedShaders.get(fileName).count--;
		else{
			glDeleteProgram(loadedShaders.get(fileName).shader);
			glDeleteShader(loadedShaders.get(fileName).vertexShader);
			glDeleteShader(loadedShaders.get(fileName).fragmentShader);
			
			loadedShaders.remove(fileName);
		}
	}
	
	public void bind(){
		GL20.glUseProgram(loadedShaders.get(fileName).shader);
	}
	
	public void unbind(){
		GL20.glUseProgram(0);
	}
	
	private int addShader(int type, String file){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			//add include option
			BufferedReader reader = new BufferedReader(new FileReader(Loader.loadFile("res/shaders/"+file)));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line + "\n");
			}
			reader.close();
		}catch(IOException e){
			System.out.println(file+" nejde naËÌtaù s dÙvodu: "+e);
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader "+file+" nebol skompilovany s dÙvodu: " + glGetShaderInfoLog(shader,1024));
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}

	protected void bindAttribute(int attribute,String variableName){
		GL20.glBindAttribLocation(loadedShaders.get(fileName).shader, attribute, variableName);
	}
	
	//UPDATERS
	
	public void updateUniform(String name, float value){
		GL20.glUniform1f(uniforms.get(name),value);
	}
	
	public void updateUniform(String name, int value){
		GL20.glUniform1i(uniforms.get(name),value);
	}
	
	public void updateUniform(String name, GVector3f vector){
		GL20.glUniform3f(uniforms.get(name), vector.getX(), vector.getY(), vector.getZ());
	}
	
	public void updateUniform(String name, GVector4f vector){
		GL20.glUniform4f(uniforms.get(name), vector.getX(), vector.getY(), vector.getZ(), vector.getW());
	}
	
	public void updateUniform(String name, GMatrix4f value){
		for(int i=0 ; i<4 ; i++){
			for(int j=0 ; j<4 ; j++){
				matrixBuffer.put(value.get(i,j));
			}
		}
		matrixBuffer.flip();
		glUniformMatrix4(uniforms.get(name), false,matrixBuffer);
	}
	
	public void updateUniform(String name, GVector2f vector){
		GL20.glUniform2f(uniforms.get(name), vector.getX(), vector.getY());
	}
	
	public void updateUniform(String name, boolean value){
		int toLoad = 0;
		if(value){
			toLoad = 1;
		}	
		GL20.glUniform1i(uniforms.get(name), toLoad);
	}

	//GETTERS
	
	public abstract void getAllUniformsLocations();

	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(loadedShaders.get(fileName).shader, uniformName);
	}
	
	public int getId(){
		return loadedShaders.get(fileName).shader;
	}
}
