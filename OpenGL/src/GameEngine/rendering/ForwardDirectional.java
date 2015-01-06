package GameEngine.rendering;

import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardDirectional extends Shader{
	private static final ForwardDirectional instance = new ForwardDirectional();
	
	public ForwardDirectional(){
		super("forward-directional");
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().Mul(worldMatrix);
		material.getTexture("diffuse").bind();
		
		setUniform("model",worldMatrix);
		setUniform("MVP",projectedMatrix);
		
		setUniformf("specularIntensity",material.getFloat("specularIntensity"));
		setUniformf("specularPower",material.getFloat("specularPower"));
		
		setUniform("eyePos",renderingEngine.getMainCamera().getTransform().getPosition());
		
		setUniformDirectionalLight("directionalLight",(DirectionalLight)renderingEngine.getActiveLight());
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
