package org.engine.rendering.shader.named;

import org.engine.rendering.shader.GBasicShader;

public class PostFXShader extends GBasicShader{
	public PostFXShader() {
		super("postFXShader");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	@Override
	public void getAllUniformsLocations() {
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
		uniforms.put("mouseMove", super.getUniformLocation("mouseMove"));
		
		uniforms.put("useCameraBlur", super.getUniformLocation("useCameraBlur"));
		uniforms.put("useAntiAliasing", super.getUniformLocation("useAntiAliasing"));
		
		uniforms.put("typeOfView", super.getUniformLocation("typeOfView"));
	}
}
