package game.rendering.shader;

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
import glib.util.Loader;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;


public abstract class GBasicShader {
	private class Data{
		private int s;
		private int fs;
		private int vs;
		private int count;
		
		public Data(int s, int fs, int vs){
			this.s = s;
			this.vs = vs;
			this.fs = fs;
			this.count = 1;
		}
	}
	
	private static HashMap<String,Data> loadedShaders = new HashMap<String,Data>();
	protected HashMap<String,Integer> uniforms;
	private String fileName;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public GBasicShader(String fileName){
		this.fileName = fileName;
		
		uniforms = new HashMap<String,Integer>();
		if(loadedShaders.containsKey(fileName)){
			loadedShaders.get(fileName).count++;
		}
		else{
			int shaderProgram = glCreateProgram();
			int vertexShader = addShader(GL_VERTEX_SHADER, fileName+".vert");
			int fragmentShader = addShader(GL_FRAGMENT_SHADER, fileName+".frag");
			
			glAttachShader(shaderProgram,vertexShader );
			glAttachShader(shaderProgram,fragmentShader );
			glLinkProgram(shaderProgram);
			glValidateProgram(shaderProgram);
			
			loadedShaders.put(fileName, new Data(shaderProgram, fragmentShader, vertexShader));
		}
		//bind();
		bindAttributes();
		getAllUniformsLocations();
	}
	
	protected abstract void bindAttributes();
	
	public abstract void getAllUniformsLocations();
	
	public void finalize(){
		cleanUp();
	}
	
	public void cleanUp(){
		if(loadedShaders.get(fileName).count>1){
			loadedShaders.get(fileName).count--;
		}
		else{
			glDeleteProgram(loadedShaders.get(fileName).s);
			glDeleteShader(loadedShaders.get(fileName).vs);
			glDeleteShader(loadedShaders.get(fileName).fs);
			
			loadedShaders.remove(fileName);
		}
	}
	
	public void bind(){
		GL20.glUseProgram(loadedShaders.get(fileName).s);
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
				source.append(line+"\n");
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
			System.err.println("BLBNE TO"+glGetShaderInfoLog(shader,1024));
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}

	protected void bindAttribute(int attribute,String variableName){
		GL20.glBindAttribLocation(loadedShaders.get(fileName).s, attribute, variableName);
	}
	
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(loadedShaders.get(fileName).s, uniformName);
	}
	
	public void updateUniform(String name, float value){
		GL20.glUniform1f(uniforms.get(name),value);
	}
	
	public void updateUniform(String name, int value){
		GL20.glUniform1i(uniforms.get(name),value);
	}
	
	public void updateUniform(String name, GVector3f vector){
		GL20.glUniform3f(uniforms.get(name), vector.getX(), vector.getY(), vector.getZ());
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

	public int getId(){
		return loadedShaders.get(fileName).s;
	}
}
