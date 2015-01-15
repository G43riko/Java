package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glBindAttribLocation;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
import GameEngine.core.Util;
import GameEngine.core.util.Matrix4f;
import GameEngine.core.util.Vector3f;
import GameEngine.rendering.resourceManagement.ShaderResource;

public abstract class BasicShader {
	protected ShaderResource resource;
	
	protected class GLSLStruct{
		public String name;
		public String type;
	};
	
	protected void setAttribLocation(String AttributName,int location){
		glBindAttribLocation(resource.getProgram(),location,AttributName);
	}
	
	public void bind(){
		glUseProgram(resource.getProgram());
	}
	
	protected void compileShader(){
		glLinkProgram(resource.getProgram());
		
		if(glGetProgrami(resource.getProgram(),GL_LINK_STATUS)==GL_FALSE){
			System.err.println("TU TO BLBNE"+glGetProgramInfoLog(resource.getProgram(),1024));
			System.exit(1);
		}
		
		glValidateProgram(resource.getProgram());
		
		if(glGetProgrami(resource.getProgram(),GL_VALIDATE_STATUS)==GL_FALSE){
			System.err.println("aû tu to blbne"+glGetProgramInfoLog(resource.getProgram(),1024));
			System.exit(1);
		}
	}
		
	protected void addUniform(String uniformName, String uniformType,HashMap<String, ArrayList<GLSLStruct>> structs){
		boolean addThis = true;
		ArrayList<GLSLStruct> structComponents = structs.get(uniformType);
		if(structComponents != null){
			addThis = false;
			
			for(GLSLStruct struct: structComponents){
				addUniform(uniformName + "." + struct.name, struct.type, structs);
			}
		}
		if(!addThis){
			return;
		}
		
		int uniformLocation = glGetUniformLocation(resource.getProgram(), uniformName);
		if(uniformLocation == 0xFFFFFFFF){
			System.out.println("Error neviem najst uniform: "+uniformName);
			System.exit(1);
		}
		resource.getUniforms().put(uniformName, uniformLocation);
	}
	
	public void setUniformf(String uniformName, float value){
		glUniform1f(resource.getUniforms().get(uniformName), value);
	}
	
	public void setUniformi(String uniformName, int value){
		glUniform1i(resource.getUniforms().get(uniformName), value);
	}
	
	public void setUniform(String uniformName, Vector3f value){
		glUniform3f(resource.getUniforms().get(uniformName), value.GetX(), value.GetY(), value.GetZ());
	}
	
	public void setUniform(String uniformName, Matrix4f value){
		glUniformMatrix4(resource.getUniforms().get(uniformName), true,Util.createFlippedBuffer(value));
	}
	
	public void setUniformBaseLight(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight){
		setUniformBaseLight(uniformName +".base",directionalLight);
		setUniform(uniformName +".direction", directionalLight.getDirection());
	}
	
	public void setUniformPointLight(String uniformName, PointLight pointLight){
		setUniformBaseLight(uniformName +".baseLight", pointLight);
		setUniformf(uniformName +".atten.constant", pointLight.getConstant());
		setUniformf(uniformName +".atten.linear", pointLight.getLinear());
		setUniformf(uniformName +".atten.exponent", pointLight.getExponent());
		setUniform(uniformName +".position", pointLight.getTransform().getPosition());
		setUniformf(uniformName +".range", pointLight.getRange());
	}
	
	public void setUniformSpotLight(String uniformName, SpotLight spotLight){
		setUniformPointLight(uniformName +".pointLight", spotLight);
		setUniform(uniformName +".direction", spotLight.getDirection());
		setUniformf(uniformName +".cutoff", spotLight.getCutoff());
	}
	
	public static String loadShader(String filename){
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		final String INCLUDE_DIRECTIVE = "#include";
		
		try{
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + filename));
			String line;
			while((line = shaderReader.readLine()) != null){
				if(line.startsWith(INCLUDE_DIRECTIVE)){
					shaderSource.append(loadShader(line.substring(INCLUDE_DIRECTIVE.length()+2,line.length()-1)));
				}
				else
				shaderSource.append(line).append("\n");
			}
			shaderReader.close();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("shader "+filename+" sa nebodarilo naËÌtaù");
		}
		return shaderSource.toString();
	}
	
	protected void addProgram(String text, int type){
		int shader = glCreateShader(type);
		if(shader==0){
			System.err.println("Shader creation failed");
			System.exit(1);
		}
		glShaderSource(shader,text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==0){
			System.err.println("BLBNE TO"+glGetShaderInfoLog(shader,1024));
			System.exit(1);
		}
		glAttachShader(resource.getProgram(),shader);
	}
}
