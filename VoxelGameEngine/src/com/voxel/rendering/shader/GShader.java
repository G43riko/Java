//package com.voxel.rendering.shader;
//
//import glib.util.vector.GMatrix4f;
//
//import com.voxel.core.Transform;
//import com.voxel.rendering.RenderingEngine;
//import com.voxel.rendering.material.Material;
//
//public class GShader extends GBasicShader{
//
//	public GShader(String fileName) {
//		super(fileName);
//	}
//	
//	protected void bindAttributes() {
//		super.bindAttribute(0, "position");
//		super.bindAttribute(1, "textureCoords");
//		super.bindAttribute(2, "normal");
//	}
//
//	protected void getAllUniformsLocations() {
//		uniforms.put("R_ambient", super.getUniformLocation("R_ambient"));
//		uniforms.put("R_color", super.getUniformLocation("R_color"));
//		uniforms.put("diffuse", super.getUniformLocation("diffuse"));
//		uniforms.put("O_texturing", super.getUniformLocation("O_texturing"));
//		uniforms.put("specularPower", super.getUniformLocation("specularPower"));
//		uniforms.put("specularIntensity", super.getUniformLocation("specularIntensity"));
//		uniforms.put("T_MVP", super.getUniformLocation("T_MVP"));
//		uniforms.put("T_model", super.getUniformLocation("T_model"));
//	}
//	
//	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
//		GMatrix4f worldMatrix = transform.getTransformation();
//		GMatrix4f MVPMatrix = renderingEngine.getMainCamera().getViewProjection().mul(worldMatrix);
//	}
//}
