package GameEngine.rendering;

import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;

public class BasicShader extends Shader {
	
	public BasicShader(){
		super();
		
		addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		compileShader();
		
		addUniform("transform");
		addUniform("color");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectionMatrix, Material material){
		material.getTexture().bind();
		
		setUniform("transform",projectionMatrix);
		setUniform("color",material.getColor());
	}
}
