package org.engine.rendering.shader.named;

import org.engine.rendering.shader.GBasicShader;

public class HudShader extends GBasicShader{
	public HudShader() {
		super("hudShader");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	@Override
	public void getAllUniformsLocations() {
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
	}
}
