package org.engine.rendeing.shader;

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
