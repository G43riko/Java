package com.voxel.rendering.shader;

public class GShader extends GBasicShader{

	public GShader(String fileName) {
		super(fileName);
		
	}
	
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	protected void getAllUniformsLocations() {
		uniforms.put("R_ambient", super.getUniformLocation("R_ambient"));
		uniforms.put("R_color", super.getUniformLocation("R_color"));
		uniforms.put("diffuse", super.getUniformLocation("diffuse"));
		uniforms.put("O_texturing", super.getUniformLocation("O_texturing"));
		uniforms.put("specularPower", super.getUniformLocation("specularPower"));
		uniforms.put("specularIntensity", super.getUniformLocation("specularIntensity"));
		uniforms.put("T_MVP", super.getUniformLocation("T_MVP"));
		uniforms.put("T_model", super.getUniformLocation("T_model"));
	}
	
	
}
