package GameEngine;

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
		if(material.getTexture() != null){
			material.getTexture().bind();
		}
		else{
			RenderUtil.unbindTextures();
		}
		
		setUniform("transform",projectionMatrix);
		setUniform("color",material.getColor());
	}
}
