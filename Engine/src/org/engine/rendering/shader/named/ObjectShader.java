package org.engine.rendering.shader.named;

import org.engine.rendering.shader.GBasicShader;

public class ObjectShader extends GBasicShader{

	public ObjectShader() {
		super("objectShader");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "normal");
	}
	
	@Override
	public void connectTextures(){
//		updateUniform("normalSampler", 1);
//		updateUniform("normalSampler", 2);
		updateUniform("specularSampler", 3);
		
	}
	
	@Override
	public void getAllUniformsLocations() {
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
		uniforms.put("projectionMatrix", super.getUniformLocation("projectionMatrix"));
		uniforms.put("viewMatrix", super.getUniformLocation("viewMatrix"));
		
		uniforms.put("ambient", super.getUniformLocation("ambient"));
		
		uniforms.put("specularIntensity", super.getUniformLocation("specularIntensity"));
		uniforms.put("specularPower", super.getUniformLocation("specularPower"));
		
		uniforms.put("sunColor", super.getUniformLocation("sunColor"));
		uniforms.put("sunDirection", super.getUniformLocation("sunDirection"));
		
		uniforms.put("fakeLight", super.getUniformLocation("fakeLight"));
		
		uniforms.put("eyePos", super.getUniformLocation("eyePos"));
		
		uniforms.put("useLights", super.getUniformLocation("useLights"));
		uniforms.put("useAmbient", super.getUniformLocation("useAmbient"));
		uniforms.put("useTexture", super.getUniformLocation("useTexture"));
		uniforms.put("useSpecular", super.getUniformLocation("useSpecular"));
		uniforms.put("useSpecularMap", super.getUniformLocation("useSpecularMap"));
		
		
//		uniforms.put("texture", super.getUniformLocation("texture"));
		uniforms.put("specularSampler", super.getUniformLocation("specularSampler"));
		
	}

}
