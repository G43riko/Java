package GameEngine.rendering;

import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardAmbient extends Shader{
	private static final ForwardAmbient instance = new ForwardAmbient();
	
	public ForwardAmbient(){
		super();
		
		String vertexShaderText = loadShader("forward-ambient.vs");
		String fragmentShaderText = loadShader("forward-ambient.fs");
		
		addVertexShader(vertexShaderText);
		addFragmentShader(fragmentShaderText);
		
//		setAttribLocation("position", 0);
//		setAttribLocation("texCoord", 1);
		addAllAttributes(vertexShaderText);
//		addAllAttributes(fragmentShaderText);
		
		compileShader();
		
		addAllUniforms(vertexShaderText);
		addAllUniforms(fragmentShaderText);
		
//		addUniform("MVP");
//		addUniform("ambientIntensity");
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().Mul(worldMatrix);
		material.getTexture("diffuse").bind();
		
		setUniform("MVP",projectedMatrix);
		setUniform("ambientIntensity",renderingEngine.getAmbientLight());
	}

	public static ForwardAmbient getInstance(){
		return instance;
	}
}
