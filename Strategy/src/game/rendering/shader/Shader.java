package game.rendering.shader;

public class Shader extends GBasicShader{

	public Shader(String fileName) {
		super(fileName);
	}
	
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "normal");
	}
	
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
	}
}
