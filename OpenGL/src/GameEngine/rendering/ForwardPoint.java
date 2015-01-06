package GameEngine.rendering;

import GameEngine.components.BaseLight;
import GameEngine.components.PointLight;
import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardPoint extends Shader{
private static final ForwardPoint instance = new ForwardPoint();
	
	public ForwardPoint(){
		super("forward-point");
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
		
		setUniformPointLight("pointLight",(PointLight)renderingEngine.getActiveLight());
		
	}

	public static ForwardPoint getInstance(){
		return instance;
	}
	
	public void setUniformBaseLight(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniformPointLight(String uniformName, PointLight pointLight){
		setUniformBaseLight(uniformName +".baseLight", pointLight);
		setUniformf(uniformName +".atten.constant", pointLight.getConstant());
		setUniformf(uniformName +".atten.linear", pointLight.getLinear());
		setUniformf(uniformName +".atten.exponent", pointLight.getExponent());
		setUniform(uniformName +".position", pointLight.getTransform().getPosition());
		setUniformf(uniformName +".range", pointLight.getRange());
	}
}
