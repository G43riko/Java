package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;
import GameEngine.core.Util;
import GameEngine.core.Vector3f;
import GameEngine.rendering.resourceManagement.ShaderResource;
import GameEngine.rendering.resourceManagement.TextureResource;

public class Shader {
	private ShaderResource resource;
	private static HashMap<String,ShaderResource> loadedShaders = new HashMap<String,ShaderResource>();
//	private int program;
	private String fileName;
	
	public Shader(String fileName){
		this.fileName = fileName;
		
		ShaderResource oldResource = loadedShaders.get(fileName);
		if(oldResource != null){
			resource = oldResource;
			resource.addReference();
		}
		else{
			resource = new ShaderResource();
			
			String vertexShaderText = loadShader(fileName+".vs");
			String fragmentShaderText = loadShader(fileName+".fs");
			
			addVertexShader(vertexShaderText);
			addFragmentShader(fragmentShaderText);
			addAllAttributes(vertexShaderText);
			
			compileShader();
			
			addAllUniforms(vertexShaderText);
			addAllUniforms(fragmentShaderText);
		}
	}
	
	private void setAttribLocation(String AttributName,int location){
		glBindAttribLocation(resource.getProgram(),location,AttributName);
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f MVPMatrix = renderingEngine.getMainCamera().getViewProjection().Mul(worldMatrix);
		
		for(int i=0 ; i<resource.getUniformNames().size() ; i++){
			String uniformName = resource.getUniformNames().get(i);
			String uniformType = resource.getUniformTypes().get(i);
			if(uniformType.equals("sampler2D")){
				int samplerSlot = renderingEngine.getSamplerSlot(uniformName);
				material.getTexture(uniformName).bind(samplerSlot);
				setUniformi(uniformName,samplerSlot);
			}
			else if(uniformName.startsWith("T_")){
				if(uniformName.equals("T_MVP"))
					setUniform(uniformName,MVPMatrix);
				else if(uniformName.equals("T_model"))
					setUniform(uniformName,worldMatrix);
				else
					System.out.println(uniformName+" je neplatný uniform pre transformáciu");
			}
			else if(uniformName.startsWith("R_")){
				String unprefixedUniformName = uniformName.substring(2);
				if (uniformType.equals("float")){
						setUniformf(uniformName, renderingEngine.getFloat(unprefixedUniformName));
				}
				else if (uniformType.equals("vec3")){
					setUniform(uniformName, renderingEngine.getVector3f(unprefixedUniformName));
				}
				else if(uniformType.equals("DirectionalLight")){
					setUniformDirectionalLight(uniformName, (DirectionalLight)renderingEngine.getActiveLight());
				}
				else if(uniformType.equals("PointLight")){
					setUniformPointLight(uniformName, (PointLight)renderingEngine.getActiveLight());
				}
				else if(uniformType.equals("SpotLight")){
					setUniformSpotLight(uniformName, (SpotLight)renderingEngine.getActiveLight());
				}
				else
					System.out.println(uniformType+" je neplatný typ uniformu pre rednering");
			}
			else if(uniformName.startsWith("C_")){
				if(uniformName.equals("C_eyePos")){
					setUniform(uniformName, renderingEngine.getMainCamera().getTransform().getPosition());
				}
				else
					System.out.println(uniformName+" je neplatný uniform pre kameru");
			}
			else{
//				System.out.println(uniformName+" je");
				if (uniformType.equals("float")){
					setUniformf(uniformName, material.getFloat(uniformName));
				}
				else if (uniformType.equals("vec3")){
					setUniform(uniformName, material.getVector3f(uniformName));
				}
				else
					System.out.println(uniformType+" je neplatný typ uniformu pre material");
			}
		}
	};
	
	public void bind(){
		glUseProgram(resource.getProgram());
	}
	
	private class GLSLStruct{
		public String name;
		public String type;
	};
	
	private HashMap<String, ArrayList<GLSLStruct>> findUniformStruct(String shaderText){
		HashMap<String, ArrayList<GLSLStruct>> result = new HashMap<String, ArrayList<GLSLStruct>>();
		final String STRUCT_KEYWORD = "struct";
		int structStartLocation = shaderText.indexOf(STRUCT_KEYWORD);
		while(structStartLocation != -1) {
			if(!(structStartLocation!=0&&(Character.isWhitespace(shaderText.charAt(structStartLocation-1 ))||shaderText.charAt(structStartLocation-1) == ';')
					&&Character.isWhitespace(shaderText.charAt(structStartLocation + STRUCT_KEYWORD.length()))))
				continue;
			int nameBegin = structStartLocation + STRUCT_KEYWORD.length()+1;
			int braceBegin = shaderText.indexOf("{",nameBegin);
			int braceEnd = shaderText.indexOf("}",braceBegin+1);
//			int end = shaderText.indexOf(";",begin);
			
//			String structLine = shaderText.substring(begin, end);
			String structName = shaderText.substring(nameBegin, braceBegin).trim();
			ArrayList<GLSLStruct> glslStructs= new ArrayList<GLSLStruct>();
			
			int componentSemicolomPos = shaderText.indexOf(";",braceBegin);
			while(componentSemicolomPos != -1 && componentSemicolomPos<braceEnd){
				
				
				int componentNameEnd = componentSemicolomPos + 1;
				while(Character.isWhitespace(shaderText.charAt(componentNameEnd - 1)) ||shaderText.charAt(componentNameEnd - 1) == ';')
					componentNameEnd--;
				
				int componentNameStart = componentSemicolomPos;
				while(!Character.isWhitespace(shaderText.charAt(componentNameStart-1)))
					componentNameStart--;
				
				int componentTypeEnd = componentNameStart;
				while(Character.isWhitespace(shaderText.charAt(componentTypeEnd - 1)))
					componentTypeEnd--;
				
				int  componentTypeStart= componentTypeEnd;
				while(!Character.isWhitespace(shaderText.charAt(componentTypeStart -1)))
					componentTypeStart--;
				
				String componentName = shaderText.substring(componentNameStart, componentSemicolomPos);
				String componentType = shaderText.substring(componentTypeStart, componentTypeEnd);
				System.out.println(componentType+" "+componentName);
//				structComponents.add(shaderText.substring(componentNameStart, componentSemicolomPos));
			
				componentSemicolomPos = shaderText.indexOf(";",componentSemicolomPos+1);
				
				GLSLStruct glslStruct = new GLSLStruct();
				glslStruct.name = componentName;
				glslStruct.type = componentType;
				glslStructs.add(glslStruct);
			}
			
			result.put(structName, glslStructs);
			
//			addUniform(structName);
			
			structStartLocation = shaderText.indexOf(STRUCT_KEYWORD,structStartLocation + STRUCT_KEYWORD.length());
		}
		return result;
	}
	
	private void addAllAttributes(String shaderText){
		final String ATTRIBUTE_KEYWORD = "attribute";
		int attributeStartLocation = shaderText.indexOf(ATTRIBUTE_KEYWORD);
		int attribNumber = 0;
		while(attributeStartLocation != -1) {
			if(!(attributeStartLocation!=0&&(Character.isWhitespace(shaderText.charAt(attributeStartLocation-1 ))||shaderText.charAt(attributeStartLocation-1) == ';')
					&&Character.isWhitespace(shaderText.charAt(attributeStartLocation + ATTRIBUTE_KEYWORD.length()))))
				continue;
			int begin = attributeStartLocation + ATTRIBUTE_KEYWORD.length()+1;
			int end = shaderText.indexOf(";",begin);
			
			String attributeLine = shaderText.substring(begin, end).trim();
			String attributeName = attributeLine.substring(attributeLine.indexOf(" ")+1, attributeLine.length()).trim();
			
			setAttribLocation(attributeName, attribNumber);
			attribNumber++;
//			addUniform(attributeName);
			
			attributeStartLocation = shaderText.indexOf(ATTRIBUTE_KEYWORD,attributeStartLocation + ATTRIBUTE_KEYWORD.length());
		}
	}
	
	private void addAllUniforms(String shaderText){
		HashMap<String, ArrayList<GLSLStruct>> structs = findUniformStruct(shaderText);
		
		final String UNIFORM_KEYWORD = "uniform";
		int uniformStartLocation = shaderText.indexOf(UNIFORM_KEYWORD);
		while(uniformStartLocation != -1) {
			if(!(uniformStartLocation!=0&&(Character.isWhitespace(shaderText.charAt(uniformStartLocation-1 ))||shaderText.charAt(uniformStartLocation-1) == ';')
					&&Character.isWhitespace(shaderText.charAt(uniformStartLocation + UNIFORM_KEYWORD.length()))))
				continue;
			int begin = uniformStartLocation + UNIFORM_KEYWORD.length()+1;
			int end = shaderText.indexOf(";",begin);
			
			String uniformLine = shaderText.substring(begin, end).trim();
			
			int whiteSpacePos = uniformLine.indexOf(" ");
			
			String uniformName = uniformLine.substring(whiteSpacePos+1, uniformLine.length()).trim();
			String uniformType = uniformLine.substring(0, whiteSpacePos).trim();
			
			resource.getUniformNames().add(uniformName);
			resource.getUniformTypes().add(uniformType);
			
			addUniform(uniformName,uniformType,structs);
			
			uniformStartLocation = shaderText.indexOf(UNIFORM_KEYWORD,uniformStartLocation + UNIFORM_KEYWORD.length());
		}
	}
	
	private void addUniform(String uniformName, String uniformType,HashMap<String, ArrayList<GLSLStruct>> structs){
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
//		uniformNames.add(uniformName);
//		uniformTypes.add(uniformType);
	}
	
	private void addVertexShader(String text){
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	private void addGeometryShader(String text){
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	private void addFragmentShader(String text){
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	private void compileShader(){
		glLinkProgram(resource.getProgram());
		if(glGetProgrami(resource.getProgram(),GL_LINK_STATUS)==GL_FALSE){
			System.err.println("TU TO BLBNE"+glGetProgramInfoLog(resource.getProgram(),1024));
			System.exit(1);
		}
		
		glValidateProgram(resource.getProgram());
		
		if(glGetProgrami(resource.getProgram(),GL_VALIDATE_STATUS)==GL_FALSE){
			System.err.println("až tu to blbne"+glGetProgramInfoLog(resource.getProgram(),1024));
			System.exit(1);
		}
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
			System.out.println("tuto to je");
			System.exit(1);
			System.out.println("aj tu");
		}
		return shaderSource.toString();
	}
	
	public void setUniformBaseLight(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight){
		setUniformBaseLight(uniformName +".base",directionalLight);
		setUniform(uniformName +".direction", directionalLight.getDirection());
	}
	
	private void addProgram(String text, int type){
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
		
		//glAttachShader(shader,this.program);
		glAttachShader(resource.getProgram(),shader);
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
}
