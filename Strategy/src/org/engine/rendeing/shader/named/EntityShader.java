package org.engine.rendeing.shader.named;

import org.engine.rendeing.shader.GBasicShader;
import org.strategy.rendering.RenderingEngineStrategy;

public class EntityShader extends GBasicShader{

	//GETTERS
	
	public EntityShader() {
		super("entityShader");
	}

	//OTHERS
	
	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "normal");
	}

	public void connectTextures(){
		updateUniform("normalSampler", 1);
	}
	
	//GETTERS
	
	@Override
	public void getAllUniformsLocations() {
		uniforms.put("view", super.getUniformLocation("view"));
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
		uniforms.put("projectionMatrix", super.getUniformLocation("projectionMatrix"));
		uniforms.put("viewMatrix", super.getUniformLocation("viewMatrix"));
		uniforms.put("color", super.getUniformLocation("color"));
		uniforms.put("blur", super.getUniformLocation("blur"));
		uniforms.put("mouseDir", super.getUniformLocation("mouseDir"));
		uniforms.put("ambient", super.getUniformLocation("ambient"));
		uniforms.put("select", super.getUniformLocation("select"));
		uniforms.put("eyePos", super.getUniformLocation("eyePos"));
		uniforms.put("alpha", super.getUniformLocation("alpha"));
		uniforms.put("shineDumper", super.getUniformLocation("shineDumper"));
		uniforms.put("reflectivity", super.getUniformLocation("reflectivity"));
		uniforms.put("texture", super.getUniformLocation("texture"));
		uniforms.put("light", super.getUniformLocation("light"));
		uniforms.put("specular", super.getUniformLocation("specular"));
		uniforms.put("normalSampler", super.getUniformLocation("normalSampler"));
		uniforms.put("fakeLight", super.getUniformLocation("fakeLight"));
		uniforms.put("fog", super.getUniformLocation("fog"));
		
		for(int i=0 ; i<RenderingEngineStrategy.MAX_LIGHTS ; i++){
			uniforms.put("lightPosition"+i, super.getUniformLocation("lightPosition["+i+"]"));
			uniforms.put("lightColor"+i, super.getUniformLocation("lightColor["+i+"]"));
			uniforms.put("attenuation"+i, super.getUniformLocation("attenuation["+i+"]"));
			uniforms.put("range"+i, super.getUniformLocation("range["+i+"]"));
		}	
	}
}
