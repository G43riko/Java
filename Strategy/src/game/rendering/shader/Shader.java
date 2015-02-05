package game.rendering.shader;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Shader extends GBasicShader{

	public Shader(String fileName) {
		super(fileName);
		getAllUniformsLocations();
	}
	
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texCoords");
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
	}
	
	public void updateUniform(String name, float value) {
		loadFloat(name, value);
	}
	
	public void updateUniform(String name, int value) {
		loadInt(name, value);
	}
	
	public void updateUniform(String name, boolean value) {
		loadBoolean(name, value);
	}
	
	public void updateUniform(String name, GVector3f value){
		loadVector3(name,value);
	}
	
	public void updateUniform(String name, GVector2f value){
		loadVector2(name,value);
	}
	
	public void updateUniform(String name, GMatrix4f value){
		loadGMatrix(name,value);
	}
}
