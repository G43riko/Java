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
	}

	@Override
	public void getAllUniformsLocations() {
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
		uniforms.put("projectionMatrix", super.getUniformLocation("projectionMatrix"));
		uniforms.put("viewMatrix", super.getUniformLocation("viewMatrix"));
		
		uniforms.put("ambient", super.getUniformLocation("ambient"));
		
		uniforms.put("texture", super.getUniformLocation("texture"));
	}

}
