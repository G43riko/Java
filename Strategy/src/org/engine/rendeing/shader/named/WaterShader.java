package org.engine.rendeing.shader.named;

import org.engine.rendeing.shader.GBasicShader;

public class WaterShader extends GBasicShader{

	public WaterShader() {
		super("waterShader");
	}
	
	protected void bindAttributes(){
		bindAttribute(0, "position");
	}

	public void getAllUniformsLocations(){
		uniforms.put("modelMatrix", super.getUniformLocation("modelMatrix"));
		uniforms.put("projectionMatrix", super.getUniformLocation("projectionMatrix"));
		uniforms.put("viewMatrix", super.getUniformLocation("viewMatrix"));
		
		uniforms.put("reflectionTexture", super.getUniformLocation("reflectionTexture"));
		uniforms.put("refractionTexture", super.getUniformLocation("refractionTexture"));
	}
	
	@Override
	public void connectTextures() {
		updateUniform("reflectionTexture", 0);
		updateUniform("refractionTexture", 1);
	}

}
