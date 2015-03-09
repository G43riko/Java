package game.rendering.shader;

import game.rendering.RenderingEngine;

public class EntityShader extends GBasicShader{

	public EntityShader() {
		super("entityShader");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "normal");
	}

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
		
		for(int i=0 ; i<RenderingEngine.MAX_LIGHTS ; i++){
			uniforms.put("lightPosition"+i, super.getUniformLocation("lightPosition["+i+"]"));
			uniforms.put("lightColor"+i, super.getUniformLocation("lightColor["+i+"]"));
			uniforms.put("attenuation"+i, super.getUniformLocation("attenuation["+i+"]"));
			uniforms.put("range"+i, super.getUniformLocation("range["+i+"]"));
		}	
	}
}
