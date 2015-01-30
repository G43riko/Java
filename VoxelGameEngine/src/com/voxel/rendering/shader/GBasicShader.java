package com.voxel.rendering.shader;

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
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glValidateProgram;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;

import com.voxel.component.light.BaseLight;
import com.voxel.component.light.DirectionalLight;
import com.voxel.component.light.PointLight;
import com.voxel.component.light.SpotLight;
import com.voxel.core.Util;

public abstract class GBasicShader {
	private static HashMap<String,Integer> loadedShader = new HashMap<String,Integer>();
	protected HashMap<String,Integer> uniforms;
	private String fileName;
	
	public GBasicShader(String fileName){
		this.fileName = fileName;
		uniforms = new HashMap<String,Integer>();
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
			loadedShader.remove(fileName+"_v");//vertexShader
			loadedShader.remove(fileName+"_f");//vertexShader
			loadedShader.remove(fileName+"_n");//number of instances
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
			System.out.println(file+" nejde na��ta�");
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

	protected void bindAttribute(int attribute,String variableName){
		GL20.glBindAttribLocation(loadedShader.get(fileName), attribute, variableName);
	}
	
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(loadedShader.get(fileName), uniformName);
	}
	
	protected void loadFloat(String name, float value){
		GL20.glUniform1f(uniforms.get(name),value);
	}
	
	protected void loadInt(String name, int value){
		GL20.glUniform1i(uniforms.get(name),value);
	}
	
	protected void loadVector(String name, GVector3f vector){
		GL20.glUniform3f(uniforms.get(name), vector.getX(), vector.getY(), vector.getZ());
	}
	
	protected void loadBoolean(String name, boolean value){
		float toLoad = 0;
		if(value){
			toLoad = 1;
		}	
		GL20.glUniform1f(uniforms.get(name), toLoad);
	}
	
	protected void loadMatrix(String name, GMatrix4f matrix){
		glUniformMatrix4(uniforms.get(name), true,Util.createFlippedBuffer(matrix));
	}

	public void loadBaseLight(String uniformName, BaseLight baseLight){
		loadVector(uniformName +".color",baseLight.getColor());
		loadFloat(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void loadDirectionalLight(String uniformName, DirectionalLight directionalLight){
		loadBaseLight(uniformName +".base",directionalLight);
		loadVector(uniformName +".direction", directionalLight.getDirection());
	}
	
	public void loadPointLight(String uniformName, PointLight pointLight){
		loadBaseLight(uniformName +".baseLight", pointLight);
		loadFloat(uniformName +".atten.constant", pointLight.getConstant());
		loadFloat(uniformName +".atten.linear", pointLight.getLinear());
		loadFloat(uniformName +".atten.exponent", pointLight.getExponent());
		loadVector(uniformName +".position", pointLight.getTransform().getPosition());
		loadFloat(uniformName +".range", pointLight.getRange());
	}
	
	public void loadSpotLight(String uniformName, SpotLight spotLight){
		loadPointLight(uniformName +".pointLight", spotLight);
		loadVector(uniformName +".direction", spotLight.getDirection());
		loadFloat(uniformName +".cutoff", spotLight.getCutoff());
	}
}
