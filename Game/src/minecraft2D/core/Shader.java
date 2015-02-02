package minecraft2D.core;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Shader extends GBasicShader{

	public Shader(String fileName) {
		super(fileName);
		bindAttributes();
		getAllUniformsLocations();
	}
	
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	protected void getAllUniformsLocations() {
//		uniforms.put("view", super.getUniformLocation("view"));
	}
	
	public void updateUniform(String name, float value) {
		loadFloat(name, value);
	}
	
	protected void updateUniform(String name, int value) {
		loadInt(name, value);
	}
	
	protected void updateUniform(String name, boolean value) {
		loadBoolean(name, value);
	}
	
	protected void updateUniform(String name, GVector3f value){
		loadVector3(name,value);
	}
	
	protected void updateUniform(String name, GVector2f value){
		loadVector2(name,value);
	}
	
	protected void updateUniform(String name, GMatrix4f value){
		loadMatrix(name,value);
	}
}
