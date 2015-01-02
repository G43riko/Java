package GameEngine.rendering;

import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardDirectional extends Shader{
private static final ForwardDirectional instance = new ForwardDirectional();
	
	public ForwardDirectional(){
		super();
		
		addVertexShader(ResourceLoader.loadShader("forward-directional.vs"));
		addFragmentShader(ResourceLoader.loadShader("forward-directional.fs"));
		
		setAttribLocation("position", 0);
		setAttribLocation("texCoord", 1);
		setAttribLocation("normal", 2);	
		
		compileShader();
		
		addUniform("model");
		addUniform("MVP");
		
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
	}
	
	public void updateUniforms(Transform transform, Material material){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().Mul(worldMatrix);
		material.getTexture().bind();
		
//		setUniform("MVP",projectionMatrix);
//		setUniform("ambientIntensity",getRenderingEngine().getAmbientLight());
		setUniform("model",worldMatrix);
		setUniform("MVP",projectedMatrix);
		
		setUniformf("specularIntensity",material.getSpecularIntensity());
		setUniformf("specularPower",material.getSpecularPower());
		
		setUniform("eyePos",getRenderingEngine().getMainCamera().getPos());
		
		setUniformDirectionalLight("directionalLight",(DirectionalLight)getRenderingEngine().getActiveLight());
	}

	public static ForwardDirectional getInstance(){
		return instance;
	}
	
	public void setUniformBaseLight(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight){
		setUniformBaseLight(uniformName +".base",directionalLight);
		setUniform(uniformName +".direction", directionalLight.getDirection());
	}
}
