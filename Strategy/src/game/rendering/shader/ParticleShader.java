package game.rendering.shader;

import org.engine.rendeing.shader.GBasicShader;

public class ParticleShader extends GBasicShader{
	public ParticleShader() {
		super("particleShader");
	}

	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texCoords");
	}

	public void getAllUniformsLocations() {
		uniforms.put("transformationMatrix", super.getUniformLocation("transformationMatrix"));
		uniforms.put("projectionMatrix", super.getUniformLocation("projectionMatrix"));
		uniforms.put("viewMatrix", super.getUniformLocation("viewMatrix"));
		uniforms.put("alpha", super.getUniformLocation("alpha"));
		uniforms.put("color", super.getUniformLocation("color"));
		uniforms.put("ambient", super.getUniformLocation("ambient"));
		
	}
}
