package com.g43riko.rendering.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import glib.util.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;

public abstract class BasicShader {
	protected HashMap<String,Integer> uniforms;
	private int shader;
	private int fragmentShader;
	private int vertexShader;
	
	public BasicShader(String fileName){
		uniforms = new HashMap<String,Integer>();
		
		shader = glCreateProgram();
		vertexShader = addShader(GL_VERTEX_SHADER, fileName+".vert");
		fragmentShader = addShader(GL_FRAGMENT_SHADER, fileName+".frag");
		
		glAttachShader(shader,vertexShader );
		glAttachShader(shader,fragmentShader );
		glLinkProgram(shader);
		glValidateProgram(shader);
		
	}
	
	protected abstract void bindAttributes();
	
	public abstract void getAllUniformsLocations();
	
	protected void bindAttribute(int attribute, String variableName){
		GL20.glBindAttribLocation(shader, attribute, variableName);
	};
	
	public void cleanUp(){
		glDeleteProgram(shader);
		glDeleteShader(fragmentShader);
		glDeleteShader(vertexShader);
	}
	
	public void bind(){
		GL20.glUseProgram(shader);
	}
	
	public void unbind(){
		GL20.glUseProgram(0);
	}
	
	private int addShader(int type, String fileName){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			//add include option
			BufferedReader reader = new BufferedReader(ResourceLoader.loadShader(fileName));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line+"\n");
			}
			reader.close();
		}catch(IOException e){
			System.out.println(fileName+" nejde naËÌtaù");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader "+fileName+" nebol skompilovany");
			System.err.println("BLBNE TO"+glGetShaderInfoLog(shader,1024));
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}
}
