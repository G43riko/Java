package com.g43riko.rendering.shader;

public class DefaultShader extends BasicShader{

	public DefaultShader() {
		super("shader");
		bindAttributes();
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texture");
		bindAttribute(2, "normal");
	}

	@Override
	public void getAllUniformsLocations() {
		
	}

}
