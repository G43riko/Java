package org.engine.rendering.shader.named;

import org.engine.component.light.BasicLight;
import org.engine.component.light.DirectionalLight;
import org.engine.component.light.PointLight;
import org.engine.rendering.shader.GBasicShader;
import org.lwjgl.opengl.GL20;

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
		updateUniform("normalSampler", 1);
//		updateUniform("bumpSampler", 2);
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
		
		initDirectionalLight("sun");
		initPointLight("pointLight");
		
		uniforms.put("fakeLight", super.getUniformLocation("fakeLight"));
		uniforms.put("receiveLight", super.getUniformLocation("receiveLight"));
		
		uniforms.put("eyePos", super.getUniformLocation("eyePos"));
		
		uniforms.put("useLights", super.getUniformLocation("useLights"));
		uniforms.put("useAmbient", super.getUniformLocation("useAmbient"));
		uniforms.put("useTexture", super.getUniformLocation("useTexture"));
		uniforms.put("useSpecular", super.getUniformLocation("useSpecular"));
		uniforms.put("useSpecularMap", super.getUniformLocation("useSpecularMap"));
		uniforms.put("useNormalMap", super.getUniformLocation("useNormalMap"));
		
		uniforms.put("specularSampler", super.getUniformLocation("specularSampler"));
		uniforms.put("normalSampler", super.getUniformLocation("normalSampler"));
	}
	
	//INITIALIZATION
	
	private void initPointLight(String name) {
		uniforms.put(name + ".range", super.getUniformLocation(name + ".range"));
		uniforms.put(name + ".position", super.getUniformLocation(name + ".position"));
		uniforms.put(name + ".attenuation", super.getUniformLocation(name + ".attenuation"));
		uniforms.put(name + ".baseLight.color", super.getUniformLocation(name + ".baseLight.color"));
		uniforms.put(name + ".baseLight.intensity", super.getUniformLocation(name + ".baseLight.intensity"));
	}

	private void initDirectionalLight(String name){
		uniforms.put(name + ".direction", super.getUniformLocation(name + ".direction"));
		uniforms.put(name + ".baseLight.color", super.getUniformLocation(name + ".baseLight.color"));
		uniforms.put(name + ".baseLight.intensity", super.getUniformLocation(name + ".baseLight.intensity"));
	}
	
	//UPDATE
	
	public void updateUniform(String name, DirectionalLight light){
		updateUniform(name + ".direction", light.getRotation());
		updateUniform(name + ".baseLight", (BasicLight)light);
	}
	
	public void updateUniform(String name, PointLight light){
		updateUniform(name + ".range", light.getRange());
		updateUniform(name + ".position", light.getPosition());
		updateUniform(name + ".attenuation", light.getAttenuation());
		updateUniform(name + ".baseLight", (BasicLight)light);
	}
	
	public void updateUniform(String name, BasicLight light){
		updateUniform(name + ".color", light.getColor());
		updateUniform(name + ".intensity", light.getIntensity());
	}

}
