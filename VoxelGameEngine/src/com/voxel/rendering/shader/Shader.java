package com.voxel.rendering.shader;

import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import glib.util.vector.GMatrix4f;

import java.util.ArrayList;
import java.util.HashMap;

import com.voxel.component.light.DirectionalLight;
import com.voxel.component.light.PointLight;
import com.voxel.component.light.SpotLight;
import com.voxel.core.Transform;
import com.voxel.rendering.RenderingEngine;
import com.voxel.rendering.material.Material;

public class Shader extends BasicShader{
	private static final String ATTRIBUTE_KEYWORD = "attribute";
	private static final String UNIFORM_KEYWORD = "uniform";
	private static final String STRUCT_KEYWORD = "struct";
	private static HashMap<String,ShaderResource> loadedShaders = new HashMap<String,ShaderResource>();
	
	public Shader(String fileName){
		ShaderResource oldResource = loadedShaders.get(fileName);
		if(oldResource != null){
			resource = oldResource;
			resource.addReference();
		}
		else{
			resource = new ShaderResource();
			
			String vertexShaderText = loadShader(fileName+".vs");
			String fragmentShaderText = loadShader(fileName+".fs");
			
			addProgram(vertexShaderText, GL_VERTEX_SHADER);
			addProgram(fragmentShaderText, GL_FRAGMENT_SHADER);
			addAllAttributes(vertexShaderText);
			
			compileShader();
			
			addAllUniforms(vertexShaderText);
			addAllUniforms(fragmentShaderText);
		}

	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		GMatrix4f worldMatrix = transform.getTransformation();
		GMatrix4f MVPMatrix = renderingEngine.getMainCamera().getViewProjection().mul(worldMatrix);
		for(int i=0 ; i<resource.getUniformNames().size() ; i++){
			String uniformName = resource.getUniformNames().get(i);
			String uniformType = resource.getUniformTypes().get(i);
			if(uniformType.equals("sampler2D")){
				int samplerSlot = renderingEngine.getSamplerSlot(uniformName);
				if(uniformName.equals("diffuse"))
					material.getTexture().bind(samplerSlot);
				setUniformi(uniformName,samplerSlot);
			}
			else if(uniformName.startsWith("T_")){
				if(uniformName.equals("T_MVP"))
					setUniform(uniformName,MVPMatrix);
				else if(uniformName.equals("T_model"))
					setUniform(uniformName,worldMatrix);
				else
					System.out.println(uniformName+" je neplatn� uniform pre transform�ciu");
			}
			else if(uniformName.startsWith("R_")){
				String unprefixedUniformName = uniformName.substring(2);
				if (uniformType.equals("float")){
						setUniformf(uniformName, renderingEngine.getFloat(unprefixedUniformName));
				}
				else if (uniformType.equals("vec3")){
					if(unprefixedUniformName.equals("color")){
						setUniform(uniformName,material.getColor());
					}
					else
						setUniform(uniformName, renderingEngine.getGVector3f(unprefixedUniformName));
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
					System.out.println(uniformType+" je neplatn� typ uniformu pre rednering");
			}
			else if(uniformName.startsWith("C_")){
				if(uniformName.equals("C_eyePos")){
					setUniform(uniformName, renderingEngine.getMainCamera().getTransform().getPosition());
				}
				else
					System.out.println(uniformName+" je neplatn� uniform pre kameru");
			}
			else if(uniformName.startsWith("O_")){
				setUniformb(uniformName,true);
			}
			else{
				if (uniformType.equals("float")){
					if(uniformName.equals("specularIntensity"))
						setUniformf(uniformName, material.getSpecularIntensity());
					if(uniformName.equals("specularPower"))
						setUniformf(uniformName, material.getSpecularPower());
					
				}
				else if (uniformType.equals("vec3")){
					System.out.println("ch�ba: "+uniformName);
				}
				else
					System.out.println(uniformType+" je neplatn� typ uniformu pre material");
			}
		}
	};
	
	private HashMap<String, ArrayList<GLSLStruct>> findUniformStruct(String shaderText){
		HashMap<String, ArrayList<GLSLStruct>> result = new HashMap<String, ArrayList<GLSLStruct>>();
		
		int structStartLocation = shaderText.indexOf(STRUCT_KEYWORD);
		while(structStartLocation != -1) {
			if(!(structStartLocation!=0&&(Character.isWhitespace(shaderText.charAt(structStartLocation-1 ))||shaderText.charAt(structStartLocation-1) == ';')
					&&Character.isWhitespace(shaderText.charAt(structStartLocation + STRUCT_KEYWORD.length()))))
				continue;
			int nameBegin = structStartLocation + STRUCT_KEYWORD.length()+1;
			int braceBegin = shaderText.indexOf("{",nameBegin);
			int braceEnd = shaderText.indexOf("}",braceBegin+1);
			
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
			
				componentSemicolomPos = shaderText.indexOf(";",componentSemicolomPos+1);
				
				GLSLStruct glslStruct = new GLSLStruct();
				glslStruct.name = componentName;
				glslStruct.type = componentType;
				glslStructs.add(glslStruct);
			}
			
			result.put(structName, glslStructs);
			
			structStartLocation = shaderText.indexOf(STRUCT_KEYWORD,structStartLocation + STRUCT_KEYWORD.length());
		}
		return result;
	}
	
	private void addAllAttributes(String shaderText){
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
			
			attributeStartLocation = shaderText.indexOf(ATTRIBUTE_KEYWORD,attributeStartLocation + ATTRIBUTE_KEYWORD.length());
		}
	}
	
	private void addAllUniforms(String shaderText){
		HashMap<String, ArrayList<GLSLStruct>> structs = findUniformStruct(shaderText);
		
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
}
