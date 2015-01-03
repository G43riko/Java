package GameEngine.rendering;

import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardAmbient extends Shader{
	private static final ForwardAmbient instance = new ForwardAmbient();
	
	public ForwardAmbient(){
		super();
		
		addVertexShader(ResourceLoader.loadShader("forward-ambient.vs"));
		addFragmentShader(ResourceLoader.loadShader("forward-ambient.fs"));
		
		setAttribLocation("position", 0);
		setAttribLocation("texCoord", 1);
		
		compileShader();
		
		addUniform("MVP");
		addUniform("ambientIntensity");
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
